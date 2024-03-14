class IntMatrices {
	static int[][] createRandomMatrix(int lines, int columns) {
        int[][] matrix = new int[lines][columns];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (int)(Math.random() * 10);
            }
        }
        return matrix;
    }

    static int[][] createRandomSquareMatrix(int n) {
        return createRandomMatrix(n, n);
    }

    static int sumMatrix(int[][] m) {
        int sum = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                sum += m[i][j];
            }
        }
        return sum;
    }

    static int numElements(int[][] m) {
        int count = 0;
        for (int i = 0; i < m.length; i++) {
            count += m[i].length;
        }
        return count;
    }

    static int[] unroll(int[][] m) {
        int[] array = new int[numElements(m)];
        int index = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                array[index++] = m[i][j];
            }
        }
        return array;
    }

    static int[][] createMatrix(int[] values, int lines, int columns) {
        int[][] matrix = new int[lines][columns];
        int index = 0;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = values[index++];
            }
        }
        return matrix;
    }
}