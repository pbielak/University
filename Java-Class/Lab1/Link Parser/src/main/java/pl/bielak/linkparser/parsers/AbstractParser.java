package pl.bielak.linkparser.parsers;

import pl.bielak.linkparser.documents.Links;

/**
 * @author Piotr Bielak
 */
public interface AbstractParser {
  void parse();
  Links getLinks();
}
