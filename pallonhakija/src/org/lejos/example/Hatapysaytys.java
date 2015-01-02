package org.lejos.example;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class Hatapysaytys implements Behavior {
	private boolean suppressed = false;

	@Override
	public void action() {
		suppressed = false;
		Motor.A.stop();
		Motor.B.stop();
		Motor.C.stop();
		
		while (!suppressed){
			Thread.yield();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

	@Override
	public boolean takeControl() {
		return Button.ENTER.isPressed();
	}

}
