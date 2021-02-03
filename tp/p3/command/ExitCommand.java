package tp.p3.command;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.logic.Game;
import tp.p3.logic.GameStates;

public class ExitCommand extends NoParamsCommand{
	
	private static final String commandText = "exit";
	private static final String commandTextMsg = "[E]xit";
	private static final String helpTextMsg = "Terminates the program.";
	
	public ExitCommand() {
		super(commandText, commandTextMsg, helpTextMsg);

	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		game.setGameState(GameStates.EXIT);
		return false;
	}


}
