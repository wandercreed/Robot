package jUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import domain.C3PO;
import domain.RobotException;
import domain.RobotIllegalStateException;
import domain.RobotMagicValueException;

@TestMethodOrder(OrderAnnotation.class)

/**
 * JUnit Test-Klasse zu C3PO
 */
public class C3POTest {

	/**
	 * Deklarierung einer Variablen vom Typ C3PO.
	 * Initialisierung von zwei finalen Int-Arrays mit den Zahlen 1-7 in willkuerlicher Anordnung,
	 * mit/ohne 42 zur gezielten Auslösung von Fehlern.
	 */
	private static C3PO c3;
	private static final int[] zahlenOhne42 = {7, 1, 4, 5, 3, 2, 6};
	private static final int[] zahlenMit42 = {7, 1, 4, 5, 3, 2, 6, 42};
	
	@BeforeAll
	static void initC3PO() {
		c3 = new C3PO("Robot", 10000);
	}
	
	/**
	 * Test der uebergebenen Attribute & dem standardmaessigen Setzen des PowerKnopfes auf AUS.
	 */
	@Test
	@Order(1)
	void AttributeTest() {
		assertEquals(c3.getName(), "Robot");
		assertEquals(c3.getId(), 10000);
		assertFalse(c3.isPowerOn());
	}
	
	/**
	 * Test der Methode {@link C3PO#triggerPowerSwitch()} durch deren Aufruf
	 * und anschließender Pruefung durch {@link C3PO#isPowerOn()}.
	 */
	@Test
	@Order(2)
	void triggerPowerSwitchTest() {
		assertFalse(c3.isPowerOn());
		c3.triggerPowerSwitch();
		assertTrue(c3.isPowerOn());
		c3.triggerPowerSwitch();
		assertFalse(c3.isPowerOn());
	}
	
	/**
	 * Test der Methode {@link C3PO#speak(int[])} bei Aufruf auf ausgeschalteten Roboter.
	 * Es wird eine RobtIllegalStateException erwartet.
	 */
	@Test
	@Order(3)
	void speakTestAusgeschaltet() {
		assertFalse(c3.isPowerOn());
		assertThrows(RobotIllegalStateException.class, () -> c3.speak(zahlenOhne42));
	}
	
	/**
	 * Test der Methode {@link C3PO#speak(int[])} bei Aufruf mit uebergebenen Int-Array,
	 * das die Zahl 42 enthaelt.
	 * Es wird eine RobotMagicValueException erwartet.
	 */
	@Test
	@Order(4)
	void speakTest42() {
		c3.triggerPowerSwitch();
		assertTrue(c3.isPowerOn());
		assertThrows(RobotMagicValueException.class, () -> c3.speak(zahlenMit42));
	}
	
	/**
	 * Test der Methode {@link C3PO#speak(int[])} bei Aufruf auf eingeschalten Roboter
	 * & der Uebergabe eines Int-Arrays ohne die Zahl 42.
	 * {@link C3PO#speak(int[])} soll dabei auf das uebergebene Int-Array einen String mit dessen Zahlen
	 * zurueckgeben, die durch ein ';' getrennt werden.
	 * 
	 * Der geliferte String muss dabei die 3-fache Länge des Int-Arrays haben, da auf jede Zahl zusätzlich 
	 * ein ';' & ' ' hinzukommt.
	 * Die korrekte Anordnung wird innerhalb der forEach-Schleife getestet.
	 * Die Variable i bestimmt dabei auf welches char getestet werden soll, also auf Zahl, Semikolon oder Leerzeichen.
	 * Die Variable j erhöht sich nur beim Test auf die Zahl und durchläuft so das Int-Array.
	 */
	@Test
	@Order(5)
	void speakTest() {
		assertTrue(c3.isPowerOn());
		String s = null;
		try {
			s = c3.speak(zahlenOhne42);
		} catch (RobotException e) {
			e.printStackTrace();
		}
		
		assertTrue((zahlenOhne42.length * 3) == s.length());
		
		int i = 0;
		int j = 0;
		for(char c : s.toCharArray()) {
			switch(i) {
			case 0:
				assertTrue(c == Character.forDigit(zahlenOhne42[j], 10)); j++; i++;
				break;
			case(1):
				assertTrue(c == ';'); i++;
				break;
			case(2):
				assertTrue(c == ' '); i=0;
				break;
				
			}
		}
	}
	
	/**
	 * Test der Methode {@link C3PO#think(int[])} bei Aufruf auf ausgeschalteten Roboter.
	 * Es wird eine RobtIllegalStateException erwartet.
	 */
	@Test
	@Order(6)
	void thinkTestAusgeschaltet() {
		c3.triggerPowerSwitch();
		assertFalse(c3.isPowerOn());
		assertThrows(RobotIllegalStateException.class, () -> c3.think(zahlenOhne42));
	}
	
	/**
	 * Test der Methode {@link C3PO#think(int[])} bei Aufruf mit uebergebenen Int-Array,
	 * das die Zahl 42 enthaelt.
	 * Es wird eine RobotMagicValueException erwartet.
	 */
	@Test
	@Order(7)
	void thinkTest42() {
		c3.triggerPowerSwitch();
		assertTrue(c3.isPowerOn());
		assertThrows(RobotMagicValueException.class, () -> c3.think(zahlenMit42));
	}
	
	/**
	 * Test der Methode {@link C3PO#think(int[])} bei Aufruf auf eingeschalten Roboter
	 * & der Uebergabe eines Int-Arrays ohne die Zahl 42.
	 * {@link C3PO#think(int[])} soll dabei das uebergeben Int-Array absteigend sortiert zurückgeben.
	 * Die korrekte Anordnung lässt sich stichprobenartig testen.
	 */
	@Test
	@Order(8)
	void thinkTest() {
		int[] zahlenSortiert = {};
		try {
			zahlenSortiert = c3.think(zahlenOhne42);
		} catch (RobotException e) {
			e.printStackTrace();
		}
		assertTrue(zahlenSortiert[0] == 7);
		assertTrue(zahlenSortiert[6] == 1);
		assertTrue(zahlenSortiert[3] == 4);
		
		
	}
	
	/**
	 * Test der Methode {@link C3PO#getLastException()}.
	 * Zunächst wird die in thinkTest42() zuletzt geworfene RobotMagicValueException erwartet.
	 * Auf ausgeschalteten Roboter wird nun eine RobotIllegalStateException erwartet.
	 */
	@Test
	@Order(9)
	void getLastExceptionTest() {
		assertEquals(c3.getLastException().getClass(), RobotMagicValueException.class);
		
		c3.triggerPowerSwitch();
		assertFalse(c3.isPowerOn());
		
		try {
			c3.think(zahlenOhne42);
		} catch (RobotException e) {
		}
		
		assertEquals(c3.getLastException().getClass(), RobotIllegalStateException.class);
	}
	
	
}
