package DataStructures;

import ADTs.ListADT; // Assuming ListADT extends CollectionADT implicitly included
import java.util.NoSuchElementException;
// IndexOutOfBoundsException and IllegalArgumentException are in java.lang (implicit)

/**
 * A dynamic array implementation of the ListADT interface.
 * Allows storing elements of type T, resizing as needed.
 * Implements core logic using loops for shifting before potential refinement.
 *
 * @param <T> the type of elements held in this list
 */
// Suppress warnings for the necessary generic array casts
public class ArrayList<T> implements ListADT<T> {

  private static final int DEFAULT_CAPACITY = 10;

  private T[] buffer;
  private int size;

  /**
   * Constructs an empty list with an initial capacity of DEFAULT_CAPACITY.
   */
  @SuppressWarnings("unchecked")
  public ArrayList() {
    // Cannot do: this.elementData = new T[DEFAULT_CAPACITY];
    // Workaround: Create Object array and cast
    this.buffer = (T[]) new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  // --- Private Helper Methods ---

  /**
   * Ensures that the internal array has space to add at least one more element.
   * If the array is full, it creates a new, larger array (typically double
   * the size) and copies the existing elements into it using a loop.
   */
  private void growIfNeeded() {
    if (this.size == this.buffer.length) {
      int oldCapacity = this.buffer.length;
      int newCapacity = (oldCapacity == 0) ? DEFAULT_CAPACITY : oldCapacity * 2;
      Object[] newElementData = new Object[newCapacity];
      // Copy elements using a standard for loop
      for (int i = 0; i < this.size; i++) {
        newElementData[i] = this.buffer[i];
      }
      this.buffer = (T[]) newElementData; // Cast needed here
    }
  }

  /**
   * Shifts elements to the right to make space for insertion at a given index.
   * Elements from elementData[index] through elementData[size-1] are moved
   * to positions elementData[index+1] through elementData[size].
   * Assumes capacity is already sufficient and index is valid (0 <= index <
   * size).
   * 
   * @param index The index at which an element will be inserted.
   */
  private void shiftRight(int index) {
    // Loop backwards from the last current element down to the insertion index
    for (int i = this.size - 1; i >= index; i--) {
      this.buffer[i + 1] = this.buffer[i];
    }
  }

  /**
   * Shifts elements to the left to fill the gap after removal at a given index.
   * Elements from elementData[index+1] through elementData[size-1] are moved
   * to positions elementData[index] through elementData[size-2].
   * 
   * @param index The index from which an element was just removed (0 <= index <
   *              size).
   */
  private void shiftLeft(int index) {
    // Loop from the removal index up to the second-to-last element
    for (int i = index; i < this.size - 1; i++) { // Condition stops at size-2
      this.buffer[i] = this.buffer[i + 1];
    }
  }

  // --- Public ListADT Methods ---

  @Override
  public void add(int index, T item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }
    if (index < 0 || index > this.size) { // index == size IS valid for add
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }
    growIfNeeded();
    shiftRight(index);
    this.buffer[index] = item;
    this.size++;
  }

