package pl.bielak.linkparser;

import pl.bielak.linkparser.configuration.AppConfiguration;
import pl.bielak.linkparser.models.HTMLDocument;
import pl.bielak.linkparser.models.Link;
import pl.bielak.linkparser.parsers.AbstractLinkParser;
import pl.bielak.linkparser.parsers.LinkParser;
import pl.bielak.linkparser.readers.AbstractReader;
import pl.bielak.linkparser.readers.ReaderFactory;

import java.util.List;

/**
 * @author Piotr Bielak
 */
public class LinkFinder implements Runnable {

  private AppConfiguration configuration;
  private HTMLDocument htmlDocument;
  private List<Link> links;
  private AbstractLinkParser parser;

  public LinkFinder(AppConfiguration configuration) {
    this.configuration = configuration;
    this.parser = new LinkParser();
  }

  @Override
  public void run() {
    findAllLinks();
    printNumberOfFoundLinks();
    printLinks();

    if (configuration.shouldShowPageSource()) {
      System.out.println("Page source code:\n" + htmlDocument);
    }
  }

  private void findAllLinks() {
    try {
      AbstractReader reader = ReaderFactory.getReader(configuration.getURL());
      htmlDocument = reader.loadHTMLPage();
      links = parser.parseLinksFromHTML(htmlDocument);
    } catch (Exception e) {
      throw new RuntimeException(String.format("An error occurred: %s", e.getMessage()));
    }
  }

  private void printNumberOfFoundLinks() {
    System.out.println(String.format("I found %d links\n", links.size()));
  }

  private void printLinks() {
    if (configuration.shouldShowOnlyCurrentDomainLinks()) {
      System.out.println("Showing only links from current domain\n");
      links.stream().filter(Link::isFromCurrentDomain).forEach(System.out::print);
    } else {
      System.out.println("Showing all links\n");
      links.forEach(System.out::println);
    }
  }
}
