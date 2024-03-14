import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Sudoku {
    private SudokuBoard sudokuBoard;
    public ColorImage boardImage;

    public Sudoku(String file, double difficulty) {
        int[][] boardLayout = loadBoardLayout("jogo1.sud"); // Method to load board layout from file
        adjustBoardForDifficulty(boardLayout, difficulty); // Method to adjust board based on difficulty
        this.sudokuBoard = new SudokuBoard(boardLayout);
        this.boardImage = new ColorImage(9, 9); 
        updateBoardImage(); // Method to draw the board state on boardImage
        printBoard(boardLayout);
    }

    private int[][] loadBoardLayout(String file) {
        int[][] board = new int[9][9];
        try (Scanner scanner = new Scanner(new File("jogo1.sud"))) {
            for (int row = 0; row < 9; row++) {
                if (scanner.hasNextLine()) {
                    String[] line = scanner.nextLine().trim().split("\\s+");
                    for (int col = 0; col < 9; col++) {
                        board[row][col] = Integer.parseInt(line[col]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return board;
    }

    private void adjustBoardForDifficulty(int[][] board, double difficulty) {
        Random rand = new Random();
        int cellsToRemove = (int)(81 * difficulty); // Difficulty determines the percentage of cells to clear
        while (cellsToRemove > 0) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                cellsToRemove--;
            }
        }
    }

    private void updateBoardImage() {
        int cellSize = Param.SQUARE_SIZE;
        int spacing = Param.SPACING;
        int boardSize = 9 * cellSize + 8 * spacing;
        boardImage = new ColorImage(boardSize, boardSize, Param.SQUARE_BACKCOLOR);
    
        // Draw the Sudoku grid
        for (int i = 0; i <= 9; i++) {
            int pos = i * (cellSize + spacing);
            // Use segment grid color for every third line
            Color lineColor = (i % 3 == 0) ? Param.SEGMENT_GRID_COLOR : Param.GRID_COLOR; 
    
            // Draw horizontal and vertical grid lines
            for (int x = 0; x < boardSize; x++) {
                boardImage.setColor(x, pos, lineColor); // Horizontal line
                boardImage.setColor(pos, x, lineColor); // Vertical line
            }
        }
    
        // Draw the numbers
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int num = sudokuBoard.getNumber(row, col);
                if (num != 0) {
                    String numStr = Integer.toString(num);
                    int textX = col * (cellSize + spacing) + cellSize / 2;
                    int textY = row * (cellSize + spacing) + cellSize / 2;
                    boardImage.drawCenteredText(textX, textY, numStr, Param.LETTER_SIZE, Param.TEXT_COLOR);
                }
            }
        }
    }
    
    private void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void makeMove(int x, int y, int number) {
        if (isValidMove(x, y, number)) {
            sudokuBoard.makeMove(x, y, number); 
            updateBoardImage();
        }
    }

    public void RandomMove() {
        sudokuBoard.makeRandomMove(); 
        updateBoardImage();
    }
    
    public void undoMove() {
        sudokuBoard.undoMove();
        updateBoardImage();
    }
    
    public void restartBoard() {
    	sudokuBoard.resetBoard();
    	updateBoardImage();
    }
   
    public void saveGame() {
        try (PrintWriter out = new PrintWriter(new File ("jogo1.sudgame"))) {
            out.println(SudokuAux.matrixToString(sudokuBoard.getBoard())); // Pass the board as an argument
            out.close();
            System.out.println("Tabuleiro guardado");
        } catch (FileNotFoundException e) {
        	System.out.println("Jogo nao guardado");
            e.printStackTrace();
        }
    }
    
    public void changeDifficulty(double newDifficulty) {
        // Validate the new difficulty value
        if (newDifficulty < 0.1 || newDifficulty > 0.9) {
            System.out.println("Invalid difficulty. Please choose a value between 0.1 and 0.9");
            return;
        }

        // Adjust the board for the new difficulty
        adjustBoardForDifficulty(sudokuBoard.getBoard(), newDifficulty);
        updateBoardImage(); // Update the board image to reflect the new difficulty
    }


    public void loadGame(String file) {
    	int[][] loadedBoardLayout = SudokuAux.sudokuScan(file); // Load board layout from file
        this.sudokuBoard = new SudokuBoard(loadedBoardLayout); // Create a new SudokuBoard with the loaded layout
        updateBoardImage();
    }
    
    public boolean isGameComplete() {
        return sudokuBoard.isCompleted();
    }    

    private boolean isValidMove(int x, int y, int number) {
        return sudokuBoard.isValidMove(x, y, number);
    }
}
