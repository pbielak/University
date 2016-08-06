package pl.bielak.eventmanager;

import pl.bielak.eventmanager.configuration.AppConfiguration;

public class TaskManagerRunner {
  private static final String PATH_TO_FILE = "config/config.properties";

  public static void main(String[] args) throws Exception {
    final AppConfiguration configuration = new AppConfiguration(PATH_TO_FILE);
    final EventManager eventManager = new EventManager(configuration);

    eventManager.run();
  }
}
