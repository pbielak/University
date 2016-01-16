package pl.bielak.mydropbox.config;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConfigServiceTest {

  @Test(expectedExceptions = NullPointerException.class)
  public void shouldThrowExceptionForInvalidPath() {
    new ConfigService("randomPath");
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void shouldThrowExceptionForNullPath() {
    new ConfigService(null);
  }

  @Test
  public void shouldReturnEmptyStringForUnknownKey() {
    ConfigService configService = new ConfigService(Keys.PROPERTIES_FILE_PATH_KEY);
    String value = configService.getStringProperty("unknownKey");
    assertEquals(value, "");
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void shouldThrowExceptionForNullKey() {
    ConfigService configService = new ConfigService(Keys.PROPERTIES_FILE_PATH_KEY);
    String value = configService.getStringProperty(null);
  }
}