package frc.team670.pi;

import java.util.ArrayList;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.PinState;

/**
 * Represents an encoder connected to the pi
 * ________ _______    ______   _______  _ 
\__   __/(  ___  )  (  __  \ (  ___  )( )
   ) (   | (   ) |  | (  \  )| (   ) || |
   | |   | |   | |  | |   ) || |   | || |
   | |   | |   | |  | |   | || |   | || |
   | |   | |   | |  | |   ) || |   | |(_)
   | |   | (___) |  | (__/  )| (___) | _ 
   )_(   (_______)  (______/ (_______)(_)
                                         
 * @author ctychen, lakshbhambhani
 *
 */
public class Encoder extends Thread{
	
	private GpioPinDigitalInput leftPin, rightPin; 
	
	private PinState leftPinState = PinState.LOW;
	private PinState rightPinState;

	private final GpioController gpio = GpioFactory.getInstance();
	
	public void run() {
		if(getLeftState() == PinState.HIGH && getRightState() == PinState.HIGH) {
			System.out.println("High");
		}
	}
	
	public Encoder(Pin leftP, Pin rightP) {
		 leftPin = gpio.provisionDigitalInputPin(leftP);
		 leftPin.setShutdownOptions(true);
		 rightPin = gpio.provisionDigitalInputPin(rightP);
		 rightPin.setShutdownOptions(true);
		 start();
	}
	
	public PinState getLeftState() {
		leftPin.addListener(new GpioPinListenerDigital() {
	        @Override
	        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
	            // display pin state on console
	            //System.out.println(" --> GPIO PIN STATE CHANGE: " + System.currentTimeMillis() + event.getPin() + " = " + event.getState());
	        	leftPinState = event.getState();
	        }


	    });
		return leftPinState;
	}
	
	public PinState getRightState() {
		rightPin.addListener(new GpioPinListenerDigital() {
	        @Override
	        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
	            // display pin state on console
	            //System.out.println(" --> GPIO PIN STATE CHANGE: " + System.currentTimeMillis() + event.getPin() + " = " + event.getState());
	        	rightPinState = event.getState();
	        }


	    });
		return rightPinState;
	}
	
	
	
	
	
	


}
