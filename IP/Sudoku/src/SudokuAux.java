import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuAux {

	public static boolean isValidSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isRowValid(board, i) || !isColumnValid(board, i) || !isBoxValid(board, i)) {
            	System.out.println("O valor é inválido");
                return false;
            }
        }
        return true;
    }

    private static boolean isRowValid(int[][] board, int row) {
        boolean[] seen = new boolean[9];
        for (int col = 0; col < 9; col++) {
            if (board[row][col] != 0) {
                if (seen[board[row][col] - 1]) {
                	System.out.println("O valor é inválido");
                	return false;
                }
                seen[board[row][col] - 1] = true;
            }
        }
        return true;
    }
    
    public static int[][] sudokuScan(String file){
		int[][] game = new int[9][9] ;
			try{
				Scanner scan = new Scanner (new File ( file )) ;
				while ( scan.hasNextInt() ){
					for (int i = 0 ; i < game.length ;i++)
						for (int j = 0 ; j < game[i].length ; j++)
							game [i] [j] = scan.nextInt() ;
			}	
				scan.close();
		}
		catch (FileNotFoundException e){
			System.out.println ("File"+file+"can't be reached");
		}
		return game;
	}

    private static boolean isColumnValid(int[][] board, int col) {
        boolean[] seen = new boolean[9];
        for (int row = 0; row < 9; row++) {
            if (board[row][col] != 0) {
                if (seen[board[row][col] - 1]) {
                	System.out.println("O valor é inválido");
                    return false;
                }
                seen[board[row][col] - 1] = true;
            }
        }
        return true;
    }

    private static boolean isBoxValid(int[][] board, int box) {
        boolean[] seen = new boolean[9];
        int startRow = (box / 3) * 3;
        int startCol = (box % 3) * 3;
        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                if (board[row][col] != 0) {
                    if (seen[board[row][col] - 1]) {
                    	System.out.println("O valor é inválido");
                        return false;
                    }
                    seen[board[row][col] - 1] = true;
                }
            }
        }
        return true;
    }

    public static int[][] generateGameBoard(int[][] solution, double percentageToRemove) {
        int totalCells = 81; // Total cells in a 9x9 Sudoku
        int cellsToRemove = (int) (totalCells * percentageToRemove);
        int[] positions = new int[totalCells];

        // Fill the positions array with numbers from 0 to 80 (inclusive)
        for (int i = 0; i < totalCells; i++) {
            positions[i] = i;
        }

        // Shuffle the positions array
        shuffleArray(positions);

        int[][] gameBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(solution[i], 0, gameBoard[i], 0, 9);
        }

        // Remove numbers from the cells based on the shuffled positions
        for (int i = 0; i < cellsToRemove; i++) {
            int pos = positions[i];
            gameBoard[pos / 9][pos % 9] = 0; // Convert linear position to 2D coordinates and set to 0
        }

        return gameBoard;
    }
    
 // Utility method to shuffle an array
    private static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Simple swap
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    public static String matrixToString(int[][] board) {
    	StringBuilder sb = new StringBuilder();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                sb.append(board[row][col]);
                if (col < board[row].length - 1) {
                    sb.append(" "); // Add space between numbers
                }
            }
            if (row < board.length - 1) {
                sb.append("\n"); // Add newline at the end of each row except the last
            }
        }
        return sb.toString();
    }

    public static void updateBoardImage(ColorImage sudokuImage, int row, int col, int number) {
        // Calcula a posição central da célula
        int x = col * Param.SQUARE_SIZE + Param.SQUARE_SIZE / 2;
        int y = row * Param.SQUARE_SIZE + Param.SQUARE_SIZE / 2;
    
        // Limpa a célula atual
        clearCell(sudokuImage, row, col);
    
        // Desenha o novo número
        if (number != 0) {
            sudokuImage.drawCenteredText(x, y, String.valueOf(number), Param.LETTER_SIZE, Param.TEXT_COLOR);
        }
    }
    
    private static void clearCell(ColorImage sudokuImage, int row, int col) {
        int x1 = col * Param.SQUARE_SIZE;
        int y1 = row * Param.SQUARE_SIZE;
    
        // Iterate over each pixel in the cell and set it to the background color
        for (int x = x1; x < x1 + Param.SQUARE_SIZE; x++) {
            for (int y = y1; y < y1 + Param.SQUARE_SIZE; y++) {
                sudokuImage.setColor(x, y, Param.SQUARE_BACKCOLOR);
            }
        }
    }
    
    public static void highlightRow(ColorImage sudokuImage, int row) {
        int x1 = 0;
        int y1 = row * Param.SQUARE_SIZE;
        int width = Param.SQUARE_SIZE * 9;
        int height = Param.SQUARE_SIZE;
    
        // Iterate over each pixel in the row and set it to the highlight color
        for (int x = x1; x < x1 + width; x++) {
            for (int y = y1; y < y1 + height; y++) {
                // Preserve the grid lines
                if ((x % (Param.SQUARE_SIZE + Param.SPACING) == 0) || (y % (Param.SQUARE_SIZE + Param.SPACING) == 0)) {
                    // Grid line color
                    sudokuImage.setColor(x, y, Param.GRID_COLOR);
                } else {
                    // Highlight color
                    sudokuImage.setColor(x, y, Param.OUTLINE_COLOR);
                }
            }
        }
    }    
    
    public static void highlightColumn(ColorImage sudokuImage, int col) {
        int x1 = col * Param.SQUARE_SIZE;
        int y1 = 0;
        int width = Param.SQUARE_SIZE;
        int height = Param.SQUARE_SIZE * 9;
    
        // Iterate over each pixel in the column and set it to the highlight color
        for (int x = x1; x < x1 + width; x++) {
            for (int y = y1; y < y1 + height; y++) {
                // Preserve the grid lines
                if ((x % (Param.SQUARE_SIZE + Param.SPACING) == 0) || (y % (Param.SQUARE_SIZE + Param.SPACING) == 0)) {
                    // Grid line color
                    sudokuImage.setColor(x, y, Param.GRID_COLOR);
                } else {
                    // Highlight color
                    sudokuImage.setColor(x, y, Param.OUTLINE_COLOR);
                }
            }
        }
    }    
 
    public static void highlightSegment(ColorImage sudokuImage, int segment) {
        int segmentRow = (segment / 3) * 3 * Param.SQUARE_SIZE;
        int segmentCol = (segment % 3) * 3 * Param.SQUARE_SIZE;
        int size = 3 * Param.SQUARE_SIZE;
    
        // Iterate over each pixel in the segment
        for (int x = segmentCol; x < segmentCol + size; x++) {
            for (int y = segmentRow; y < segmentRow + size; y++) {
                // Check for segment boundaries (thicker grid lines)
                boolean isSegmentBoundary = (x % (3 * Param.SQUARE_SIZE) == 0) || 
                                            (y % (3 * Param.SQUARE_SIZE) == 0) ||
                                            (x == segmentCol + size - 1) || 
                                            (y == segmentRow + size - 1);
    
                // Check for normal grid lines
                boolean isGridLine = (x % Param.SQUARE_SIZE == 0) || (y % Param.SQUARE_SIZE == 0);
    
                // Apply colors based on the pixel's location
                if (isSegmentBoundary) {
                    sudokuImage.setColor(x, y, Param.SEGMENT_GRID_COLOR);
                } else if (isGridLine) {
                    sudokuImage.setColor(x, y, Param.GRID_COLOR);
                } else {
                    sudokuImage.setColor(x, y, Param.OUTLINE_COLOR);
                }
            }
        }
    }
}
