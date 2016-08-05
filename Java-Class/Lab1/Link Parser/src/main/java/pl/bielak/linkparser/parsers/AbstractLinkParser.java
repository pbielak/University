package pl.bielak.linkparser.parsers;

import pl.bielak.linkparser.models.HTMLDocument;
import pl.bielak.linkparser.models.Link;

import java.util.List;

/**
 * @author Piotr Bielak
 */
public interface AbstractLinkParser {
  List<Link> parseLinksFromHTML(HTMLDocument htmlDocument);
}
