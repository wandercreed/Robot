package domain;
import java.io.Serializable;

public class RobotMagicValueException extends RobotException implements Serializable {

	public RobotMagicValueException(String robotName) {
		super(robotName);
	}

}
