public class Algebra {

    // Criar uma matriz identidade de tamanho n.
    public static int[][] createIdentity(int order) {
        int[][] identity = new int[order][order];
        for (int i = 0; i < order; i++) {
            identity[i][i] = 1;
        }
        return identity;
    }

    // Multiplicar uma matriz por um escalar (modificando-a).
    public static void scalarMultiplication(int[][] m, int c) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] *= c;
            }
        }
    }

    // Verificar se uma matriz é uma matriz identidade.
    public static boolean isIdentity(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if ((i == j && m[i][j] != 1) || (i != j && m[i][j] != 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Adição de duas matrizes (a primeira matriz é modificada).
    public static void addition(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] += b[i][j];
            }
        }
    }

    // Verificar se todas as linhas têm o mesmo comprimento.
    public static boolean isValid(int[][] m) {
        int len = m[0].length;
        for (int i = 1; i < m.length; i++) {
            if (m[i].length != len) {
                return false;
            }
        }
        return true;
    }

    // Verificar se a matriz é quadrada.
    public static boolean isSquare(int[][] m) {
        return (isValid(m) && m.length == m[0].length);
    }

    // Verificar se duas matrizes são iguais.
    public static boolean areEqual(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Obter uma coluna da matriz como um vetor.
    public static int[] getColumn(int[][] m, int colIndex) {
        int[] col = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            col[i] = m[i][colIndex];
        }
        return col;
    }

    // Obter a matriz transposta de uma matriz.
    public static int[][] transpose(int[][] m) {
        int[][] transposed = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                transposed[j][i] = m[i][j];
            }
        }
        return transposed;
    }

    // Verificar se uma matriz é simétrica.
    public static boolean isSymmetric(int[][] m) {
        if (!isSquare(m)) {
            return false;
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i][j] != m[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Exercício extra: multiplicação de matrizes.
    public static int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            return null;
        }

        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }
}

class TI_2223 {
	static double PercentageofDiscount(int n){
		if(n<10)
			return 0.0;
		if(n>9 && n<20)
			return 0.05;
		else
			return 0.10;
	}
	
	static double TotalPrice(double price, int n){
		return (n * (1 - PercentageofDiscount(n)) * price);
	}
	
	static void ModifyVector(char[] v){
		for(int i=0;i<v.length;i++){
			if(v[i]>='0' && v[i]<='9')
				v[i]='?';
			if(v[i]>='a' && v[i]<='z'||v[i]>='A' && v[i]<='Z')
				v[i]='*';
		}
	}		
	static int[] FirstHalf(int[] v,boolean first){
		int size = (int)(v.length/2.0+0.5);
		int[] u = new int[size];
		if (first)
			for(int i=0;i<size;i++)
				u[i]=v[i];
		else 
			for(int j=0;j<size;j++)
				u[size-1-j]=v[v.length-1-j];
		return u;
	}	
	
	static int [] count (int n, int[][] m) {
		int count=0;
		int[] v = new int[m.length];
		for (int i=0;i<m.length;i++){
			if(i!=0)
			v[i-1]=count;
			count=0;
			for(int j=0;j<m[i].length;j++){
				if (m[i][j]== n){
					count++;
				}
			}
		}
		return v;
	}
}
					
				
	
	
	
	
	
	
	
	
	
	
	
	
