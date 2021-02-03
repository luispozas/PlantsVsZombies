package tp.p3.command;

import tp.p3.entity.Plant;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.InvalidPositionException;
import tp.p3.exceptions.NotEnoughSuncoinsException;
import tp.p3.logic.Game;
import tp.p3.utility.PlantFactory;

public class AddPlantCommand extends AddCommand{

	private static final String commandText = "add";
	private static final String commandTextMsg = "[A]dd <plant> <x> <y>";
	private static final String helpTextMsg = "Adds a plant in position x, y.";
	
	public AddPlantCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	public AddPlantCommand(String plantName, int x, int y) {
		super(plantName, x, y, commandText, commandTextMsg, helpTextMsg);
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
	       Plant plant = PlantFactory.getPlant(objName);
		   if (plant != null) {
			   try {
				   game.addPlantToGame(plant, x, y);
				}
				catch(NotEnoughSuncoinsException ex) {
					throw new CommandExecuteException(" Failed to add " + plant.getObjectName() + ": not enough suncoins to buy it", ex);
				}
				catch(InvalidPositionException ex) {
					game.addSuncoins(plant.COST);
					throw new CommandExecuteException(ex.getMessage(), ex);
				}
		   }
		   else throw new CommandExecuteException(" Unknown plant name: " + objName);
		   game.updateGame();
		   return true;
	}
	
	public AddPlantCommand getNewAddCommand(String plantName, int x, int y) {return new AddPlantCommand(plantName, x, y);}

}
