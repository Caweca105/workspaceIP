import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
//Fun��o respons�vel pela liga��o das 2 classes anteriores e da cria��o de ficheiros
public class Sudoku {
	//Cria��o do objeto
	private SudokuBoard sudokuBoard;
	//Cria��o da imagem
	public ColorImage square;
	//Cria��o da matriz inicial
	private int [][] init;
	//Cria��o de um contrutor com o ficheiro e a dificuldade nos parametros
	public Sudoku(String file, double difficulty){
		 this.init=FileMatrix("jogo1.sud");
		 this.sudokuBoard=new SudokuBoard(init,difficulty);
		 this.square=SudokuAux.drawMatrix();
		 SudokuAux.InsertImg(square, init);
	}
	//Cria o ficheiro atrav�s da matriz
	public static int[][] FileMatrix(String f){
		int [][]m = new int [9][9];
		File file= new File(f);
		try{
			Scanner S = new Scanner(file);
			for(int x=0; x<9; x++){
				for(int y=0; y<9; y++){
					m[x][y]=S.nextInt();
				}
			}
			S.close();
		}
		catch(FileNotFoundException nf){
			nf.printStackTrace();
		}
		return m;
	}
	public void Play(int num, int line, int col){
		sudokuBoard.PlayAt(num, col, line);
		SudokuAux.InsertImg(square, init);
	}
	public void RandomPlay(){
		sudokuBoard.AutoPlayAt();
		SudokuAux.InsertImg(square, init);
	}
	public void Undo(){
		sudokuBoard.undo();
		SudokuAux.InsertImg(square, init);
	}
	public void Reset(){
		sudokuBoard.resetBoard();
		SudokuAux.InsertImg(square, init);
	}
	public void Save(){
		try{
			PrintWriter P=new PrintWriter(new File("jogo.sudgame"));
			P.println(SudokuAux.ConvertToString(init));
			P.close();
			System.out.println("Guardado com Sucesso");
		}
		catch(FileNotFoundException nf){
			nf.printStackTrace();
		}
	}
	public void load(){
		SudokuAux.drawMatrix();
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
			init[i][j]=FileMatrix("jogo.sudgame")[i][j];
			}
		}
		System.out.println("Jogo carregado com sucesso");
		SudokuAux.InsertImg(square,init);
	}
}