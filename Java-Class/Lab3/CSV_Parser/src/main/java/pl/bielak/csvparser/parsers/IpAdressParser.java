package pl.bielak.csvparser.parsers;

public class IpAdressParser {
  public static boolean isValidIpAdress(String ip) {
    if(ip == null) {
      throw new IllegalArgumentException();
    }

    return ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
  }
}
