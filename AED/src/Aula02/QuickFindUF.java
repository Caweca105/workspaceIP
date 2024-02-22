package Aula02;

public class QuickFindUF {
    private int[] id;
    
    public QuickFindUF(int N) {
        id = new int[N];

        if (N < 0) throw new IllegalArgumentException("");
        
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }
    
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < id.length; i++) {
            s += id[i] + " ";
        }
        return s;
    }

    public static void main(String[] args) {
        QuickFindUF qf = new QuickFindUF(7);
        qf.union(4, 3);
        qf.union(3, 8);
        qf.union(6, 5);
        qf.union(9, 4);
        qf.union(2, 1);
        qf.union(5, 0);
        qf.union(7, 2);
        qf.union(6, 1);
        System.out.println(qf);
    }
}
