package pl.bielak.linkparser.documents;

import lombok.AllArgsConstructor;

/**
 * @author Piotr Bielak
 */

@AllArgsConstructor
public class Link {
  private String fullHtmlCode;
  private String linkURL;

  public boolean isFromCurrentDomain() {
    return !(linkURL.startsWith("http") || linkURL.startsWith("//"));
  }

  @Override
  public String toString() {
    return String.format("Original link: %s\nURL: %s\n", fullHtmlCode, linkURL);
  }
}
