package pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

public class Motors {

	GpioPinDigitalOutput ePin, fPin, rPin;
	final GpioController gpio = GpioFactory.getInstance();
	
	private static int MOTOR_L_PIN_A = 4;
	private static int MOTOR_L_PIN_B = 5;
	private static int MOTOR_R_PIN_A = 0;
	private static int MOTOR_R_PIN_B = 2;
	
	final GpioPinDigitalOutput motor1pinE = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "m1E");
	final GpioPinDigitalOutput motor2pinE = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "m2E");

	/**
	 * 
	 * @param e GPIO pin for 'e'(refer to motor shield schematic)
	 * @param f GPIO pin for forwards motion
	 * @param r GPIO pin for reverse motion
	 */
	public Motors() {
		SoftPwm.softPwmCreate(MOTOR_L_PIN_A, 0, 100);
		SoftPwm.softPwmCreate(MOTOR_L_PIN_B, 0, 100);
		SoftPwm.softPwmCreate(MOTOR_L_PIN_A, 0, 100);
		SoftPwm.softPwmCreate(MOTOR_L_PIN_B, 0, 100);
		
		motor2pinE.high();
		motor1pinE.high();
	}


	public void leftForward(int speed) {
		System.out.println("Left Motors Forward");
		SoftPwm.softPwmWrite(MOTOR_L_PIN_A, speed);
		SoftPwm.softPwmWrite(MOTOR_L_PIN_B, 0);
	}
	
	public void leftReverse(int speed) {
		System.out.println("Left Motors Reverse");
		SoftPwm.softPwmWrite(MOTOR_L_PIN_A, 0);
		SoftPwm.softPwmWrite(MOTOR_L_PIN_B, speed);
	}

	public void rightForward(int speed) {
		System.out.println("Right Motors Forward");
		SoftPwm.softPwmWrite(MOTOR_R_PIN_A, speed);
		SoftPwm.softPwmWrite(MOTOR_R_PIN_B, 0);
	}
	
	public void rightReverse(int speed) {
		System.out.println("Right Motors Reverse");
		SoftPwm.softPwmWrite(MOTOR_R_PIN_A, 0);
		SoftPwm.softPwmWrite(MOTOR_R_PIN_B, speed);
	}
	
	public void reverse(int speed) {
		System.out.println("All Motors Reverse");
		SoftPwm.softPwmWrite(MOTOR_L_PIN_A, 0);
		SoftPwm.softPwmWrite(MOTOR_L_PIN_B, speed);
		SoftPwm.softPwmWrite(MOTOR_R_PIN_A, 0);
		SoftPwm.softPwmWrite(MOTOR_R_PIN_B, speed);
	}
	
	public void forward(int speed) {
		System.out.println("All Motors Forward");
		SoftPwm.softPwmWrite(MOTOR_L_PIN_A, speed);
		SoftPwm.softPwmWrite(MOTOR_L_PIN_B, 0);
		SoftPwm.softPwmWrite(MOTOR_R_PIN_A, speed);
		SoftPwm.softPwmWrite(MOTOR_R_PIN_B, 0);
	}
	
	public void stop() {
		System.out.println("Stopping");
		SoftPwm.softPwmWrite(MOTOR_R_PIN_A, 0);
		SoftPwm.softPwmWrite(MOTOR_R_PIN_B, 0);
		SoftPwm.softPwmWrite(MOTOR_L_PIN_A, 0);
		SoftPwm.softPwmWrite(MOTOR_L_PIN_B, 0);
	}
	
	public void close() {
		motor1pinE.low();
		motor2pinE.low();
	}
	
	
	
	
	
}