package tp.p3.controller;

import tp.p3.command.*;
import tp.p3.exceptions.*;
import tp.p3.logic.Game;
import tp.p3.utility.CommandParser;

import java.util.Scanner;

public class Controller {
	private Game mGame;
	private Scanner in;
	public boolean PrintGame;
	private static final String errorCommandMessage = "Wrong command, type help";
	private static final String prompt = " Command > ";

	public Controller(Game game) {
		super();
		mGame = game;
		in = new Scanner(System.in);
		PrintGame = true;
	}

	public void run() {
		printGame();
		while (!mGame.isGameOver()) {
			userCommand();
			printGame();
		}
		System.out.println(mGame.getEndMessage());
	}

	private void userCommand() {
		boolean okCommand = false;
		String[] words;
		Command command = null;
		while(!okCommand) {
			System.out.print(prompt);
			words = in.nextLine().toLowerCase().trim().split("\\s+");
			try {
				command = CommandParser.parseCommand(words);
				if (command != null) {
					PrintGame = command.execute(mGame); 
					okCommand = true;
				}
				else System.out.println (" " + errorCommandMessage + "\n");
			}
			catch(CommandParseException | CommandExecuteException ex){
				System.out.format(ex.getMessage() + " %n %n");
			}
		}
	}
	
	private void printGame() {
		if(PrintGame) System.out.println(mGame);
	}
	
	public static void printMessage(String msg) { System.out.println(msg);}
	
	
	
}
