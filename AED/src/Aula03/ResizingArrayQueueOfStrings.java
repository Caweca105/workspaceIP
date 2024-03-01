package Aula03;

public class ResizingArrayQueueOfStrings {
    private String[] queue;
    private int n; // number of elements
    private int first; // index of first element of queue
    private int last; // index of next available slot

    public ResizingArrayQueueOfStrings() {
        queue = new String[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public void enqueue(String item) {
        if (n == queue.length) resize(2 * queue.length); // double size of array if necessary
        queue[last++] = item;
        if (last == queue.length) last = 0; // wrap-around
        n++;
    }

    public String dequeue() {
        if (isEmpty()) throw new IllegalArgumentException("Queue underflow");
        String item = queue[first];
        queue[first] = null; // to avoid loitering
        n--;
        first++;
        if (first == queue.length) first = 0; // wrap-around
        if (n > 0 && n == queue.length/4) resize(queue.length/2);
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        String[] temp = new String[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = queue[(first + i) % queue.length];
        }
        queue = temp;
        first = 0;
        last = n;
    }

    public void shift() {
        if (isEmpty()) return;
        String lastItem = dequeue(); // Remove the last item
        enqueue(lastItem); // Add it back to the front
    }
}
