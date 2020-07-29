package frc.team670.pi.sensors;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

/**
 * Represents an IR sensor connected to the pi motor shield
 * @author ctychen,lakshbhambhani 
 *
 */

public class IRSensor extends Thread {
	
	private GpioPinDigitalInput echo;
	private final GpioController gpio = GpioFactory.getInstance();
	
	public IRSensor(Pin echo) {
		this.echo = gpio.provisionDigitalInputPin(echo);
		 this.echo.setShutdownOptions(true);
		 start();
	}
	
	public void run() {
		
	}
	

}
