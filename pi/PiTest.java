package pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;
/**
 * Controls motor direction of a single motor
 * @author https://javatutorial.net
 */
public class PiTest {
	public static void main(String[] args) throws InterruptedException {
		// get a handle to the GPIO controller
		Motors motors = new Motors();
		System.out.println("rotate motor clockwise for 3 seconds");
		motors.forward(100);
		// wait 3 seconds
		Thread.sleep(3000);
		System.out.println("rotate motor in oposite derection for 6 seconds");
		motors.reverse(100);
		
		Thread.sleep(6000);
		// stop motor
		motors.stop();
		System.out.println("Stopping motor");
		
		
		
	}
}