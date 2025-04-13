package DataStructures;

import ADTs.ListADT;
import java.util.NoSuchElementException;
import java.util.Arrays; // Needed for Arrays.fill
import java.util.Objects; // Needed for Objects.equals

/**
 * A dynamic array implementation of the ListADT interface (Refined).
 * Uses System.arraycopy for efficiency, Arrays.fill, Objects.equals,
 * and delegates convenience methods to core implementations.
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

  // --- Private Helper Methods (Refined) ---

  @SuppressWarnings("unchecked")
  private void growIfNeeded() {
    if (this.size == this.buffer.length) {
      int oldCapacity = this.buffer.length;
      int newCapacity = (oldCapacity == 0) ? DEFAULT_CAPACITY : oldCapacity * 2;
      Object[] newBuffer = new Object[newCapacity];
      // Refinement: Use System.arraycopy for efficiency
      System.arraycopy(this.buffer, 0, newBuffer, 0, this.size);
      this.buffer = (T[]) newBuffer;
    }
  }

  private void shiftRight(int index) {
    // Refinement: Use System.arraycopy
    int numToMove = this.size - index;
    if (numToMove > 0) {
      System.arraycopy(this.buffer, index, this.buffer, index + 1, numToMove);
    }
  }

  private void shiftLeft(int index) {
    // Refinement: Use System.arraycopy
    int numToMove = this.size - 1 - index;
    if (numToMove > 0) {
      System.arraycopy(this.buffer, index + 1, this.buffer, index, numToMove);
    }
  }

  // --- Public ListADT Methods (Some Refined with Delegation) ---

  @Override
  public void add(int index, T item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }
    growIfNeeded();
    if (index < this.size) { // Only shift if not adding at the end
      shiftRight(index);
    }
    this.buffer[index] = item;
    this.size++;
  }

  @Override
  public void addFirst(T item) {
    // Refinement: Delegate to add(0, item)
    add(0, item);
  }

  @Override
  public void addLast(T item) {
    // Refinement: Delegate to add(size, item)
    add(this.size, item);
  }

  @Override
  public boolean addAfter(T existing, T item) {
    if (existing == null || item == null) {
      throw new IllegalArgumentException("Existing item and new item cannot be null.");
    }
    int foundIndex = indexOf(existing); // Reuse indexOf
    if (foundIndex >= 0) {
      add(foundIndex + 1, item); // Delegate to add
      return true;
    } else {
      return false;
    }
  }

  @Override
  public T removeFirst() {
    // Refinement: Handle specific exception, then delegate
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    return remove(0);
  }

  @Override
  public T removeLast() {
    // Refinement: Handle specific exception, then delegate
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    return remove(this.size - 1);
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }
    T removedItem = this.buffer[index];
    if (index < this.size - 1) { // Only shift if not removing the last element
      shiftLeft(index);
    }
    this.size--;
    this.buffer[this.size] = null; // Help GC
    return removedItem;
  }

  @Override
  public boolean remove(T item) {
    int foundIndex = indexOf(item);
    if (foundIndex >= 0) {
      remove(foundIndex); // Delegate
      return true;
    } else {
      return false;
    }
  }

  @Override
  public T first() {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    return this.buffer[0]; // Direct access is fine and slightly faster than get(0)
  }

  @Override
  public T last() {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    return this.buffer[this.size - 1]; // Direct access is fine
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }
    return this.buffer[index];
  }

  @Override
  public T set(int index, T item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }
    T oldItem = this.buffer[index];
    this.buffer[index] = item;
    return oldItem;
  }

  @Override
  public int indexOf(T item) {
    // Refinement: Use Objects.equals for null-safe comparison
    for (int i = 0; i < this.size; i++) {
      if (Objects.equals(item, this.buffer[i])) {
        return i;
      }
    }
    return -1;
  }

  // --- Methods from CollectionADT (Refined) ---

  @Override
  public void clear() {
    // Refinement: Use Arrays.fill for conciseness and potential efficiency
    if (this.size > 0) { // Only fill if there's something to clear
      Arrays.fill(this.buffer, 0, this.size, null);
    }
    this.size = 0;
  }

  @Override
  public boolean contains(T item) {
    // Delegation to refined indexOf is implicitly correct
    return indexOf(item) >= 0;
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public int size() {
    return this.size;
  }

  // --- toString Helper ---
  public String toDetailedString() {
    final int ELEMENTS_PER_LINE = 8;
    StringBuilder sb = new StringBuilder();
    sb.append("ArrayList[Size=").append(size);
    sb.append(", Capacity=").append(buffer.length).append("]\n");
    sb.append("  Internal: [\n");
    if (buffer.length > 0) {
      sb.append("    ");
      for (int i = 0; i < buffer.length; i++) {
        sb.append(buffer[i]);
        if (i < buffer.length - 1) {
          sb.append(" | ");
          if ((i + 1) % ELEMENTS_PER_LINE == 0) {
            sb.append("\n    ");
          }
        }
      }
    }
    sb.append("\n  ]");
    return sb.toString();
  }
}