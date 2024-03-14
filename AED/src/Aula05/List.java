package Aula05;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * The List class represents a singly linked list data structure.
 * It implements the Iterable interface, allowing iteration over the elements in the list.
 *
 * @param <T> the type of elements stored in the list
 */
public class List<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        T item;
        Node next;
        
        Node(T item) {
            this.item = item;
            this.next = null;
        }
    }

    /**
     * Initializes a new instance of the List class.
     * The list is initially empty.
     */
    public List() {
        first = last = null;
        size = 0;
    }

    /**
     * Adds the specified item to the end of the list.
     *
     * @param item the item to be added
     */
    public void add(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Retrieves the element at the specified index in this list.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    /**
     * Removes and returns the element at the specified index in this list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node prev = null;
        Node current = first;
        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.next;
        }
        if (prev == null) {
            first = first.next;
            if (first == null) last = null;
        } else {
            prev.next = current.next;
            if (prev.next == null) last = prev;
        }
        size--;
        return current.item;
    }

    /**
     * Removes the first occurrence of the specified item from the list.
     *
     * @param item the item to be removed
     * @return true if the item was found and removed, false otherwise
     */
    public boolean removeFirst(T item) {
        for (Node prev = null, current = first; current != null; prev = current, current = current.next) {
            if (current.item.equals(item)) {
                if (prev == null) {
                    first = current.next;
                    if (first == null) last = null;
                } else {
                    prev.next = current.next;
                    if (prev.next == null) last = prev;
                }
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the last occurrence of the specified item from the list.
     *
     * @param item the item to be removed
     * @return true if the item was found and removed, false otherwise
     */
    public boolean removeLast(T item) {
        Node prevToRemove = null;
        Node toRemove = null;
        for (Node prev = null, current = first; current != null; prev = current, current = current.next) {
            if (current.item.equals(item)) {
                prevToRemove = prev;
                toRemove = current;
            }
        }
        if (toRemove != null) {
            if (prevToRemove == null) {
                first = toRemove.next;
                if (first == null) last = null;
            } else {
                prevToRemove.next = toRemove.next;
                if (prevToRemove.next == null) last = prevToRemove;
            }
            size--;
            return true;
        }
        return false;
    }

    /**
     * Removes all occurrences of the specified item from the list.
     *
     * @param item the item to be removed from the list
     * @return true if at least one occurrence of the item was removed, false otherwise
     */
    public boolean removeAll(T item) {
        boolean removed = false;
        while (removeFirst(item)) {
            removed = true;
        }
        return removed;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the list contains the specified item.
     *
     * @param item the item to be checked for existence in the list
     * @return true if the list contains the item, false otherwise
     */
    public boolean contains(T item) {
        for (Node current = first; current != null; current = current.next) {
            if (current.item.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the linked list is a palindrome.
     * A linked list is considered a palindrome if it reads the same forwards and backwards.
     *
     * @return true if the linked list is a palindrome, false otherwise.
     */
    public boolean isPalindrome() {
        if (isEmpty() || size == 1) return true;
        Stack<T> stack = new Stack<>();
        Node slow = first, fast = first;
        while (fast != null && fast.next != null) {
            stack.push(slow.item);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) slow = slow.next; // Odd number of elements
        while (slow != null) {
            if (!slow.item.equals(stack.pop())) return false;
            slow = slow.next;
        }
        return true;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list
     */
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
        List<String> list = new List<>();
        System.out.println("Is empty: " + list.isEmpty());
        list.add("apple");
        list.add("banana");
        list.add("orange");
        list.add("banana");
        list.add("apple");
        list.add("nozes");
        for (String fruit : list) {
            System.out.println(fruit);
        }
        System.out.println("Size: " + list.size());
        System.out.println("Is palindrome: " + list.isPalindrome());
        list.remove(2);
        for (String fruit : list) {
            System.out.println(fruit);
        }
        System.out.println("Size: " + list.size());
        System.out.println("Is palindrome: " + list.isPalindrome());
        list.removeFirst("banana");
        for (String fruit : list) {
            System.out.println(fruit);
        }
        System.out.println("Size: " + list.size());
        System.out.println("Is palindrome: " + list.isPalindrome());
        list.remove(2);
        for (String fruit : list) {
            System.out.println(fruit);
        }
        System.out.println("Size: " + list.size());
        System.out.println("Is palindrome: " + list.isPalindrome());
        list.removeLast("nozes");
        for (String fruit : list) {
            System.out.println(fruit);
        }
        System.out.println("Size: " + list.size());
        System.out.println("Is palindrome: " + list.isPalindrome());
        System.out.println("Contains 'banana': " + list.contains("banana"));
        System.out.println("What's the first item'': " + list.get(0));
    }
}
