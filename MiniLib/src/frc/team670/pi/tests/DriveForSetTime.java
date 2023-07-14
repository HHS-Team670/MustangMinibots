package frc.team670.pi.tests;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

import frc.team670.pi.Motor;
/**
 * Drive for a specified amount of time
 */
public class DriveForSetTime {

	private static int MOTOR_1_PIN_A = 4;
	private static int MOTOR_1_PIN_B = 5;
	private static int MOTOR_2_PIN_A = 0;
	private static int MOTOR_2_PIN_B = 1;

	public static void main(String[] args) throws InterruptedException {

		// get a handle to the GPIO controller
		final GpioController gpio = GpioFactory.getInstance();
		// initialize your motors
		Motor left = new Motor(MOTOR_1_PIN_A, MOTOR_1_PIN_B, RaspiPin.GPIO_06);
		Motor right = new Motor(MOTOR_2_PIN_A, MOTOR_2_PIN_B, RaspiPin.GPIO_03);
		left.set(1.0);
		right.set(-1.0);
		// wait 3 seconds
		Thread.sleep(3000);
		left.set(0);
		right.set(0);
		left.close();
		right.close();
		gpio.shutdown();

	}
}