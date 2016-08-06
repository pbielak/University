package pl.bielak.taskmanager.models;

public class Task implements Comparable {
  private int ID;
  private String name;
  private String description;
  private TaskPriority priority;
  private TaskStage stage;
  private boolean shouldBeRemoved;
  private Category category;

  public Task(int id, String name, String description, TaskPriority priority, TaskStage stage, Category category) {
    this.ID = id;
    this.name = name;
    this.description = description;
    this.priority = priority;
    this.stage = stage;
    this.category = category;

    this.shouldBeRemoved = false;
  }

  public int getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public TaskPriority getPriority() {
    return priority;
  }

  public TaskStage getStage() {
    return stage;
  }

  public Category getCategory() {
    return category;
  }

  public void updateStage(TaskStage stage) {
    this.stage = stage;
  }

  public boolean isUnfinished() {
    return stage != TaskStage.DONE;
  }

  public void markAsToBeRemoved() {
    this.shouldBeRemoved = true;
  }

  public boolean shouldBeRemoved() {
    return shouldBeRemoved;
  }

  @Override
  public String toString() {
    return String.format("Id: %d, Name: %s, Description: %s, Priority: %s, Stage: %s", ID, name, description, priority, stage);
  }

  @Override
  public int compareTo(Object o) {
    TaskPriority otherPriority = ((Task) o).getPriority();
    return priority.compareTo(otherPriority);
  }
}
