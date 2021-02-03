package tp.p3.printer;

import tp.p3.logic.Game;
import tp.p3.utility.MyStringUtils;

public class DebugPrinter extends BoardPrinter {
	private static final int cellSize = 21;
	private static final String printerName = "debug";
	private int actualdimX;
	private int actualdimY;
	
	public DebugPrinter() {
		super(0, 0, printerName);
	}
	
	public DebugPrinter(int dimX, int dimY) {
		super(dimX, dimY, printerName);
		actualdimX = 0;
		actualdimY = 0;
	}
	
	protected void encodeGame(Game game) {	
		actualdimY = game.getNumObjects();
		actualdimX = (actualdimY > 0) ? 1 : 0;
		board = new String[dimX][dimY];
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				board[i][j] = game.stringOfElementDebug(j);
			}
		}
	}
	
	public DebugPrinter getNewPrinter() {return new DebugPrinter(1, Game.MAXOBJECTS);}
	
	protected String gameHeader(Game game) {
		StringBuilder header = new StringBuilder();
		header.append("\n Number of cycles: " + game.getNumCycles());
		header.append("\n Sun coins: " + game.getSuncoins());
		header.append("\n Remaining zombies: " + game.getZombiesLeft());
		header.append("\n Level: " + game.getLevel().name());
		header.append("\n Seed: " + game.getSeed() + "\n");
		return header.toString();
	}
	
	public String toString() {
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (actualdimY * (cellSize + 1)) - 1); 
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

		StringBuilder str = new StringBuilder();

		str.append(lineDelimiter);

		for (int i = 0; i < actualdimX; i++) { 
			str.append(margin).append(vDelimiter);
			for (int j = 0; j < actualdimY; j++) {
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
