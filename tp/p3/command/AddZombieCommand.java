package tp.p3.command;

import tp.p3.entity.Zombie;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.InvalidPositionException;
import tp.p3.logic.Game;
import tp.p3.utility.ZombieFactory;


public class AddZombieCommand extends AddCommand{
	private static final String commandText = "addz";
	private static final String commandTextMsg = "[A]dd[Z] <zombie> <x> <y>";
	private static final String helpTextMsg = "Adds a zombie in position x, y.";
	
	public AddZombieCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	public AddZombieCommand(String zomName, int x, int y) {
		super(zomName, x, y, commandText, commandTextMsg, helpTextMsg);
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
	       Zombie zom = ZombieFactory.getZombie(objName);
		   if (zom != null) {
			   try {
				   game.addZombieToGame(zom, x, y);
				}
				catch(InvalidPositionException ex) {
					throw new CommandExecuteException(ex.getMessage(), ex);
				}
		   }
		   else throw new CommandExecuteException(" Unknown zombie name: " + objName);
		   game.updateGame();
		   return true;
	}
	
	public AddZombieCommand getNewAddCommand(String zomName, int x, int y) {return new AddZombieCommand(zomName,x,y);}
	
	public boolean isCommandValid(String comm) {
		if(commandText.equals(comm) || (commandText.substring(0, 1) + commandText.substring(3, 4)).equals(comm)) return true;
		return false;
	}
}
