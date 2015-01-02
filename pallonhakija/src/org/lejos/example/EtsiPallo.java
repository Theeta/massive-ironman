package org.lejos.example;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class EtsiPallo implements Behavior {
	private boolean suppressed = false;
	private UltrasonicSensor ultra;
	private DifferentialPilot pilotti;
	
	public EtsiPallo(UltrasonicSensor u, DifferentialPilot p){
		this.ultra = u;
		this.pilotti = p;
	}

	@Override
	public void action() {
		suppressed = false;
		int etaisyys = ultra.getDistance();
		pilotti.rotate(2);
		int tokaEtaisyys = ultra.getDistance();
		pilotti.rotate(2);
		int kolmasEtaisyys = ultra.getDistance();
		while (!(etaisyys > tokaEtaisyys + 10 && kolmasEtaisyys > tokaEtaisyys + 10)){
			etaisyys = tokaEtaisyys;
			tokaEtaisyys = kolmasEtaisyys;
			pilotti.rotate(2);
			kolmasEtaisyys = ultra.getDistance();
		}
		HelloWorld.palloHavaittu = true;
		while(pilotti.isMoving() && !suppressed){
			Thread.yield();
		}
		pilotti.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

}
