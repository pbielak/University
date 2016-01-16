package pl.bielak.mydropbox.workers;

import org.testng.annotations.Test;
import pl.bielak.mydropbox.logging.StatsCounter;

import static org.testng.Assert.*;

public class FileWorkerTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowExceptionForNegativePoolValue() {
    new FileWorker(-1, new StatsCounter(10));
  }
}