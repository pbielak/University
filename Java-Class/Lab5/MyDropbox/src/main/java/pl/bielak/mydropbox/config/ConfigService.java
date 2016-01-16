package pl.bielak.mydropbox.config;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigService {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private Properties properties;

  public ConfigService(String path){
    properties = new Properties();
    try {
      properties.load(getClass().getClassLoader().getResourceAsStream(path));
    } catch (IOException e) {
      LOGGER.warning("IOException: " + e.getMessage());
    }
  }

  public String getStringProperty(String key) {
    return properties.getProperty(key, "");
  }

  public int getIntProperty(String key) {
    return Integer.parseInt(getStringProperty(key));
  }

}
