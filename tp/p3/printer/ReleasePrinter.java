package tp.p3.printer;

import tp.p3.logic.Game;
import tp.p3.utility.MyStringUtils;

public class ReleasePrinter extends BoardPrinter {
	private static final int cellSize = 7;
	private static final String printerName = "release";
	
	public ReleasePrinter() {
		super(Game.ROWS, Game.COLUMNS, printerName);
	}
	
	public ReleasePrinter(int dimX, int dimY) {
		super(dimX, dimY, printerName);
	}
	
	protected void encodeGame(Game game) {
		board = new String[dimX][dimY];
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				board[i][j] = game.stringOfElementRelease(i, j); 
			}
		}
	}
	
	public ReleasePrinter getNewPrinter() {return new ReleasePrinter(Game.ROWS, Game.COLUMNS);}
	
	protected String gameHeader(Game game) {
		StringBuilder header = new StringBuilder();
		header.append("\n Number of cycles: " + game.getNumCycles());
		header.append("\n Sun coins: " + game.getSuncoins());
		header.append("\n Remaining zombies: " + game.getZombiesLeft() + "\n");
		return header.toString();
	}
	
	public String toString() {
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1); 
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

		StringBuilder str = new StringBuilder();

		str.append(lineDelimiter);

		for (int i = 0; i < dimX; i++) { 
			str.append(margin).append(vDelimiter);
			for (int j = 0; j < dimY; j++) {
				str.append(MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter); 
			}
			str.append(lineDelimiter);
		}
		return str.toString();
	}
	
	
	public String printGame(Game game) {
		encodeGame(game);
		return gameHeader(game) + this;
	}
	
}
