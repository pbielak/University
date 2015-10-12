package pl.bielak.linkparser.documents;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Bielak
 */

public class HTMLDocument {
  @Getter private List<String> lines;

  public HTMLDocument() {
    lines = new ArrayList<>();
  }

  public void addLine(String currentLine) {
    lines.add(currentLine);
  }

  public void printPageSourceCode() {
    lines.forEach(System.out::println);
  }
}
