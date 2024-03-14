//Classe de objetos SudokuBoard responsável pela lógica e estado do jogo
public class SudokuBoard {
	//Criação da matriz incial
	public int[][] initM;
	//Criação da matriz principal
	public int[][] m;
	//Criação do vetor para mostrar as colunas inválidas
	int [] InvCol;
	//Criação do vetor para mostrar as linhas inválidas
	int [] InvLines;
	//Criação do vetor para mostrar os blocos inválidos
	int [] InvBlk;
	//Guarda o numero da jogada atual
	int currentPlay = 0;
	//Guarda as coordenadas horizontais
	int[] xPlayed;
	//Guarda as coordenadas verticais 
	int[] yPlayed;
//1--------------------------------------------------------------------------------------
	//Criação do construtor recebendo a matriz e a percentagem de zeros por parametro
	public SudokuBoard(int[][] m, double p) {
		//Aponta a matriz para o novo jogo de sudoku
        this.m = SudokuAux.GenerateBoard(m,p);
        //Copia a matriz para ficar armazenada caso o seja feito um reset
        this.initM = copyMatrix(this.m);
        //Guarda os zeros em relação as colunas da matriz
        xPlayed = new int[SudokuAux.numZeros(initM)];
        //Guarda os zeros em relação as linhas da matriz
		yPlayed = new int[SudokuAux.numZeros(initM)];
	}
	//Função responsável por salvar as jogadas
	public void savePlay(int x, int y, int n){
		//Guarda a coordenada das linhas
		xPlayed[currentPlay] = x;
		//Guarda a coordenada das colunas
		yPlayed[currentPlay] = y;
		//Guarda o numero da jogada
		currentPlay++;
		//System.out.print(n + "" + x + "" + y);
	}
//2--------------------------------------------------------------------------------------
    //Função que escolhe um número da matriz de acordo com as coordenadas
  	public int PickNum(int i, int j){
  		//Retorna o número
  		return m[i][j];
  	}
//3--------------------------------------------------------------------------------------
    //Função que insere um número na matriz escolhendo as coordenadas
  	public void PlayAt(int num, int i, int j){
  		//Validação do número
  		if(num<1 || num>9 || i<0 || i>9 || j<0 || j>9){
  			//Caso seja inválido é enviado um aviso 
  			throw new IllegalArgumentException("Dados inválidos");
  		}
  		//Valida se a posição é valida para jogar
  		if(m[i][j]==0){
  			//Guarda o numero na posição
  			m[i][j] = num;
  			//guarda a jogada
  			savePlay(i,j,num);
  		}else{
  			//TODO perguntar ao professor se é suposto mandar uma excepção
  		}
  	}
//4--------------------------------------------------------------------------------------
  	//Função que realiza uma jogada aleatória 
  	public void AutoPlayAt(){
  		//Criação das variaveis 
  		int x=0; int y=0; int num=0;
		//Guarda a matriz numa variavel auxiliar
		int [][] sudokuSave = copyMatrix(m);
		//variável para validar se o ciclo pode acabar
		boolean val=false;
		//Ciclo while para continuar até a jogada ser feita
		while(val==false){
  			//Atribui um valor aleatório para as linhas
  			x = (int)(Math.random() * 9 );
  			//If adicionado para facilitar a procura da uma posição
  			if(m[x][y] != 0) y = (int)(Math.random() * 9 );
			//Verificação da posição para ver se está vazia 
			if(m[x][y] == 0){
				//Gera um numero aleatório
				num = (int)(Math.random() * 10 );
				//Adiciona o número à posição da matriz 
				sudokuSave[x][y] = num;
				//Validação da matriz com o novo número
				if(SudokuAux.ValidSudoku(sudokuSave)){
					val=true;
					//Se o jogo for valido será guardado na matriz principal
					m = copyMatrix(sudokuSave);
  					//Guarda nova alteração
  		  			savePlay(x,y,num);
  		  			//Força a saída da função
  		  			return;
				}else{
					//Caso o numero seja inválido o numero é retirado
					sudokuSave[x][y] = 0;
				}
			}
  		}                                              
  	}
//5--------------------------------------------------------------------------------------
  	//Criação de uma função para copiar matrizes
  	public static int[][] copyMatrix(int[][] m){
  		//Criação de uma matriz
  		int[][] newM = new int[9][9];
  		//Percorre as linhas da matriz
  		for(int i = 0; i < m.length; i++)
  			//Percorre as colunas da matriz
  			for(int j = 0; j < m[0].length; j++)
  				//Copia os numeros da matriz principal
  				newM[i][j] = m[i][j];
  		//Retorna a matriz copiada
  		return newM;
  	}
  	//Função responsável por reiniciar o jogo
  	public void resetBoard(){
  		//Altera a matriz para a matriz inicial criada anteriormente
  		this.m = copyMatrix(this.initM);
  	}
//6--------------------------------------------------------------------------------------
//  	public int [] ShowInvBlk(){
//  		InvBlk = SudokuAux.NumBlk(m);	
//		return InvBlk;
//  	}
//7--------------------------------------------------------------------------------------
	public int [] ShowInvX(){
		InvCol = SudokuAux.NumX(this.m);	
		return InvCol;
	}
	public int [] ShowInvY(){
		InvLines = SudokuAux.NumY(this.m);	
		return InvLines;
	}
//8--------------------------------------------------------------------------------------
  	//Função responsável por verificar se o jogo acabou ou não
  	public static boolean EndGame(int [][] m){
  		//Caso não sejam encontrados mais zeros 
  		if(SudokuAux.numZeros(m)==0){
  			//Retorna que o jogo ja está acabado
			return true;
  		}else{
	  		//Se forem encontrados zero o jogo continua 
	  		return false;
	  	}
  	}
//9--------------------------------------------------------------------------------------
	//Função para anular as jogadas
  	public void undo(){
  		//remove a ultima coordenada das colunas
		int x = xPlayed[currentPlay - 1];
		//remove a ultima coordenada das linhas
		int y = yPlayed[currentPlay - 1];
		//Remove a ultima jogada
		currentPlay--;
		//Altera o valor da posição para 0
		this.m[x][y] = 0;
	}
//---------------------------------------------------------------------------------------  	
//Função de testes
  	public static void testes(){
		SudokuBoard board = new SudokuBoard(new int[][]{
		        {0, 3, 4, 0, 0, 8, 0, 1, 0},
		        {6, 0, 2, 0, 0, 0, 3, 0, 0},
		        {1, 0, 0, 0, 0, 0, 5, 0, 0},
		        {0, 5, 9, 0, 0, 1, 4, 2, 3},
		        {4, 2, 0, 8, 0, 0, 0, 0, 0},
		        {7, 1, 3, 0, 2, 0, 8, 0, 6},
		        {0, 0, 1, 5, 0, 7, 7, 8, 4},
		        {0, 8, 0, 0, 1, 0, 0, 0, 0},
		        {0, 0, 5, 2, 0, 6, 7, 7, 0}
		}, 0);
		board.PlayAt(2,0,0);
		board.PlayAt(3,1,1);
		board.undo();
//		board.undo();
		board.PlayAt(2,2,2);
//		board.AutoPlayAt();
//		board.ShowInvX();
//		board.ShowInvY();
//		board.ShowInvBlk();
		return;
	}
}