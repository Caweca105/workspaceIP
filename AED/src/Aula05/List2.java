package Aula05;

import java.util.Iterator;

public class List2<T> implements Iterable<T> {
	
	private Node first;
	private Node last;
	private int size;
	
	public List2(){  
		
		first = null;
		last = null;
		size = 0;
	}
	
	public void add(T item) {
		
		Node oldLast = last;
		last = new Node();
		last.item = item;
		
		if(first == null) {
			first = last;
		}else {
			oldLast.next = last;
			last.previous = oldLast; 
		}	
		
		size++;
	}
	
    public T get(int index) {
    	
    	if (index < 0 || index >= size) throw new IllegalArgumentException("Error: index out of bounds");
    
    	Node pos = first;
    	
    	for (int n = 0; n < index; n++) pos = pos.next;
    	
    	return pos.item;
    }
    
    public T remove(int index){
    	
    	if (index <= 0 || index > size()) throw new IllegalArgumentException("Error: index out of bounds");
    	
    	Node ant = null, pos = first;
    	
    	for (int n = 0; n < index; n++) {
    	ant = pos;
    	pos = pos.next;
    	}
    	
    	if(ant == null) first = first.next;
    	else ant.next = pos.next;
    	
    	if(first == null) last = null;
    	
    	size--;
    	return pos.item;
    }
    
    public boolean removeFirst(T item) {
    	
        Node current = first;
        Node ant = null;

        while (current != null) {
        	
            if (current.item.equals(item)) {
            	
                if (ant == null) {
                	
                    first = current.next;
                    if (first == null) last = null;
                    
                }else{
                	
                    ant.next = current.next;
                    if (current.next != null) current.next.previous = ant;
                    if (current.next == null) last = ant;
                }
                size--;
                return true; 
            }
            ant = current;
            current = current.next;
        }

        return false; 
    }
    
    public boolean removeLast(T item) {
    	
        Node current = last;
        Node pos = null;

        while (current != null) {
        	
            if (current.item.equals(item)) {
            	
                if (pos == null) {
                	
                    first = current.next;
                    if (first == null) last = null;
                    
                }else{
                	
                    pos.next = current.next;
                    if (current.next != null) current.next = pos;
                    if (current.next == null) last = pos;
                }
                size--;
                return true; 
            }
            pos = current;
            current = current.next;
        }

        return false; 
    }
	
    public boolean removeAll(T item) {
    	
        boolean removed = false;

        while (removeFirst(item)) removed = true;

        return removed;
    }
	
	public boolean isEmpty() {
		
		return first == null;
	}
	
	public boolean contains(T item) {
		
		Node current = first;
		
		while(current != null) {
			if(item.equals(current.item)) return true;
			
			current = current.next;
		}
		return false;
	}
	
	public int size() {
		
		return size;
	}
	
	public boolean isPalindrome() {
		
	    if (isEmpty() || size() == 1) return true;
	    
	    Node end = last;
	    Node start = first;
	    
	    while (start != end && start != null && end != null) {
	        if (!start.item.equals(end.item)) return false; 

	        start = start.next;
	        end = end.previous;
	    }

	    return true;
	}
    
	public Iterator<T> iterator() {
		
		return new ListIterator();
	}
	
	private class Node {
		
		public T item;
		public Node next;
		public Node previous;
	}
	
	public static void main(String[] args) {
        List2<String> list = new List2<>();
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
	
	private class ListIterator implements Iterator<T>{

	    private Node current = first;

	    public boolean hasNext() {
	        return current != null;
	    }

	    public T next() {
	    	T item = current.item;
	    	current = current.next;
	        return item;
	    }       	        
	}
}
