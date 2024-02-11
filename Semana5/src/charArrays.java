class charArrays {
	static char[] create(char c, int n){
		char[] array = new char[n];
		
		
		for(int i = 0; i < array.length; i++) {
			array[i] = c;
		}
		return array;
	}
	
	static void fill(char c, char[] v){
	    for (int i = 0; i < v.length; i++) {
	    	v[i] = c;
	    }
	}
	
	static void replace(char newChar, char oldChar, char [] v) {
		for (int i = 0; i < v.length; i++) {
			if (v[i] == oldChar) {
				v[i] = newChar;
			}
		}
		
	}
	
	static void replaceFirst(char c, char [] v) {
		for (int i = 0; i < v.length;) {
			v[i] = c; 
			return;
		}
	}
	
	static void replaceLast(char c, char [] v) {
		for (int i = 0; i < v.length - 1;) {
			v[i] = c; 
			return;
		}
	}
	
	// Rodar os seus elementos para a esquerda.
    public static void shiftLeft(char[] v) {
        char temp = v[0];
        for (int i = 0; i < v.length - 1; i++) {
            v[i] = v[i + 1];
        }
        v[v.length - 1] = temp;
    }

    // Rodar os seus elementos para a direita.
    public static void shiftRight(char[] v) {
        char temp = v[v.length - 1];
        for (int i = v.length - 1; i > 0; i--) {
            v[i] = v[i - 1];
        }
        v[0] = temp;
    }
	
	static void swap(int i, int j, char[] v){
	    char temp = v[i];
	    v[i] = v[j];
	    v[j] = temp;
	}
	
	static void invert(char[] v) {
        for (int i = 0; i < v.length / 2; i++) {
            swap(i, v.length - 1 - i, v);
        }
    }
	
	static char[] copy(char[] array) {
        char[] newArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
	
	static char[] subarray(char[] array, int start, int end) {
        char[] sub = new char[end - start + 1];
        for (int i = start, j = 0; i <= end; i++, j++) {
            sub[j] = array[i];
        }
        return sub;
    }
	
	static int randomIndex(int max) {
        return (int)(Math.random() * (max + 1));
    }
	
	static void shuffle(char c, char[] array) {
//        for (int i = array.length - 1; i > 0; i--) {
//            int j = randomIndex(i);
//            swap(i, j, array);
//		}
		int temp = randomIndex(array.length - 1);
		array[temp] = c;
    }
	
	static void bubbleSort(char[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1, array);
                }
            }
        }
    }
	
	static char[] concat(char[] array, char c) {
        char[] newArray = new char[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = c;
        return newArray;
    }
	
	static char[] concat(char[] left, char[] right) {
        char[] newArray = new char[left.length + right.length];
        int i = 0;
        for (; i < left.length; i++) {
            newArray[i] = left[i];
        }
        for (int j = 0; j < right.length; j++, i++) {
            newArray[i] = right[j];
        }
        return newArray;
    }
    
    // Substituir o valor de uma posição aleatória do vetor por um dado caráter c.
    static void replaceRandom(char c, char[] array) {
        int index = randomIndex(array.length - 1);
        array[index] = c;
    }

    // “Trocar as metades” de um vetor de caracteres.
    static void swapHalves(char[] array) {
        int half = array.length / 2;
        for (int i = 0; i < half; i++) {
            swap(i, half + i, array);
        }
    }
}