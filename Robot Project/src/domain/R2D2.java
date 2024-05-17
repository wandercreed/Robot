package domain;

import java.util.*;
import java.util.stream.IntStream;

import infrastructure.BlackBox;

/**
 * Die Umsetzung des Roboters R2D2 nach den implementierenden Interfaces.
 * Attribute werden von Klasse Machine geerbt.
 *
 */
public class R2D2 extends Machine {

	public R2D2(String name, int id) {
		this.name = name;
		this.id = id;
		this.istEingeschaltet = false;
	} 
	
	/**
	 * 
	 * @param zahlen
	 * @return zahlen als String mit ', ' als Trennzeichen
	 * @throws RobotMagicValueException
	 * @throws RobotIllegalStateException
	 * Zunächst wird geprüft, ob Robot eingeschaltet ist und ein StringBuilder initiialisiert 
	 * Danach wird geprüft, ob zahlen die Zahl 42 enthält und falls der Fall {@link R2D2#fehlerWerfen(RobotException)} aufgerufen und eine neue RobotMagicValueException übergeben.
	 * Es wird ein Datenstrom erzeugt und die zahlen übergeben. 
	 * Für jede Zahl des Stroms wird diese dem StringBuilder mit eine Komma angehängt.
	 */
	@Override
	public String speak(int[] zahlen) throws RobotException {
		if (this.istEingeschaltet == false)
			BlackBox.fehlerWerfen(new RobotIllegalStateException(this.name), this.id);
		
		StringBuilder sb = new StringBuilder();
		for (int zahl : zahlen) {
			if (zahl == 42)
				BlackBox.fehlerWerfen(new RobotMagicValueException(this.name), this.id);
		}
		IntStream.of(zahlen).forEach(zahl -> sb.append(zahl + ", "));
		return sb.toString();
	}
    
	/**
	 * @see RobotInstructions#think(int[])
	 * @see R2D2#fehlerWerfen(RobotException)
	 * @see R2D2#selectionSort(int[])
	 * @param zahlen
	 * @return das aufsteigend sortierte Int-Array zahlen
	 * @throws RobotIllegalStateException und RobotMagicValueException
	 * 
	 * Laut Interface RobotInstructions darf diese Methode nicht auf ausgeschalteten Robotern aufgerufen werden.
	 * Ist dies der Fall wird die private Methode {@link R2D2#fehlerWerfen(RobotException)} aufgerufen und eine neue RobotIllegalStateException übergeben.
	 * 
	 * Das übergebene Int-Array wird mit Hilfe eines Selection-Algorithmus {@link R2D2#selectionSort(int[])} aufsteigend sortiert.
	 * Falls dabei die Zahl 42 auftaucht wird {@link R2D2#fehlerWerfen(RobotException)} aufgerufen und eine neue RobotMagicValueException übergeben. 
	 */
	@Override
	public int[] think(int[] zahlen) throws RobotException {
		if (this.istEingeschaltet == false)
			BlackBox.fehlerWerfen(new RobotIllegalStateException(this.name), this.id);
		for(int i : zahlen) {
			if(i == 42) BlackBox.fehlerWerfen( new RobotMagicValueException(this.name), this.id);
		}
		/*
		 * Schnellste weg zur Sortierung:
		 * int[] sortiert = Arrays.stream(zahlen).map(i -> +i).sorted().toArray();
		 */
		return selectionSort(zahlen);
	}
	
     /**
      * @param liste
      * @return liste aufsteigend sortiert
      * Nimmt eine int[] als Parameter, sortiert aufsteigend und liefert die selbe Liste zurück. 
     */
	private int[] selectionSort(int[] liste) {
		for(int i = 0; i < liste.length-1;i++) {
			int min = i;
			 for(int j = i+1; j< liste.length;j++) {
				 if(liste[j] < liste[min]) min=j;
			 }
			 int tmp = liste[min];
			 liste[min] = liste[i];
			 liste[i] = tmp;
		}
		return liste;
	}

}
