package pl.bielak.linkparser.models;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Piotr Bielak
 */

public class HTMLDocument {
  @Getter
  private List<String> lines;

  public HTMLDocument(List<String> lines) {
    this.lines = lines;
  }

  @Override
  public String toString() {
    return lines.stream().collect(Collectors.joining("\n"));
  }
}
