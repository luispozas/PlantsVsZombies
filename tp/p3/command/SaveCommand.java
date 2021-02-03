package tp.p3.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static tp.p3.PlantsVsZombies.appName;
import static tp.p3.PlantsVsZombies.appVersion;
import tp.p3.controller.Controller;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.utility.MyFileUtils;

public class SaveCommand extends Command{
	private static final String commandText = "save";
	private static final String commandTextMsg = "[SA]ve <fileName>";
	private static final String helpTextMsg = "Save the actual state in file [fileName].";
	private final String fileName;
	
	public SaveCommand(String fileName) {
		super(commandText, commandTextMsg, helpTextMsg);
		this.fileName = fileName.trim() + ".dat";
	}
	
	public SaveCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
		fileName = "";
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length >= 1 && (commandWords[0].equals(commandText) || commandWords[0].equals(commandText.substring(0, 2)))){
			if(commandWords.length == 2) return new SaveCommand(commandWords[1]);
			throw new CommandParseException(IncorrectNumArgsMsg);
		}
		return null;
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		if(MyFileUtils.isValidFilename(fileName)) {
			try (BufferedWriter outChars = new BufferedWriter(new FileWriter(fileName))) {
				outChars.write(appName + " " + appVersion + "\n"); 
				game.store(outChars);
			}
			catch (IOException ex) {
				throw new CommandExecuteException(" Exceptions produced by failed or interrupted I/O operations", ex);
			}
			Controller.printMessage(" Game successfully saved in file " + fileName +". Use the load command to reload it");
		}
		else throw new CommandExecuteException(" Invalid filename: the filename contains invalid characters");
		return false;
	}

}
