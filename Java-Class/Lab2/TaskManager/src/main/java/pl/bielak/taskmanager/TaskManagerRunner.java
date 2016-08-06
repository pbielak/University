package pl.bielak.taskmanager;

import pl.bielak.taskmanager.configuration.AppConfiguration;

public class TaskManagerRunner {
  private static final String PATH_TO_FILE = "config/config.properties";

  public static void main(String[] args) throws Exception {
    final AppConfiguration configuration = new AppConfiguration(PATH_TO_FILE);
    final TaskManager taskManager = new TaskManager(configuration);

    taskManager.run();
  }
}
