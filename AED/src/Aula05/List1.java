import java.util.Iterator;

public class List<T> implements Iterable<T> {

	private class Node {
		public T item;
		public Node next;
		public Node previous;
	}

	private Node first;
	private Node last;
	private int size;

	public List() {
		first = null;
		last = null;
		size = 0;
	}

	public void add(T item) {
		Node oldLast = last;

		last = new Node();
		last.item = item;

		if (first == null) {
			first = last;
		} else {
			oldLast.next = last;
			last.previous = oldLast;// diferença
		}
		size++;
	}

	public boolean contains(T item) {
		Node current = first;
		while (current != null) {
			if (item.equals(current.item)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public boolean isPalindrome() {
		// Node current = first;
		while (first != null && last != null) {
			if (!first.item.equals(last.item)) {
				return false;
			}
			first = first.next;
			last = last.previous;
		}
		return true;
	}

	public T get(int index) {
		if (index < 0 || index > size())
			throw new IllegalArgumentException("O Index é Excedido");

		Node oldLast = first;
		for (int n = 1; n != index; n++)
			oldLast = oldLast.next;
		return oldLast.item;
	}

	public T remove(int index) {
		if (index <= 0 || index > size())
			throw new IllegalArgumentException("O Index é Excedido");
		Node ant = null, pos = first;
		for (int n = 1; n != index; n++) {
			ant = pos;
			pos = pos.next;
		}
		if (ant == null)
			first = first.next;
		else
			ant.next = pos.next;
		if (first == null)
			last = null;
		return pos.item;

	}

	public boolean removeFirst(T item) {
		Node current = last;
		while (current != null) {
Node oldLast = null;
if (item.equals(current.item)) {
    if (oldLast == null) {
        first = current.next;
    } else {
        oldLast.next = current.next;
        if (current.next == null) {
            last = oldLast;
        }
    }
    size--;
    return true;
}

oldLast = current;
current = current.next;
		}
		return false;
	}

	public boolean removeLast(T item) { // SUS DE PROBLEMAS oldlast
		Node current = first, oldlast = null, lastNode = null;
		while (current != null) {
			if (item.equals(current.item)) {
				lastNode = current;
			}
			oldlast = current;
			current = current.next;
		}
		if (lastNode != null) {
			if (oldlast == null) {
				first = lastNode.next;
			} else {
				oldlast.next = lastNode.next;
				if (lastNode.next == null) {
					last = oldlast;
				}
			}
			size--;
			return true;
		}
		return false;
	}

	public boolean removeAll(T item) {
		boolean removed = false;
		Node current = first, oldlast = null;
		while (current != null) {
			if (item.equals(current.item)) {
				if (oldlast == null) {
					first = current.next;
					current = first;
				} else {
					oldlast.next = current.next;
					current = current.next;
				}
				if (current == null) {
					last = oldlast;
				}
				size--;
				removed = true;
			} else {
				oldlast = current;
				current = current.next;
			}
		}
		return removed;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<T> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T item = current.item;
			current = current.next;
			return item;
		}
	}
}