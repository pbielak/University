package pl.bielak.eventmanager.configuration;

import com.google.common.io.Resources;
import pl.bielak.eventmanager.documents.Category;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static pl.bielak.eventmanager.configuration.Keys.*;

public class AppConfiguration {

  private final Properties properties = new Properties();

  public AppConfiguration(final String pathToConfigFile) {
    try (InputStream inputStream = Resources.getResource(pathToConfigFile).openStream()) {
      properties.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong while loading configuration: " + e.getMessage());
    }
  }

  public boolean shouldShowCategories() {
    return Boolean.parseBoolean(properties.getProperty(SHOW_CATEGORIES_KEY));
  }

  public Category getCategoryToShowTasksFrom() {
    return new Category(properties.getProperty(SHOW_TASKS_FROM_CATEGORY_KEY));
  }

  public int getIdOfTaskToRemove() {
    return Integer.parseInt(properties.getProperty(TASK_TO_REMOVE_ID_KEY));
  }

  public int getFinishedTaskId() {
    return Integer.parseInt(properties.getProperty(FINISHED_TASK_ID_KEY));
  }

  public boolean shouldShowUnfinishedTasks() {
    return Boolean.parseBoolean(properties.getProperty(SHOW_UNFINISHED_TASKS_KEY));
  }

}
