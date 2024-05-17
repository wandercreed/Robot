package infrastructure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import domain.RobotException;

/**
 * Klasse dient zum persistieren und laden von Objekten
 * 
 */
public class BlackBox {

	/**
	 * Speichert das übergebene Objekt in einer File
	 * 
	 * @param o, id
	 * @throws Exception
	 */
	public static void speicherObject(Object o, int id) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object" + id + ".ser"));
		oos.writeObject(o);

		oos.close();
	}

	/**
	 * Ladet ein Objekt aus einer File
	 * 
	 * @param id
	 * @return Das Objekt welches zuvor in einer File gespeichert wurde
	 * @throws Exception
	 */
	public static Object ladeObject(int id) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object" + id + ".ser"));
		Object o = ois.readObject();

		ois.close();
		return o;
	}
	
	
	/**
	 * 
	 * @param RobotException re, id
	 * @throws RobotException
	 * 
	 * Die Methode übernimmt die 2 Funktionen: Speichern & Werfen der Exception.
	 * Die übergebene RobotException wird an die statische Methode {@link BlackBox#speicherObject(Object)} weitergeben und gespeichert.
	 * Die übergebene RobotException wird anschließend geworfen.
	 */
	public static void fehlerWerfen(RobotException re, int id) throws RobotException {
		try {
			speicherObject(re, id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		throw re;
	}
	
	
}
