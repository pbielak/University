package pl.bielak.eventmanager.exceptions;

public class NoSuchCategoryException extends Exception{
  public NoSuchCategoryException() {
    super("Can't find that category!");
  }
}
