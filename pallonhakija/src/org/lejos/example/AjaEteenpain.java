package org.lejos.example;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class AjaEteenpain implements Behavior {
	private boolean suppressed = false;
	private DifferentialPilot pilotti;
	
	public AjaEteenpain(DifferentialPilot p){
		this.pilotti = p;
	}

	@Override
	public void action() {
		suppressed = false;
		pilotti.setTravelSpeed(5);
		pilotti.forward();
		while(!suppressed){
			Thread.yield();
		}
		HelloWorld.palloHavaittu = false;
		pilotti.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

	@Override
	public boolean takeControl() {
		return HelloWorld.palloHavaittu;
	}

}
