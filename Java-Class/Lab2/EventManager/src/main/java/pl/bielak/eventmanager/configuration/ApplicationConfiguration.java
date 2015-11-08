package pl.bielak.eventmanager.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfiguration {

  private final String PATH_TO_CONFIG_FILE = "config/config.properties";
  private Properties properties;

  public ApplicationConfiguration() {
    InputStream inputStream = null;
    properties = new Properties();

    try {
      inputStream = getClass().getClassLoader().getResourceAsStream(PATH_TO_CONFIG_FILE);
      properties.load(inputStream);

    } catch (IOException e) {
      System.out.println("Something went wrong while loading configuration: " + e.getMessage());
    } finally {
      tryToClose(inputStream);
    }
  }

  private void tryToClose(InputStream inputStream) {
    if (inputStream != null) {
      try {
        inputStream.close();
      } catch (IOException e) {
        System.out.println("Something went wrong while closing input stream: " + e.getMessage());
      }
    }
  }

  public String getProperty(String propertyName) {
    return properties.getProperty(propertyName);
  }

  public boolean shouldShowCategories() {
    return Boolean.parseBoolean(properties.getProperty("SHOW_CATEGORIES"));
  }


  public boolean shouldShowUnfinishedEvents() {
    return Boolean.parseBoolean(properties.getProperty("SHOW_UNFINISHED_EVENTS"));
  }

}
