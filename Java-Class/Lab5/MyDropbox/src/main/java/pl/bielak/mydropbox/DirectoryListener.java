package pl.bielak.mydropbox;

import pl.bielak.mydropbox.config.ConfigService;
import pl.bielak.mydropbox.config.Keys;
import pl.bielak.mydropbox.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.*;
import static pl.bielak.mydropbox.config.Keys.DEFAULT_DIRECTORY_KEY;
import static pl.bielak.mydropbox.config.Keys.PROPERTIES_FILE_PATH_KEY;

public class DirectoryListener implements Runnable{
  private ConfigService configService;
  private WatchService watcher;
  private ExecutorService executor;
  private Path dir;

  public DirectoryListener(ConfigService service) throws IOException {
    Logger.startLogger();
    this.configService = service;
    initWatcher(configService.getStringProperty(DEFAULT_DIRECTORY_KEY));
    executor = Executors.newFixedThreadPool(5);
  }

  private void initWatcher(String pathStr) throws IOException {
    dir = Paths.get(pathStr);
    watcher = dir.getFileSystem().newWatchService();
    dir.register(watcher, ENTRY_MODIFY, ENTRY_CREATE);
  }

  @Override
  public void run(){
    WatchKey key = null;
    try {
      key = watcher.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    while(true) {
      List<WatchEvent<?>> events = key.pollEvents();

      for(WatchEvent event : events) {
        WatchEvent<Path> ev = (WatchEvent<Path>) event;
        Path filename = ev.context();

        if(event.kind() == ENTRY_MODIFY || event.kind() == ENTRY_CREATE) {
          Logger.info("Zmodyfikowano: " + filename);


          //Logger.incrementFileCounter();
          //executor.submit(new FileWorker(new File(dir + "/" +  filename)));
        }


        boolean isValid = key.reset();
        if(!isValid) {
          break;
        }

      }
    }
  }

}
