package tp.p3.entity;

import tp.p3.logic.Game;

public class CommonZombie extends Zombie{
	public static final int HARM = 1; // // Daño por golpe
	private static final int FREC = 2; // Frecuencia de actualizacion cada x ciclos
	private static final int CELLS = 1; // Numero de casillas por actualizacion
	private static final int RESISTANCE = 5; // Vida maxima
	
	private static final String zomName = "Common [Z]ombie";
	private static final String zomChar = "Z";
	private static final String zomCommand = "zombie";


	public CommonZombie(int posX, int posY, Game copy) {
		super(posX, posY, copy, RESISTANCE, FREC, HARM, zomName, zomChar, zomCommand);
	}
	
	public CommonZombie() {
		super(RESISTANCE, FREC, HARM, zomName, zomChar, zomCommand);
	}

	public void update() {
		if (!gamecopy.canAttackZombie(posX, posY)) {
			countCycles = ++countCycles % FREC;
			if (countCycles == 0 && gamecopy.canMoveZombie(posX, posY)) posY -= CELLS;
		} 
		else gamecopy.damagePlant(HARM, posX, posY);
	}
	
	public CommonZombie getNewObject() {return new CommonZombie();}

}
