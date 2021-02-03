package tp.p3.command;

import tp.p3.exceptions.*;
import tp.p3.logic.Game;

public abstract class AddCommand extends Command{
	protected final String objName;
	protected final int x;
	protected final int y;
	
	public AddCommand(String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
		objName = "";
		x = -1;
		y = -1;
	}
	
	public AddCommand(String objName, int x, int y, String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
		this.objName = objName;
		this.x = x;
		this.y = y;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords.length >= 1 && isCommandValid(commandWords[0])) {
			if(commandWords.length == 4) {
				try {
					return getNewAddCommand(commandWords[1],Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
				}
				catch(NumberFormatException e){
					throw new CommandParseException(IncorrectIntegerArgMsg, e);
				}
			}else throw new CommandParseException(IncorrectNumArgsMsg);
		}
		return null;
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	public abstract AddCommand getNewAddCommand(String plantName, int x, int y);
	
	public boolean isCommandValid(String comm) {
		if(commandName.equals(comm) || commandName.substring(0, 1).equals(comm)) return true;
		return false;
	}

}
