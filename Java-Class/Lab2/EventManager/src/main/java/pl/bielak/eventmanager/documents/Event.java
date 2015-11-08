package pl.bielak.eventmanager.documents;

public class Event implements Comparable{
  private int ID;
  private String name;
  private String description;
  private Priorities priority;
  private TaskStages stage;
  private boolean shouldBeRemoved;

  public Event(int id, String name, String description, Priorities priority, TaskStages stage) {
    this.ID = id;
    this.name = name;
    this.description = description;
    this.priority = priority;
    this.stage = stage;
    this.shouldBeRemoved = false;
  }

  public int getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Priorities getPriority() {
    return priority;
  }

  public void setPriority(Priorities priority) {
    this.priority = priority;
  }

  public TaskStages getStage() {
    return stage;
  }

  public void setStage(TaskStages stage) {
    this.stage = stage;
  }

  public boolean isUnfinished() {
    return (stage != TaskStages.DONE);
  }

  public void setRemovingStatus() {
    this.shouldBeRemoved = true;
  }

  public boolean getRemovingStatus() {
    return shouldBeRemoved;
  }

  @Override
  public String toString() {
    return String.format("Id: %d, Name: %s, Description: %s, Priority: %s, Stage: %s", ID, name, description, priority, stage);
  }

  @Override
  public int compareTo(Object o) {
    Priorities otherPriority = ((Event) o).getPriority();
    return priority.compareTo(otherPriority);
  }
}
