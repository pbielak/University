package pl.bielak.eventmanager;

import pl.bielak.eventmanager.configuration.ApplicationConfiguration;
import pl.bielak.eventmanager.documents.Category;
import pl.bielak.eventmanager.documents.Event;
import pl.bielak.eventmanager.documents.TaskStages;
import pl.bielak.eventmanager.exceptions.NoSuchCategoryException;
import pl.bielak.eventmanager.exceptions.NoSuchEventException;
import pl.bielak.eventmanager.readers.CategoryReader;
import pl.bielak.eventmanager.readers.EventReader;
import pl.bielak.eventmanager.savers.EventSaver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EventManager {

  private ApplicationConfiguration appConfig;
  private List<Category> defaultCategories;
  private Map<Category, List<Event>> events;

  public EventManager() throws IOException {
    appConfig = new ApplicationConfiguration();
    defaultCategories = new CategoryReader().getDefaultCategories();
    events = new EventReader(defaultCategories).getAllEvents();
  }

  public void run() throws NoSuchCategoryException, NoSuchEventException, FileNotFoundException {

    if(appConfig.shouldShowCategories()) {
      showAllCategories();
    }

    Category category = new Category(appConfig.getProperty("SHOW_EVENTS_FROM_CATEGORY"));
    showAllEventFromCategory(category);

    int eventID = Integer.parseInt(appConfig.getProperty("EVENT_TO_REMOVE_ID"));
    removeEvent(eventID);

    int finishedEventID = Integer.parseInt(appConfig.getProperty("FINISHED_EVENT_ID"));
    markEventAsDone(finishedEventID);

    if(appConfig.shouldShowUnfinishedEvents()) {
      showAllUnfinishedEvents();
    }
  }

  private Event findEventByID(int id) throws NoSuchEventException {
    for (Category category : defaultCategories) {
      List<Event> eventsFromCategory = events.get(category);
      for (Event event : eventsFromCategory) {
        if (event.getID() == id) {
          return event;
        }
      }
    }

    throw new NoSuchEventException();
  }

  private void markEventAsDone(int finishedEventID) throws NoSuchEventException, FileNotFoundException {
    Event event = findEventByID(finishedEventID);
    event.setStage(TaskStages.DONE);
    EventSaver.saveEvents(events);
  }

  private void removeEvent(int eventID) throws NoSuchEventException, FileNotFoundException {
    Event event = findEventByID(eventID);
    event.setRemovingStatus();
    EventSaver.saveEvents(events);
  }

  private void showAllUnfinishedEvents() {
    for (Category category : defaultCategories) {
      System.out.println(String.format("In category: \"%s\": ", category.getCategoryName()));

      events.get(category).stream().filter(Event::isUnfinished).forEach(System.out::println);
    }
  }

  private void showAllEventFromCategory(Category category) throws NoSuchCategoryException {
    if (!defaultCategories.contains(category)) {
      throw new NoSuchCategoryException();
    }
    System.out.println(String.format("Showing events from category \"%s\"", category.getCategoryName()));
    events.get(category).stream().sorted().forEach(System.out::println);
  }

  private void showAllCategories() {
    System.out.println("List of all categories:");
    defaultCategories.forEach(System.out::println);
  }
}
