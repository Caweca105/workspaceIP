package Aula03;

import java.util.NoSuchElementException;

public class ResizingArrayQueueOfStrings {
    private String[] queue; // Array for storing the elements of the queue
    private int n; // Number of elements in the queue
    private int first; // Index of the first element of the queue
    private int last; // Index of the next available slot in the queue

    // Constructor
    public ResizingArrayQueueOfStrings() {
        queue = new String[2]; // Initialize with a small capacity
        n = 0; // Initially, the queue is empty
        first = 0; // First element index
        last = 0; // Next available slot index
    }

    // Method to add an item to the queue
    public void enqueue(String item) {
        if (n == queue.length) resize(2 * queue.length); // Double the array size if it's full
        queue[last++] = item; // Add the item and increment 'last'
        if (last == queue.length) last = 0; // Wrap-around if 'last' reaches the end of the array
        n++; // Increment the number of elements
    }

    // Method to remove and return the least recently added item
    public String dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        String item = queue[first]; // Store the item to be returned
        queue[first] = null; // Nullify the slot for garbage collection
        n--; // Decrement the number of elements
        first++; // Move 'first' to the next element
        if (first == queue.length) first = 0; // Wrap-around if 'first' reaches the end of the array
        if (n > 0 && n == queue.length / 4) resize(queue.length / 2); // Halve the size of the array if it's only a quarter full
        return item; // Return the dequeued item
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return n == 0;
    }

    // Method to get the number of items in the queue
    public int size() {
        return n;
    }

    // Private helper method for resizing the array
    private void resize(int capacity) {
        assert capacity >= n;
        String[] temp = new String[capacity]; // Temporary array for resizing
        for (int i = 0; i < n; i++) {
            temp[i] = queue[(first + i) % queue.length]; // Copy elements to the new array
        }
        queue = temp; // Replace old array with the new one
        first = 0; // Reset 'first'
        last = n; // Set 'last' to the current number of elements
    }

    // Method to move the last element to the start of the queue
    public void shift() {
        if (isEmpty()) return;
        String lastItem = dequeue(); // Remove the last item
        enqueue(lastItem); // Add it back to the front
    }
}
