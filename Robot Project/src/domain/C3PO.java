package domain;

import java.util.stream.IntStream;

import infrastructure.BlackBox;

/**
 * Die Umsetzung des Roboters C3PO nach den implementierenden Interfaces.
 * Attribute werden von Klasse Machine geerbt.
 *
 */
public class C3PO extends Machine {
	
	public C3PO(String name, int id) {
		this.name = name;
		this.id = id;
		this.istEingeschaltet = false;
	}

	/**
	 * @see RobotInstructions#speak(int[])
	 * @see C3PO#fehlerWerfen(RobotException)
	 * @see C3PO#umwandeln(int[])
	 * 
	 * @param zahlen
	 * @return das in ein String umgewandelte Int-Array.
	 * @throws RobotIllegalStateException
	 * 
	 * Laut Interface RobotInstructions darf diese Methode nicht auf ausgeschalteten Robotern aufgerufen werden.
	 * Ist dies der Fall wird die private Methode {@link C3PO#fehlerWerfen(RobotException)} aufgerufen und eine neue RobotIllegalStateException übergeben.
	 * 
	 * Die Implementierung der speak Methode erfolgt in der privaten Methode {@link C3PO#umwandeln(int[])}, an die das Int-Array weitergegeben wird.
	 */
	@Override
	public String speak(int[] zahlen) throws RobotException {
		if(this.istEingeschaltet == false)
			BlackBox.fehlerWerfen(new RobotIllegalStateException(this.name), this.id);
		return umwandeln(zahlen);
	}

	/**
	 * @see RobotInstructions#think(int[])
	 * @see C3PO#fehlerWerfen(RobotException)
	 * 
	 * @param zahlen
	 * @return das absteigend sortierte Int-Array zahlen
	 * @throws RobotIllegalStateException und RobotMagicValueException
	 * 
	 * Laut Interface RobotInstructions darf diese Methode nicht auf ausgeschalteten Robotern aufgerufen werden.
	 * Ist dies der Fall wird die private Methode {@link C3PO#fehlerWerfen(RobotException)} aufgerufen und eine neue RobotIllegalStateException übergeben.
	 * 
	 * Das übergebene Int-Array wird mit Hilfe eines Insertion-Algorithmus absteigend sortiert.
	 * Falls dabei die Zahl 42 auftaucht wird {@link C3PO#fehlerWerfen(RobotException)} aufgerufen und eine neue RobotMagicValueException übergeben. 
	 */
	@Override
	public int[] think(int[] zahlen) throws RobotException {
		if(this.istEingeschaltet == false)
			BlackBox.fehlerWerfen(new RobotIllegalStateException(this.name), this.id);
		int temp;
		for(int i = 0; i < zahlen.length; i++) {
			temp = zahlen[i];
			if(temp == 42)
				BlackBox.fehlerWerfen(new RobotMagicValueException(this.name), this.id);
			int j = i;
			while(j > 0 && zahlen[j-1] <= temp) {
				zahlen[j] = zahlen[j-1];
				j--;
			}
			zahlen[j] = temp;
		}
		return zahlen;
	}
	
	/**
	 * 
	 * @param zahlen
	 * @return zahlen als String mit ';' als Trennzeichen
	 * @throws RobotMagicValueException
	 * 
	 * Zunächst wird geprüft, ob zahlen die Zahl 42 enthält und falls der Fall {@link C3PO#fehlerWerfen(RobotException)} aufgerufen und eine neue RobotMagicValueException übergeben.
	 * 
	 * Ein neuer StringBuilder wird initialisiert.
	 * Es wird ein Datenstrom erzeugt und die zahlen übergeben. Für jede Zahl des Stroms wird diese dem StringBuilder mit einem Semikolon angehängt.
	 */
	
	private String umwandeln(int[] zahlen) throws RobotException {
		for(int zahl : zahlen) {
			if(zahl == 42)
				BlackBox.fehlerWerfen(new RobotMagicValueException(this.name), this.id);
		}	
		StringBuilder sb = new StringBuilder();
		IntStream.of(zahlen).forEach(zahl -> sb.append(zahl + "; "));
		return sb.toString();
	}

}
