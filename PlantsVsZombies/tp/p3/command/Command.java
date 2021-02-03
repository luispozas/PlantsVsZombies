package tp.p3.command;

import tp.p3.exceptions.*;
import tp.p3.logic.Game;

public abstract class Command {
	private String helpText;
	private String helpInfo;
	protected final String commandName;

	protected final String IncorrectNumArgsMsg;
	protected final String NoArgsMsg;
	protected final String IncorrectIntegerArgMsg;

	public Command(String commandText, String commandTextMsg, String helpTextMsg) {
		helpText = commandTextMsg;
		helpInfo = helpTextMsg;
		commandName = commandText;
		NoArgsMsg =  " \"" + commandName + "\" command has no arguments";
		IncorrectNumArgsMsg = " Incorrect number of arguments for " + commandText + " command: " + helpText;
		IncorrectIntegerArgMsg = " Invalid argument for " + commandName + " command, number expected: " + helpText;
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException;

	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
	public String helpText() {
		return " " + helpText + ": " + helpInfo;
	}
}
