package tp.p3.command;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.logic.Game;

public class ResetCommand extends NoParamsCommand {
	
	private static final String commandText = "reset";
	private static final String commandTextMsg = "[R]eset";
	private static final String helpTextMsg = "Starts a new game.";

	public ResetCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		game.resetGame();
		return true;
	}

}
