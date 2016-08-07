package pl.bielak.taskmanager;

import pl.bielak.taskmanager.configuration.AppConfiguration;
import pl.bielak.taskmanager.models.Category;
import pl.bielak.taskmanager.models.Task;
import pl.bielak.taskmanager.models.TaskStage;
import pl.bielak.taskmanager.exceptions.NoSuchCategoryException;
import pl.bielak.taskmanager.exceptions.NoSuchTaskException;
import pl.bielak.taskmanager.readers.CategoryReader;
import pl.bielak.taskmanager.readers.TaskReader;
import pl.bielak.taskmanager.savers.TaskSaver;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class TaskManager {

  private final AppConfiguration appConfiguration;
  private List<Category> defaultCategories = CategoryReader.readCategories();
  private Map<Category, List<Task>> tasks = TaskReader.readTasks();

  public TaskManager(AppConfiguration appConfiguration) {
    this.appConfiguration = appConfiguration;
  }

  public void run() throws NoSuchCategoryException, NoSuchTaskException {

    if (appConfiguration.shouldShowCategories()) {
      showAllCategories();
    }

    showAllTasksFromCategory(appConfiguration.getCategoryToShowTasksFrom());

    removeTask(appConfiguration.getIdOfTaskToRemove());

    finishTask(appConfiguration.getFinishedTaskId());

    if (appConfiguration.shouldShowUnfinishedTasks()) {
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

  private void removeTask(int taskID) throws NoSuchTaskException {
    processTask(taskID, Task::markAsToBeRemoved);
  }

  private void finishTask(int taskID) throws NoSuchTaskException {
    processTask(taskID, task -> task.updateStage(TaskStage.DONE));
  }

  private void processTask(int taskID, Consumer<Task> consumer) throws NoSuchTaskException {
    Optional<Task> task = findTaskByID(taskID);

    if (task.isPresent()) {
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
