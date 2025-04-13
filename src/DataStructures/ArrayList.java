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
    // --- Skeleton Plan ---
    // 1. Check for null item; throw IllegalArgumentException if invalid.
    // 2. Check index bounds (0 <= index <= size); throw IndexOutOfBoundsException
    // if invalid.
    // 3. Ensure internal array has capacity... (handle resizing).
    // 4. Shift elements right...
    // 5. Place 'item' at buffer[index].
    // 6. Increment 'size'.
    throw new UnsupportedOperationException("Skeleton only, not implemented."); // Keep stub exception
  }

  @Override
  public void addFirst(T item) {
    // --- Skeleton Plan ---
    // 1. Check for null item (throw IllegalArgumentException).
    // 2. Ensure capacity (logical block `ensureCapacityForAdd`).
    // 3. Shift *all* existing elements (0 to size-1) one position right (logical
    // block `shiftRightFromIndex(0)`).
    // 4. Place item at buffer[0].
    // 5. Increment size.
    throw new UnsupportedOperationException("Skeleton only, not implemented."); // Keep stub exception
  }

  @Override
  public void addLast(T item) {
    // --- Direct Logic Skeleton ---
    // 1. Check for null item (throw IllegalArgumentException).
    // 2. Ensure capacity (logical block `ensureCapacityForAdd`).
    // 3. Place item at buffer[size] (No shifting required!).
    // 4. Increment size.
    throw new UnsupportedOperationException("Skeleton only, not implemented."); // Keep stub exception
  }

  @Override
  public boolean addAfter(T existing, T item) {
    // --- Skeleton Steps ---
    // 1. Check for null 'existing' or null 'item' (throw IllegalArgumentException,
    // as per Javadoc).
    // 2. Find the index of the 'existing' item...
    // 3. If 'existing' item was found...
    // - Plan: Execute the direct logic for adding 'item' at 'foundIndex + 1':...
    // - Return true (indicating success).
    // 4. Else ('existing' item was not found...):
    // - Return false.
    throw new UnsupportedOperationException("Skeleton only, not implemented."); // Keep stub exception
  }

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