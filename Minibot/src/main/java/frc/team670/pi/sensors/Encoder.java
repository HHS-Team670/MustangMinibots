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
import frc.team670.robot.RobotConstants;
import jpigpio.JPigpio;
import jpigpio.PigpioException;
import jpigpio.PigpioSocket;

/**
 * Represents an encoder connected to the pi
 * 
 * @author ctychen, lakshbhambhani
 *
 */
public class Encoder {

	private GpioPinDigitalInput leftPin, rightPin;

	private int leftPinState;
	private int rightPinState;

	private boolean reversed;

	public int count;

	// private final GpioController gpio = GpioFactory.getInstance();

	JPigpio pigpio;

	private final int[] outcome = { 0, -1, 1, 0, -1, 0, 0, 1, 1, 0, 0, -1, 0, -1, 1, 0 };

	int last_AB = 0;

	/**
	 * Runs the encoder counting in another thread
	 */
	public void update() {
		// while(RobotState.isEnabled()) {
		int A = leftPinState;
		int B = rightPinState;
		int current_AB = (A << 1) | B;
		int position = (last_AB << 2) | current_AB;
		count += outcome[position];
		last_AB = current_AB;
		// }
	}

	/**
	 * Creates an encoder on 2 pins which can be used to get data from the motor
	 * 
	 * @param leftP  Pin for the left sensor on the encoder
	 * @param rightP Pin for the right sensor on the encoder
	 * @throws PigpioException
	 */
	public Encoder(int leftP, int rightP, boolean reversed) throws PigpioException {
		count = 0;
		this.reversed = reversed;

//		 leftPin = gpio.provisionDigitalInputPin(leftP);
//		 leftPin.setShutdownOptions(true);
//		 rightPin = gpio.provisionDigitalInputPin(rightP);
//		 rightPin.setShutdownOptions(true);
		pigpio = new PigpioSocket("127.0.0.1", 8888);
		pigpio.gpioSetAlertFunc(leftP, (gpio, level, tick) -> {
//				System.out.println(
//					String.format("Callback in Java: We received an alert on: %d with %d at %d",
//						gpio, level, tick));
			if (level < 2) {
				leftPinState = level;
				update();
			}
		});
		pigpio.gpioSetAlertFunc(rightP, (gpio, level, tick) -> {
//				System.out.println(
//					String.format("Callback in Java: We received an alert on: %d with %d at %d",
//						gpio, level, tick));
			if (level < 2) {
				rightPinState = level;
				update();
			}

		});

	}

	/**
	 * Returns number of ticks the encoder has gone through
	 * 
	 * @return int Ticks - the number of ticks
	 */
	public int getTicks() {
		// if (reversed) {
		// 	return -1 * this.count;
		// } else {
		// 	return this.count;
		// }
		return this.count;
	}

	/**
	 * Calculates and returns the number of rotations the wheel has gone through
	 * 
	 * @return Double rotations - the number of rotations the wheel has gone through
	 */
	public double getRotations() {
		return Math.abs((double)getTicks()) / RobotConstants.ENCODER_TICKS_PER_ROTATION;
	}

	/**
	 * 
	 * Calculates and returns the distance for which the motors have rotated
	 * 
	 * @return distance traveled in inches
	 */
	public double getDistance() {
		return (Math.PI * RobotConstants.DRIVE_BASE_WHEEL_DIAMETER * getRotations());
	}

//	public double getVelocityCm() {
//		return (RobotConstants.ENCODER_TICKS_PER_ROTATION);
//	}
//	
//	public double getVelocityTicks() {
//		
//	}

}
