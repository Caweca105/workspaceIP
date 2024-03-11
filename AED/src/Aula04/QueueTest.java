package Aula04;
import java.util.NoSuchElementException;

public class QueueTest {
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