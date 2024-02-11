class Function {
	static int doubleOf(int x) {
		return x * 2;
	}
	
	static int squareOf(int x) {
		return x * x;
	}
	
	static int differenceOf(int x, int y) {
		return x - y;
	}
	
	static double percentageOf(int n, int total){
		return (double) n / total;
	}
	
	static double median(int x, int y) {
		return (double) (x + y) / 2;
	}
	
	static double roundUp(double x) {
		return (int)(x + 0.5);
	}
	
	static boolean isNegative(int x) {
		return (x < 0);
	}
	
	static boolean notPair(int x) {
		return (x % 2 == 1);
	}
	
	static boolean pairOf(int x) {
		return (x % 2 == 0);
	}
	
	static boolean lessThan(int x, int y) {
		return (x < y);
	}
	
	static boolean numberOf(int x) {
		return (x < 10 && x >= 0);
	}
	
	static boolean isIncluded(int x, int y, int z) {
		return (x < y && x >z);
	}
	
	static boolean isExcluded(int x, int y, int z) {
		return (x > y && x < z);
	}
	
	static boolean comparingOf(int x, int y) {
		return (x % y == 0 && x / y == 0);
	}
	
	static boolean comparing(int x, int y) {
		return (x % y == 0 || x / y <= 0);
	}	
	
	static boolean xor(boolean x, boolean y) {
		return (x && !y) || (!x && y) ;
	}
	
	static boolean isVowel(char c) {
		return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
	}
}