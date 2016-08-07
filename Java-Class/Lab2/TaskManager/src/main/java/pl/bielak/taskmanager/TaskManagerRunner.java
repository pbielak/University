package pl.bielak.taskmanager;

import pl.bielak.taskmanager.configuration.AppConfiguration;
import pl.bielak.taskmanager.exceptions.NoSuchCategoryException;
import pl.bielak.taskmanager.exceptions.NoSuchTaskException;

public class TaskManagerRunner {
  private static final String PATH_TO_FILE = "config/config.properties";

  public static void main(String[] args) {
    final AppConfiguration configuration = new AppConfiguration(PATH_TO_FILE);
    final TaskManager taskManager = new TaskManager(configuration);

    try {
      taskManager.run();
    } catch (NoSuchCategoryException | NoSuchTaskException | RuntimeException e) {
      System.err.println("An error occurred: " + e.getMessage());
    }
  }
}
