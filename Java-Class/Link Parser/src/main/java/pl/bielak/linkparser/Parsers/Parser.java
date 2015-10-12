package pl.bielak.linkparser.parsers;

import lombok.RequiredArgsConstructor;
import pl.bielak.linkparser.documents.HTMLDocument;
import pl.bielak.linkparser.documents.Link;
import pl.bielak.linkparser.documents.Links;

import java.util.stream.Collectors;

/**
 * @author Piotr Bielak
 */

@RequiredArgsConstructor
public class Parser implements AbstractParser {

  private final String TYPICAL_LINK_CHAR_SEQUENCE = "a href";
  private final HTMLDocument htmlDocument;
  private Links links = new Links();

  @Override
  public void parse() {
    htmlDocument.getLines().stream().filter(this::containsLink).forEach(currentLine -> links.addLink(new Link(currentLine, getUrlFromLink(currentLine))));
  }

  @Override
  public Links getLinks() {
    return links;
  }

  private boolean containsLink(String currentLine) {
    return currentLine.contains(TYPICAL_LINK_CHAR_SEQUENCE);
  }

  private String getUrlFromLink(String link) {
    int startUrlPosition = link.indexOf(TYPICAL_LINK_CHAR_SEQUENCE) + 8;
    int endUrlPosition = link.indexOf("\"", startUrlPosition + 1);

    return link.substring(startUrlPosition, endUrlPosition);
  }
}
