package frc.team670.pi.tests;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

import frc.team670.pi.Motor;

/**
 * Example of how to turn, then drive forwards based on timing only
 */
public class TimedTurnThenDrive {

	private static int MOTOR_1_PIN_A = 4;
	private static int MOTOR_1_PIN_B = 5;
	private static int MOTOR_2_PIN_A = 0;
	private static int MOTOR_2_PIN_B = 1;

	public static void main(String[] args) throws InterruptedException {

		// get a handle to the GPIO controller
		final GpioController gpio = GpioFactory.getInstance();
		// initialize your motors
		Motor m1 = new Motor(MOTOR_1_PIN_A, MOTOR_1_PIN_B, RaspiPin.GPIO_06);
		Motor m2 = new Motor(MOTOR_2_PIN_A, MOTOR_2_PIN_B, RaspiPin.GPIO_03);
    // turning
		m1.set(0.5);
		m2.set(1.0);
		// wait 2 seconds
		Thread.sleep(2000);
		// then drive forwards for 4 seconds
		m1.set(1.0);
		Thread.sleep(4000);
		m1.set(0);
		m2.set(0);
		m1.close();
		m2.close();
		gpio.shutdown();

	}
}