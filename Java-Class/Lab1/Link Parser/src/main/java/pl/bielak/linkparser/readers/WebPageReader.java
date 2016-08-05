package pl.bielak.linkparser.readers;

import lombok.RequiredArgsConstructor;
import pl.bielak.linkparser.models.HTMLDocument;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * @author Piotr Bielak
 */

@RequiredArgsConstructor
public class WebPageReader implements AbstractReader {

  private final String PATH_TO_FILE;

  @Override
  public HTMLDocument loadHTMLPage() throws Exception {
    URL url = new URL(PATH_TO_FILE);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

    return new HTMLDocument(bufferedReader.lines().collect(Collectors.toList()));
  }
}