  @Override
  public void addFirst(T item) {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check for null item
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }
    // 2. Ensure capacity
    growIfNeeded(); // Call helper
    // 3. Shift *all* existing elements right
    shiftRight(0); // Call helper
    // 4. Place item at buffer[0]
    this.buffer[0] = item;
    // 5. Increment size
    this.size++;
  }

  @Override
  public void addLast(T item) {
    // Direct implementation as derived (could also call add(size, item))
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }
    growIfNeeded();
    this.buffer[this.size] = item;
    this.size++;
  }

  @Override
  public boolean addAfter(T existing, T item) {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check null arguments
    if (existing == null || item == null) {
      throw new IllegalArgumentException("Existing item and new item cannot be null.");
    }
    // 2. Find index of existing item
    int foundIndex = indexOf(existing); // Reuse indexOf()

    // 3. If found...
    if (foundIndex >= 0) {
      // --- Direct Logic for add(foundIndex + 1, item) ---
      int insertionIndex = foundIndex + 1;
      // Index bounds check (0 <= insertionIndex <= size) is implicitly handled
      // by the logic below and the fact foundIndex is valid.

      // Ensure capacity (Call helper)
      growIfNeeded();
      shiftRight(insertionIndex);
      // Place item
      this.buffer[insertionIndex] = item;
      // Increment size
      this.size++;
      // --- End of Direct Logic ---
      return true;
    } else {
      // 5. If not found...
      return false;
    }
  }

  @Override
  public T removeFirst() {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check if empty
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    // 2. Store element at index 0
    T removedItem = this.buffer[0];
    // 3. Shift elements 1..size-1 left (if any exist)
    shiftLeft(0); // Call helper
    // 4. Decrement size
    this.size--;
    // 5. Null out the now-unused slot at the new end
    this.buffer[this.size] = null; // Help GC
    // 6. Return the stored element
    return removedItem;
  }

  @Override
  public T removeLast() {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check if empty
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    // 2. Calculate last index
    int lastIndex = this.size - 1;
    // 3. Store element at last index
    T removedItem = this.buffer[lastIndex];
    // 4. Decrement size *before* nulling out
    this.size--;
    // 5. Null out the now-unused slot (at the new size, which was the old
    // lastIndex)
    this.buffer[this.size] = null; // Help GC - No shifting needed!
    // 6. Return the stored element
    return removedItem;
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }
    T removedItem = this.buffer[index];
    // Only shift if removing element before the last one
    shiftLeft(index);
    this.size--; // Decrement size *before* nulling out
    // Set the now-unused last slot to null (helps Garbage Collector)
    this.buffer[this.size] = null;
    return removedItem;
  }

  @Override
  public boolean remove(T item) {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Find the index of the first occurrence of 'item'.
    int foundIndex = indexOf(item); // Reuse indexOf()

    // 2. Check if the item was found.
    if (foundIndex >= 0) {
      // --- Direct Logic for remove(foundIndex) ---
      // Bounds check is implicit (foundIndex is 0 to size-1)

      // Store item (optional, not needed for boolean return)
      // T removedItem = this.buffer[foundIndex];

      // If removing before the last one, shift left (Call helper)
      shiftLeft(foundIndex);
      // Decrement size
      this.size--;
      // Null out now-unused slot (Help GC)
      this.buffer[this.size] = null;
      // --- End of Direct Logic ---
      return true; // Indicate success
    } else {
      // 4. If not found, return false.
      return false;
    }
  }

  @Override
  public T first() {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check if empty
    if (isEmpty()) { // Reuse isEmpty() method
      throw new NoSuchElementException("List is empty.");
    }
    // 2. Return buffer[0].
    return this.buffer[0];
  }

  @Override
  public T last() {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check if empty
    if (isEmpty()) { // Reuse isEmpty() method
      throw new NoSuchElementException("List is empty.");
    }
    // 2. Return buffer[size - 1].
    return this.buffer[this.size - 1];
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
    for (int i = 0; i < this.size; i++) {
      T currentElement = this.buffer[i];
      // Null-safe comparison
      if (item == null) {
        if (currentElement == null) {
          return i;
        }
      } else {
        if (item.equals(currentElement)) {
          return i;
        }
      }
    }
    return -1; // Not found
  }

  // --- Methods from CollectionADT ---

  @Override
  public void clear() {
    // Help GC by nulling out references
    for (int i = 0; i < this.size; i++) {
      this.buffer[i] = null;
    }
    this.size = 0;
    // Optionally resize back down to DEFAULT_CAPACITY here? Omitted for now.
  }

  @Override
  public boolean contains(T item) {
    // Delegate to indexOf logic
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

  /**
   * Provides a detailed string representation of the ArrayList's internal state,
   * optimized for readability on narrower displays by using line breaks.
   * Includes size, capacity, and the full content of the backing array.
   * Intended for debugging and demonstration purposes.
   *
   * @return A multi-line string detailing the internal state.
   */
  public String toDetailedString() {
    final int ELEMENTS_PER_LINE = 8; // Adjust as needed for desired width
    StringBuilder sb = new StringBuilder();

    // Line 1: Summary
    sb.append("ArrayList[Size=").append(size);
    sb.append(", Capacity=").append(buffer.length).append("]\n"); // Add newline

    // Line 2 onwards: Internal array representation
    sb.append("  Internal: [\n"); // Indent and start array on new line
    if (buffer.length > 0) {
      sb.append("    "); // Indent first line of elements
      for (int i = 0; i < buffer.length; i++) {
        // Append element
        sb.append(buffer[i]);

        // Check if it's not the last element
        if (i < buffer.length - 1) {
          sb.append(" | "); // Add separator

          // Check if we need to wrap to the next line
          if ((i + 1) % ELEMENTS_PER_LINE == 0) {
            sb.append("\n    "); // Newline and indent next line
          }
        }
      }
    }
    sb.append("\n  ]"); // Close bracket on a new line, indented

    return sb.toString();
  }
}