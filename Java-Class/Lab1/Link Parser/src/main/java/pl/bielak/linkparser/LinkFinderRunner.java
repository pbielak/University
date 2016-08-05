package pl.bielak.linkparser;

import pl.bielak.linkparser.configuration.AppConfiguration;

/**
 * @author Piotr Bielak
 */
public class LinkFinderRunner {
  public static void main(String[] args) {
    final String PATH_TO_CONFIG_FILE = "config/config.properties";
    final AppConfiguration configuration = new AppConfiguration(PATH_TO_CONFIG_FILE);
    final LinkFinder finder = new LinkFinder(configuration);

    finder.run();
  }
}