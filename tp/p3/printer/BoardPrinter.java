package tp.p3.printer;

import tp.p3.logic.Game;

public abstract class BoardPrinter implements GamePrinter {
	protected static final String space = " ";
	protected static final int marginSize = 2;
	protected static final String vDelimiter = "|";
	protected static final String hDelimiter = "-";
	protected String printerName;
	
	protected final int dimX;
	protected final int dimY;
	protected String[][] board;
	
	public BoardPrinter(int dimX, int dimY, String printerName) {
		this.dimX = dimX;
		this.dimY = dimY;
		this.printerName = printerName;
	}
	
	protected abstract void encodeGame(Game game);
	
	public abstract BoardPrinter getNewPrinter();
	
	public BoardPrinter parse(String commandWords) {
		if(commandWords.equals(printerName)) return getNewPrinter();
		else return null;
	}
	
	public abstract String toString();
	
}
