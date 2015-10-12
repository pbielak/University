package pl.bielak.linkparser.readers;

import pl.bielak.linkparser.documents.HTMLDocument;

/**
 * @author Piotr Bielak
 */
public interface AbstractReader {
  void loadPage() throws Exception;
  HTMLDocument getHtmlDocument();
}