package pl.bielak.csvparser.parsers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pl.bielak.csvparser.parsers.EmailParser.*;

public class EmailParserTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowExceptionForNull() {
    isValidEmail(null);
  }

  @DataProvider
  public Object[][] validEmailProvider() {
    return new Object[][] {
      {"janusz.maj@onet.pl"}, {"andrzej_rysuje@wp.pl"}, {"admin@9gag.com"}, {"fashasoi@republika.pl"}
    };
  }

  @Test(dataProvider = "validEmailProvider")
  public void shouldReturnTrueForValidEmail(String email) {
    assertTrue(isValidEmail(email));
  }


  @DataProvider
  public Object[][] invalidEmailProvider() {
    return new Object[][] {
      {"oasasmd"}, {"aduisndsu@@aoism.out"}, {"fuiehfuiehie.aifowi@seien.company"}, { "janusz.maj@2!##@*.lol"}
    };
  }

  @Test(dataProvider = "invalidEmailProvider")
  public void shouldReturnFalseForInvalidEmail(String email){
    assertFalse(isValidEmail(email));
  }
}