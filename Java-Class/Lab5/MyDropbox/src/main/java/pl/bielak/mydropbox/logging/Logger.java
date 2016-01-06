package pl.bielak.mydropbox.logging;

import pl.bielak.mydropbox.config.ConfigService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static pl.bielak.mydropbox.config.Keys.PROPERTIES_FILE_PATH_KEY;
import static pl.bielak.mydropbox.config.Keys.REFRESH_RATE_KEY;

public class Logger implements Runnable{
  private static Logger instance = null;
  private static AtomicInteger fileCounter = new AtomicInteger(0);
  private static final int DELAY = new ConfigService(PROPERTIES_FILE_PATH_KEY).getIntProperty(REFRESH_RATE_KEY);

  private Logger() {
  }

  public static Logger getInstance() {
    if(instance == null) {
      instance = new Logger();
    }

    return instance;
  }

  public static void startLogger() {
    ExecutorService service = Executors.newSingleThreadExecutor();
    service.submit(Logger.getInstance());
  }

  public static void incrementFileCounter() {
    fileCounter.incrementAndGet();
  }

  public static void info(String message){

    System.out.println("[" + getCurrentTime() + " INFO] " + message);
  }

  public static String getCurrentTime() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    return dateFormat.format(cal.getTime());
  }

  @Override
  public void run() {
    while(true) {
      double files_per_second = fileCounter.getAndSet(0) / ((double) DELAY);
      info("Wysyłanie: " +  files_per_second + " [plików/s]");

      try {
        TimeUnit.SECONDS.sleep(DELAY);
      } catch (InterruptedException e) {
        throw new IllegalStateException("Interrupted Exception", e);
      }
    }
  }
}
