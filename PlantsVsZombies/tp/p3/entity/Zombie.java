package tp.p3.entity;

import tp.p3.logic.Game;

public abstract class Zombie extends GameObject{
	private final int VEL;
	private final int RES;
	
	public Zombie(int x, int y,Game copy, int health, int VEL, int HARM, String zomName, String zomChar, String zomCommand){
		super(x, y,copy, health, HARM, VEL, zomName, zomChar, zomCommand);
		this.VEL = VEL;
		this.RES = health;
	}
	
	public Zombie(int health, int VEL, int HARM, String zomName, String zomChar, String zomCommand){
		super(health, HARM, VEL, zomName, zomChar, zomCommand);
		this.VEL = VEL;
		this.RES = health;
	}
	
	public abstract void update();
	
	public abstract Zombie getNewObject();
	
	public String getInfo() {
		return objName +": Speed: " + VEL + ",  Harm: " + HARM + ",  Life: " + RES;
	}
	
}
