package pl.bielak.linkparser.readers;

import lombok.RequiredArgsConstructor;
import pl.bielak.linkparser.models.HTMLDocument;

import java.io.BufferedReader;
import java.util.stream.Collectors;

/**
 * @author Piotr Bielak
 */
@RequiredArgsConstructor
public class FileReader implements AbstractReader {

  private final String PATH_TO_FILE;

  @Override
  public HTMLDocument loadHTMLPage() throws Exception {
    BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(PATH_TO_FILE));

    return new HTMLDocument(bufferedReader.lines().collect(Collectors.toList()));
  }
}
