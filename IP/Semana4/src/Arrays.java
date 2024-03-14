class Arrays {
	static int[] naturals(int n) {
		int[] array = new int[n];
		int i = 0;
		
		for(i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}
		return array;
	}
	
	static int sum(int[] v) {
        int total = 0;
        int i = 0;
        while (i < v.length) {
            int num = v[i];
            total += num;
            i++;
        }
        return total;
    }

    static int[] randomDigits(int size) {
        int[] array = new int[size];
        int i = 0;
        
        while (i < array.length) {
            array[i] = (int)(Math.random() * 10);
            i++;
        }
        
        return array;
    }
    
    static double mean(int[] v) {
        // Verificar se o vetor está vazio para evitar divisão por zero
        if (v.length == 0) {
            return 0;
        }
        
        return (double) sum(v) / v.length;
    }
    
    static int[] copy(int[] v, int size) {
        // Iniciar o novo vetor com o tamanho especificado
        int[] newArray = new int[size];
        
        // Iniciar a variável do índice 0
        int i = 0;
        
        // Copiar os elementos do vetor original para o novo vetor
        while (i < v.length && i < newArray.length) {
            newArray[i] = v[i];
            i++;
        }
        
        // Os espaços restantes no novo vetor serão automaticamente preenchidos com zeros
        // devido ao inicio padrão de arrays de inteiros em Java.
        return newArray;
    }
    
    static int[] copy(int[] v) {
        return copy(v, v.length);
    }
    
    static boolean exists(int n, int[] v) {
        // Iniciar a variável do índice
        int i = 0;
        
        // Usar um loop while para verificar cada elemento do vetor
        while (i < v.length) {
            if (v[i] == n) {
                return true;  // O número foi encontrado, retornar true
            }
            i++;
        }
        
        return false;  // O número não foi encontrado, retornar false
    }

    static int count(int n, int[] v) {
        // Iniciar o contador de ocorrências
        int count = 0;
        
        // Iniciar a variável do índice
        int i = 0;
        
        // Usar um loop while para verificar cada elemento do vetor
        while (i < v.length) {
            if (v[i] == n) {
                count++;  // Incrementar o contador se encontrar uma ocorrência
            }
            i++;
        }
        
        return count;  // Retornar o número total de ocorrências
    }
    
    static int max(int[] v) {
        // Iniciar a variável para guardar o valor máximo com o primeiro elemento do vetor
        int maxValue = v[0];
        
        // Iniciar a variável do índice
        int i = 1;
        
        // Usar um loop while para percorrer o vetor
        while (i < v.length) {
            if (v[i] > maxValue) {
                maxValue = v[i];  // Atualizar o valor máximo se encontrar um valor maior
            }
            i++;
        }
        
        return maxValue;  // Retornar o valor máximo encontrado
    }

    static int[] subArray(int start, int end, int[] v) {
        // Verificar a validade dos índices
        if (start < 0 || end >= v.length || start > end) {
            return new int[0];  // Devolve um vetor vazio se os índices não forem válidos
        }
        
        // Determinar o tamanho do novo sub-vetor
        int length = end - start + 1;
        
        // Iniciar o novo sub-vetor
        int[] subArray = new int[length];
        
        // Iniciar as variáveis do índice
        int i = start;
        int j = 0;
        
        // Usar um loop while para copiar os elementos
        while (i <= end) {
            subArray[j] = v[i];
            i++;
            j++;
        }
        
        return subArray;
    }
    
    static int[] firstHalf(int[] v, boolean includeMiddle) {
        // Determinar o tamanho do vetor original
        int length = v.length;
        
        // Determinar o tamanho da primeira metade
        int halfLength;
        if (includeMiddle && length % 2 != 0) {
            halfLength = (length + 1) / 2;
        } else {
            halfLength = length / 2;
        }
        
        // Iniciar o novo vetor
        int[] firstHalfArray = new int[halfLength];
        
        // Copiar os elementos do vetor original para o novo vetor
        int i = 0;
        int j = 0;
        while (j < halfLength) {
            firstHalfArray[j] = v[i];
            i++;
            j++;
        }
        
        return firstHalfArray;
    }

    static int[] secondHalf(int[] v, boolean includeMiddle) {
        // Determinar o tamanho do vetor original
        int length = v.length;
        
        // Determinar o ponto de início da segunda metade
        int startIndex;
        if (includeMiddle && length % 2 != 0) {
            startIndex = (length - 1) / 2;
        } else {
            startIndex = length / 2;
        }
        
        // Determinar o tamanho da segunda metade
        int secondHalfLength = length - startIndex;
        
        // Iniciar o novo vetor
        int[] secondHalfArray = new int[secondHalfLength];
        
        // Copiar os elementos do vetor original para o novo vetor
        int i = startIndex;
        int j = 0;
        while (i < length) {
            secondHalfArray[j] = v[i];
            i++;
            j++;
        }
        
        return secondHalfArray;
    }

    static int[] merge(int[] left, int[] right) {
        // Determinar os tamanhos dos vetores left e right
        int leftLength = left.length;
        int rightLength = right.length;
        
        // Iniciar o novo vetor com o tamanho combinado
        int[] mergedArray = new int[leftLength + rightLength];
        
        // Copiar os elementos do vetor left
        int i = 0;
        int j = 0;
        while (i < leftLength) {
            mergedArray[j] = left[i];
            i++;
            j++;
        }
        
        // Copiar os elementos do vetor right
        i = 0;
        while (i < rightLength) {
            mergedArray[j] = right[i];
            i++;
            j++;
        }
        
        return mergedArray;
    }

    static int[] invert(int[] original) {
        // Determinar o tamanho do vetor original
        int length = original.length;
        
        // Iniciar o novo vetor
        int[] invertedArray = new int[length];
        
        // Copiar os elementos do vetor original para o novo vetor de forma invertida
        int i = length - 1;
        int j = 0;
        while (i >= 0) {
            invertedArray[j] = original[i];
            i--;
            j++;
        }
        
        return invertedArray;
    }

    static int randomElement(int[] array) {
        // Gerar um índice aleatório entre 0 e o tamanho do vetor - 1
        int randomIndex = (int) (Math.random() * array.length);
        
        // Retornar o elemento no índice aleatório
        return array[randomIndex];
    }

    static int[] duplicateInverted(int[] original) {
        // Determinar o tamanho do vetor original
        int length = original.length;

        // Iniciar o novo vetor com o dobro do tamanho do vetor original
        int[] duplicatedInvertedArray = new int[length * 2];

        // Copiar os elementos do vetor original para a primeira metade do novo vetor
        int i = 0;
        int j = 0;
        while (i < length) {
            duplicatedInvertedArray[j] = original[i];
            i++;
            j++;
        }

        // Copiar os elementos do vetor original para a segunda metade do novo vetor, de forma invertida
        i = length - 1;
        while (i >= 0) {
            duplicatedInvertedArray[j] = original[i];
            i--;
            j++;
        }

        return duplicatedInvertedArray;
    }

    static int[] copyWithoutMiddleElement(int[] original) {
        int size = original.length;
        int newSize= (size% 2 == 1) ? size - 1 : size;
        int[] newArray = new int[newSize];

        int middleIndex = size / 2;
        int i = 0, j = 0;

        while (i < size) {
        	if (i != middleIndex || size % 2 == 0) {
                newArray[j] = original[i];
                j++;
            }
            i++;
        }

        return newArray;
    }

    static int[] fibonacciSequence(int n) {
        // Iniciar o vetor com tamanho n
        int[] fibonacciArray = new int[n];

        // Preencher os dois primeiros elementos, se possível
        if (n >= 1) {
            fibonacciArray[0] = 0;
        }
        if (n >= 2) {
            fibonacciArray[1] = 1;
        }

        // Preencher o resto do vetor
        int i = 2;
        while (i < n) {
            fibonacciArray[i] = fibonacciArray[i - 1] + fibonacciArray[i - 2];
            i++;
        }

        return fibonacciArray;
    }
}