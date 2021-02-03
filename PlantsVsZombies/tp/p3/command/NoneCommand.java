package tp.p3.command;

import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public class NoneCommand extends Command{

	private static final String commandText = "none";
	private static final String commandTextMsg = "[N]one";
	private static final String helpTextMsg = "Skips cycle.";
	
	public NoneCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	public boolean execute(Game game) {
		game.updateGame();
		return true;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords.length >= 1 && (commandText.equals(commandWords[0]) || commandText.substring(0, 1).equals(commandWords[0]) || commandWords[0].equals(""))) {
			if(commandWords.length > 1) throw new CommandParseException(NoArgsMsg);
			return this;
		}
	    return null;
	}

}
