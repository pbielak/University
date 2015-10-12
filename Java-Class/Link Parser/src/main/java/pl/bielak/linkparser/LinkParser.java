package pl.bielak.linkparser;

import pl.bielak.linkparser.configuration.AppConfiguration;
import pl.bielak.linkparser.documents.HTMLDocument;
import pl.bielak.linkparser.documents.Links;
import pl.bielak.linkparser.parsers.AbstractParser;
import pl.bielak.linkparser.parsers.Parser;
import pl.bielak.linkparser.readers.AbstractReader;
import pl.bielak.linkparser.readers.ReaderFactory;

/**
 * @author Piotr Bielak
 */
public class LinkParser {

  private AppConfiguration configuration;

  public LinkParser() {
    configuration = new AppConfiguration();
  }

  public void run() {
    try {
      String URL = configuration.getURL();
      boolean showOnlyCurrentDomainLinks = configuration.getProperty("SHOW_ONLY_CURRENT_DOMAIN_LINKS");
      boolean showPageSource = configuration.getProperty("SHOW_PAGE_SOURCE");

      AbstractReader reader = ReaderFactory.getReader(URL);
      reader.loadPage();

      HTMLDocument htmlDocument = reader.getHtmlDocument();
      AbstractParser parser = new Parser(htmlDocument);
      parser.parse();

      Links links = parser.getLinks();
      System.out.println(String.format("I found %d links\n", links.getCount()));

      if (showOnlyCurrentDomainLinks) {
        System.out.println("Showing only links from current domain\n");
        links.printCurrentDomainLinks();
      } else {
        System.out.println("Showing all links\n");
        links.printAllLinks();
      }

      if (showPageSource) {
        System.out.println("Showing page source code\n");
        htmlDocument.printPageSourceCode();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
