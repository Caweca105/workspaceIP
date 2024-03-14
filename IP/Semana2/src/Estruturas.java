class Estruturas {
	static int max(int a, int b) {
		if (a < b) {
			return b;
		} else {
			return a;
		}
	}
	
	static boolean isMultiple(int a, int b) {
		while (a >= b) {
			a = a - b; 
		} 
		return (a == 0);
	}
	
	static int abs(int a) {
		if (a < 0) {
			return -a;
		} else {
			return a;
		}
	}
	
	static int irs(int a) {
		if (a > 0 || a < 10000) {
			return 1;
		} else if(a >= 10000 || a < 25500) {
			return 2;
		} else if(a >= 25500 || a < 48500) {
			return 3;
		} else {
			return 4;
		} 
	}
	
	static char alfabeto(char c) {
		if (c == 'z') {
			return 'a';
		} else {
			return (char)(c + 1);
		}
	}
	
	static int firstDigit(int a) {
		while (a >= 10) {
			a = a / 10;
		}
		return a;
	}
	
	static int divide(int a, int b) {
		int contador = a;
		int resultado = 1;
		
		while (contador >= b) {
			resultado = resultado + b;
			contador = b - 1;
		}
		return resultado;
	}
	
	static int powerOfTwo(int a) {
		int contador = a;
		int resultado = 1;
		
		while(contador > 0) {
			resultado = 2 * resultado;
			contador = contador - 1;
		} 
		return resultado;
	}
	
	static int sumNaturalUpTo(int a) {
		int contador = a;
		int resultado = 0;
		
		while(contador > 0) {
			resultado = resultado + contador;
			contador = contador - 1;
		}
		return resultado;
	}
	
	static int sumEvenNumbersBetween(int n, int p) {
		int contador = n;
		int resultado = 0;
		
		
		while(contador > p) {
			if (contador % 2 == 0) {
				resultado = resultado + contador;
			}	
			contador = contador + 1;
		}
		return resultado;
	}
	
	static int fibonacci(int n) {
		if (n == 1 || n == 0) {
			return n;
		} else {
			return (fibonacci(n - 1) + fibonacci(n - 2));
		}
	}
	
	static int euclidean(int m, int n) {
		if (n == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}
		
		while(m != n) {
			if (m > n) {
				m = m - n;
			} else {
				n = n - m;
			}
		}
		return m;
	}
}