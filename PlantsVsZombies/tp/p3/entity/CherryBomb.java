package tp.p3.entity;

import tp.p3.logic.Game;

public class CherryBomb extends Plant {
	private static final int COST = 50; // SunCoins
	private static final int HARM = 10; // 
	private static final String plantName = "[C]herryBomb";
	private static final String plantChar = "C";
	private static final String plantCommand = "cherrybomb";
	
	private static final int FREC = 2; // Frecuencia de actualizacion cada x ciclos
	private static final int RESISTANCE = 2; // Vida maxima
	
	
	public CherryBomb(int posX, int posY, Game copy) {
		super(posX, posY, copy, RESISTANCE, COST, HARM, FREC, plantName, plantChar, plantCommand);
	}
	
	public CherryBomb() {
		super (RESISTANCE, COST, HARM, FREC, plantName, plantChar, plantCommand);
	}
	
	public void update() {
		countCycles = ++countCycles % FREC;
		if (countCycles == 0) {
			gamecopy.damageZombiesinArea(HARM, posX, posY);
			health = 0;
		}
	}
	
	public CherryBomb getNewObject() {return new CherryBomb();}
}
