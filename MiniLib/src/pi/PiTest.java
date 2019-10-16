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
	
	private static int MOTOR_1_PIN_A = 4;
	private static int MOTOR_1_PIN_B = 5;
	private static int MOTOR_2_PIN_A = 0;
	private static int MOTOR_2_PIN_B = 1;
	
	public static void main(String[] args) throws InterruptedException {
		// get a handle to the GPIO controller
//		Motors motors = new Motors();
//		
//		System.out.println("rotate motor clockwise for 3 seconds");
//		motors.rightForward(50);
//		Thread.sleep(3000); 		// wait 3 seconds
//		
//		System.out.println("rotate motor in oposite derection for 6 seconds");
//		motors.rightReverse(50);
//		Thread.sleep(3000);
//		
//		// stop motor
//		motors.stop();
//		System.out.println("Stopping motor");
//		
		// get a handle to the GPIO controller
		
		// get a handle to the GPIO controller
				final GpioController gpio = GpioFactory.getInstance();
				// init soft PWM pins
				// softPwmCreate(int pin, int value, int range)
				// the range is set like (min=0 ; max=100)
				SoftPwm.softPwmCreate(MOTOR_1_PIN_A, 0, 100);
				SoftPwm.softPwmCreate(MOTOR_1_PIN_B, 0, 100);
				SoftPwm.softPwmCreate(MOTOR_2_PIN_A, 0, 100);
				SoftPwm.softPwmCreate(MOTOR_2_PIN_B, 0, 100);
				// init GPIO pins
				final GpioPinDigitalOutput motor1pinE = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "m1E");
				final GpioPinDigitalOutput motor2pinE = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "m2E");
				System.out.println("rotate motor 1 clockwise at 15% speed for 2 seconds");
				motor1pinE.high();
				motor2pinE.high();
				SoftPwm.softPwmWrite(MOTOR_1_PIN_A, 100);
				SoftPwm.softPwmWrite(MOTOR_2_PIN_B, 100);
				// wait 2 seconds
				Thread.sleep(3000);
				
				SoftPwm.softPwmWrite(MOTOR_2_PIN_B, 0);
				SoftPwm.softPwmWrite(MOTOR_1_PIN_B, 0);
				motor2pinE.low();
				motor1pinE.low();
				gpio.shutdown();
		
	
		
	}
}
