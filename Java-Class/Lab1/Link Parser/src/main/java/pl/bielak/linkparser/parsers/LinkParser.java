package pl.bielak.linkparser.parsers;

import lombok.RequiredArgsConstructor;
import pl.bielak.linkparser.models.HTMLDocument;
import pl.bielak.linkparser.models.Link;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Piotr Bielak
 */

@RequiredArgsConstructor
public class LinkParser implements AbstractLinkParser {

  private final Pattern pattern = Pattern.compile(".*a href=\"(?<url>[^\"]+)\".*");

  @Override
  public List<Link> parseLinksFromHTML(HTMLDocument htmlDocument) {
    return htmlDocument.getLines().stream()
        .map(String::trim)
        .filter(this::containsLink)
        .map(currentLine -> new Link(currentLine, getUrlFromLink(currentLine)))
        .collect(Collectors.toList());
  }

  private boolean containsLink(String currentLine) {
    return pattern.matcher(currentLine.trim()).matches();
  }

  private String getUrlFromLink(String link) {
    Matcher matcher = pattern.matcher(link);
    matcher.find();
    return matcher.group("url");
  }
}
