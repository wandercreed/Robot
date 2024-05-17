package domain;

/**
 * Die Umsetzung des Roboters Nexus6 nach den implementierenden Interfaces.
 * Attribute werden von Klasse Machine geerbt.
 * Klasse ist ein Singleton und dementsprechend auch nur eine einzige Instanz zur Verfügung.
 *
 */
public class Nexus6 extends Machine {
	
	private static final Nexus6 NEXUS = new Nexus6("Pris", 19281982);
	
	private Nexus6(String name, int id) {
		this.name = name;
		this.id = id;
		this.istEingeschaltet = false;
	}
	
	/**
	 * Gibt eine Instanz von Nexus6 zurück.
	 * 
	 * @return die einzige Instanz von Nexus6.
	 */
	public static Nexus6 getInstance() {
		return NEXUS;
	}

	/**
	 * Diese Methode hat keine Funktion
	 */
	@Override
	public void triggerPowerSwitch() {	
	}
	
	/**
	 * Ähnlich wie bei der Methode {@link Nexus6#triggerPowerSwitch()} hat diese Methode auch keine
	 * Funktion und wirft nur eine Exception.
	 */
	@Override
	public String speak(int[] zahlen) throws RobotException {
		throw new RobotIllegalStateException(this.name);
		
	}

	/**
	 * @see Nexus6#speak(int[])
	 */
	@Override
	public int[] think(int[] zahlen) throws RobotException {
		throw new RobotIllegalStateException(this.name);
	}
}
