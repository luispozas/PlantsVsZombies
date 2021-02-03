package tp.p3.utility;

import tp.p3.command.*;
import tp.p3.exceptions.CommandParseException;

public class CommandParser{
	
	private static Command[] availableCommands = { 
		new AddPlantCommand(),
		new AddZombieCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new ListCommand(),
		new ZombieListCommand(),
		new PrintModeCommand(),
		new LoadCommand(),
		new SaveCommand(),
		new NoneCommand()
	};

	public static Command parseCommand(String[] words) throws CommandParseException { 
		Command comm = null;
		for(Command itComm : availableCommands) {
			comm = itComm.parse(words);
			if(comm != null) return comm;
		}
		return null;
	}
	
	public static String commandHelp() {
		StringBuilder msg = new StringBuilder("\n");
		for(int i = 0; i < availableCommands.length; i++) msg.append(" " + availableCommands[i].helpText() + "\n");
		return msg.toString();	
	}
	
}