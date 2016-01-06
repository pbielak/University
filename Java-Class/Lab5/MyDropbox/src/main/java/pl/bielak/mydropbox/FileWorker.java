package pl.bielak.mydropbox;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxFiles;
import pl.bielak.mydropbox.dropbox.CloudConnector;
import pl.bielak.mydropbox.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FileWorker implements Callable{

  private File file;

  public FileWorker(File file) {
    this.file = file;
  }

  @Override
  public Object call() throws Exception {

    System.out.println("Sending file: " + file.getName() + " from path: " + file.getAbsolutePath());

    InputStream in = new FileInputStream(file);
    DbxClientV2 client = CloudConnector.getClientInstance();
    DbxFiles.Metadata metadata = client.files.uploadBuilder("/" + file.getName()).run(in);
    in.close();

    System.out.println(metadata);

    System.out.println("Done sending file: " + file.getName());
    return null;
  }
}
