package pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Represents a DC motor which can be controlled with the pi motorshield.
 * 
 * @author ctychen
 *
 */
public class Motor {

	GpioPinDigitalOutput ePin;
	int mpinA, mpinB;
	int speed;
	final GpioController gpio = GpioFactory.getInstance();

	/**
	 * @param a GPIO pin number of Motor Pin A
	 * @param b GPIO pin number of Motor Pin B
	 * @param e GPIO pin (example: RaspiPin.GPIO_06) of digital output
	 */
	public Motor(int a, int b, Pin e) {
		this.mpinA = a;
		this.mpinB = b;
		SoftPwm.softPwmCreate(a, 0, 100);
		SoftPwm.softPwmCreate(b, 0, 100);
		speed = 0;
		ePin = gpio.provisionDigitalOutputPin(e, "mpinE");
		ePin.high();
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

	public void setInverted(boolean inverted) {

	}

	private void forward() {
		System.out.println("...Motor Forward...");
		SoftPwm.softPwmWrite(mpinA, this.speed);
		SoftPwm.softPwmWrite(mpinB, 0);
	}

	private void reverse() {
		System.out.println("...Motor Reverse...");
		SoftPwm.softPwmWrite(mpinA, 0);
		SoftPwm.softPwmWrite(mpinB, this.speed);
	}

	private void stop() {
		System.out.println("...Motor Stopping...");
		SoftPwm.softPwmWrite(mpinA, 0);
		SoftPwm.softPwmWrite(mpinB, 0);
	}

	public void close() {
		ePin.low();
	}

	public GpioPinDigitalOutput getPinE() {
		return this.ePin;
	}
}
