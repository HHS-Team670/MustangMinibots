package pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * For representing a DC motor to use with the Raspberry Pi motorshield
 * 
 * @author ctychen
 *
 */
public class PiMotor {

	GpioPinDigitalOutput fPin, rPin;
	GpioPinPwmOutput ePin;
	final GpioController gpio = GpioFactory.getInstance();
	double speed;
  
	/**
	 * 
	 * @param e GPIO pin for 'e'(refer to motor shield schematic)
	 * @param f GPIO pin for forwards motion
	 * @param r GPIO pin for reverse motion
	 */
	public PiMotor(Pin e, Pin f, Pin r) {
		ePin = gpio.provisionPwmOutputPin(e);
		fPin = gpio.provisionDigitalOutputPin(e, PinState.LOW);
		rPin = gpio.provisionDigitalOutputPin(e, PinState.LOW);
		com.pi4j.wiringpi.Gpio.pwmSetRange(100);
		com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
		speed = 0;
	}

	/**
	 * 
	 * @param speed double between -1.0 (full reverse) and +1.0 (full forward)
	 */
	public void setSpeed(double s) {
		this.speed = Math.abs(s);
		ePin.setPwm((int)(this.speed*100));
		if (s > 0)
			forward();
		else if (s < 0)
			reverse();
		else
			stop();
	}

	public void setInverted() {
		this.speed *= (-1);
		setSpeed(this.speed);
	}

	/**
	 * Drives the motor forward
	 */
	public void forward() {
		System.out.println("Forward");
		fPin.setState(PinState.HIGH);
		rPin.setState(PinState.LOW);
	}

	/**
	 * Drives the motor in reverse
	 */
	public void reverse() {
		System.out.println("In reverse");
		fPin.setState(PinState.LOW);
		rPin.setState(PinState.HIGH);

	}

	public void stop() {
		System.out.println("Stopping");
		ePin.setPwm(0);
		fPin.setState(PinState.LOW);
		rPin.setState(PinState.LOW);
	}

}
