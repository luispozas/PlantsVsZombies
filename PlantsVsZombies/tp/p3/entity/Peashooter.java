package tp.p3.entity;

import tp.p3.logic.Game;

public class Peashooter extends Plant{
	private static final int COST = 50; // Valor en soles
	private static final int HARM = 1; // // Daño por guisante
	private static final String plantName = "[P]eashooter";
	private static final String plantChar = "P";
	private static final String plantCommand = "peashooter";
	
	private static final int FREC = 1; // Frecuencia de actualizacion cada x ciclos
	private static final int NPEAS = 1; // Numero de guisantes por actualizacion
	private static final int RESISTANCE = 3; // Vida maxima


	public Peashooter(int posX, int posY, Game copy) {
		super(posX, posY, copy, RESISTANCE, COST, HARM, FREC, plantName, plantChar, plantCommand);
	}
	
	public Peashooter() {
		super (RESISTANCE, COST, HARM, FREC, plantName, plantChar, plantCommand);
	}

	public void update() {
		countCycles = ++countCycles % FREC;
		if (countCycles == 0) gamecopy.damageZombie(HARM * NPEAS, posX, posY);
	}
	
	public Peashooter getNewObject() {return new Peashooter();}

}