package tp.p3.logic;

import java.util.Random;

import tp.p3.entity.Zombie;
import tp.p3.utility.ZombieFactory;

public class ZombieManager {

	private int NumZombies;
	private Levels difficulty;
	private Random rand;

	public ZombieManager(Levels difficulty, Random rand) {
		this.rand = rand;
		this.difficulty = difficulty;
		NumZombies = difficulty.getNumberOfZombies();
	}
	
	public ZombieManager(ZombieManager copy) {
		rand = copy.rand;
		difficulty = copy.difficulty;
		NumZombies = copy.NumZombies;
	}

	public int zombiesLeft() {return NumZombies;}

	public boolean isZombieAdded() {
		boolean ok = false;
		if (NumZombies > 0) {
			int frec = rand.nextInt(10) + 1;
			ok = (frec <= 10 * difficulty.getZombieFrequency());
		}
		return ok;
	}
	
	public Zombie generateZombie() {
		if(isZombieAdded()) {
			int x = rand.nextInt(Game.ROWS);
			Zombie zom = ZombieFactory.getZombie(rand);
			zom.setPosX(x);
			zom.setPosY(Game.COLUMNS - 1);
			return zom;
		}
		return null;
	}

	public void reduceNumZombies() {NumZombies--;}
	
	public void increaseNumZombies() {NumZombies++;}
	
	public void setNumZombies(int Zombies) {NumZombies = Zombies;}
	
	public void setDifficulty(Levels level) {difficulty = level;}

}
