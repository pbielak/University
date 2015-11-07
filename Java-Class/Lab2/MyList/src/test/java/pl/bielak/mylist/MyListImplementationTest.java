package pl.bielak.mylist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyListImplementationTest {
  private MyListImplementation<Integer> myList;

  @Before
  public void setUp() {
    myList = new MyListImplementation<>();
  }

  @After
  public void tearDown() {
    myList = null;
  }

  @Test
  public void initialSizeShouldBeZero() {
    assertEquals(true, myList.isEmpty());
  }

  @Test
  public void sizeAfterAddingOneElementShouldBeOne() {
    myList.add(666);
    assertEquals(1, myList.size());
  }

  @Test
  public void shouldAddAllElements() {
    int howManyElements = 100;
    for (int i = 0; i < howManyElements; i++) {
      myList.add(i);
    }
    assertEquals(howManyElements, myList.size());
  }

  @Test
  public void shouldContainAddedElement() {
    int[] elements = {10, 34, 94, 32, 99};

    for( int e : elements) {
      myList.add(e);
    }

    assertEquals(true, myList.contains(34));
  }

  @Test
  public void shouldRemoveElement() {
    int[] elements = {11, 22, 33, 44, 55};

    for( int i : elements) {
      myList.add(i);
    }

    try {
      myList.remove(44);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals(false, myList.contains(44));

  }

  @Test
  public void shouldBeEmptyAfterClearing() {
    int[] elements = {1,2,3,4,5,6,7,8};

    for(int i : elements) {
      myList.add(i);
    }

    myList.clear();

    assertEquals(true, myList.isEmpty());
  }
}