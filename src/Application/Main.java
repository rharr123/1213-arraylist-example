package Application;

import DataStructures.ArrayList;
// import ADTs.ListADT; // No longer needed if we use ArrayList directly

/**
 * A simple application class to demonstrate and manually test the
 * DataStructures.ArrayList implementation, showing its internal state.
 */
public class Main {

  public static void main(String[] args) {
    // Declare variable as ArrayList to access implementation-specific methods
    ArrayList<String> myList = new ArrayList<>(); // Uses default capacity (e.g., 10)

    System.out.println("===== Initial Empty List =====");
    // Directly print the detailed state
    System.out.println("State: " + myList.toDetailedString());
    System.out.println();

    // --- Demonstrate addLast ---
    System.out.println("===== Demonstrating addLast =====");
    System.out.println("--- Operation: addLast(\"A\") ---");
    myList.addLast("A");
    System.out.println("State After addLast(\"A\"): " + myList.toDetailedString());
    System.out.println();

    System.out.println("--- Operation: addLast(\"B\") ---");
    myList.addLast("B");
    System.out.println("State After addLast(\"B\"): " + myList.toDetailedString());
    System.out.println();

    System.out.println("--- Operation: addLast(\"C\") ---");
    myList.addLast("C");
    System.out.println("State After addLast(\"C\"): " + myList.toDetailedString());
    System.out.println();
    // Example state: ArrayList[Size=3, Capacity=10, Internal=[A | B | C | null |
    // null | null | null | null | null | null]]

    // --- Demonstrate add(index, item) ---
    System.out.println("\n===== Demonstrating add(index, item) =====");
    System.out.println("State Before add(1, \"X\"): " + myList.toDetailedString());
    String itemToAdd = "X";
    int indexToAdd = 1;
    System.out.println("--- Operation: add(" + indexToAdd + ", \"" + itemToAdd + "\") ---");
    myList.add(indexToAdd, itemToAdd);
    System.out.println("State After add(1, \"X\"): " + myList.toDetailedString());
    System.out.println();
    // Example state: ArrayList[Size=4, Capacity=10, Internal=[A | X | B | C | null
    // | null | null | null | null | null]]

    // --- Demonstrate remove(index) ---
    System.out.println("\n===== Demonstrating remove(index) =====");
    System.out.println("State Before remove(2): " + myList.toDetailedString());
    int indexToRemove = 2; // Removes "B"
    System.out.println("--- Operation: remove(" + indexToRemove + ") ---");
    String removedItem = myList.remove(indexToRemove);
    System.out.println(" Removed Item: \"" + removedItem + "\"");
    // Note: The implementation nulls out the last element after shifting
    System.out.println("State After remove(2): " + myList.toDetailedString());
    System.out.println();
    // Example state: ArrayList[Size=3, Capacity=10, Internal=[A | X | C | null |
    // null | null | null | null | null | null]]

    // --- Demonstrate remove(item) ---
    System.out.println("\n===== Demonstrating remove(item) =====");
    System.out.println("State Before remove(\"X\"): " + myList.toDetailedString());
    String itemToRemove = "X";
    System.out.println("--- Operation: remove(\"" + itemToRemove + "\") ---");
    boolean wasRemoved = myList.remove(itemToRemove);
    System.out.println(" Was item removed? " + wasRemoved);
    System.out.println("State After remove(\"X\"): " + myList.toDetailedString());
    System.out.println();
    // Example state: ArrayList[Size=2, Capacity=10, Internal=[A | C | null | null |
    // null | null | null | null | null | null]]

    // --- Demonstrate clear ---
    System.out.println("\n===== Demonstrating clear() =====");
    System.out.println("State Before clear(): " + myList.toDetailedString());
    System.out.println("--- Operation: clear() ---");
    myList.clear(); // Creates a new internal array
    System.out.println("State After clear(): " + myList.toDetailedString());
    System.out.println();
    // Example state: ArrayList[Size=0, Capacity=10, Internal=[null | null | null |
    // null | null | null | null | null | null | null]]

    // --- Demonstrate Dynamic Resizing ---
    System.out.println("\n===== Demonstrating Dynamic Resizing =====");
    System.out.println("Creating a new list and filling to capacity (10) with letters A-J...");
    myList = new ArrayList<>(); // Fresh list
    for (int i = 0; i < 10; i++) { // Loop 0 to 9 (10 times)
      // Calculate character: 'A' + 0 = 'A', 'A' + 1 = 'B', ..., 'A' + 9 = 'J'
      char elementChar = (char) ('A' + i);
      myList.addLast(String.valueOf(elementChar)); // Add the character as a String
    }
    System.out.println("State After adding 10 items (A-J): " + myList.toDetailedString());

    // Add the 11th element ('K')
    char eleventhChar = (char) ('A' + 10); // 'K'
    String eleventhElement = String.valueOf(eleventhChar);
    System.out.println("--- Operation: addLast(\"" + eleventhElement + "\") --- (This triggers internal resize)");
    myList.addLast(eleventhElement);
    System.out.println("State After adding 11th item (" + eleventhElement + "): " + myList.toDetailedString());
    System.out.println("(Note: Internal array capacity has doubled to 20)");
    System.out.println();
    // Example state: ArrayList[Size=11, Capacity=20, Internal=[A | B | ... | J | K
    // | null | ... | null]]
  }
}