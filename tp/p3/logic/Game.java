package tp.p3.logic;
 
import tp.p3.entity.*;
import tp.p3.exceptions.*;
import tp.p3.printer.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import static tp.p3.utility.MyFileUtils.loadLine;

public class Game {
	public static final int ROWS = 4;
	public static final int COLUMNS = 8;
	private static final int SUNCOININI = 50;
	public static final int MAXOBJECTS = ROWS*COLUMNS;
	private Board board;
	private SuncoinManager CoinManager;
	private ZombieManager ZomManager;
	private Random rand;
	private Levels difficulty;
	private GameStates actualState; 
	private int seed;
	private int NumCycles;
	private boolean existseed;
	private GamePrinter printer;
	
	public static final String invalidAttributeValueMsq = "invalid value on attribute ";
	
	public Game(Levels difficulty, int seed, GamePrinter printer) {
		this.difficulty = difficulty;
		existseed = true;
		this.seed = seed;
		rand = new Random(seed);
		board = new Board();
		NumCycles = 0;
		CoinManager = new SuncoinManager(SUNCOININI);
		ZomManager = new ZombieManager(difficulty, rand);
		actualState = GameStates.RUNNING;
		this.printer = printer;
	}
	
	public Game(Levels difficulty, GamePrinter printer) {
		this.difficulty = difficulty;
		existseed = false;
		rand = new Random();
		board = new Board();
		NumCycles = 0;
		CoinManager = new SuncoinManager(SUNCOININI);
		ZomManager = new ZombieManager(difficulty, rand);
		actualState = GameStates.RUNNING;
		this.printer = printer;
	}
	
	public Game(Game copy) { //Copia profunda de SuncoinManager y ZombieManager, en los demas atributos superficial
		difficulty = copy.difficulty;
		existseed = copy.existseed;
		seed = copy.seed;
		rand = copy.rand;
		board = copy.board;
		NumCycles = copy.NumCycles;
		CoinManager = new SuncoinManager(copy.CoinManager);
		ZomManager = new ZombieManager(copy.ZomManager);
		actualState = copy.actualState;
		printer = copy.printer;
	}
	
	public void RestoreFromCopy(Game copy) {
		difficulty = copy.difficulty;
		existseed = copy.existseed;
		seed = copy.seed;
		rand = copy.rand;
		board = copy.board;
		NumCycles = copy.NumCycles;
		CoinManager = copy.CoinManager;
		ZomManager = copy.ZomManager;
		actualState = copy.actualState;
		printer = copy.printer;
	}
	

	public GameStates getGameState() {return actualState;}
	
	public void setGameState(GameStates state){actualState = state;}
	
	public int getNumCycles() {return NumCycles;}
	
	public int getSuncoins() {return CoinManager.getSuncoins();}
	
	public int getZombiesLeft() {return ZomManager.zombiesLeft();}
	
	public int getSeed() {return seed;}
	
	public Levels getLevel() {return difficulty;}
	
	public void addSuncoins(int suns) {
		CoinManager.setSuncoins(CoinManager.getSuncoins() + suns);
	}
	
	public void updateGame() {
		computerAction();
		update();
	}
	
	private void computerAction() {
		Zombie zom = ZomManager.generateZombie();
		if(zom != null) {
			zom.setGame(this);
			try {
				board.addZombie(zom); 
				ZomManager.reduceNumZombies();
			}
			catch(InvalidPositionException ex) {} //Solamente lanzara excepcion si no cupieran mas zombies en la lista
		}
	}
	
	private void update() { 
		NumCycles++;
		board.updateBoard();
	}

	public String getEndMessage() {
		if(actualState != GameStates.EXIT) {
			if (actualState == GameStates.PLAYERWINS) return ("\n GameOver \n Player wins!");
			else if (actualState == GameStates.ZOMBIESWIN) return ("\n GameOver \n Zombies win!");
		}
		return ("\n GameOver");
	}
	
