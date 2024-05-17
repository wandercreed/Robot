package domain;

import java.io.Serializable;

public class RobotException  extends Exception implements Serializable{

	private String robotName;
	
	public RobotException(String robotName) {
		this.robotName = robotName;
	}
	
	public String getRobotName() {
		return this.robotName;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + ", von " + this.robotName;
	}
	
}
