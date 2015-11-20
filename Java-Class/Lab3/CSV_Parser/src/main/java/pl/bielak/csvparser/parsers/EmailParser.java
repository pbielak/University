package pl.bielak.csvparser.parsers;

public class EmailParser {
  public static boolean isValidEmail(String email) {
    if(email == null) {
      throw new IllegalArgumentException();
    }

    return email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
  }
}
