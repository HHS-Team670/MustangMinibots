package frc.team670.pi.sensors;

import java.util.ArrayList;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.PinState;

import edu.wpi.first.wpilibj.RobotState;

/**
 * Represents an encoder connected to the pi
 * @author ctychen, lakshbhambhani
 *
 */
public class Encoder extends Thread{
	
	private GpioPinDigitalInput leftPin, rightPin; 
	
	private PinState leftPinState = PinState.LOW;
	private PinState rightPinState;
	
	public int count;

	private final GpioController gpio = GpioFactory.getInstance();
	
	private final int[] outcome = {0,-1,1,0,-1,0,0,1,1,0,0,-1, 0, -1, 1, 0};
	
	int last_AB = 0;
	
	/**
	 * Runs the encoder counting in another thread
	 */
	public void run(){
		while(RobotState.isEnabled()) {
			int A = getLeftState();
			int B = getRightState();
			int current_AB = (A << 1) | B;
			int position = (last_AB << 2) | current_AB;
			count += outcome[position];
			last_AB = current_AB;
		}
	}
	
	/**
	 * Creates an encoder on 2 pins which can be used to get data from the motor
	 * @param leftP Pin for the left sensor on the encoder
	 * @param rightP Pin for the right sensor on the encoder
	 */
	public Encoder(Pin leftP, Pin rightP) {
		 count = 0;
		 leftPin = gpio.provisionDigitalInputPin(leftP);
		 leftPin.setShutdownOptions(true);
		 rightPin = gpio.provisionDigitalInputPin(rightP);
		 rightPin.setShutdownOptions(true);
		 leftPin.addListener(new GpioPinListenerDigital() {
		        @Override
		        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		            // display pin state on console
		            //System.out.println(" --> GPIO PIN STATE CHANGE: " + System.currentTimeMillis() + event.getPin() + " = " + event.getState());
		        	leftPinState = event.getState();
		        }


		    });
		 rightPin.addListener(new GpioPinListenerDigital() {
		        @Override
		        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		            // display pin state on console
		            //System.out.println(" --> GPIO PIN STATE CHANGE: " + System.currentTimeMillis() + event.getPin() + " = " + event.getState());
		        	rightPinState = event.getState();
		        }


		    });
		 start();
	}
	
	private int getLeftState() {
		leftPinState = leftPin.getState();
		if(leftPinState == PinState.HIGH) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	private int getRightState() {
		rightPinState = rightPin.getState();

		if(rightPinState == PinState.HIGH) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Returns number of ticks the encoder has gone through
	 * @return int Ticks - the number of ticks
	 */
	public int getTicks() {
		return this.count;
	}
	
	/**
	 * Calculates and returns the number of rotations the wheel has gone through
	 * @return Double rotations - the number of rotations the wheel has gone through
	 */
	public double getRotations() {
		return getTicks()/800;
	}
	
	/**
	 * Calculates and returns the distance for which the motors have rotated
	 * @param diameter
	 * @return distance traveled in centimeters
	 */
	public double getDistance(double diameter) {
		return (2 * Math.PI * (diameter/2) * getRotations());
	}
	

	
	
	
	
	


}
