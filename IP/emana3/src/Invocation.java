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
	
	static boolean isPrime(int n) {
		if (divisor(n) == 2) {
			return true;
		}

        return false;
	}
	
	static int sumPrimesSmallerThan(int n) {
		int count = 0;
		int i = 1;
		
		while (i < n) {
            if (isPrime(n)) {
            	count = count + i;
            }
            i = i + 1;
        }
        return count;
	}
	
	static int countPrimesUpTo(int n) {
        int count = 0;
        int i = 1;
        
        while (i <= n) {
            if (isPrime(i)) {
                count = count + 1;
            }
        }
        return count;
    }
	
	static boolean existsPrimeBetween(int n, int p) {
		int i = n + 1;
		
		while (i < p) {
            if (isPrime(i) == true) {
            	return true;
            }
            i = i + 1;
        }
		return false;
	}
    
	static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
        	return fibonacci(n - 1) + fibonacci(n - 2);	
        }
    }  
	
	 static int factorial(int n) {
		 if (n == 0 || n == 1) {
            return 1;
        } else {
        	return n * factorial(n - 1);
        }
	 }
	 
	 static int gcd(int a, int b) {
	        if (b == 0) {
	            return a;
	        }
	        return gcd(b, a % b);
	    }
	 
	 static int largerDifferenceBetweenPrimes(int n) {
	        int maxDiff = 0;
	        int prevPrime = 2;

	        for (int i = 2; i <= n; i++) {
	            if (isPrime(i)) {
	                if (prevPrime != -1) {
	                    int diff = i - prevPrime;
	                    if (diff > maxDiff) {
	                        maxDiff = diff;
	                    }
	                }
	                prevPrime = i;
	            }
	        }

	        return maxDiff;
	    }
}