package tp.p3.entity;

import tp.p3.logic.Game;

public class WallNut extends Plant{
	private static final int COST = 50; // SunCoins
	private static final int HARM = 0; // 
	private static final String plantName = "[N]uez";
	private static final String plantChar = "N";
	private static final String plantCommand = "nuez";
	
	private static final int RESISTANCE = 10; // Vida maxima
	
	
	public WallNut(int posX, int posY, Game copy) {
		super(posX, posY, copy, RESISTANCE, COST, HARM, 0, plantName, plantChar, plantCommand);
	}
	
	public WallNut() {
		super (RESISTANCE, COST, HARM, 0, plantName, plantChar, plantCommand);
	}
	
	public void update() {}
	
	public WallNut getNewObject() {return new WallNut();}
}
