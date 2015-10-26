package pl.bielak.linkparser.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Piotr Bielak.
 */
public class AppConfiguration {

  private final String PATH_TO_CONFIG_FILE = "config/config.properties";
  private Properties properties;

  public AppConfiguration() {
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

  public boolean getProperty(String propertyName) {
    return Boolean.parseBoolean(properties.getProperty(propertyName));
  }

  public String getURL() {
    return properties.getProperty("URL");
  }
}
