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
    // --- Skeleton Plan ---
    // 1. Check if list is empty (size == 0).
    // 2. If empty, throw NoSuchElementException.
    // 3. If not empty, perform the logic for remove(0): ...
    // - Return the stored element.
    throw new UnsupportedOperationException("Skeleton only, not implemented.");
  }

  @Override
  public T removeLast() {
    // --- Skeleton Plan ---
    // 1. Check if list is empty (size == 0).
    // 2. If empty, throw NoSuchElementException.
    // 3. If not empty: ...
    // - Return the stored element.
    throw new UnsupportedOperationException("Skeleton only, not implemented.");
  }

  @Override
  public T remove(int index) {
    // --- Skeleton Plan ---
    // 1. Check index bounds (0 <= index < size); throw IndexOutOfBoundsException if
    // invalid.
    // 2. Store buffer[index] in a temporary variable (e.g., 'removedItem').
    // 3. If removing element before the last one (index < size - 1), perform "Shift
    // Left" helper logic...
    // 4. Decrement 'size'.
    // 5. Set buffer[size] (the now-unused last slot) to null.
    // 6. Return 'removedItem'.
    throw new UnsupportedOperationException("Skeleton only, not implemented.");
  }

  @Override
  public boolean remove(T item) {
    // --- Skeleton Steps ---
    // 1. Find the index of the first occurrence of 'item'...
    // 2. Check if the item was found.
    // - If foundIndex >= 0:
    // - Plan: Perform the direct logic for removing the element at 'foundIndex':...
    // - Return true.
    // - Else (foundIndex == -1):
    // - Return false.
    throw new UnsupportedOperationException("Skeleton only, not implemented.");
  }

  // Accessor/Query Methods
  @Override
  public T first() {
    // --- Skeleton Plan ---
    // 1. Check if empty (size == 0); throw NoSuchElementException if true.
    // 2. Return buffer[0].
    throw new UnsupportedOperationException("Skeleton only, not implemented.");
  }

  @Override
  public T last() {
    // --- Skeleton Plan ---
    // 1. Check if empty (size == 0); throw NoSuchElementException if true.
    // 2. Return buffer[size - 1].
    throw new UnsupportedOperationException("Skeleton only, not implemented.");
  }

  @Override
  public T get(int index) {
    // --- Implementation based on Step 4 Skeleton ---

    // 1. Check index bounds (0 <= index < size)
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }

    // 2. Return buffer[index]
    return this.buffer[index];
  }

  @Override
  public T set(int index, T item) {
    // --- Implementation based on Step 4 Skeleton ---

    // 1. Check for null item (as per Javadoc contract)
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }

    // 2. Check index bounds (0 <= index < size)
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }

    // 3. Store current buffer[index]
    T oldItem = this.buffer[index];

    // 4. Update the array
    this.buffer[index] = item;

    // 5. Return 'oldItem'
    return oldItem;
  }

  @Override
  public int indexOf(T item) {
    // --- Skeleton Plan ---
    // 1. Plan: Iterate with index 'i' from 0 to size - 1.
    // 2. Inside loop, compare 'item' with buffer[i] using null-safe equals...
    // 3. If a match is found, return index 'i' immediately.
    // 4. If loop finishes without a match, return -1.
    throw new UnsupportedOperationException("Skeleton only, not implemented.");
  }

  @Override
  public boolean contains(T item) {
    // --- Skeleton Plan (Leveraging indexOf logic) ---
    // 1. Perform the logic planned for indexOf(item). Let the result be
    // 'foundIndex'.
    // 2. Return true if 'foundIndex' is not -1, otherwise return false.
    // - Return (foundIndex >= 0);
    throw new UnsupportedOperationException("Skeleton only, not implemented.");
  }

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
    // --- Skeleton Steps ---
    // 1. Optional (for GC): Iterate from 0 to size-1 and set buffer[i] to null.
    // 2. Reset the size.
    // - Set size = 0;
    throw new UnsupportedOperationException("Skeleton only, not implemented.");
  }

  // Helper for toString (can be included in starter)
  public String toDetailedString() {
    // Basic stub or the full version from final code
    return "ArrayList[Size=" + size + ", Capacity=" + (buffer != null ? buffer.length : 0)
        + "] (Implementation Pending)";
  }

  // No private helpers needed yet
}