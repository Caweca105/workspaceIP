package Aula02;

public class DoublingHypothesis {

	private static final long LIMIT = 128000; //250*2^9
	private static final int AVERAGE_OF = 5;
	
	public static double getLapsedTimeMillis(int N) {
		
		QuickFindUF uf = new QuickFindUF (N);
		double start = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			int p = randint(0, N-1);
			int q = randint(0, N-1);
			uf.union(p,q);
		}
		double end = System.currentTimeMillis();
		return end-start;	
	}
	
	public static int randint (int start, int end){
		return (int) (start+(end-start)*Math.random());
	}
	
	public static double getAverageTimeMillis (int N, int trials) {
		double sum = 0;

		for (int i = 0; i < trials; i++) {
            sum+=getLapsedTimeMillis(N);
        }
		return sum/trials;
	}
	
	public static double millisecondsToSeconds (double millis) {
		return millis/1000;
	}
	
	private static void applyDoubleHypothesis () {
		double previous = millisecondsToSeconds(getAverageTimeMillis(125, AVERAGE_OF));
		double lgratio = 0;
		
		System.out.println("Double Hypothesis\n");
		System.out.println("N\t\tT(N)\t\tratio\t\tlg(ratio)");
		
		for (int n=250; n<=LIMIT; n+=n) {
			double time = millisecondsToSeconds(getAverageTimeMillis(n, AVERAGE_OF));
			double ratio = time/previous;
			lgratio = Math.log(ratio)/Math.log(2);
			System.out.printf("%d\t\t%.3f\t\t%.3f\t\t%.3f\n", n, time, ratio, lgratio);
			previous = time;
		}
		double b = lgratio;
		System.out.println("b="+b);
	}	
	
	public static void main(String[] args) {
		applyDoubleHypothesis();
	}
}