package facade;

import java.util.Collection;
import java.util.HashMap;

import domain.C3PO;
import domain.Nexus6;
import domain.R2D2;
import domain.Robot;
import domain.RobotException;
import domain.RobotType;
import infrastructure.BlackBox;

/**
 * Klasse dient zum erstellen von R2D2 und C3PO Instanzen über die {@link RobotFactory#getRobot(String, RobotType)} Methode.
 * 
 */
public class RobotFactory {
	
	private HashMap<Integer, Robot> lager = new HashMap<>();

	/**
	 * Erstellt eine Instanz von C3PO oder R2D2, mit einer zufälligen ID in einem bestimmten Wertebereich und dem angegebenen Namen.
	 * 
	 * @param name
	 * @param type
	 * @return die vom jeweiligen Typ abhängige Instanz
	 * @throws RobotException
	 */
	public Robot getRobot(String name, RobotType type) throws RobotException {
		Robot r = null;
		
		switch (type) {
		case C3PO:
			int id = (int) (Math.random() * ((20000 - 10000)));
			if (lager.containsKey(id) == false) {
				r = new C3PO(name, id);
			} else {
				System.err.println("ID schon vorhanden");
				BlackBox.fehlerWerfen(new RobotException(name), id);
			}
			break;

		case R2D2:
			int id2 = (int) (Math.random() * 10000);
			if (lager.containsKey(id2) == false) {
				r = new R2D2(name, id2);
			} else {
				System.err.println("ID schon vorhanden");
				BlackBox.fehlerWerfen(new RobotException(name), id2);
			}
			break;

		case NEXUS6:
			System.err.println("Wird nicht produziert");
			BlackBox.fehlerWerfen(new RobotException(Nexus6.getInstance().getName()), Nexus6.getInstance().getId());
		}
		
		lager.put(r.getId(), r);
		System.out.println(r.getName() + " " + r.getId());	//Syso nur zum Testen
		return r;
	}

	/**
	 * Gibt ein String-Array von der ID und Namen von allen erstellten Instanzen zurück.
	 * 
	 * @return String-Array in welchem die ID und der Name vom Roboter enthalten sind.
	 */
	public String[] getRoboterliste() {
		String[] liste = new String[lager.size()];
		Collection<Robot> roboter = lager.values();
		
		int counter = 0;
		for (Robot robot : roboter) {
			liste[counter] = "ID: " + robot.getId() + " Name: " + robot.getName();
			counter++;
		}
		
		return liste;
	}
	
	/**
	 * Gibt eine Instanz zurück, welche von der Seriennummer abhängt.
	 * 
	 * @param seriennummer
	 * @return Gesuchte Roboter-Instanz
	 */
	public Robot findeRoboter(int seriennummer) {
		return lager.get(seriennummer);
	}
	
	/**
	 * Getter für die HashMap der erstellten Instanzen von getRobot.
	 * 
	 * @return HashMap welche die gespeicherten Instanzen von getRobot enthält
	 */
	public HashMap<Integer, Robot> getLager() {
		return lager;
	}
}
