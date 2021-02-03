package tp.p3.command;

import tp.p3.exceptions.*;
import tp.p3.logic.Game;
import tp.p3.printer.*;
import tp.p3.utility.PrinterManager;

public class PrintModeCommand extends Command{
	private static final String commandText = "printmode";
	private static final String commandTextMsg = "[P]rintMode <mode>";
	private static final String helpTextMsg = "Change print mode [Release|Debug].";
	private final String printMode;
	
	public PrintModeCommand(String printMode) {
		super(commandText, commandTextMsg, helpTextMsg);
		this.printMode = printMode;
	}
	
	public PrintModeCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
		printMode = "";
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length >= 1 && (commandWords[0].equals(commandText) || commandWords[0].equals(commandText.substring(0, 1)))){
			if(commandWords.length == 2) return new PrintModeCommand(commandWords[1]);
			throw new CommandParseException(IncorrectNumArgsMsg);
		}
		return null;
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		BoardPrinter printType = PrinterManager.parseBoardPrinter(printMode);
		if(printType != null)  game.setPrinter(printType);
		else throw new CommandExecuteException(" PrintMode " + printMode + " doesnt exist, try help.");
		return true;
	}

}
