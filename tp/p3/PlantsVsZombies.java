package tp.p3;
//DANIEL ALFARO MIRANDA, LUIS POZAS PALOMO

import tp.p3.controller.Controller;
import tp.p3.logic.*;
import tp.p3.printer.*;

public class PlantsVsZombies {
	public static final String appName = "Plants Vs Zombies";
	public static final String appVersion =  "v3.0";
	
	
	public static void main(String[] args) {
		if(args.length > 0 && args.length < 3) {
			Levels difficulty = Levels.levelExist(args[0].toUpperCase());
			if (difficulty == null) throw new IllegalArgumentException("Error: Level must be one of the following: " + Levels.all(", "));
			Game game;
			String strSeed = "Aleatory";
			if (args.length > 1) {
				try {game = new Game(difficulty, Integer.parseInt(args[1]), new ReleasePrinter());}
				catch(NumberFormatException e) {
					throw new IllegalArgumentException("Error: Optional argument seed must be an Integer");
				}	
				strSeed = args[1];
			}
			else game = new Game(difficulty, new ReleasePrinter());
			System.out.println(" Random seed used : " + strSeed);
			Controller ctr = new Controller(game);
			ctr.run();
		}
		else System.err.println("Help: PlantsVsZombies <level> <<seed>> \nlevel: " + Levels.all(", ") + "\nseed: Integer");
	}
}
