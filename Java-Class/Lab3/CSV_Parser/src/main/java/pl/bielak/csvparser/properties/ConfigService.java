package pl.bielak.csvparser.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static pl.bielak.csvparser.properties.Keys.*;

public class ConfigService {
  private Properties p = new Properties();

  public ConfigService() {
    try {
      InputStream is = getClass().getClassLoader().getResourceAsStream(PATH_TO_CONFIG_FILE);
      p.load(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getProperty(String propertyName) {
    return p.getProperty(propertyName);
  }

  public int getInteger(String propertyName) {
    return Integer.parseInt(p.getProperty(propertyName));
  }
}
