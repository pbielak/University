package pl.bielak.csvparser.configuration;

import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static pl.bielak.csvparser.configuration.Keys.*;

public class AppConfiguration {

  private final Properties properties = new Properties();

  public AppConfiguration(final String pathToConfigFile) {
    try (InputStream inputStream = Resources.getResource(pathToConfigFile).openStream()) {
      properties.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong while loading configuration: " + e.getMessage());
    }
  }

  public String getInputFilename() {
    return properties.getProperty(INPUT_FILENAME_KEY);
  }

  public String getOutputFilename() {
    return properties.getProperty(OUTPUT_FILENAME_KEY);
  }

  public String getErrorsFilename() {
    return properties.getProperty(ERRORS_FILENAME_KEY);
  }

  public int getRowsPerFile() {
    return Integer.parseInt(properties.getProperty(ROWS_PER_FILE_KEY));
  }

}
