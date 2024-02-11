//Classe que cont�m fun��es e procedimentos respons�veis pela cria��o, manipula��o e a 
//visualiza��o de uma imagem do tabuleiro
public class SudokuAux {
//1--------------------------------------------------------------------------------------
	//Fun��o respons�vel por verificar os resultados das fun��es que verificam as 
	//linhas, as colunas e os blocos. 
    public static boolean ValidSudoku(int[][] sudoku){
    	//Valida��o das colunas, das linhas e dos blocos 
    	return ValidXandY(sudoku) && ValidBlocks(sudoku);
    }
    //Fun��o respons�vel por verificar os n�meros das linhas da matriz 
    private static boolean ValidXandY(int[][] sudoku){
    	//Ciclo que percorre as linhas todas do matriz
    	for (int i = 0; i < 9; i++){
    		//Cria um novo array 
        	int[] columns = new int[9];
        	//Percorre as colunas da matriz
            for (int a = 0; a < 9; a++){
            	//Guarda os valores da matriz no array
                columns[a] = sudoku[a][i];
            }
    		//Valida se as linhas e as colunas s�o v�lidas
            if (!ValidGroup(sudoku[i]) || !ValidGroup(columns)){
            	//Retorna false se forem encontrados erros
            	return false;
            }
    	}
    	//Retorna true caso n�o tenham sido encontrados erros
        return true;
    }
    //Fun��o respons�vel por verificar os blocos da matriz
    public static boolean ValidBlocks(int[][] sudoku){
    	//Percorre os blocos da matriz
    	for (int i = 0; i < 9; i += 3){
    		//Cria um novo array para guardar os blocos 
    		int[] block = new int[9];
    		//Percorre os blocos da matriz 
    		for (int j = 0; j < 9; j += 3){
    			//Index dos blocos
                int index = 0;
                //Percorre as linhas da matriz
                for (int x = i; x < i + 3; x++){
                	//Percorre as colunas da matriz
                	for (int y = j; y < j + 3; y++){
                		//guarda o bloco no array
                        block[index++] = sudoku[x][y];
                    }
                }
                //Valida��o do bloco 
                if (!ValidGroup(block)){
                    //retorna falso se o bloco for inv�lido
                	return false;
                }
            }
        }
    	//Retorna true caso n�o sejam encontrados erros
        return true;
    }
    //Fun��o respons�vel por verificar os n�meros das linhas da matriz 
    public static int [] NumX(int[][] sudoku){
    	int[] lines = new int[9];
    	//Ciclo que percorre as linhas todas do matriz
    	for (int i = 0; i < 9; i++){
    		//Valida se as linhas e as colunas s�o v�lidas
            if (!ValidGroup(sudoku[i])){
            	//Retorna false se forem encontrados erros
            	lines[i]=1;
            }
    	}
    	//Retorna true caso n�o tenham sido encontrados erros
        return lines;
    }
    //Fun��o respons�vel por verificar os n�meros das linhas da matriz 
    public static int [] NumY(int[][] sudoku){
    	int[] InvCol = new int[9];
    	int [] columns = new int[9];
    	//Ciclo que percorre as linhas todas do matriz
    	for (int i = 0; i < 9; i++){	
        	//Percorre as colunas da matriz
            for (int a = 0; a < 9; a++){
            	//Guarda os valores da matriz no array
                columns[a] = sudoku[a][i];
            }
    		//Valida se as linhas e as colunas s�o v�lidas
            if (!ValidGroup(columns)){
            	//Retorna false se forem encontrados erros
            	InvCol[i]=1;
            }
    	}
    	//Retorna true caso n�o tenham sido encontrados erros
        return InvCol;
    }
    //Fun��o respons�vel por verificar os n�meros das linhas da matriz 
//    public static int [] NumBlk(int[][] sudoku){
//
//    }
    //Fun��o respons�vel por validar os digitos
    public static boolean ValidGroup(int[] grupo) {
    	//Cria um array de booleans
    	boolean[] validation = new boolean[9];
    	//Percorre todo o grupo
        for (int i = 0; i < grupo.length; i++) {
        	//Nova vari�vel para armazenar os valores do grupo
        	int value = grupo[i];
        	//Valida��o para verificar se o campo � nulo
            if (value != 0) {
            	//Verifica se ja existe o numero no array
            	if (validation[value - 1]) {
            		//Retorna false caso seja encontrado
            		return false;
            	}
            	//retorna true caso o numero seja v�lido
            	validation[value - 1] = true;
            }
        }
        //Retorna true caso seja tudo valido 
        return true;
    }
//2--------------------------------------------------------------------------------------
    //Fun��o respons�vel por gerar um jogo de sudoku
    public static int[][] GenerateBoard(int[][] sudoku, double p){
    	//Atribui o valor m�ximo da matriz
        int Cel = 81;
        //Guarda o n�mero de espa�os que vai por nulo
        int ZeroCel = (int) (p * Cel); 
        //Introdu��o de um ciclo para validar se ainda existem vagas para retirar numeros 
        while (ZeroCel > 0){
        	//Escolha aleat�ria da linha
        	int linha = (int) (Math.random() * 9);
        	//Escolha aleat�ria da coluna
        	int coluna = (int) (Math.random() * 9);
        	//Valida��o para n�o ser escolhido um espa�o j� nulo
            if (sudoku[linha][coluna] != 0) {
            	//P�e o espa�o igual a 0
            	sudoku[linha][coluna] = 0;
            	//Diminui o numero de espa�os para ver se � possivel repetir o ciclo
            	ZeroCel--;
            }
        }
        //Retorna o novo jogo com os espa�os em branco
        return sudoku;
    }
//3--------------------------------------------------------------------------------------
    //Fun��o respons�vel por converter inteiros em strings
    public static String ConvertToString(int[][] sudokuGame){
    	//Nova vari�vel do tipo string vazia 
    	String result = "";
    	//Percorre as linhas do sudoku 
        for (int i = 0; i < sudokuGame.length; i++){
        	//Percorre as colunas do sudoku
        	for (int j = 0; j < sudokuGame[i].length; j++){
        		//guarda o numero na vari�vel
        		result += sudokuGame[i][j] + " ";
        	}
        	//D� um enter na matriz de strings
        	result += "\n";
        }
        //Retorna a matriz de strings
        return result;
    }
//4--------------------------------------------------------------------------------------
	//Fun��o respons�vel por desenhar a matriz
	static ColorImage drawMatrix(){
		//Cria a cor White(Branco)
		Color White = new Color (255, 255, 255);
		//Cria a cor Black(Preto)
		Color Black = new Color (0 ,0 ,0);
		//Cria um quadrado com a cor preta
		ColorImage square = new ColorImage (405, 405, White);
		//Inicia um ciclo for para percorrer os pixeis todos do quadrado
		for ( int x = 0 ; x<405 ; x++){
			//Linha de cima
			square.setColor(x,0,Black);
			square.setColor(x,1,Black);
			square.setColor(x,2,Black);
			//Linha da esquerda
			square.setColor(0,x,Black);
			square.setColor(1,x,Black);
			square.setColor(2,x,Black);
			//Linha de baixo
			square.setColor(x,402,Black);
			square.setColor(x,403,Black);
			square.setColor(x,404,Black);
			//Linha da direita
			square.setColor(402,x,Black);
			square.setColor(403,x,Black);
			square.setColor(404,x,Black);
			//1� linha horizontal
			square.setColor(x,133,Black);
			square.setColor(x,134,Black);
			square.setColor(x,135,Black);
			//1� linha vertical na matriz 
			square.setColor(133,x,Black);
			square.setColor(134,x,Black);
			square.setColor(135,x,Black);
			//2� linha horizontal na matriz 
			square.setColor(x,268,Black);
			square.setColor(x,269,Black);
			square.setColor(x,270,Black);
			//2� linha vertical na matriz 
			square.setColor(268,x,Black);
			square.setColor(269,x,Black);
			square.setColor(270,x,Black);
			//Linhas verticais e horizontais de dentro do quadrado para constru�r a 
			//matriz 9x9
			for ( int y = 45 ; y<405 ; y=y+45){
				//linhas horizontais
				square.setColor(x,y,Black);
				//linhas verticais
				square.setColor(y,x,Black);
			}
		}
		return square;
	}	
//---------------------------------------------------------------------------------------
	//Fun��o respons�vel por inserir os n�meros na matriz
	public static ColorImage InsertImg(ColorImage square, int [][] sudoku){
		//Cria a cor Black(Preto)
		Color Black = new Color (0 ,0 ,0);
		//Percorre as linhas
		for(int x=0; x<9; x++){
			//Percorre as colunas
			for(int y=0; y<9; y++){
				//Verifica os valores que n�o s�o nulos 
				if(sudoku[x][y] !=0){
					//desenha no jogo atraves do drawCenterText
					square.drawCenteredText(23+(45*x), 23+(45*y),
							""+sudoku[x][y],40,Black);
				}
			}
		}
		return square;
	}	
//5--------------------------------------------------------------------------------------
	//Fun��o respons�vel por inserir os n�meros na matriz
	public static ColorImage ChangeImg(ColorImage square, int [][] sudoku, int num, int i, int j){
		//Percorre as linhas
		for(int x=0; x<9; x++){
			//Percorre as colunas
			for(int y=0; y<9; y++){
				//Verifica os valores que n�o s�o nulos 
				if((j==x) && (i==y)){
					//Altera a posi��o da matriz com n�mero dado
					sudoku[x][y]=num;
					//Atualiza a imagem do sudoku 
					InsertImg(square,sudoku);
				}
			}
		}
		//Retorna a imagem com o texto
		return square;
	}
//6--------------------------------------------------------------------------------------
	//Fun��o respons�vel por circundar a vermelho as linhas
	public static ColorImage RedAreaLine(ColorImage square){
			//Cria a cor Red(Vermelho)
			Color Red = new Color (255 ,0 ,0);
			//Escolha da linha para realizar testes-------------
			int line=4;
			//SSQ -- Tamanho do quadrado 
			int SSQ=45;
			//Espa�o para ser removido para a linha 9 
			int rem=3;
			//x1  -- Ponto inicial das linhas horizontais
			int x1 = (line)*SSQ-(SSQ);
			//x2  -- Ponto inicial das linhas horizontais
			int x2 = (line)*SSQ-1;
			//Ajustes para os contornos da primeira linha devido � moldura
			if(line==1){x1=x1+2;}
			//Ajustes para os contornos da ultima linha devido � moldura
			if(line==9){x2=x2-3; rem=5;}
			//Ajustes � 3� e � 6� linha devido �s linhas intermedi�rias
			if(line==3 || line==6){x2=x2-2;}
			if(line>=1 && line<=9){
				//Comprimento das linhas horizontais
				for (int i=4; i<(SSQ*9)-4; i++){
					//1�linha horizontal com espessura de 2px
					square.setColor(i,x1+2, Red);
					square.setColor(i,x1+3, Red);
					//2�linha horizontal com espessura de 2px
					square.setColor(i,x2-2, Red);
					square.setColor(i,x2-1, Red);
				}
				//Comprimento das linhas verticais
				for( int i=4; i<(SSQ-rem); i++){
					//1�linha vertical com espessura de 2px
					square.setColor(4,x1+i, Red);
					square.setColor(5,x1+i, Red);
					//2�linha vertical com espessura de 2px
					square.setColor(399,x1+i, Red);
					square.setColor(400,x1+i, Red);
				}
			}
			//Retorno da imagem
			return square;
	}
//7--------------------------------------------------------------------------------------
	//Fun��o respons�vel por circundar a vermelho as colunas
	public static ColorImage RedAreaColumn(ColorImage square){
			//Cria a cor Red(Vermelho)
			Color Red = new Color (255 ,0 ,0);
			//Escolha da linha para realizar testes-------------
			int Col=9;
			//SSQ -- Tamanho do quadrado 
			int SSQ=45;
			//Espa�o para ser removido para a linha 9 
			int rem=3;
			//x1  -- Ponto inicial das linhas horizontais
			int x1 = (Col)*SSQ - (SSQ);
			//x2  -- Ponto inicial das linhas horizontais
			int x2 = (Col)*SSQ-1;
			//Ajustes para os contornos da primeira linha devido � moldura
			if(Col==1){x1=x1+2;}
			//Ajustes para os contornos da ultima linha devido � moldura
			if(Col==9){x2=x2-3; rem=5;}
			//Ajustes � 3� e � 6� linha devido �s linhas intermedi�rias
			if(Col==3 || Col==6){x2=x2-2;}
			if(Col>=1 && Col<=9){
				//Comprimento das linhas verticais
				for (int i = 4; i <(SSQ*9)-4 ; i++){
					//1�linha vertical com espessura de 2px
					square.setColor(x1+2,i, Red);
					square.setColor(x1+3,i, Red);
					//2�linha vertical com espessura de 2px
					square.setColor(x2-2,i, Red);
					square.setColor(x2-1,i, Red);
				}
				//Comprimento das linhas horizontais
				for( int i = 4; i < (SSQ-rem) ; i++){
					//1�linha horizontal com espessura de 2px
					square.setColor(x1+i,4, Red);
					square.setColor(x1+i,5, Red);
					//2�linha horizontal com espessura de 2px
					square.setColor(x1+i,399, Red);
					square.setColor(x1+i,400, Red);
				}
			}
			//Retorno da imagem
			return square;
	}

//8--------------------------------------------------------------------------------------
	//Fun��o respons�vel por circundar a vermelho o bloco 
	public static ColorImage RedAreaBlock(ColorImage square, int blk){
			//Cria a cor Red(Vermelho)
			Color Red = new Color (255 ,0 ,0);
			//Guarda o tamanho do lado do bloco
			int SSQ = 45*3;
			//Guarda a primeira coluna
			int x1 = (((blk-1)%3)*SSQ);
			//Guarda a segunda coluna
			int x2 = x1 + SSQ;
			//Guarda a primeira linha
			int y1 = (((blk-1)/3)*SSQ);
			//Guarda a segunda linha
			int y2 = y1 + SSQ;
			//Vari�veis para ajustar as coordenadas dos blocos
			int rem=3; int rem2=0; int rem3=2; int rem4=0; int rem5=4; int rem6=0;
			//Altera��o das vari�veis de ajuste relativamente a cada bloco
			if(blk==3 || blk==6 || blk==9){rem=4; rem4=1; rem5=2;}
			if(blk==7 || blk==8 || blk==9){rem2=1;}
			if(blk==1 || blk==2 || blk==3){rem6=2;}
			if(blk==1 || blk==4 || blk==7){rem3=0;}
			if(blk==2 || blk==5 || blk==8){rem5=2;}
			if(blk>=1 && blk<=9){
				//Linhas horizontais
				for(int i=x1+rem5; i<(SSQ+x1)-rem; i++){
					//Linha de cima
					square.setColor(i,y1+2+rem6,Red);
					square.setColor(i,y1+3+rem6,Red);
					//Linha de baixo
					square.setColor(i,y2-4-rem2,Red);
					square.setColor(i,y2-5-rem2,Red);
				}
				//Linhas verticais
				for(int i = y1+4; i<(SSQ+y1)-4; i++){
					//Linha da esquerda
					square.setColor(x1+4-rem3,i,Red);
					square.setColor(x1+5-rem3,i,Red);
					//Linha da direita
					square.setColor(x2-4-rem4,i,Red);
					square.setColor(x2-5-rem4,i,Red);
				}
			}
			//Retorna a matriz
			return square;
	}
//---------------------------------------------------------------------------------------
	//Fun��o que conta os zeros
	public static int numZeros(int[][] m){
		//Cria uma contador
		int out = 0;
		//Percorre as linhas da matriz
		for(int i = 0; i < m.length; i++)
			//Percorre as colunas da matriz
			for(int j = 0; j < m[0].length; j++)
				//Verifica se o n�mero � igual a 0
				if(m[i][j] == 0)
					//Adiciona mais 1 ao contador
					out++;
		//Retorna o contador
		return out;
	}
}