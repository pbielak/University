package pl.bielak.mydropbox;

import pl.bielak.mydropbox.config.ConfigService;
import pl.bielak.mydropbox.listeners.DirectoryListener;
import pl.bielak.mydropbox.logging.StatsCounter;
import pl.bielak.mydropbox.workers.FileWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;
import java.util.logging.Logger;

import static pl.bielak.mydropbox.config.Keys.*;

public class MyDropbox {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private StatsCounter statsCounter;
  private DirectoryListener listener;
  private FileWorker worker;
  private ExecutorService executorService;

  public MyDropbox() {
    ConfigService configService = new ConfigService(PROPERTIES_FILE_PATH_KEY);
    statsCounter = new StatsCounter(configService.getIntProperty(REFRESH_RATE_KEY));
    worker = new FileWorker(configService.getIntProperty(INITIAL_THREAD_POOL_SIZE_KEY), statsCounter);
    listener = new DirectoryListener(configService.getStringProperty(DEFAULT_DIRECTORY_KEY), worker);
  }

  public void setLoggerHandler(Handler handler) {
    LOGGER.setUseParentHandlers(false);
    LOGGER.addHandler(handler);
  }

  public void setDirToListen(String dirPath) {
    listener.setDirectory(dirPath);
  }

  public void setThreadPoolSize(int size) {
    worker.setThreadPoolSize(size);
  }

  public void startListening() {
    executorService = Executors.newFixedThreadPool(2);
    executorService.submit(listener);
    executorService.submit(statsCounter);
  }

  public void stopListening() {
    LOGGER.info("Stopping listening...");
    executorService.shutdownNow();
  }
}
