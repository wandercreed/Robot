package domain;

import java.util.Arrays;

import facade.RobotFactory;

/**
 * Klasse dient nur zum Testen von Methoden
 *
 */
public class Main {

	public static void main(String[] args) throws RobotException {
		RobotFactory r = new RobotFactory();
		
		r.getRobot("R", RobotType.C3PO);
		r.getRobot("R2", RobotType.C3PO);
//		r.getRobot("N", RobotType.NEXUS6);
		Robot robot = new R2D2("ss", 2);

		
		
		int[] zahlen = { 3, 5, 1, 2, 4 };
		robot.triggerPowerSwitch();;
		
		
	    System.out.println(robot.speak(zahlen));	    
	    for(int i : robot.think(zahlen)) {
	    	System.out.println(i);
	    }
	    Arrays.stream(r.getRoboterliste()).forEach(n -> System.out.println(n));

		int[] myNumbers = {1,2,3,5,2,4,656,34,565,23,335,657,232};
		//r.getLager().get("R").triggerPowerSwitch();
		//System.out.println(r.getLager().get("R").speak(myNumbers));
		//Arrays.stream(r.getLager().get("R").think(myNumbers)).forEach(n -> System.out.print(n + " "));
	}
}
