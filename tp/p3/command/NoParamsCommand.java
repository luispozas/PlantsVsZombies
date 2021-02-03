package tp.p3.command;

import tp.p3.exceptions.*;
import tp.p3.logic.Game;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);

	}

	public abstract boolean execute(Game game) throws CommandExecuteException;

	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length >= 1 && isCommandValid(commandWords[0])) {
			if(commandWords.length > 1) throw new CommandParseException(NoArgsMsg);
			return this;
		}
	    return null;
	}
	
	public boolean isCommandValid(String comm) {
		if(commandName.equals(comm) || commandName.substring(0, 1).equals(comm)) return true;
		return false;
	}
}
