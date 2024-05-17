package jUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.C3PO;
import domain.RobotException;

/**
 * JUnit Test-Klasse zu RobotException
 */
public class RobotExceptionTest {
	
	/**
	 * Deklarierung zweier Variablen vom Typ RobotException und C3PO
	 */
	private static RobotException re;
	private static C3PO c3;
	
	/**
	 * Ininialisierung einer neuen RoboterException mit dem Name des Roboters als Parameter
	 */
	@BeforeAll
	static void init() {
		c3 = new C3PO("RobotC3", 10000);
		re = new RobotException(c3.getName());
	}
	
	/**
	 * Test des übergebenen Roboternamen an RobotException
	 */
	@Test
	void robotExceptionTest() {
		assertEquals(c3.getName(), re.getRobotName());
	}
}
