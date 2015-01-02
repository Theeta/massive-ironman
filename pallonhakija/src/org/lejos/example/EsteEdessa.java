package org.lejos.example;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class EsteEdessa implements Behavior {
	private UltrasonicSensor ultra;
	private DifferentialPilot pilotti;
	private boolean suppressed = false;
	
	public EsteEdessa(UltrasonicSensor u, DifferentialPilot p){
		this.ultra = u;
		this.pilotti = p;
	}

	@Override
	public void action() {
		suppressed = false;
		pilotti.rotate(40);
		
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
		return ultra.getDistance() < 20;
	}

}
