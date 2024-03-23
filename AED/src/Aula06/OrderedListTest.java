import org.junit.Test;
import static org.junit.Assert.*;

public class OrderedListTest {
    
    @Test
    public void testSort() {
        // Test case 1: Sorting an empty array
        Comparable[] arr1 = new Comparable[0];
        OrderedList.sort(arr1);
        assertEquals(0, arr1.length);
        
        // Test case 2: Sorting an array with one element
        Comparable[] arr2 = {5};
        OrderedList.sort(arr2);
        assertEquals(1, arr2.length);
        assertEquals(5, arr2[0]);
        
        // Test case 3: Sorting an array with multiple elements
        Comparable[] arr3 = {9, 2, 7, 4, 1};
        OrderedList.sort(arr3);
        assertEquals(5, arr3.length);
        assertEquals(1, arr3[0]);
        assertEquals(2, arr3[1]);
        assertEquals(4, arr3[2]);
        assertEquals(7, arr3[3]);
        assertEquals(9, arr3[4]);
    }
}