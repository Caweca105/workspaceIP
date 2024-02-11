class Rectangle {
	//	Atributos
	final int width; // inteiros maiores que 0
	final int height; // inteiros maiores que 0
	
	// Métodos Construtores	
	Rectangle(int width, int height) {
		if(height <= 0 || width <= 0) {
			throw new IllegalArgumentException("Argumentos maiores que 0, não sejais idiota!");
		}
		
		this.width = width;
		this.height = height;		
	}
	
	// Métodos de instância
	int area() {
		return width * height;
	}
	
	int perimeter() {
		return 2 * width + 2 * height;
	}
	
	double diagonal() {
		return Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
	}
	
	boolean isSquare() {
		return (height == width);
	}
	
	Rectangle scale(int factor) {
		if (factor <= 0)
			throw new IllegalArgumentException("Factor maior que 0, idiota!");
		
		return new Rectangle (width * factor, height * factor);
	}
	
	Rectangle sum(int width, int height) {
		return new Rectangle( this.width + width, this.height + height); 
	}
	
	Rectangle sum(Rectangle r) {
		return new Rectangle (width + r.width, height + r.height);
	}
	
	boolean fits(Rectangle r) {
		return width <= r.width && height <= r.height;
	}
	
	
	// Funções Auxiliares
	static Rectangle square(int size) {
		return new Rectangle(size, size);
	}
	
	
	// Função de teste
	static void test(){
		Rectangle r1 = new Rectangle(8, 12);
		Rectangle r2 = new Rectangle (5, 2);
		Rectangle r3 = new Rectangle (6, 7);
		
		int aux = r3.area();
		Rectangle r4 = r3.sum(r2);
		Rectangle r5 = r2.sum(r3);
		
		boolean b1 = r4.fits(r3);
		boolean b2 = r3.fits(r4);
		
		return;
	}
}