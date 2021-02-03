package tp.p3.command;

import tp.p3.controller.Controller;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.logic.Game;
import tp.p3.utility.ZombieFactory;

public class ZombieListCommand extends NoParamsCommand{
	
	private static final String commandText = "listz";
	private static final String commandTextMsg = "[L]ist[Z]ombies";
	private static final String helpTextMsg = "Prints the list of available zombies.";
	
	public ZombieListCommand() {
		super(commandText, commandTextMsg, helpTextMsg);

	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		Controller.printMessage(ZombieFactory.listOfAvilableZombies());
		return false;
	}
	
	public boolean isCommandValid(String comm) {
		if(commandText.equals(comm) || (commandText.substring(0, 1) + commandText.substring(4, 5)).equals(comm)) return true;
		return false;
	}
}
