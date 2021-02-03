package tp.p3.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static tp.p3.PlantsVsZombies.appName;
import static tp.p3.PlantsVsZombies.appVersion;

import tp.p3.controller.Controller;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.utility.MyFileUtils;

public class LoadCommand extends Command{
	
	private static final String commandText = "load";
	private static final String commandTextMsg = "[L]oa[d] <FileName>";
	private static final String helpTextMsg = "Load the state of the game from a file.";
	private final String fileName;
	
	public LoadCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
		fileName = "";
	}

	public LoadCommand(String fileName) {
		super(commandText, commandTextMsg, helpTextMsg);
		this.fileName = fileName;
	}

	public boolean execute(Game game) throws CommandExecuteException {
		if(MyFileUtils.isReadable(fileName)){
			try(BufferedReader file = new BufferedReader(new FileReader(fileName))){
				String input = file.readLine();
				if(input != null && input.trim().equals(appName + " " + appVersion)) {
					file.readLine();
					game.load(file);
				}
			}
			catch(IOException ex) {
				throw new CommandExecuteException(" Exceptions produced by failed or interrupted I/O operations", ex);
			}
			catch(Exception ex) {
				throw new CommandExecuteException(" File contents error: " + ex.getMessage(), ex);
			}
			Controller.printMessage(" Game successfully loaded from file " + fileName);
		}
		else{
			if(!MyFileUtils.isValidFilename(fileName)) throw new CommandExecuteException(" Invalid filename: the filename contains invalid characters");
			else throw new CommandExecuteException(" File not found");
		}
		return true;
	}


	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length >= 1 && (commandWords[0].equals(commandText) || commandWords[0].equals(commandText.substring(0, 1) + commandText.substring(3, 4)))){
			if(commandWords.length == 2) return new LoadCommand(commandWords[1]);
			throw new CommandParseException(IncorrectNumArgsMsg);
		}
		return null;
	}

}
