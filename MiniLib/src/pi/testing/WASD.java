package pi.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

import pi.Motor;

/**
 * Just for fun
 *
 */
public class WASD {

	private static int MOTOR_1_PIN_A = 4;
	private static int MOTOR_1_PIN_B = 5;
	private static int MOTOR_2_PIN_A = 0;
	private static int MOTOR_2_PIN_B = 1;

	public static void main(String[] args) throws InterruptedException, IOException {

		// get a handle to the GPIO controller
		final GpioController gpio = GpioFactory.getInstance();
		// initialize your motors
		Motor left = new Motor(MOTOR_1_PIN_A, MOTOR_1_PIN_B, RaspiPin.GPIO_06);
		Motor right = new Motor(MOTOR_2_PIN_A, MOTOR_2_PIN_B, RaspiPin.GPIO_03);
		System.out.println("Press F to disable");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";

		while (line.equalsIgnoreCase("f") == false) {
			try {
				line = in.readLine();
				if (line.charAt(line.length() - 1) == 'w') {
					left.set(1.0);
					right.set(1.0);
				}
				if (line.charAt(line.length() - 1) == 'a') {
					left.set(1.0);
					right.set(0.6);
				}
				if (line.charAt(line.length() - 1) == 's') {
					left.set(-1.0);
					right.set(-1.0);
				}
				if (line.charAt(line.length() - 1) == 'd') {
					left.set(0.6);
					right.set(1.0);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// do something
		}

		in.close();
		left.set(0);
		right.set(0);
		left.close();
		right.close();
		gpio.shutdown();

	}

}
