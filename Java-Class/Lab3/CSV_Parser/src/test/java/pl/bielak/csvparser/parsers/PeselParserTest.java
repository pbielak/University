package pl.bielak.csvparser.parsers;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pl.bielak.csvparser.parsers.PeselParser.*;

public class PeselParserTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowExceptionForNull() {
    isValidPesel(null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowExceptionForCharactersInInput() {
    isValidPesel("12312321iad");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowExceptionForTooShortInput() {
    isValidPesel("12");
  }

  @Test
  public void shouldReturnTrueForValidInput() {
    assertTrue(isValidPesel("91012507771"));
  }

  @Test
  public void shouldReturnFalseForInvalidInput() {
    assertFalse(isValidPesel("82062801652"));
  }

}