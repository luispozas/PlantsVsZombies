package tp.p3.utility;

import tp.p3.entity.*;

public class PlantFactory {
	
	private static Plant[] availablePlants = {
			new Sunflower(),
			new Peashooter(),
			new CherryBomb(),
			new WallNut()
	};
		
	public static Plant getPlant(String plantName){
		Plant newPlant = null;
		for(Plant plt : availablePlants) {
			newPlant = (Plant) plt.parse(plantName);
			if(newPlant != null) return newPlant;
		}
		return null;
	}
	
		
	public static String listOfAvilablePlants() {
		StringBuilder msg = new StringBuilder("\n");
		for(Plant plt : availablePlants) {
			msg.append(" " + plt.getInfo() + "\n");
		}
		return msg.toString();	
	}
}
