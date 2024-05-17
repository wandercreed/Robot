package jUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import domain.RobotIllegalStateException;
import domain.RobotMagicValueException;
import infrastructure.BlackBox;

/**
 * JUnit Test-Klasse zu BlackBox
 */
public class BlackBoxTest {
	
	/**
	 * Test der Speicherung einer RobotIllegalstateException mit der Uebergabe einer ID 100
	 * und anschließendem Laden ueber entsprechende ID.
	 * Diese wird anschließend mit einer RobotMagicValueException ueberschrieben.
	 */
	@Test
	void fehlerSpeichernTest() {
		try {
			BlackBox.speicherObject(new RobotIllegalStateException("Robotername"), 100);
			assertEquals(BlackBox.ladeObject(100).getClass(), RobotIllegalStateException.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			BlackBox.speicherObject(new RobotMagicValueException("Robotername"), 100);
			assertEquals(BlackBox.ladeObject(100).getClass(), RobotMagicValueException.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
