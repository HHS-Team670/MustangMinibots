package frc.team670.pi.tests;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

import frc.team670.pi.Motor;
import frc.team670.pi.sensors.Encoder;
import jpigpio.PigpioException;

/**
 * Represents a DC motor which can be controlled with the pi motorshield.
 * 
 * @author ctychen, lakshbhambhani
 *
 */
public class EncoderTest {

	private GpioPinDigitalInput pin; 
	private int p;
	
	private boolean inverted;
	
	private static int MOTOR_1_PIN_A = 4;
	private static int MOTOR_1_PIN_B = 5;
	private static int MOTOR_2_PIN_A = 0;
	private static int MOTOR_2_PIN_B = 1;
	
	private final GpioController gpio = GpioFactory.getInstance();

	/**
	 * @param a GPIO pin number of Motor Pin A
	 * @param b GPIO pin number of Motor Pin B
	 * @param e GPIO pin (example: RaspiPin.GPIO_06) of digital output
	 * @throws PigpioException 
	 */
//	public Encoder(int pin) {
//		gpio.provisionDigitalInputPin(RaspiPin.GPIO_12);          
//
//		if (gpio.provisionDigitalInputPin(RaspiPin.GPIO_12) == 0){ // it is day, so doesn't need LEDs
//		      System.out.println("Day, LEDs are not switched on");
//		}else{ // it is night, LEDs are needed
//		      System.out.println("Night, LEDs are switched on");
//		}
//		}
//	}

public static void main(String args[]) throws InterruptedException, PigpioException {
    System.out.println("<--Pi4J--> GPIO Listen Example ... started.");

    // create gpio controller
//    final GpioController gpio = GpioFactory.getInstance();
//
//    // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
//    final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_21);
//
//    // set shutdown state for this input pin
//    myButton.setShutdownOptions(true);
    
    Motor left = new Motor(MOTOR_1_PIN_A, MOTOR_1_PIN_B, RaspiPin.GPIO_06);
	Motor right = new Motor(MOTOR_2_PIN_A, MOTOR_2_PIN_B, RaspiPin.GPIO_03);
	
	Encoder lEncoder = new Encoder(13, 26, false);//RaspiPin.GPIO_07
	Encoder rEncoder = new Encoder(5, 6, false);//RaspiPin.GPIO_21

//	left.set(0.4);
//	right.set(0.4);
//	
//	Thread.sleep(350);
//	left.set(0);
//	right.set(0);
//	
//	left.close();
//	right.close();
	while(true) {
		System.out.println(lEncoder.count + " " +  rEncoder.count);
		Thread.sleep(1);
	}
	// wait 3 seconds
	
	

//    // create and register gpio pin listener
//    myButton.addListener(new GpioPinListenerDigital() {
//        @Override
//        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//            // display pin state on console
//            System.out.println(" --> GPIO PIN STATE CHANGE: " + System.currentTimeMillis() + event.getPin() + " = " + event.getState());
//        }
//
//    });
//
//    System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");

    // keep program running until user aborts (CTRL-C)
   
    
    

    // stop all GPIO activity/threads by shutting down the GPIO controller
    // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
    // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
}

	
}
