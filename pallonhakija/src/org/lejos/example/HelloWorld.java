package org.lejos.example;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * Example leJOS Project with an ant build file
 *
 */
public class HelloWorld {
	static boolean palloHavaittu = false;

	public static void main(String[] args) {
		System.out.println("Missa pallo?");
		Button.waitForPress();
		DifferentialPilot pilotti = new DifferentialPilot(2.5f, 15.0f, Motor.A, Motor.C);
		UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S1);
		Behavior aja = new AjaEteenpain(pilotti);
		Behavior este = new EsteEdessa(ultra, pilotti);
		Behavior pysaytys = new Hatapysaytys();
		Behavior etsi = new EtsiPallo(ultra, pilotti);
		Behavior[] bArray = {etsi, aja, este, pysaytys};
		Arbitrator arbitrator = new Arbitrator(bArray);
		arbitrator.start();
	}
}
