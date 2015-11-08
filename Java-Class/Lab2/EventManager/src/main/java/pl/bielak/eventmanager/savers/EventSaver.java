package pl.bielak.eventmanager.savers;

import pl.bielak.eventmanager.documents.Category;
import pl.bielak.eventmanager.documents.Event;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class EventSaver {
  private static final String PATH_TO_FILE = "events.txt";

  public static void saveEvents(Map<Category, List<Event>> events) throws FileNotFoundException {
    PrintWriter printWriter = new PrintWriter(PATH_TO_FILE);

    for (Category category : events.keySet()) {
      String categoryName = category.getCategoryName();
      for (Event event : events.get(category)) {

        if(event.getRemovingStatus()) continue; // If should be removed

        int id = event.getID();
        String eventName = event.getName();
        String eventPriority = event.getPriority().toString();
        String eventDescription = event.getDescription();
        String eventStage = event.getStage().toString();

        printWriter.println(String.format("%d/%s/%s/%s/%s/%s", id, eventName, categoryName, eventPriority, eventDescription, eventStage));
      }
    }

    printWriter.close();
  }
}
