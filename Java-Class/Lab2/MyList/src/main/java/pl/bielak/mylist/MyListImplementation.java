package pl.bielak.mylist;


public class MyListImplementation<E> {
  private final int INITIAL_CAPACITY = 10;

  private E[] elements;
  private int currentSize;
  private int maxCapacity;

  public MyListImplementation() {
    createEmptyList();
  }

  public int size() {
    return currentSize;
  }

  public boolean isEmpty() {
    return (currentSize == 0);
  }

  public boolean contains(Object o) {

    for (int i = 0; i < currentSize; i++) {
      if (elements[i].equals(o)) {
        return true;
      }
    }

    return false;
  }

  public boolean add(E e) {
    currentSize++;

    if (currentSize >= maxCapacity) {
      increaseCapacity();
      copyAllElementsToResizedArray();
    }

    elements[currentSize - 1] = e;
    return true;
  }

  public boolean remove(Object o) throws Exception {
    if (isEmpty())
      throw new Exception("Can't remove element: List is already empty!");

    if (!contains(o))
      throw new Exception("Can't remove element: List doesn't contain that element!");

    int indexOfCurrentElement = indexOf(o);
    for (int i = indexOfCurrentElement; i < currentSize - 1; i++) {
      elements[i] = elements[i + 1];
    }

    elements[currentSize - 1] = null;
    currentSize--;

    return true;
  }

  public void clear() {
    createEmptyList();
  }

  public E get(int index) throws ArrayIndexOutOfBoundsException {
    isValidIndex(index);

    return elements[index];
  }

  public E set(int index, E element) throws ArrayIndexOutOfBoundsException {
    isValidIndex(index);

    return (elements[index] = element);
  }

  public int indexOf(Object o) {
    for (int i = 0; i < currentSize; i++) {
      if (elements[i].equals(o)) return i;
    }

    return -1;
  }

  public void printList() {
    System.out.print("[");

    for ( int i = 0; i < currentSize; i++) {
      System.out.print(elements[i] + " ");
    }

    System.out.println("]");
  }

  private void createEmptyList() {
    elements = (E[]) new Object[INITIAL_CAPACITY];
    currentSize = 0;
    maxCapacity = INITIAL_CAPACITY;
  }

  private void increaseCapacity() {
    maxCapacity *= 2;
  }

  private void copyAllElementsToResizedArray() {
    E[] temporaryCopyOfElements = (E[]) new Object[maxCapacity];

    int i = 0;
    for (E element : elements) {
      temporaryCopyOfElements[i++] = element;
    }

    elements = temporaryCopyOfElements;
  }

  private void isValidIndex(int index) throws ArrayIndexOutOfBoundsException {
    if ((index < 0) || (index >= currentSize))
      throw new ArrayIndexOutOfBoundsException();
  }
}
