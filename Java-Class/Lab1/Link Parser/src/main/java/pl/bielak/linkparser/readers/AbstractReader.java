package pl.bielak.linkparser.readers;

import pl.bielak.linkparser.models.HTMLDocument;

/**
 * @author Piotr Bielak
 */
public interface AbstractReader {
  HTMLDocument loadHTMLPage() throws Exception;
}