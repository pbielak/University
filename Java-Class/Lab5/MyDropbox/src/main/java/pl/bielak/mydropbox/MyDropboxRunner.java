package pl.bielak.mydropbox;


import pl.bielak.mydropbox.config.ConfigService;
import pl.bielak.mydropbox.config.Keys;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static pl.bielak.mydropbox.config.Keys.PROPERTIES_FILE_PATH_KEY;

public class MyDropboxRunner {

  public static void main(String[] args) {
    ConfigService configService = new ConfigService(PROPERTIES_FILE_PATH_KEY);
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    try {
      executorService.submit(new DirectoryListener(configService));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
