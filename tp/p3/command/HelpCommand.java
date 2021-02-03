package tp.p3.command;

import tp.p3.controller.Controller;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.logic.Game;
import tp.p3.utility.CommandParser;

public class HelpCommand extends NoParamsCommand {
	
	private static final String commandText = "help";
	private static final String commandTextMsg = "[H]elp";
	private static final String helpTextMsg = "Prints this help message.";
	
	public HelpCommand() {
		super(commandText, commandTextMsg, helpTextMsg);

	}

	public boolean execute(Game game) throws CommandExecuteException{
		Controller.printMessage(CommandParser.commandHelp());
		return false;
	}

}
