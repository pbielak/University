package pl.bielak.linkparser.readers;

/**
 * @author Piotr Bielak
 */
public class ReaderFactory {

  public static AbstractReader getReader(String filename) {
    if( isWebSite(filename) )
      return new WebPageReader(filename);

    return new FileReader(filename);
  }

  private static boolean isWebSite(String filename) {
    return ( filename.startsWith("http") || filename.startsWith("www") );
  }
}
