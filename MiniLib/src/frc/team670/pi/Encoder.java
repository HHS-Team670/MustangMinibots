package frc.team670.pi;

import java.util.ArrayList;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.sun.glass.ui.Robot;
import com.pi4j.io.gpio.PinState;

import edu.wpi.first.wpilibj.RobotState;

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
	
	public int count;
	private final int DIAMETER =  0;

	private final GpioController gpio = GpioFactory.getInstance();
	
	private final int[] outcome = {0,-1,1,0,-1,0,0,1,1,0,0,-1, 0, -1, 1, 0};
	
	int last_AB = 0;
	
	public void run(){
		while(RobotState.isEnabled()) {
			int A = getLeftState();
			int B = getRightState();
			int current_AB = (A << 1) | B;
			int position = (last_AB << 2) | current_AB;
			count += outcome[position];
			last_AB = current_AB;
//			try {
//				Thread.sleep(0, );
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
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
	
	public int getLeftState() {
		leftPinState = leftPin.getState();
		if(leftPinState == PinState.HIGH) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public int getRightState() {
		rightPinState = rightPin.getState();

		if(rightPinState == PinState.HIGH) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
//	public double getDistanceRotated() {
//		return DIAMETER * rotations;
//	}
	
	
	
	
	
	


}
