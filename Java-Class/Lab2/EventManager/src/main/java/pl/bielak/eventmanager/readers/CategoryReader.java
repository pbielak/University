package pl.bielak.eventmanager.readers;

import pl.bielak.eventmanager.documents.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryReader {

  private final String PATH_TO_FILE = "categories.txt";
  private List<Category> categories;

  public CategoryReader() throws IOException {
    categories = new ArrayList<>();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(PATH_TO_FILE)));
    String[] categoryNames = bufferedReader.readLine().split(";");
    for (String name : categoryNames) {
      categories.add(new Category(name));
    }

    bufferedReader.close();
  }

  public List<Category> getDefaultCategories() {
    return categories;
  }
}
