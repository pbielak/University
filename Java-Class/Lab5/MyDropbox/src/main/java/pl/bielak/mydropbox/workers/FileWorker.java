package pl.bielak.mydropbox.workers;


import pl.bielak.mydropbox.dropbox.FileSender;
import pl.bielak.mydropbox.logging.StatsCounter;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static java.util.logging.Logger.GLOBAL_LOGGER_NAME;

public class FileWorker {
  private final static Logger LOGGER = Logger.getLogger(GLOBAL_LOGGER_NAME);
  private ExecutorService executor;
  private StatsCounter statsCounter;

  public FileWorker(int threadPoolSize, StatsCounter statsCounter) {
    if(threadPoolSize <= 0) {
      throw new IllegalArgumentException("Thread pool size must be a positive number!");
    }

    setThreadPoolSize(threadPoolSize);
    this.statsCounter = statsCounter;
  }

  public void setThreadPoolSize(int size) {
    this.executor = Executors.newFixedThreadPool(size);
  }

  public void submitFile(String filePath) {
    File currentFile = new File(filePath);
    LOGGER.info("Sending file: " + currentFile.getName() + " from path: " + currentFile.getAbsolutePath());
    executor.submit(new FileSender(currentFile, statsCounter));
  }

}
