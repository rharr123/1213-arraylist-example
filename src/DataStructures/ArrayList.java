package DataStructures;

import ADTs.ListADT; // Assumes ListADT extends CollectionADT
import java.util.NoSuchElementException;
// Other exceptions (IndexOutOfBounds, IllegalArgument) are in java.lang

/**
 * Starter skeleton for ArrayList implementation.
 * Contains only the basic structure, fields, constructor, and method stubs.
 * 
 * @param <T> the type of elements held in this list
 */
public class ArrayList<T> implements ListADT<T> {

  private static final int DEFAULT_CAPACITY = 10;
  private T[] buffer;
  private int size;

  @SuppressWarnings("unchecked")
  public ArrayList() {
    this.buffer = (T[]) new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  // --- Stubs for ALL ListADT/CollectionADT methods ---
  // Add Methods
  @Override
  public void add(int index, T item) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public void addFirst(T item) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public void addLast(T item) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public boolean addAfter(T existing, T item) {
    throw new UnsupportedOperationException("Not implemented yet.");
    /* return false; */ } // Adjusted stub

  // Remove Methods
  @Override
  public T removeFirst() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public T removeLast() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public T remove(int index) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public boolean remove(T item) {
    throw new UnsupportedOperationException("Not implemented yet.");
    /* return false; */ }

  // Accessor/Query Methods
  @Override
  public T first() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public T last() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public T get(int index) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public T set(int index, T item) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public int indexOf(T item) {
    throw new UnsupportedOperationException("Not implemented yet.");
    /* return -1; */ }

  @Override
  public boolean contains(T item) {
    throw new UnsupportedOperationException("Not implemented yet.");
    /* return false; */ }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  } // Implemented

  @Override
  public int size() {
    return this.size;
  } // Implemented

  // Clear Method
  @Override
  public void clear() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  // Helper for toString (can be included in starter)
  public String toDetailedString() {
    // Basic stub or the full version from final code
    return "ArrayList[Size=" + size + ", Capacity=" + (buffer != null ? buffer.length : 0)
        + "] (Implementation Pending)";
  }

  // No private helpers needed yet
}