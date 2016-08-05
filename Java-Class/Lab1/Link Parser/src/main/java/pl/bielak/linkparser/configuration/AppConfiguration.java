package pl.bielak.linkparser.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static pl.bielak.linkparser.configuration.Keys.*;

/**
 * @author Piotr Bielak.
 */
public class AppConfiguration {

  private final Properties properties = new Properties();

  public AppConfiguration(final String pathToConfigFile) {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pathToConfigFile)) {
      properties.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong while loading configuration: " + e.getMessage());
    }
  }

  public boolean shouldShowOnlyCurrentDomainLinks() {
    return Boolean.parseBoolean(properties.getProperty(CURRENT_DOMAIN_LINKS_KEY));
  }

  public boolean shouldShowPageSource() {
    return Boolean.parseBoolean(properties.getProperty(PAGE_SOURCE_KEY));
  }

  public String getURL() {
    return properties.getProperty(URL_KEY);
  }
}
