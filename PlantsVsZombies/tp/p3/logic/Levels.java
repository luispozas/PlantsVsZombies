package tp.p3.logic;

public enum Levels {
	EASY(3, 0.1), HARD(5, 0.2), INSANE(10, 0.3);
	
	private int NumZombies;
	private double Zomfrec;
	
	private Levels(int NumZombies, double ZomFrec) {
		this.NumZombies = NumZombies;
		this.Zomfrec = ZomFrec;
	}

	public static Levels levelExist(String level) {
		for (Levels lev : Levels.values()) {
			if (lev.name().equalsIgnoreCase(level)) return lev;
		}
		return null;
	}
	
	public int getNumberOfZombies() {
		return NumZombies;
	}
	
	public double getZombieFrequency() {
		return Zomfrec;
	}
	
	public static String all(String separator) {
		StringBuilder sb = new StringBuilder();
		for (Levels level : Levels.values()) sb.append(level.name() + separator);
		return sb.substring(0, sb.length() - separator.length()).toString();
	}
}
