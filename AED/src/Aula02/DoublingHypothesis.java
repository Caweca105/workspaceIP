package Aula02;

public class DoublingHypothesis {
    private static final long LIMIT = 128000;
    private static final int AVERAGE_OF = 5;

    public static double getElapsedTimeMillis(int N) {
        QuickFindUF qf = new QuickFindUF(N);
        long start = System.currentTimeMillis();
        for (int i = 0; i < LIMIT; i++) {
            qf.union(0, N-1);
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }

    public static int randInt(int start, int end) {
        return start + (int) (Math.random() * (end - start + 1));
    }

    public static double getAverageTimeMillis(int N, int trials) {
        double time = 0;
        for (int i = 0; i < trials; i++) {
            time += getElapsedTimeMillis(N);
        }
        return time / trials;
    }

    public static double millisecondsToSeconds(double time) {
        return time / 1000;
    }

    private static void applyDoubleHypothesis() {
        double previous = millisecondsToSeconds(getAverageTimeMillis(125, AVERAGE_OF));

        double lgRatio = 0;

        System.out.println("N\tTime\tRatio\tlgRatio");

        for (int N = 250; true; N += N) {
            double time = millisecondsToSeconds(getAverageTimeMillis(N, AVERAGE_OF));
            double ratio = time / previous;
            lgRatio = Math.log(ratio) / Math.log(2);
            System.out.printf("%d\t%.3f\t%.3f\t%.3f\n", N, time, ratio, lgRatio);
            previous = time;
        }
    }

    public static void main(String[] args) {
        for (int N = 250; true; N += N) {
            double time = 0;
            for (int i = 0; i < AVERAGE_OF; i++) {
                time += getElapsedTimeMillis(N);
            }
            time /= AVERAGE_OF;
            System.out.println(N + " " + time);
        }
    }
}
