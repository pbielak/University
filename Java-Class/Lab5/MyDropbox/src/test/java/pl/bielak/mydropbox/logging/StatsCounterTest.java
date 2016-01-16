package pl.bielak.mydropbox.logging;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.Executors;

import static org.testng.Assert.*;

public class StatsCounterTest {

  private StatsCounter statsCounter;
  private static final int DELAY = 4;

  @BeforeMethod
  public void setUp() throws Exception {
    statsCounter = new StatsCounter(DELAY);
  }

  @Test
  public void counterValueShouldBeZeroOnBeginning() {
    assertTrue(statsCounter.getCounterValue() == 0);
  }

  @Test
  public void counterValueShouldBeOneAfterIncrement() {
    statsCounter.incrementFileCounter();
    assertTrue(statsCounter.getCounterValue() == 1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowExceptionForNegativeDelay() {
    statsCounter = new StatsCounter(-5);
  }

  @Test
  public void counterValueShouldResetAfterFiveSeconds() throws InterruptedException {
    statsCounter.incrementFileCounter();
    Executors.newSingleThreadExecutor().submit(statsCounter);
    Thread.sleep((DELAY + 1) * 1000);
    assertTrue(statsCounter.getCounterValue() == 0);
  }

}