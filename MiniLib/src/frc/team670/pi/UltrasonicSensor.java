package frc.team670.pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

/**
 * Represents an ultrasonic sensor connected to the pi motor shield
 * 
 * @author ctychen, lakshbhambhani
 *
 */
public class UltrasonicSensor extends Thread {

	private GpioPinDigitalInput echo;
	private GpioPinDigitalOutput trigger;
	private final GpioController gpio = GpioFactory.getInstance();
	private int boundary;
	private long lastRead, startTime, endTime, elapsed, measure;

	/**
	 * 
	 * @param trig     Trigger pin for sending signal to put sensor in detecting
	 *                 mode
	 * @param echo     Pin that sends signals back
	 * @param boundary an integer specifying the minimum distance at which the
	 *                 sensor will return a Triggered response of True.
	 */
	public UltrasonicSensor(Pin trig, Pin echo, int boundary) {
		this.trigger = gpio.provisionDigitalOutputPin(trig);
		this.trigger.setShutdownOptions(true);
		this.echo = gpio.provisionDigitalInputPin(echo);
		this.echo.setShutdownOptions(true);
		this.boundary = boundary;
		lastRead = 0;
		start();
	}

	public void run() {
		System.out.println("Ultrasonic started");
		if (echo.getState() == PinState.LOW) {
			startTime = System.currentTimeMillis();
		}
		if (echo.getState() == PinState.HIGH) {
			endTime = System.currentTimeMillis();
		}
		elapsed = endTime - startTime;
		measure = (elapsed * 34300) / 2;
		this.lastRead = measure;
		if (this.boundary > measure) {
			System.out.println("Boundary breached: " + this.boundary + "Measure: " + this.measure);
			this.trigger.setState(PinState.HIGH);
		} else {
			this.trigger.setState(PinState.LOW);
		}

	}

	public boolean isTriggered() {
		return (trigger.getState() == PinState.HIGH);
	}

}
