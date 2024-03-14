package Aula05;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

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

    public List() {
        first = last = null;
        size = 0;
    }

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

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

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

    public boolean removeAll(T item) {
        boolean removed = false;
        while (removeFirst(item)) {
            removed = true;
        }
        return removed;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T item) {
        for (Node current = first; current != null; current = current.next) {
            if (current.item.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

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
}
