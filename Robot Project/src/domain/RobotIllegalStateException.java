package domain;

import java.io.Serializable;

public class RobotIllegalStateException extends RobotException implements Serializable{

	public RobotIllegalStateException(String robotName) {
		super(robotName);
	}

}
