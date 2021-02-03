package tp.p3.entity;

import tp.p3.logic.Game;

public class BucketHead extends Zombie{
	public static final int HARM = 1; // // Danio por golpe
	private static final int FREC = 4; // Frecuencia de actualizacion cada x ciclos
	private static final int CELLS = 1; // Numero de casillas por actualizacion
	private static final int RESISTANCE = 8; // Vida maxima
	
	private static final String zomName = "[B]ucketHead Zombie";
	private static final String zomChar = "B";
	private static final String zomCommand = "buckethead";


	public BucketHead(int posX, int posY, Game copy) {
		super(posX, posY, copy, RESISTANCE, FREC, HARM, zomName, zomChar, zomCommand);
	}
	
	public BucketHead() {
		super(RESISTANCE, FREC, HARM, zomName, zomChar, zomCommand);
	}

	public void update() {
		if (!gamecopy.canAttackZombie(posX, posY)) {
			countCycles = ++countCycles % FREC;
			if (countCycles == 0 && gamecopy.canMoveZombie(posX, posY)) posY -= CELLS;
		} 
		else gamecopy.damagePlant(HARM, posX, posY);
	}
	
	public BucketHead getNewObject() {return new BucketHead();}

}
