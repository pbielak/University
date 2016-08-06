package pl.bielak.eventmanager.savers;

import pl.bielak.eventmanager.documents.Category;
import pl.bielak.eventmanager.documents.Task;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TaskSaver {
  private static final String PATH_TO_FILE = "tasks.txt";

  public static void saveTasks(Map<Category, List<Task>> tasks) {

    try (PrintWriter writer = new PrintWriter(PATH_TO_FILE)) {
      tasks.entrySet().stream()
          .flatMap(entry -> entry.getValue().stream())
          .filter(task -> !task.shouldBeRemoved())
          .map(task -> String.format("%d/%s/%s/%s/%s/%s", task.getID(), task.getName(), task.getCategory(), task.getPriority(), task.getDescription(), task.getStage()))
          .forEach(writer::println);

      writer.close();
    } catch (FileNotFoundException e) {
      throw new RuntimeException("An error occurred while saving tasks:" + e.getMessage());
    }

  }
}
