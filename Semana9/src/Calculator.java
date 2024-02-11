class Calculator {
	// Atributos
	int value;
	
	// Métodos contrutores
	
	// Métodos de instância
	
	void plus(int n) {
		if (n < 0) 
			return;
		
		value += n;
	}
	
	void subtract(int n) {
		if (n < 0) 
			return;
		
		value -= n;
		
		if (value < 0)
			value = 0;
	}
	
	void reset() {
		value = 0;
	}
	
	void multiply(int n) {
		if (n < 0) {
			return;
		}
		int aux = value;
		
		for (int i = 0; i != n - 1; i++) {
			plus(aux);
		}
		
	}
	
	void exponent(int n) {
		if (n < 0) {
			return;
		}
		
		int aux = value;
		
		for (int i = 0; i != n - 1; i++) {
			plus(aux);
		}
	}
	
	void divide(int n) {
		if (n < 0)
			return;
		
		int aux = value;
		
		int i;
		for (i = 0; value >= n; i++) {
			subtract(n);
		}
		
		value = i;
	}
	
	void resto(int n) {
		if (n < 0)
			return;
		
		while (value >= n) {
			subtract(n);
		}
	}
	
	static void test() {
		Calculator calc1 = new Calculator();
		Calculator calc2 = new Calculator();
		return;
	}
}