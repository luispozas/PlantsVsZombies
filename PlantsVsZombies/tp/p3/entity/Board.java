package tp.p3.entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static tp.p3.logic.Game.invalidAttributeValueMsq;
import static tp.p3.logic.Game.ROWS;
import static tp.p3.logic.Game.COLUMNS;
import static tp.p3.utility.MyFileUtils.loadLine;

import tp.p3.exceptions.FileContentsException;
import tp.p3.exceptions.InvalidPositionException;
import tp.p3.logic.Game;
import tp.p3.utility.PlantFactory;
import tp.p3.utility.ZombieFactory;

public class Board {
	private GameObjectList PlantList;
	private GameObjectList ZomList;
	
	public Board(){
		PlantList = new GameObjectList();
		ZomList = new GameObjectList();
	}
	
	public void load(BufferedReader inStream, Game gamecopy) throws IOException, FileContentsException{
		GameObjectList oldPlantList = PlantList;
		GameObjectList oldZomList = ZomList;
		try {
			PlantList = new GameObjectList();
			loadObjectList(loadLine(inStream, "plantList", true), gamecopy, true);
			ZomList = new GameObjectList();
			loadObjectList(loadLine(inStream, "zombieList", true), gamecopy, false);
		}
		catch(FileContentsException ex) {
			PlantList = oldPlantList;
			ZomList = oldZomList;
			throw ex;
		}
	}
	
	private void loadObjectList(String[] objList, Game gamecopy, boolean isPlant) throws FileContentsException {
		String prefix = isPlant ? "plantList" : "zombieList";
		String[] data;
		String objName;
		if(objList != null && objList.length > 0) {
			for(String strObj : objList) {
				if(!strObj.contains(":")) throw new FileContentsException(invalidAttributeValueMsq + prefix);
				objName = strObj.substring(0, strObj.indexOf(":"));
				data = strObj.substring(strObj.indexOf(":") + 1).split(":");
				GameObject obj = isPlant ? PlantFactory.getPlant(objName) : ZombieFactory.getZombie(objName);
				if(obj == null || !obj.parseObjectAttributes(data)) throw new FileContentsException(invalidAttributeValueMsq + prefix);
				obj.setGame(gamecopy);
				try {
					if(isPlant) addPlant((Plant)obj);
					else addZombie((Zombie)obj);
				}
				catch (InvalidPositionException ex) {
					throw new FileContentsException(invalidAttributeValueMsq + prefix);
				}
			}
		}
	}
	
	
	public void store(BufferedWriter outChars) throws IOException {
		outChars.write("\nplantList: ");
		for (int i = 0; i < PlantList.getNumObjects(); i++) {
			outChars.write(PlantList.getStringToWriteOnFile(i));
			if(i < PlantList.getNumObjects()-1) outChars.write(", ");
		}
		outChars.write("\nzombieList: ");
		for (int j = 0; j < ZomList.getNumObjects() ; j++) {
			outChars.write(ZomList.getStringToWriteOnFile(j));
			if(j < ZomList.getNumObjects()-1) outChars.write(", ");
		}
	}
	
	
	public void addPlant(Plant plant) throws InvalidPositionException {
		int x = plant.getPosX(), y = plant.getPosY();
		if(y < COLUMNS - 1 && isIntoBoard(x, y)) {
			if(isCellEmpty(x, y)) {
				if(!PlantList.add(plant)) throw new InvalidPositionException(" Failed to add " + plant.getObjectName() + ": maximum number of objects reached");
			}
			else throw new InvalidPositionException(" Failed to add " + plant.getObjectName() + ": position (" + x + ", " + y + ") is already occupied");		
		}
		else throw new InvalidPositionException(" Failed to add " + plant.getObjectName() + ": (" + x + ", " + y + ") is an invalid position");
	}
	
	public void addZombie(Zombie zombie) throws InvalidPositionException {
		int x = zombie.getPosX(), y = zombie.getPosY();
		if(isIntoBoard(x, y)) {
			if(isCellEmpty(x, y)) {
				if(!ZomList.add(zombie)) throw new InvalidPositionException(" Failed to add " +zombie.getObjectName() + ": maximum number of objects reached");
			}
			else throw new InvalidPositionException(" Failed to add " + zombie.getObjectName() + ": position (" + x + ", " + y + ") is already occupied");
		}
		else throw new InvalidPositionException(" Failed to add " + zombie.getObjectName() + ": (" + x + ", " + y + ") is an invalid position");
	}
	
	public void updateBoard() {
		PlantList.updateList();
		ZomList.updateList();
	}
	
	public boolean ZombiesAtEnd() {
		return ZomList.ObjectsAtEnd();
	}
	
	public int getNumZombiesintoBoard() {return ZomList.getNumObjects();}
	
	public int getNumPLantsintoBoard() {return PlantList.getNumObjects();}
	
	public int getNumObjects() {return ZomList.getNumObjects() + PlantList.getNumObjects();}
	
	public void attackZombie(int damage, int posX, int posY) {
		ZomList.attackObject(damage, posX, posY, true);
	}
	
	public void attackPlant(int damage, int posX, int posY) {
		PlantList.attackObject(damage, posX, posY, false);
	}
	
	public void attackZombieinArea(int damage, int posX, int posY) {
		ZomList.attackObjectArea(damage, posX, posY);
	}
	
	public int findPlantIdx(int posX, int posY) {
		return PlantList.findObjectIdx(posX, posY, false);
	}
	
	public int findZombieIdx(int posX, int posY, boolean inRow) {
		return ZomList.findObjectIdx(posX, posY, inRow);
	}
	
	public boolean isCellEmpty(int posX, int posY) {
		boolean empty = false;
		if (PlantList.findObjectIdx(posX, posY, false) == -1) {
			if (ZomList.findObjectIdx(posX, posY, false) == -1) empty = true;
		}
		return empty;
	}
	
	public String stringOfElementRelease(int posX, int posY) {
		String type = "       ";
		int idx = PlantList.findObjectIdx(posX, posY, false);
		if (idx != -1) type = PlantList.getStringReleaseInPos(idx);
		else {
			idx = ZomList.findObjectIdx(posX, posY, false);
			if (idx != -1) type = ZomList.getStringReleaseInPos(idx);
		}
		return type;
	}
	
	public String stringOfElementDebug(int idx) {
		String type = "";
		int NumObjects = PlantList.getNumObjects() + ZomList.getNumObjects();
		if(idx >= 0 && idx < NumObjects) {
			if(idx < PlantList.getNumObjects()) type = PlantList.getStringDebugInPos(idx);
			else type = ZomList.getStringDebugInPos(idx - PlantList.getNumObjects());
		}	
		return type;
	}
	
	public boolean isIntoBoard(int posX, int posY) {
		return (posX >= 0 && posX < ROWS) && (posY >= 0 && posY < COLUMNS);
	}
	
}
