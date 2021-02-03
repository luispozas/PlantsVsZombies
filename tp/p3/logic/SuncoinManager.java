package tp.p3.logic;

import tp.p3.exceptions.NotEnoughSuncoinsException;

public class SuncoinManager {
	private int Suncoins;

	public SuncoinManager(int coins) {
		Suncoins = coins;
	}
	
	public SuncoinManager(SuncoinManager copy) {
		Suncoins = copy.Suncoins;
	}

	public int getSuncoins() {return Suncoins;}

	public void setSuncoins(int coins) { 
		if (coins >= 0) Suncoins = coins;
	}
	
	public void payPlant(int cost) throws NotEnoughSuncoinsException{ 
		if (Suncoins >= cost) Suncoins -= cost;
		else throw new NotEnoughSuncoinsException();
	}

}
