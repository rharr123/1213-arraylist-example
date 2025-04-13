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
    // --- Implementation based on Step 4 Skeleton ---

    // 1. Check for null item
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }

    // 2. Check index bounds for insertion (0 <= index <= size)
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }

    // 3. Ensure capacity for one more element
    growIfNeeded(); // Use helper implemented previously

    // 4. Make space by shifting elements right (if index < size)
    if (index < this.size) {
      shiftRight(index); // Use our shiftRight helper
    }
    // If index == size, no shift is needed.

    // 5. Place the new item at the specified index
    this.buffer[index] = item;

    // 6. Increment the list size
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
    // 3. Shift *all* existing elements right (if any exist)
    if (this.size > 0) { // Need to check if size > 0 before shifting index 0..size-1
      shiftRight(0); // Call helper
    }
    // 4. Place item at buffer[0]
    this.buffer[0] = item;
    // 5. Increment size
    this.size++;
  }

  @Override
  public void addLast(T item) {
    // --- Implementation based on Step 4 Direct Logic Skeleton ---

    // 1. Check for null item (as per Javadoc contract)
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }

    // 2. Ensure internal array has capacity for one more element
    growIfNeeded(); // Call our private helper method

    // 3. Place item at buffer[size] (the first unused slot)
    this.buffer[this.size] = item;

    // 4. Increment size
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
      // --- Direct Logic for add(foundIndex + 1, item) --- // Reusing add method is
      // cleaner
      add(foundIndex + 1, item);
      return true;
    } else {
      // 5. If not found...
      return false;
    }
  }

  // Remove Methods
  @Override
  public T removeFirst() {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check if empty
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    // 2. Delegate to remove(0) - Simpler than direct logic here
    return remove(0);
  }

  @Override
  public T removeLast() {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check if empty
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    // 2. Delegate to remove(size - 1)
    return remove(this.size - 1);
  }

  @Override
  public T remove(int index) {
    // --- Implementation based on Step 4 Skeleton ---

    // 1. Check index bounds (0 <= index < size)
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }

    // 2. Store buffer[index] to return later
    T removedItem = this.buffer[index];

    // 3. If removing element before the last one (index < size - 1), shift left
    if (index < this.size - 1) {
      shiftLeft(index); // Use our new helper
    }
    // If removing the last element (index == size - 1), no shifting is needed.

    // 4. Decrement size *before* nulling out the slot
    this.size--; // List is now logically shorter

    // 5. Set the now-unused last slot to null (helps Garbage Collector)
    this.buffer[this.size] = null;

    // 6. Return the stored 'removedItem'
    return removedItem;
  }

  @Override
  public boolean remove(T item) {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Find the index of the first occurrence of 'item'.
    int foundIndex = indexOf(item); // Reuse indexOf()

    // 2. Check if the item was found.
    if (foundIndex >= 0) {
      // --- Delegate to remove(foundIndex) --- // Cleaner
      remove(foundIndex);
      return true; // Indicate success
    } else {
      // 4. If not found, return false.
      return false;
    }
  }

  // Accessor/Query Methods
  @Override
  public T first() {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check if empty
    if (isEmpty()) { // Reuse isEmpty() method
      throw new NoSuchElementException("List is empty.");
    }
    // 2. Return buffer[0].
    return this.buffer[0]; // Direct access ok, or get(0)
  }

  @Override
  public T last() {
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Check if empty
    if (isEmpty()) { // Reuse isEmpty() method
      throw new NoSuchElementException("List is empty.");
    }
    // 2. Return buffer[size - 1].
    return this.buffer[this.size - 1]; // Direct access ok, or get(size - 1)
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
    // --- Implementation based on Step 4 Skeleton ---
    // 1. Iterate through the list elements with index 'i' from 0 to size - 1.
    for (int i = 0; i < this.size; i++) {
      // 2. Inside loop, compare 'item' with buffer[i] using null-safe equals.
      T currentElement = this.buffer[i];
      if (item == null) {
        // If searching for null, find the first null element
        if (currentElement == null) {
          return i; // Found null at index i
        }
      } else {
        // If searching for non-null, use item.equals()
        if (item.equals(currentElement)) {
          return i; // Found match at index i
        }
      }
    }
    // 4. If loop finishes without finding a match, return -1.
    return -1;
  }

  @Override
  public boolean contains(T item) {
    // --- Implementation based on Step 4 Skeleton (Leveraging indexOf) ---
    // 1. Perform the logic for indexOf(item).
    // 2. Return true if the index found is not -1.
    return indexOf(item) >= 0;
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
    // --- Implementation based on Step 4 Skeleton ---

    // 1. Optional (Helps GC): Null out references in the array slots
    // that were actually used (0 to size-1).
    for (int i = 0; i < this.size; i++) {
      this.buffer[i] = null;
    }

    // 2. Reset the size to 0.
    this.size = 0;
  }

  // Helper for toString (can be included in starter)
  public String toDetailedString() {
    // Using the full version from final code for better demo
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

  // No private helpers needed yet

  // --- Private Helper Methods ---

  /**
   * Ensures that the internal array has space to add at least one more element.
   * If the array is full, it creates a new, larger array (typically double
   * the size) and copies the existing elements into it using a loop.
   */
  @SuppressWarnings("unchecked") // For the cast when assigning back to buffer
  private void growIfNeeded() {
    // Check if the array is full
    if (this.size == this.buffer.length) {
      // Determine the new capacity
      int oldCapacity = this.buffer.length;
      int newCapacity = (oldCapacity == 0) ? DEFAULT_CAPACITY : oldCapacity * 2;

      // Create the new, larger array (as Object[] due to erasure)
      Object[] newbuffer = new Object[newCapacity];

      // --- Copy elements using a standard for loop ---
      // We need to copy 'size' elements, from index 0 to size-1
      for (int i = 0; i < this.size; i++) {
        newbuffer[i] = this.buffer[i]; // Copy element at index i
      }
      // --- End of copy loop ---

      // Update the buffer reference to point to the new array
      // This requires the cast, hence the @SuppressWarnings on the method
      this.buffer = (T[]) newbuffer;

      // Optional: Log or print a message when resizing happens (for debugging)
      // System.out.println("DEBUG: Resized array from " + oldCapacity + " to " +
      // newCapacity);
    }
    // If not full, do nothing.
  }

  /**
   * Shifts elements to the right to make space for insertion at a given index.
   * Elements from buffer[index] through buffer[size-1] are moved
   * to positions buffer[index+1] through buffer[size].
   * Assumes capacity is already sufficient and index is valid for insertion.
   * 
   * @param index The index at which an element will be inserted (0 <= index <
   *              size).
   */
  private void shiftRight(int index) {
    // Loop backwards from the last current element down to the insertion index
    // Example: size=3, index=1. Need to move elements at 1, 2.
    // i = 2: buffer[3] = buffer[2] (Move C)
    // i = 1: buffer[2] = buffer[1] (Move B)
    for (int i = this.size - 1; i >= index; i--) {
      this.buffer[i + 1] = this.buffer[i];
    }
    // The slot at buffer[index] now contains a copy but is ready to be overwritten.
  }

  /**
   * Shifts elements to the left to fill the gap after removal at a given index.
   * Elements from buffer[index+1] through buffer[size-1] are moved
   * to positions buffer[index] through buffer[size-2].
   * Assumes index is valid for removal (0 <= index < size).
   * 
   * @param index The index from which an element was just removed.
   */
  private void shiftLeft(int index) {
    // Loop from the removal index up to the second-to-last element
    // Example: size=4, index=1 (removed B). Need to move C, D.
    // i = 1: buffer[1] = buffer[2] (Move C left)
    // i = 2: buffer[2] = buffer[3] (Move D left)
    // Loop condition i < size - 1 (i < 3) stops it correctly.
    for (int i = index; i < this.size - 1; i++) {
      this.buffer[i] = this.buffer[i + 1];
    }
    // The slot at the old end (size - 1) now contains a duplicate
    // but will be handled by the calling remove method.
  }
}