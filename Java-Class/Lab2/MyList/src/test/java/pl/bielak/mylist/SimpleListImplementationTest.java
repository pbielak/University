package pl.bielak.mylist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleListImplementationTest {
  @Test
  public void shouldAddAllNumbers() {
    SimpleListImplementation<Integer> numbers = new SimpleListImplementation<>();

    int i = 50;

    while (i > 0) {
      numbers.add(i);
      i--;
    }

    assertEquals(50, numbers.size());
  }
}
