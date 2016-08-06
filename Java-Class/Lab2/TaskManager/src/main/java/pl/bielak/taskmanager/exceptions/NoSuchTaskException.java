package pl.bielak.taskmanager.exceptions;

public class NoSuchTaskException extends Exception {
  public NoSuchTaskException() {
    super("Can't find that task!");
  }
}
