class Rational {
	final int num;
	final int den;
	
	Rational(int num) {
		this.num = num;
		den = 1;
	}
	
	Rational(int num, int den) {
		if(den <= 0)
			throw new IllegalArgumentException("Valores positivos no denominador, idiota!");
		
		this.num = num;
		this.den = den;
	}
	
	public String toString() {
		return num + "/" + den;
	}
	
	double value() {
		return (double)num / den;
	}
	
	Rational scale(int factor) {
		if(factor <= 0) {
			throw new IllegalArgumentException("");
		}
		
		return new Rational (num * factor, den);
	} 
	
	Rational multiply(Rational other) {
        return new Rational(this.num * other.num, this.den * other.den);
    }
	
	Rational add(Rational other) {
        int commonDenominator = this.den * other.den;
        int adjustedNumThis = this.num * other.den;
        int adjustedNumOther = other.num * this.den;
        return new Rational(adjustedNumThis + adjustedNumOther, commonDenominator);
    }
	
	boolean isEqual(Rational other) {
	       return this.num * other.den == this.den * other.num;
	}
	
	boolean isGreater(Rational other) {
        return this.num * other.den > this.den * other.num;
    }
	
	boolean isLess(Rational other) {
        return this.num * other.den < this.den * other.num;
    }
	
	static void test() {
		Rational r1 = new Rational(5);
		Rational r2 = new Rational(6, 11);
		Rational r3 = new Rational(9, 8);
	}
}