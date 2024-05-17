package jUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import domain.R2D2;
import domain.RobotException;
import domain.RobotIllegalStateException;
import domain.RobotMagicValueException;
/**
 * Klasse dient zum Testen von der Klasse R2D2
 * unter regulär und exceptions Benutzerfällen.
 */
@TestMethodOrder(OrderAnnotation.class)
public class R2D2Test {

	private static R2D2 r2;
	private static int[] zahlenMit42 = {10,5,1,3,11,15,42};
	private static int[] zahlenOhne42 = {7,5,1,3,2,9};
	
	/**
	 * Initialisiert ein R2D2 Robot Object.
	 */
	@BeforeAll
	static void initialiseR2D2(){
		r2 = new R2D2("TestName",10);
	}
	
	/**
	 * @see R2D2#getId()
	 * @see R2D2#getName()
	 * @see R2D2#isPowerOn()
	 * Prüft ob die R2D2 Attributen richtig angelegt sind und
	 * durch{@link R2D2#getId(), R2D2#getName(), R2D2#isPowerOn()}
	 * zugreigbar sind.
	 */
	@Test
	@Order(1)
	 void testAtt() {
		assertEquals(r2.getId(), 10);
		assertEquals(r2.getName(), "TestName");
		assertFalse(r2.isPowerOn());
		
	}
	/**
	 * @see R2D2#triggerPowerSwitch()
	 * Prüft ob die {@link R2D2#triggerPowerSwitch()} funktioniert
	 */
	@Test
	@Order(2)
	void triggerPowerTest() {
		r2.triggerPowerSwitch();
		assertTrue(r2.isPowerOn());
		r2.triggerPowerSwitch();
		assertFalse(r2.isPowerOn());
	}
	
	/**
	 * @see R2D2#speak(int[])
	 * @see RobotIllegalStateException
	 * Prüft ob die {@link R2D2#speak(int[])} die RobotIllegalStateException wirft,
	 * wenn das Robot ist ausgeschaltet
	 * @throws RobotIllegalStateException
	 */
	@Test
	@Order(3)
	void speakTestPowerOFF() {
		assertThrows(RobotIllegalStateException.class,() -> r2.speak(zahlenMit42));
	}
	
	/**
	 * @see R2D2#getLastException()
	 * Prüft ob die {@link R2D2#getLastException()} genau das richtige Exception
	 * mit die Name von letze geworfene in {@link R2D2Test#speakTestPowerOFF()} Exception ist.
	 */
	@Test
	@Order(4)
	void getLastExceptionTest() {
    RobotException test = r2.getLastException();
    assertTrue(test.getRobotName().equals("TestName"));
	}
	
	
	/**
	 * @see R2D2#speak(int[])
	 * @see R2D2#triggerPowerSwitch()
	 * Prüft ob die Methode {@link R2D2#speak(int[])} richtig funktioniert und die 
	 * Chars auf die richtige positionen nach der Ausgabe sind.
	 */
	@Test
	@Order(5)
	void speakTestRegularCase() {
		String test = null;
		r2.triggerPowerSwitch();
		try {
			test = r2.speak(zahlenOhne42);
		} catch (RobotException e) {
			e.printStackTrace();
		}
		assertTrue(test.charAt(0) == '7');
		assertTrue(test.charAt(1) == ',');
		assertTrue(test.charAt(3) == '5');
	}
	
	/**
	 * @see R2D2#speak(int[])
	 * @see RobotMagicValueException
	 * @throws RobotMagicValueException
	 * Prüft ob die Methode {@link R2D2#speak(int[])} die {@link RobotMagicValueException}
	 * wirft, wenn die Zahl 42 in int[] vorkommt.
	 */
	@Test
	@Order(6)
	void speakTestPowerON42() {
		assertThrows(RobotMagicValueException.class,() -> r2.speak(zahlenMit42));

	}
	
	/**
	 * @see R2D2#think(int[])
	 * @see RobotIllegalStateException
	 * @throws RobotIllegalStateException
	 * Prüft ob die {@link R2D2#think(int[])} die {@link RobotIllegalStateException} wirft,
	 * wenn das Robot ist ausgeschaltet
	 */
	@Test
	@Order(7)
	void thinkTestPowerOFF(){
		r2.triggerPowerSwitch();
		assertFalse(r2.isPowerOn());
		assertThrows(RobotIllegalStateException.class, () -> r2.think(zahlenMit42));
		
	}
	/**
	 * @see R2D2#think(int[])
	 * @see R2D2#triggerPowerSwitch()
	 * Prüft ob die Methode {@link R2D2#think(int[])} richtig funktioniert und die 
	 * Zahlen in aufsteigende reihenfolge sortiert.
	 */
	@Test
	@Order(8)
	void thinkTestRegularCase() {
		r2.triggerPowerSwitch();
		int[] sortiert = {};
		try {
			sortiert = r2.think(zahlenOhne42);
		} catch (RobotException e) {
			e.printStackTrace();
		}
		assertTrue(sortiert[5] == 9);
		assertTrue(sortiert[3] == 5);
		assertTrue(sortiert[0] == 1);
		
		
	}
	
	/**
	 * @see R2D2#think(int[])
	 * @throws RobotMagicValueException
	 * Prüft ob die Methode {@link R2D2#think(int[])} die {@link RobotMagicValueException}
	 * wirft, wenn die Zahl 42 in int[] vorkommt.
	 */
	
	@Test
	@Order(9)
	void thinkTestPowerON42() {
		assertThrows(RobotMagicValueException.class,() -> r2.think(zahlenMit42));
	}
}
