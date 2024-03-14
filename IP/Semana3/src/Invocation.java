class Invocation {
	static int divisor(int n) {
		int count = 0;
		int i = 1;
		
		while(i <= n) {
			if (n % i == 0){
				count = count + 1;
			}
			i = i + 1;
		}
		return count;
	}
	
	static int sumDivisor(int n) {
		int acc = 0;
		int i = 1;
		
		while(i < n) {
			if (n % i == 0){
				acc= acc+ i;
			}
			i = i + 1;
		}
		return acc;
	}
	
	static int perfectNumber(int n){
		int count = 0;
		int i = 1;
		
		while(i <= n) {
			if (sumDivisor(i) == i) {
				count = count + 1;
			}
			i = i + 1;
		}
		return count;
	}
}