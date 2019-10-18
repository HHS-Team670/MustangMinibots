package frc.team670.pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Represents a DC motor which can be controlled with the pi motorshield.
 * 
 * @author ctychen, lakshbhambhani
 *
 */
public class Encoder {

	private GpioPinDigitalInput pin; 
	private int p;
	
	private boolean inverted;
	
	private final GpioController gpio = GpioFactory.getInstance();

	/**
	 * @param a GPIO pin number of Motor Pin A
	 * @param b GPIO pin number of Motor Pin B
	 * @param e GPIO pin (example: RaspiPin.GPIO_06) of digital output
	 */
	public Encoder(int pin) {
		
	}

	
}
