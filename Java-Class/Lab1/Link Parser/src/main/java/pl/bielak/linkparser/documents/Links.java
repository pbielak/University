package pl.bielak.linkparser.documents;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Bielak
 */
public class Links {
  private List<Link> links;

  public Links() {
    links = new ArrayList<>();
  }

  public void addLink(Link currentLink) {
    links.add(currentLink);
  }

  public int getCount() {
    return links.size();
  }

  public void printCurrentDomainLinks() {
    links.stream().filter(link -> link.isFromCurrentDomain()).forEach(System.out::println);
  }

  public void printAllLinks() {
    links.forEach(System.out::println);
  }

}
