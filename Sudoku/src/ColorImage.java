
/**
 * Represents color images.
 * Image data is represented as a matrix:
 * - the number of lines corresponds to the image height (data.length)
 * - the length of the lines corresponds to the image width (data[*].length)
 * - pixel color is encoded as integers (ARGB)
 */

class ColorImage {

	private int[][] data; // @colorimage


	// Constructors

	ColorImage(int width, int height) {
		data = new int[height][width];
	}

	ColorImage(String file) {
		this.data = ImageUtil.readColorImage(file);
	}

	ColorImage(int[][] data) {
		this.data = data;
	}

	ColorImage(int width, int height, Color color) {
		data = new int[height][width];

		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
				this.setColor(x, y, color);
			}
		}
	}

	public static ColorImage createSudokuBoardImage(int[][] board) {
		// Substituted 'cellSize' for 'SQUARE_SIZE' defined in Param
        int boardSize = 9 * Param.SQUARE_SIZE + 8 * Param.SPACING;
		// Create white background image
        ColorImage sudokuImage = new ColorImage(boardSize, boardSize, Param.SQUARE_BACKCOLOR); 

        // Draw the Sudoku grid
        drawGrid(sudokuImage);

        // Fill in the numbers from the Sudoku board
        fillNumbers(sudokuImage, board);

		// Return the generated Sudoku board image
        return sudokuImage; 
    }


	// Methods

	int getWidth() {
		return data[0].length;
	}

	int getHeight() {
		return data.length;
	}

	void setColor(int x, int y, Color c) {
		if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
			data[y][x] = ImageUtil.encodeRgb(c.getR(), c.getG(), c.getB());
		} else {
			// Optionally, you can log or handle the case where the indices are out of bounds
//			System.out.println("Attempt to set color out of bounds: x=" + x + ", y=" + y);
		}
//		int height = data.length;
//		int width = data[0].length; // Assuming all rows have the same length
//
//		for (int z = 0; z < height; z++) {
//		    for (int w = 0; w < width; w++) {
//		        // Set the color at (x, y) using the setColor method
//		        // Replace 'someColor' with the Color instance you want to use
//		        setColor(x, y, c);
//		    }
//		}
	}

	Color getColor(int x, int y) {
		int[] rgb = ImageUtil.decodeRgb(data[y][x]);
		return new Color(rgb[0], rgb[1], rgb[2]);
	}

	// Text functions

	void drawText(int textX, int textY, String text, int textSize, Color textColor) {
		drawText(textX, textY, text, textSize, textColor, false);
	}


	void drawCenteredText(int textX, int textY, String text, int textSize, Color textColor) {
		drawText(textX, textY, text, textSize, textColor, true);
	}
	
	ColorImage copy () {
		
		ColorImage img = new ColorImage (getWidth(), getHeight());
		
		for (int x = 0; x < this.getWidth(); x++)
			for (int y = 0; y < this.getHeight(); y++)
				img.setColor(x, y, getColor(x,y));
				
		return img;
	}

	private static void fillNumbers(ColorImage sudokuImage, int[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] != 0) {
					// Calcula a posição central da célula
					int x = col * (Param.SQUARE_SIZE + Param.SPACING) + Param.SQUARE_SIZE / 2;
					int y = row * (Param.SQUARE_SIZE + Param.SPACING) + Param.SQUARE_SIZE / 2;
					// Desenha o número centralizado na célula
					sudokuImage.drawCenteredText(x, y, String.valueOf(board[row][col]), Param.LETTER_SIZE, Param.TEXT_COLOR);
				}
			}
		}
	}
	

	public static void drawGrid(ColorImage sudokuImage) {
		int boardSize = Param.SQUARE_SIZE * 9 + Param.SPACING * 8;
		for (int i = 0; i <= 9; i++) {
			int thickness = (i % 3 == 0) ? Param.SEGMENT_GRID_THICKNESS : Param.GRID_THICKNESS;
	
			// Desenha linhas verticais
			for (int j = 0; j < thickness; j++) {
				for (int y = 0; y < boardSize; y++) {
					Color lineColor = (i % 3 == 0) ? Param.SEGMENT_GRID_COLOR : Param.GRID_COLOR;
					sudokuImage.setColor(i * (Param.SQUARE_SIZE + Param.SPACING) + j, y, lineColor);
				}
			}
			// Desenha linhas horizontais
			for (int j = 0; j < thickness; j++) {
				for (int x = 0; x < boardSize; x++) {
					Color lineColor = (i % 3 == 0) ? Param.SEGMENT_GRID_COLOR : Param.GRID_COLOR;
					sudokuImage.setColor(x, i * (Param.SQUARE_SIZE + Param.SPACING) + j, lineColor);
				}
			}
		}
	}
	

	public void drawText(int textX, int textY, String text, int textSize, Color textColor, boolean isCentered) {
		int r = 255 - textColor.getR();
		int g = 255 - textColor.getG();
		int b = 255 - textColor.getB();

		Color maskColor = new Color(r, g, b);

		int encodedMaskRGB = ImageUtil.encodeRgb(r, g, b);

		int[][] aux = ImageUtil.createColorImageWithText(getWidth(), getHeight(), maskColor, textX, textY, text, textSize, textColor, isCentered);

		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[i].length; j++) {
				int value = aux[i][j];
				if(value != encodedMaskRGB) {
					data[i][j] = aux[i][j];
				}
			}
		}
	}
}