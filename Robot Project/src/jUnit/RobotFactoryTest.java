package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import domain.Robot;
import domain.RobotException;
import domain.RobotType;
import facade.RobotFactory;

/**
 * Klasse dient zum Testen von der Klasse RobotFactory.
 *
 */
class RobotFactoryTest {

	private static RobotFactory r;
	
	/**
	 * Instanziiert eine RobotFactory-Instanz.
	 */
	@BeforeAll
	static void initFactory() {
		r = new RobotFactory();
	}
	
	/**
	 * Prüft die Instanziierung von RobotFactory.
	 */
	@Test
	@Order(1)
	void factoryErstellenTest() {
		assertNotNull(r);
		assertEquals(2, r.getLager().size());
	}
	
	/**
	 * @see RobotFactory#getRobot(String, RobotType)
	 * Prüft die Methode {@link RobotFactory#getRobot(String, RobotType)}, ob die Instanzen korrekt instanziiert werden.
	 * 
	 * @throws RobotException
	 */
	@Test
	@Order(2)
	void objekteErstellenTest() throws RobotException {
		r.getRobot("R", RobotType.C3PO);
		r.getRobot("R2", RobotType.C3PO);
		assertEquals(4, r.getLager().size());
//		assertEquals("R", r.getLager().get("R").getName());
	}
	
	/**
	 * @see RobotFactory#getRobot(String, RobotType)
	 * Prüft die Methode {@link RobotFactory#getRobot(String, RobotType)}, ob eine RobotException geworfen wird, beim Instanziieren von einem Nexus6-Objekt.
	 */
	@Test
	@Order(3)
	void objekteErstellenTest2() {
		assertThrows(RobotException.class, () -> {			
			r.getRobot("N", RobotType.NEXUS6);
		});
	}
	
	/**
	 * @see RobotFactory#getRoboterliste()
	 * Prüft die Methode {@link RobotFactory#getRoboterliste()}, ob die richtige Größe vom Array angegeben wird.
	 * 
	 * @throws RobotException
	 */
	@Test
	@Order(4)
	void getRoboterlisteTest() throws RobotException {
		r.getRobot("R", RobotType.C3PO);
		r.getRobot("R2", RobotType.C3PO);
		assertEquals(2, r.getRoboterliste().length);
	}
	
	/**
	 * @see RobotFactory#findeRoboter(int)
	 * Prüft die Methode {@link RobotFactory#findeRoboter(int)}, ob die gesuchte Instanz identisch ist wie in der HashMap von RobotFactory.
	 */
	@Test
	@Order(5)
	void findeRoboterTest() {
		Collection<Robot> co = r.getLager().values();
		int id = co.iterator().next().getId();
		assertTrue(r.getLager().get(id) == r.findeRoboter(id));
	}
}
