package pl.bielak.csvparser.services;

import com.google.common.io.Resources;
import pl.bielak.csvparser.configuration.AppConfiguration;
import pl.bielak.csvparser.models.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PeopleService {
  private final AppConfiguration appConfiguration;

  public PeopleService(final AppConfiguration appConfiguration) {
    this.appConfiguration = appConfiguration;
  }

  public List<Person> getPeople() {
    final String inputFilename = appConfiguration.getInputFilename();
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(Resources.getResource(inputFilename).getPath()))) {
      return reader.lines()
          .map(line -> line.split(","))
          .map(values -> new Person(values[0], values[1], values[2], values[3], values[4], values[5]))
          .distinct()
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException("An error occurred while reading people from file:" + e.getMessage());
    }
  }
}