package pl.bielak.taskmanager.readers;

import com.google.common.io.Resources;
import pl.bielak.taskmanager.models.Category;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryReader {

  private static final String PATH_TO_FILE = "categories.txt";

  public static List<Category> readCategories() {
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(Resources.getResource(PATH_TO_FILE).getPath()))) {
      return Arrays.stream(reader.readLine().split(";")).map(Category::new).collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException("An error occurred while reading the categories: " + e.getMessage());
    }
  }
}
