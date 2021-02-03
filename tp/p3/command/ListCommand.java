package tp.p3.command;

import tp.p3.controller.Controller;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.logic.Game;
import tp.p3.utility.PlantFactory;

public class ListCommand extends NoParamsCommand {
	
	private static final String commandText = "list";
	private static final String commandTextMsg = "[L]ist";
	private static final String helpTextMsg = "Prints the list of available plants.";
	
	public ListCommand() {
		super(commandText, commandTextMsg, helpTextMsg);

	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		Controller.printMessage(PlantFactory.listOfAvilablePlants());
		return false;
	}

}
