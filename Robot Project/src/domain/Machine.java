package domain;

import infrastructure.BlackBox;

public abstract class Machine implements Robot {

	protected String name;
	protected int id;
	protected boolean istEingeschaltet;
	
	/**
	 * @see RobotControl#getId()
	 */
	@Override
	public int getId() {
		return this.id;
	}

	/**
	 * @see RobotControl#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @see RobotControl#triggerPowerSwitch()
	 * Setzt das boolean-Attribut 'istEingeschaltet' auf den jeweils anderen Wert.
	 */
	@Override
	public void triggerPowerSwitch() {
		if(this.istEingeschaltet == false)
			this.istEingeschaltet = true;
		else
			this.istEingeschaltet = false;
		
	}

	/**
	 * @see RobotControl#isPowerOn()
	 */
	@Override
	public boolean isPowerOn() {
		return this.istEingeschaltet;
	}

	/**
	 * @see RobotControl#getLastException()
	 * @see BlackBox#ladeObject()
	 * Die zuletzt aufgetretene Exception lässt sich über die statische Methode {@link BlackBox#ladeObject()} einlesen.
	 * 
	 * @return die eingelesene RobotException bzw. null, falls noch keine existiert.
	 */
	@Override
	public RobotException getLastException() {
		RobotException re = null;
		try {
			re = (RobotException)BlackBox.ladeObject(this.id);	
		} catch(Exception e) {
			//TODO Fehlerbehandlung
			e.printStackTrace();
		}
		return re;
	}
}
