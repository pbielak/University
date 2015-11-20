package pl.bielak.csvparser.parsers;

import org.junit.Test;

import static org.junit.Assert.*;
import static pl.bielak.csvparser.parsers.PeselParser.*;

public class PeselParserTest {

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionForNull() {
    isValid(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionForCharactersInInput() {
    isValid("12312321iad");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionForTooShortInput() {
    isValid("12");
  }

  @Test
  public void shouldReturnTrueForValidInput() {
    assertTrue(isValid("91012507771"));
  }

  @Test
  public void shouldReturnFalseForInvalidInput() {
    assertFalse(isValid("82062801652"));
  }

}