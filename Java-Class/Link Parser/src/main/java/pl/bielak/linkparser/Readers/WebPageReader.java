package pl.bielak.linkparser.readers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.bielak.linkparser.documents.HTMLDocument;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Piotr Bielak
 */

@RequiredArgsConstructor
public class WebPageReader implements AbstractReader {

  private final String PATH_TO_FILE;
  @Getter private HTMLDocument htmlDocument;

  @Override
  public void loadPage() throws Exception {
    htmlDocument = new HTMLDocument();
    URL url = new URL(PATH_TO_FILE);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

    String line;
    while ((line = bufferedReader.readLine()) != null) {
      htmlDocument.addLine(line);
    }

    bufferedReader.close();
  }
}