	public boolean isGameOver() { 
		boolean end = true;
		if(actualState == GameStates.RUNNING) {
			if (board.ZombiesAtEnd()) actualState = GameStates.ZOMBIESWIN;
			else if (ZomManager.zombiesLeft() <= 0 && board.getNumZombiesintoBoard() == 0) actualState = GameStates.PLAYERWINS;
			else end = false;
		}
		return end;
	}
	
	public void damageZombie(int damage, int posX, int posY) { // Linea de Planta que Ataca (posX)
		board.attackZombie(damage, posX, posY);
	}
	
	public void damageZombiesinArea(int damage, int posX, int posY) { //Ataque de cherrybomb
		board.attackZombieinArea(damage, posX, posY);
	}

	public void damagePlant(int damage, int posX, int posY) { // Posicion del Zombie
		posY--; // Ataca 1 Posicion Delante
		board.attackPlant(damage, posX, posY);
	}

	public boolean canAttackZombie(int posX, int posY) { // Posicion del Zombie
		boolean ok = true;
		posY--; // ataca 1 posicion delante
		if (board.findPlantIdx(posX, posY) == -1) ok = false;
		return ok;
	}

	public boolean canMoveZombie(int posX, int posY) { // posicion del zombie
		boolean ok = false;
		posY--; // avanza 1 posicion delante
		if (board.isIntoBoard(posX, posY)) {
			if (board.findZombieIdx(posX, posY, false) == -1) ok = true;
		}
		return ok;
	}

	public void resetGame() {
		rand = existseed ? new Random(seed) : new Random();
		board = new Board();
		NumCycles = 0;
		CoinManager = new SuncoinManager(SUNCOININI);
		ZomManager = new ZombieManager(difficulty, rand);
		actualState = GameStates.RUNNING;
	}

	public String toString() {
		return printer.printGame(this);
	}

	public void addPlantToGame (Plant plant, int x, int y) throws NotEnoughSuncoinsException, InvalidPositionException{
		plant.setPosX(x);
		plant.setPosY(y);
		plant.setGame(this);
		CoinManager.payPlant(plant.COST);
		board.addPlant(plant);
	}
	
	public void addZombieToGame (Zombie zom, int x, int y) throws InvalidPositionException{
		zom.setPosX(x);
		zom.setPosY(y);
		zom.setGame(this);
		board.addZombie(zom);
	}
	
	
	public int getNumObjects() { return board.getNumObjects();}
	
	public void setPrinter(GamePrinter print) {printer = print;}
	
	public String stringOfElementRelease(int posX, int posY) {
		return board.stringOfElementRelease(posX, posY);
	}
	
	public String stringOfElementDebug(int idx) {
		return board.stringOfElementDebug(idx);
	}
	
	public void store(BufferedWriter outChars) throws IOException {
		outChars.write("\ncycle: " + NumCycles);
		outChars.write("\nsunCoins: " + CoinManager.getSuncoins());
		outChars.write("\nlevel: " + difficulty);
		outChars.write("\nremZombies: " + ZomManager.zombiesLeft());
		board.store(outChars);
	}
	
	
	public void load(BufferedReader inStream) throws IOException, FileContentsException{
		Game copy = new Game(this);
		try {
			loadGameAttributes(inStream);
			board.load(inStream, this);
		}
		catch(Exception ex) {
			RestoreFromCopy(copy);
			throw ex;
		}
	}
	
	private void loadGameAttributes(BufferedReader inStream) throws IOException, FileContentsException{
		String prefix = "";
		try {
			prefix = "cycle";
			NumCycles = Integer.parseInt(loadLine(inStream, prefix, false)[0]);
			prefix = "sunCoins";
			CoinManager.setSuncoins(Integer.parseInt(loadLine(inStream, prefix, false)[0]));
			difficulty = Levels.levelExist(loadLine(inStream, "level", false)[0]);
			if(difficulty == null) throw new FileContentsException(invalidAttributeValueMsq + "level");
			ZomManager.setDifficulty(difficulty);
			prefix = "remZombies";
			ZomManager.setNumZombies(Integer.parseInt(loadLine(inStream, prefix, false)[0]));
		}
		catch(NumberFormatException ex) {
			throw new FileContentsException(invalidAttributeValueMsq + prefix);
		}
	}
	
	
}
