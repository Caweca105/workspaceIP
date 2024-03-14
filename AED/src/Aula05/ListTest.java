package Aula05;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListTest {
    
    @Test
    public void testAddAndGet() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }
    
    @Test
    public void testRemove() {
        List<String> list = new List<>();
        list.add("apple");
        list.add("banana");
        list.add("orange");
        
        assertEquals("banana", list.remove(1));
        assertEquals(2, list.size());
        assertFalse(list.contains("banana"));
    }
    
    @Test
    public void testRemoveFirst() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        
        assertTrue(list.removeFirst(2));
        assertEquals(3, list.size());
        assertFalse(list.contains(2));
    }
    
    @Test
    public void testRemoveLast() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        
        assertTrue(list.removeLast(2));
        assertEquals(3, list.size());
        assertFalse(list.contains(2));
    }
    
    @Test
    public void testRemoveAll() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        
        assertTrue(list.removeAll(2));
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }
    
    @Test
    public void testIsEmpty() {
        List<String> list = new List<>();
        assertTrue(list.isEmpty());
        
        list.add("apple");
        assertFalse(list.isEmpty());
        
        list.remove(0);
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void testSize() {
        List<String> list = new List<>();
        assertEquals(0, list.size());
        
        list.add("apple");
        list.add("banana");
        list.add("orange");
        assertEquals(3, list.size());
        
        list.remove(1);
        assertEquals(2, list.size());
    }
    
    @Test
    public void testIsPalindrome() {
        List<Character> palindrome = new List<>();
        palindrome.add('r');
        palindrome.add('a');
        palindrome.add('c');
        palindrome.add('e');
        palindrome.add('c');
        palindrome.add('a');
        palindrome.add('r');
        
        assertTrue(palindrome.isPalindrome());
        
        List<Character> notPalindrome = new List<>();
        notPalindrome.add('n');
        notPalindrome.add('o');
        notPalindrome.add('t');
        notPalindrome.add('p');
        notPalindrome.add('a');
        notPalindrome.add('l');
        notPalindrome.add('i');
        notPalindrome.add('n');
        notPalindrome.add('d');
        notPalindrome.add('r');
        notPalindrome.add('o');
        notPalindrome.add('m');
        notPalindrome.add('e');
        
        assertFalse(notPalindrome.isPalindrome());
    }
    
    @Test
    public void testIterator() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        
        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num);
        }
        
        assertEquals("123", sb.toString());
    }
}