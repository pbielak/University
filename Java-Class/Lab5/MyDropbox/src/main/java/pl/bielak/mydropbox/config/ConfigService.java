package pl.bielak.mydropbox.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {

  private Properties properties;

  public ConfigService(String path){
    properties = new Properties();
    try {
      properties.load(getClass().getClassLoader().getResourceAsStream(path));
    } catch (IOException e) {
      throw new IllegalStateException("IOException", e);
    }
  }

  public String getStringProperty(String key) {
    return properties.getProperty(key, "");
  }

  public int getIntProperty(String key) {
    return Integer.parseInt(getStringProperty(key));
  }

}
