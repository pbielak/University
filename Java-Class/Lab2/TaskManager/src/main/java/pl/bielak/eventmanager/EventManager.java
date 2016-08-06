package pl.bielak.eventmanager;

import pl.bielak.eventmanager.configuration.AppConfiguration;
import pl.bielak.eventmanager.documents.Category;
import pl.bielak.eventmanager.documents.Task;
import pl.bielak.eventmanager.documents.TaskStage;
import pl.bielak.eventmanager.exceptions.NoSuchCategoryException;
import pl.bielak.eventmanager.exceptions.NoSuchTaskException;
import pl.bielak.eventmanager.readers.CategoryReader;
import pl.bielak.eventmanager.readers.TaskReader;
import pl.bielak.eventmanager.savers.TaskSaver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class EventManager {

  private final AppConfiguration appConfiguration;
  private List<Category> defaultCategories = CategoryReader.readCategories();
  private Map<Category, List<Task>> tasks = TaskReader.readTasks();

  public EventManager(AppConfiguration appConfiguration) throws IOException {
    this.appConfiguration = appConfiguration;
  }

  public void run() throws NoSuchCategoryException, NoSuchTaskException {

    if(appConfiguration.shouldShowCategories()) {
      showAllCategories();
    }

    showAllTasksFromCategory(appConfiguration.getCategoryToShowTasksFrom());

    removeTask(appConfiguration.getIdOfTaskToRemove());

    finishTask(appConfiguration.getFinishedTaskId());

    if(appConfiguration.shouldShowUnfinishedTasks()) {
      showAllUnfinishedTasks();
    }
  }

  private void showAllCategories() {
    System.out.println("List of all categories:");
    defaultCategories.forEach(System.out::println);
  }

  private void showAllTasksFromCategory(Category category) throws NoSuchCategoryException {
    if (!defaultCategories.contains(category)) {
      throw new NoSuchCategoryException();
    }
    System.out.println(String.format("Showing tasks from category \"%s\"", category.getCategoryName()));
    tasks.get(category).stream().sorted().forEach(System.out::println);
  }

  private void removeTask(int taskID) {
    try {
      processTask(taskID, Task::markAsToBeRemoved);
    } catch (NoSuchTaskException e) {
      throw new RuntimeException("An error occurred while removing task: " + e.getMessage());
    }
  }

  private void finishTask(int taskID) {
    try {
      processTask(taskID, task-> task.updateStage(TaskStage.DONE));
    } catch (NoSuchTaskException e) {
      throw new RuntimeException("An error occurred while finishing task: " + e.getMessage());
    }
  }

  private void processTask(int taskID, Consumer<Task> consumer) throws NoSuchTaskException {
    Optional<Task> task = findTaskByID(taskID);

    if(task.isPresent()) {
      consumer.accept(task.get());
      TaskSaver.saveTasks(tasks);
    } else {
      throw new NoSuchTaskException();
    }
  }

  private Optional<Task> findTaskByID(int id) {
    return tasks.entrySet().stream()
        .flatMap(entry -> entry.getValue().stream())
        .filter(task -> task.getID() == id)
        .findFirst();
  }

  private void showAllUnfinishedTasks() {
    tasks.entrySet().stream()
        .flatMap(entry -> entry.getValue().stream())
        .filter(Task::isUnfinished)
        .forEach(task -> System.out.println(String.format("%s -> %s ", task.getCategory(), task.toString())));
  }


}
