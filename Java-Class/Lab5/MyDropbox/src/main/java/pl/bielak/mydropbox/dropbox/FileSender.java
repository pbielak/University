package pl.bielak.mydropbox.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import pl.bielak.mydropbox.logging.StatsCounter;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import static com.dropbox.core.v2.DbxFiles.WriteMode.overwrite;
import static java.util.logging.Logger.GLOBAL_LOGGER_NAME;

public class FileSender implements Callable<Void>{

  private static final Logger LOGGER = Logger.getLogger(GLOBAL_LOGGER_NAME);
  private File file;
  private StatsCounter statsCounter;

  public FileSender(File file, StatsCounter statsCounter) {
    this.file = file;
    this.statsCounter = statsCounter;
  }

  @Override
  public Void call() throws IOException, DbxException {
    DbxClientV2 client = DropboxConnector.getClientInstance();
    InputStream in = new FileInputStream(file);
    client.files.uploadBuilder("/" + file.getName()).mode(overwrite).run(in);
    in.close();

    statsCounter.incrementFileCounter();
    LOGGER.info("Done sending file: " + file.getName());
    return null;
  }
}
