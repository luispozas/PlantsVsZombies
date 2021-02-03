package tp.p3.entity;

import tp.p3.logic.Game;

public class GameObjectList {
	private static final int MAXARRAY = Game.MAXOBJECTS;
	private GameObject[] objArray;
	private int counter = 0;

	public GameObjectList() {
		objArray = new GameObject[MAXARRAY];
	}

	public boolean add(GameObject obj){
		if (counter < MAXARRAY) {
			objArray[counter++] = obj;
			return true;
		}
		else return false;
	}

	public void delete(int idx) {
		for (int i = idx; i < counter; i++) objArray[i] = objArray[i + 1];
		counter--;
	}

	public int getNumObjects() { return counter;}

	public void updateList() {
		for (int i = 0; i < counter; i++) {
			objArray[i].update();
			if(objArray[i].getHealth() == 0) delete(i--);
		}
	}

	// si inRow true se busca en linea el primero a partir
		// de posY
	public int findObjectIdx(int posX, int posY, boolean inRow) { 
		boolean posOk = false;
		for(int i = 0; i < counter; i++) {
			posOk = (inRow) ? objArray[i].getPosY() > posY : objArray[i].getPosY() == posY;
			if (posOk && objArray[i].getPosX() == posX) return i;
		}
		return -1;
	}

	public void attackObject(int damage, int posX, int posY, boolean inRow) {
		int idx = findObjectIdx(posX, posY, inRow);
		if (idx != -1 && objArray[idx].lowerHealth(damage)) delete(idx);
	}
	
	public void attackObjectArea (int damage, int posX, int posY) {
		int idx[] = {0, -1, 1};
		for (int i = 0; i < idx.length; i++) {
			for (int j = 0; j < idx.length; j++) {
				attackObject(damage, posX+idx[i], posY+idx[j], false);
			}
		}
	}
	
	public boolean ObjectsAtEnd() { /// funcion solamente usada en lista de zombies
		for(int i = 0; i < counter; i++) if(objArray[i].getPosY() == 0) return true;
		return false;
	}

	public String getStringReleaseInPos(int idx) {
		return (objArray[idx] + "");
	}
	
	public String getStringDebugInPos(int idx) {
		return (objArray[idx].toStringDebug());
	}
	
	public String getStringToWriteOnFile(int idx) {
		return (objArray[idx].StringToWriteOnFile());
	}
	
	
	
}
