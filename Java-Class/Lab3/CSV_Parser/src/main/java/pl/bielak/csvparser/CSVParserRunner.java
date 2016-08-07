package pl.bielak.csvparser;

import pl.bielak.csvparser.configuration.AppConfiguration;

import java.io.IOException;

public class CSVParserRunner {
  private static final String PATH_TO_FILE = "config/config.properties";

  public static void main(String[] args) throws IOException {
    final AppConfiguration appConfiguration = new AppConfiguration(PATH_TO_FILE);
    final CSVParser parser = new CSVParser(appConfiguration);

    parser.run();
  }
}
