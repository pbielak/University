package pl.bielak.linkparser.models;

import lombok.AllArgsConstructor;

/**
 * @author Piotr Bielak
 */

@AllArgsConstructor
public class Link {
  private String htmlCode;
  private String URL;

  public boolean isFromCurrentDomain() {
    return !(URL.startsWith("http") || URL.startsWith("//"));
  }

  @Override
  public String toString() {
    return String.format("HTML: %s\nURL: %s\n", htmlCode, URL);
  }
}
