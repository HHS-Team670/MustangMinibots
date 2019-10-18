package frc.team670.pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Represents a DC motor which can be controlled with the pi motorshield.
 * 
 * @author ctychen, lakshbhambhani
 *
 */
public class Motor {

	private GpioPinDigitalOutput ePin;
	
	private int mpinA, mpinB, speed;;
	private boolean inverted;
	
	private final GpioController gpio = GpioFactory.getInstance();

	/**
	 * @param a GPIO pin number of Motor Pin A
	 * @param b GPIO pin number of Motor Pin B
	 * @param e GPIO pin (example: RaspiPin.GPIO_06) of digital output
	 */
	public Motor(int mpinA, int mpinB, Pin mpinE) {
		this.mpinA = mpinA;
		this.mpinB = mpinB;
		SoftPwm.softPwmCreate(mpinA, 0, 100);
		SoftPwm.softPwmCreate(mpinB, 0, 100);
		speed = 0;
		ePin = gpio.provisionDigitalOutputPin(mpinE, "mpinE");
		ePin.high();
		inverted = false;
	}

	/**
	 * 
	 * @param s [-1.0, +1.0] with -1.0 being full speed reverse, +1.0 being full
	 *          speed forward
	 */
	public void set(double s) {
		if (s <= 1.0 && s >= -1.0) {
			this.speed = ((int) ((Math.abs(s)) * 100));
			if (s > 0)
				forward();
			else if (s < 0)
				reverse();
			else
				stop();
		}
	}

	/**
	 * Sets the motors to run in the opposite direction of its original direction
	 * @param inverted
	 */
	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	/**
	 * Runs the motor forwards
	 */
	private void forward() {
		if(!inverted) {
			System.out.println("...Motor Forward...");
			SoftPwm.softPwmWrite(mpinA, this.speed);
			SoftPwm.softPwmWrite(mpinB, 0);
		}
		else {
			reverse();
		}
	}

	/**
	 * Runs the motors backwards
	 */
	private void reverse() {
		if(inverted) {
			System.out.println("...Motor Reverse...");
			SoftPwm.softPwmWrite(mpinA, 0);
			SoftPwm.softPwmWrite(mpinB, this.speed);
		}
		else {
			forward();
		}
	}

	/**
	 * Stops the motor
	 */
	private void stop() {
		System.out.println("...Motor Stopping...");
		SoftPwm.softPwmWrite(mpinA, 0);
		SoftPwm.softPwmWrite(mpinB, 0);
	}

	/**
	 * Closes the connection to the motor. Motor has to be reinitialized to be turned on after this.
	 */
	public void close() {
		ePin.low();
	}

	/**
	 * Returns the E pin for the motor
	 * @return GpioDigitalOutput pin E - The E pin controlling the motor
	 */
	public GpioPinDigitalOutput getPinE() {
		return this.ePin;
	}
}
