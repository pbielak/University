package pl.bielak.mydropbox.listeners;

import pl.bielak.mydropbox.workers.FileWorker;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import static java.nio.file.StandardWatchEventKinds.*;

public class DirectoryListener implements Callable {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private WatchService watcher;
  private Path dir;
  private FileWorker worker;

  public DirectoryListener(String path, FileWorker worker) {
    this.worker = worker;
    setDirectory(path);
  }

  public void setDirectory(String pathStr) {
    dir = Paths.get(pathStr);
    try {
      watcher = dir.getFileSystem().newWatchService();
      dir.register(watcher, ENTRY_MODIFY, ENTRY_CREATE);
    } catch (IOException e) {
      LOGGER.warning("IOException: " + e.getMessage());
    }
  }

  @Override
  public Object call() throws Exception {
    LOGGER.info("Started listening directory: " + dir);
    WatchKey key = watcher.take();

    while (!Thread.currentThread().isInterrupted()) {
      List<WatchEvent<?>> events = key.pollEvents();

      for (WatchEvent event : events) {
        WatchEvent<Path> ev = (WatchEvent<Path>) event;
        Path filename = ev.context();

        if (event.kind() == ENTRY_MODIFY || event.kind() == ENTRY_CREATE) {
          worker.submitFile(dir + "/" + filename);
        }

        boolean isValid = key.reset();
        if (!isValid) {
          break;
        }

      }
    }
    return null;
  }
}
