package Aula06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedList<T extends Comparable<T>> implements Iterable<T> {
    private Node first;
    private int size = 0;

    /***
     * Node class to represent each element in the list.
     * @param <T> the type of the element to be stored in the list.
     */
    private class Node {
        T item;
        Node next;

        Node(T item) {
            this.item = item;
            this.next = null;
        }
    }
    
    /**
     * 
     */
    public OrderedList() {
    }

    /**
     * Adds the specified item to the end of the list.
     *
     * @param item the item to be added
     */
    public void add(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
        } else {
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If the list does not contain the element, it is unchanged.
     * @param item element to be removed from this list, if present
     * @return true if the list contained the specified element, false otherwise
     */
    public boolean contains (T item) {
        Node current = first;
        while (current != null) {
            if (current.item.equals(item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in this list.
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If the list does not contain the element, it is unchanged.
     * @param item element to be removed from this list, if present
     * @return true if the list contained the specified element, false otherwise
     */
    public void sort() {
        if (first == null || first.next == null) {
            return; // The list is already sorted or empty.
        }
    
        Node sorted = null; // Start with an empty sorted list.
        Node current = first; // Iterate over the original list.
        while (current != null) {
            Node next = current.next; // Save next for later.
    
            // Find the position to insert the current node in the sorted list.
            if (sorted == null || sorted.item.compareTo(current.item) > 0) {
                current.next = sorted;
                sorted = current;
            } else {
                Node temp = sorted;
                while (temp.next != null && temp.next.item.compareTo(current.item) < 0) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }
            current = next; // Move to the next node.
        }
        first = sorted; // Update the list to be the sorted list.
    }

    /**
     * Returns true if the list is sorted in ascending order.
     * @return
     */
    public boolean isSorted() {
        if (first == null || first.next == null) return true; // Empty or single element lists are sorted.
    
        Node current = first;
        while (current.next != null) {
            if (current.item.compareTo(current.next.item) > 0) {
                return false; // Found two consecutive items that are out of order.
            }
            current = current.next;
        }
        return true; // No items were out of order.
    }

    /**
     * Shuffles the list in place.
     */
    public void shuffle() {
        if (first == null) return; // List is empty, nothing to shuffle.
    
        Node current = first;
        int index = 0;
        while (current != null) {
            int swapIndex = index + (int) (Math.random() * (size - index));
            Node swapNode = nodeAt(swapIndex);
            T temp = current.item;
            current.item = swapNode.item;
            swapNode.item = temp;
    
            current = current.next;
            index++;
        }
    }
    
    /**
     * Returns the node at the specified index.
     * @param index
     * @return
     */
    private Node nodeAt(int index) {
        Node current = first;
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        return current;
    }
    
    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = first;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    public static void main(String[] args) {
        OrderedList<Integer> list = new OrderedList<>();
        // Expected: true, since the list is empty.
        System.out.println("Is the list empty? " + list.isEmpty());
        
        // Add elements
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(9);
        list.add(2);
        list.add(6);

        // Expected: 3 1 4 1 5 9 2 6 (in the order added, since sorting hasn't been applied yet)
        System.out.println("Initial list:");
        for (int item : list) {
            System.out.print(item + " ");
        }
        System.out.println("\n");

        // Expected: true for 3, false for 7, as 3 is in the list but 7 is not.
        System.out.println("Contains 3? " + list.contains(3));
        System.out.println("Contains 7? " + list.contains(7));
        
        // Expected: 8, since 8 elements have been added.
        System.out.println("Size of the list: " + list.size());
        // Expected: false, since the list is not empty.
        System.out.println("Is the list empty? " + list.isEmpty());

        // Sort list
        list.sort();
        // Expected after sorting: 1 1 2 3 4 5 6 9 (sorted in ascending order)
        System.out.println("List after sorting:");
        for (int item : list) {
            System.out.print(item + " ");
        }
        System.out.println("\n");
        
        // Expected: true, since the list has been sorted in ascending order.
        System.out.println("Is the list sorted? " + list.isSorted());

        // Shuffle list
        list.shuffle();
        // Expected after shuffling: Elements in a randomized order, not predictable. All elements present but mixed.
        System.out.println("List after shuffling:");
        for (int item : list) {
            System.out.print(item + " ");
        }
        System.out.println("\n");
        
        // Expected: 8, size remains unchanged after shuffling.
        System.out.println("Size of the list after modifications: " + list.size());
        // Expected: false, the list remains non-empty.
        System.out.println("Is the list still empty? " + list.isEmpty());
    }
}
