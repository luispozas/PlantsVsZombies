package tp.p3.entity;

import tp.p3.logic.Game;

public class AthleteZombie extends Zombie {
	public static final int HARM = 1; // // Danio por golpe
	private static final int FREC = 1; // Frecuencia de actualizacion cada x ciclos
	private static final int CELLS = 1; // Numero de casillas por actualizacion
	private static final int RESISTANCE = 2; // Vida maxima
	
	private static final String zomName = "[A]thlete Zombie";
	private static final String zomChar = "A";
	private static final String zomCommand = "athlete";


	public AthleteZombie(int posX, int posY, Game copy) {
		super(posX, posY, copy, RESISTANCE, FREC, HARM, zomName, zomChar, zomCommand);
	}
	
	public AthleteZombie() {
		super(RESISTANCE, FREC, HARM, zomName, zomChar, zomCommand);
	}

	public void update() {
		if (!gamecopy.canAttackZombie(posX, posY)) {
			countCycles = ++countCycles % FREC;
			if (countCycles == 0 && gamecopy.canMoveZombie(posX, posY)) posY -= CELLS;
		} 
		else gamecopy.damagePlant(HARM, posX, posY);
	}
	
	public AthleteZombie getNewObject() {return new AthleteZombie();}

}
