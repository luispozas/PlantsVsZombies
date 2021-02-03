package tp.p3.entity;

import tp.p3.logic.Game;

public abstract class GameObject {
	
	protected Game gamecopy;
	protected int posX;
	protected int posY;
	protected int health;
	protected int countCycles;
	protected final String objName;
	protected final String objChar;
	protected final String objCommand;
    protected final int HARM;
    protected final int RES;
    protected final int FREC;
	
	public GameObject(int x, int y,Game copy, int health, int harm, int FREC, String objName, String objChar, String objCommand) {
		posX = x;
		posY = y;
		this.health = health;
		RES = health;
		this.objName = objName;
		this.objChar = objChar;
		countCycles = 0;
		gamecopy = copy;
		this.objCommand = objCommand;
		HARM = harm;
		this.FREC = FREC;
	}
	
	
	public GameObject(int health, int harm, int FREC, String objName,String objChar, String objCommand) {
		this.health = health;
		RES = health;
		this.objName = objName;
		this.objChar = objChar;
		posX = -1;
		posY = -1;
		countCycles = 0;
		gamecopy = null;
		this.objCommand = objCommand;
		HARM = harm;
		this.FREC = FREC;
	}
	
	public boolean parseObjectAttributes(String[] attr){
		if(attr != null && attr.length == 4) {
			try{
				this.health = Integer.parseInt(attr[0]);
				if(health > RES || health <= 0) throw new IllegalArgumentException();
				posX = Integer.parseInt(attr[1]);
				posY = Integer.parseInt(attr[2]);
				countCycles = Integer.parseInt(attr[3]);
				if(countCycles > FREC - 1 || countCycles < 0) throw new IllegalArgumentException();
				return true;
			}
			catch(IllegalArgumentException ex) {}
		}
		return false;
	}
	
	protected boolean isCommandValid(String comm) {
		if(objCommand.equals(comm) || objCommand.substring(0, 1).equals(comm)) return true;
		return false;
	}
	
	public GameObject parse(String comm) {
		if(isCommandValid(comm)) return getNewObject();
		return null;
	}
	
	public abstract GameObject getNewObject();
	
	public abstract void update();
	
	public abstract String getInfo();

	public String toString() {
		return " " + objChar +" [" + health + "] ";
	}
	
	public String toStringDebug() {
		return objChar + "[l:" + health + ", x:" + posX + ", y:" + posY + ", t:" + countCycles + "]" ;
	}
	
	public String StringToWriteOnFile() {
		return objChar.toLowerCase() + ":" + health + ":" + posX + ":" + posY + ":" + countCycles;
	}
	
	public boolean lowerHealth(int damage) {
		health -= damage;
		return (health <= 0);
	}
	
	public int getHealth() {return health;}
	
	public void setHealth(int health) {this.health = health;}
	
	public void setCycles(int Cycles) {countCycles = Cycles;}

	public int getPosX() {return posX;}

	public int getPosY() {return posY;}
	
	public String getObjectName() {return objName;}
	
	public void setPosX(int posX) { this.posX= posX;}

	public void setPosY(int posY) { this.posY = posY;}
	
	public void setGame(Game game) {gamecopy = game;}
}
