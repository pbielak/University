package pl.bielak.csvparser.parsers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pl.bielak.csvparser.parsers.IpAddressParser.*;

public class IpAddressParserTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowExceptionForNull() {
    isValidIpAddress(null);
  }

  @DataProvider
  public Object[][] validIpAddressProvider() {
    return new Object[][] {
      {"127.0.0.1"}, {"192.168.0.1"}, {"192.168.0.14"}, {"169.254.0.1"}, {"255.255.255.0"}
    };
  }

  @Test(dataProvider = "validIpAddressProvider")
  public void shouldReturnTrueForValidIp(String ip) {
    assertTrue(isValidIpAddress(ip));
  }

  @DataProvider
  public Object[][] invalidIpAddressProvider() {
    return new Object[][] {
      {"0..."}, {"666.666.666.666"}, {"-1.4.2.6"}, {"1.457.0.255"}, {"192/168/0/1"}
    };
  }

  @Test(dataProvider = "invalidIpAddressProvider")
  public void shouldReturnFalseForInvalidIp(String ip) {
    assertFalse(isValidIpAddress(ip));
  }

}