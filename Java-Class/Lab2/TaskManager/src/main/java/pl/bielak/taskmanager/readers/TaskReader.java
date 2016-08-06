package pl.bielak.taskmanager.readers;

import pl.bielak.taskmanager.models.Category;
import pl.bielak.taskmanager.models.Task;
import pl.bielak.taskmanager.models.TaskPriority;
import pl.bielak.taskmanager.models.TaskStage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskReader {
  private static final String PATH_TO_FILE = "tasks.txt";

  public static Map<Category, List<Task>> readTasks() {
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_TO_FILE))) {
      return reader.lines()
          .map(TaskReader::makeTaskFromText)
          .collect(Collectors.groupingBy(Task::getCategory));
    } catch (IOException e) {
      throw new RuntimeException("An error occurred while reading tasks: " + e.getMessage());
    }
  }

  private static Task makeTaskFromText(String text) {
    String[] eventValues = text.split("/");

    int id = Integer.parseInt(eventValues[0]);
    String name = eventValues[1];
    Category category = new Category(eventValues[2]);
    TaskPriority priority = TaskPriority.valueOf(eventValues[3]);
    String description = eventValues[4];
    TaskStage stage = TaskStage.valueOf(eventValues[5]);

    return new Task(id, name, description, priority, stage, category);
  }
}
