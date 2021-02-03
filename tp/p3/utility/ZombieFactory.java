package tp.p3.utility;

import java.util.Random;

import tp.p3.entity.*;

public class ZombieFactory {
	
	private static Zombie[] availableZombies = {
			new CommonZombie(),
			new BucketHead(),
			new AthleteZombie()
	};
		
	public static Zombie getZombie(String zombieName){
		Zombie newZom = null;
		for(Zombie zom : availableZombies) {
			newZom = (Zombie) zom.parse(zombieName);
			if(newZom != null) return newZom;
		}
		return null;
	}
		
	public static String listOfAvilableZombies() {
		StringBuilder msg = new StringBuilder("\n");
		for(Zombie zom : availableZombies) {
			 msg.append(" " + zom.getInfo() + "\n");
		}
		return msg.toString();	
	}
	
	public static Zombie getZombie(Random rand) {
		if (rand != null) return availableZombies[rand.nextInt(availableZombies.length)].getNewObject();
		return null;
	}
}
