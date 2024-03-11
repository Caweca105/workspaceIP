package Aula04;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {
	private Node first;
	private Node last;
	private int n;

	private class Node {
		public T item;
		public Node next;
	}

	public Queue() {
		first = null;
		last = null;
		n = 0;
	}

	public void enqueue(T item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldLast.next = last;
		n++;
	}

	public T dequeue() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		T item = first.item;
		first = first.next;
		n--;
		if (isEmpty()) last = null;
		return item;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return n;
	}

	public void shift() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		Node oldLast = last;
		last = new Node();
		last.item = oldLast.item;
		last.next = first;
		first = last;
	}

	public Iterator<T> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<T> {
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
	}
	public static void main(String[] args) {
		// Create a new queue
		Queue<Integer> queue = new Queue<>();

		// Test enqueue and size
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		System.out.println("Size: " + queue.size()); // Expected output: 3

		// Test dequeue
		System.out.println(queue.dequeue()); // Expected output: 1
		System.out.println(queue.dequeue()); // Expected output: 2
		System.out.println(queue.dequeue()); // Expected output: 3

		// Test isEmpty
		System.out.println(queue.isEmpty()); // Expected output: true

		// Test dequeue on empty queue
		try {
			queue.dequeue();
		} catch (NoSuchElementException e) {
			System.out.println("Caught exception: " + e.getMessage()); // Expected output: Caught exception: Queue underflow
		}

		// Test shift
		queue.enqueue(4);
		queue.enqueue(5);
		queue.shift();
		System.out.println(queue.dequeue()); // Expected output: 5
		System.out.println(queue.dequeue()); // Expected output: 4
	}
}