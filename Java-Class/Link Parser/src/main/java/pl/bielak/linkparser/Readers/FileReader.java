package pl.bielak.linkparser.readers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.bielak.linkparser.documents.HTMLDocument;

import java.io.BufferedReader;

/**
 * @author Piotr Bielak
 */
@RequiredArgsConstructor
public class FileReader implements AbstractReader {

  private final String PATH_TO_FILE;
  @Getter private HTMLDocument htmlDocument;

  @Override
  public void loadPage() throws Exception {
    htmlDocument = new HTMLDocument();
    BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(PATH_TO_FILE));

    String line;
    while ((line = bufferedReader.readLine()) != null) {
      htmlDocument.addLine(line);
    }
  }
}
