package pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Motor {

	GpioPinDigitalOutput ePin;
	GpioPinPwmOutput fPin, rPin;
	final GpioController gpio = GpioFactory.getInstance();

	/**
	 * 
	 * @param e GPIO pin for 'e'(refer to motor shield schematic)
	 * @param f GPIO pin for forwards motion
	 * @param r GPIO pin for reverse motion
	 */
	public Motor(Pin e, Pin f, Pin r) {
		ePin = gpio.provisionDigitalOutputPin(e, PinState.HIGH);
		fPin = gpio.provisionPwmOutputPin(f);
		rPin = gpio.provisionPwmOutputPin(r);
		com.pi4j.wiringpi.Gpio.pwmSetRange(100);
		com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
	}


	public void forward(int speed) {
		System.out.println("Forward");
		fPin.setPwm(speed);
		rPin.setPwm(0);
	}

	public void reverse(int speed) {
		System.out.println("In reverse");
		fPin.setPwm(0);
		rPin.setPwm(speed);
		
	}
	
	public void stop() {
		System.out.println("Stopping");
		fPin.setPwm(0);
		rPin.setPwm(0);
	}
	
	
	
}
