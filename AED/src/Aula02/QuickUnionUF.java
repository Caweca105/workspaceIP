package Aula02;

public class QuickUnionUF {
    private int[] id;
    
    public QuickUnionUF(int N) {
        if (N < 0) throw new IllegalArgumentException("Size cannot be negative");

        id = new int[N];
        
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }
    
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
    
    public static void main(String[] args) {
        QuickUnionUF qu = new QuickUnionUF(10); // create an instance with 10 elements

        // Perform some union operations
        qu.union(4, 3);
        qu.union(3, 8);
        qu.union(6, 5);
        qu.union(9, 4);
        qu.union(2, 1);

        // Check if two elements are connected
        boolean connected = qu.connected(8, 9); // true
        System.out.println("8 and 9 connected? " + connected);

        connected = qu.connected(5, 4); // false
        System.out.println("5 and 4 connected? " + connected);

        // Perform another union operation
        qu.union(5, 0);
        qu.union(7, 2);
        qu.union(6, 1);
        qu.union(7, 3);

        // Check again if two elements are connected
        connected = qu.connected(5, 4);
        System.out.println("5 and 4 connected after union? " + connected);
    }
}