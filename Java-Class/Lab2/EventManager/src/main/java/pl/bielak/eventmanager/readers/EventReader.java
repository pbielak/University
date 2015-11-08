package pl.bielak.eventmanager.readers;

import pl.bielak.eventmanager.documents.Category;
import pl.bielak.eventmanager.documents.Event;
import pl.bielak.eventmanager.documents.Priorities;
import pl.bielak.eventmanager.documents.TaskStages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventReader {
  private final String PATH_TO_FILE = "events.txt";
  private final List<Category> categories;
  private Map<Category, List<Event>> events = new HashMap<>();

  public EventReader(List<Category> defaultCategories) throws IOException {
    categories = defaultCategories;
    categories.forEach(category -> events.put(category, new ArrayList<>()));

    BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE));


    String line;
    while ((line = bufferedReader.readLine()) != null) {
      String[] eventValues = line.split("/");

      int id = Integer.parseInt(eventValues[0]);
      String name = eventValues[1];

      Category category = new Category(eventValues[2]);
      if (!categories.contains(category)) {
        System.out.println(String.format("Can't find category with name: \"%s\", i'll add it for you!", category.getCategoryName()));
        categories.add(category);
        events.put(category, new ArrayList<>());
      }

      Priorities priority = Priorities.valueOf(eventValues[3]);
      String description = eventValues[4];
      TaskStages stage = TaskStages.valueOf(eventValues[5]);

      events.get(category).add(new Event(id, name, description, priority, stage));
    }

    bufferedReader.close();
  }

  public Map<Category, List<Event>> getAllEvents() {
    return events;
  }
}
