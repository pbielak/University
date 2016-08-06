package pl.bielak.taskmanager.models;

public class Category {
  private String categoryName;

  public Category(String name) {
    categoryName = name;
  }

  public String getCategoryName() {
    return categoryName;
  }

  @Override
  public String toString() {
    return categoryName;
  }

  @Override
  public boolean equals(Object obj) {
    Category other = (Category) obj;
    return categoryName.equals(other.categoryName);
  }

  @Override
  public int hashCode() {
    return categoryName.hashCode();
  }
}
