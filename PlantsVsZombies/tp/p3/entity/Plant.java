package tp.p3.entity; 

import tp.p3.logic.Game;

public abstract class Plant extends GameObject{
	public final int COST;
	
	public Plant(int x, int y,Game copy, int health, int COST, int HARM, int FREC, String plantName, String plantChar, String plantCommand){
		super(x, y,copy, health, HARM, FREC, plantName, plantChar, plantCommand);
		this.COST = COST;
	}
	
	public Plant(int health,int COST,int HARM, int FREC, String plantName, String plantChar, String plantCommand) {
		super(health, HARM, FREC, plantName, plantChar, plantCommand);
		this.COST = COST;
	}
	
	public abstract void update();
	
	public abstract Plant getNewObject(); // devuelve una planta del mismo tipo

	public String getInfo() {
		return objName +": Cost: " + COST + " suncoins,  Harm: " + HARM;
	}
		
}
