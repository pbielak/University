package pl.bielak.eventmanager.exceptions;

public class NoSuchEventException extends Exception {
  public NoSuchEventException() {
    super("Can't find that event!");
  }
}
