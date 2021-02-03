package tp.p3.entity;

import tp.p3.logic.Game;

public class Sunflower extends Plant {
	private static final int COST = 20; // Valor en soles
	private static final int HARM = 0; // // Daño
	private static final String plantName = "[S]unflower";
	private static final String plantChar = "S";
	private static final String plantCommand = "sunflower";
	
	private static final int FREC = 2; // Frecuencia de actualizacion cada x ciclos
	private static final int NSUN = 10; // Numero de soles por actualizacion
	private static final int RESISTANCE = 1; // Vida maxima
	
	
	public Sunflower(int posX, int posY, Game copy) {
		super(posX, posY, copy, RESISTANCE, COST, HARM, FREC, plantName, plantChar, plantCommand);
	}
	
	public Sunflower() {
		super (RESISTANCE, COST, HARM, FREC, plantName, plantChar, plantCommand);
	}
	
	public void update() {
		countCycles = ++countCycles % FREC;
		if (countCycles == 0) gamecopy.addSuncoins(NSUN);
	}
	
	public Sunflower getNewObject() {return new Sunflower();}
	
}
