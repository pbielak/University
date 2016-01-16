package pl.bielak.mydropbox.logging;


import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class StatsCounter implements Callable {
  private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private AtomicInteger fileCounter;
  private final int DELAY;

  public StatsCounter(int delay) {
    if(delay <= 0) {
      throw new IllegalArgumentException("Delay must be positive!");
    }
    this.fileCounter = new AtomicInteger(0);
    this.DELAY = delay;
  }

  public void incrementFileCounter() {
    fileCounter.incrementAndGet();
  }

  public int getCounterValue() {
    return fileCounter.getAndSet(0);
  }

  @Override
  public Object call() throws Exception {
    while (!Thread.currentThread().isInterrupted()) {
      TimeUnit.SECONDS.sleep(DELAY);
      double files_per_second = getCounterValue() / ((double) DELAY);
      LOGGER.info("Wysylanie: " + files_per_second + " [plikow/s]");
    }
    return null;
  }
}
