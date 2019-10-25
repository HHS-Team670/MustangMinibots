package frc.team670.pi.sensors;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Represents an ultrasonic sensor connected to the pi motor shield
 * 
 * @author ctychen, lakshbhambhani
 *
 */
public class UltrasonicSensor extends Thread {

	private GpioPinDigitalInput echo;
	private GpioPinDigitalOutput trig;
	private final GpioController gpio = GpioFactory.getInstance();
	private double boundary;
	private double lastRead;
	
	/**
	 * Creates an ultrasonic sensor that could measure distance and check if its triggered based on a boundary
	 * @param trig     trig pin for sending signal to put sensor in detecting mode
	 * @param echo     Pin that sends signals back
	 * @param boundary an integer specifying the minimum distance at which the
	 *                 sensor will return a triged response of True.
	 */
	public UltrasonicSensor(Pin trig, Pin echo, int boundary) {
		this.trig = gpio.provisionDigitalOutputPin(trig);
		this.trig.setShutdownOptions(true);
		this.echo = gpio.provisionDigitalInputPin(echo);
		this.echo.setShutdownOptions(true);
		this.boundary = boundary;
		lastRead = 0;
		start();
	}

	public void run() {
		//System.out.println("Ultrasonic started");
		while(true){
			try {
			//Thread.sleep(2000);
			trig.high(); // Make trigger pin HIGH
			Thread.sleep((long) 0.01);// Delay for 10 microseconds
			trig.low(); //Make trigger pin LOW
		
			while(echo.isLow()){ //Wait until the ECHO pin gets HIGH
				
			}
			long startTime= System.nanoTime(); // Store the surrent time to calculate ECHO pin HIGH time.
			while(echo.isHigh()){ //Wait until the ECHO pin gets LOW
				
			}
			long endTime= System.nanoTime(); // Store the echo pin HIGH end time to calculate ECHO pin HIGH time.
			lastRead = ((((endTime-startTime)/1e3)/2) / 29.1);
			//System.out.println("Distance :"+((((endTime-startTime)/1e3)/2) / 29.1) +" cm"); //Printing out the distance in cm  			
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
	}
	
	/**
	 * Calculates and returns the distance
	 * @return Double distance - The distance sensed by the us sensor
	 * @post creates a new thread and kills it to do this
	 */
	public double getDist() {
		return lastRead;
	}

	/**
	 * Checks if the measured distance is less than the boundary
	 * @return Boolean triggered based on whether its within the boundary
	 */
	public boolean isTriggered() {
		return (lastRead <= boundary);
	}
	
	/**
	 * Sets a new boundary for the sensor
	 * @param boundary The new value for the boundary
	 */
	public void setBoundary(double boundary) {
		this.boundary =  boundary;
	}

}
