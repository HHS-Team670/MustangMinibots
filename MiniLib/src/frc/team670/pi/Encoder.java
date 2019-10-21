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


public class Encoder {
	
	private GpioPinDigitalInput leftPin, rightPin; 
	
	private PinState leftPinState = PinState.LOW;
	private PinState rightPinState;

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
public class Encoder {

	private GpioPinDigitalInput pin;
	private int pinNum;
	private int count;

	private boolean inverted;


	private final GpioController gpio = GpioFactory.getInstance();
	
	public Encoder(Pin leftP, Pin rightP) {
		 leftPin = gpio.provisionDigitalInputPin(leftP);
		 leftPin.setShutdownOptions(true);
		 rightPin = gpio.provisionDigitalInputPin(rightP);
		 rightPin.setShutdownOptions(true);
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

	/**
	 * @param a GPIO pin number of Motor Pin A
	 * @param b GPIO pin number of Motor Pin B
	 * @param e GPIO pin (example: RaspiPin.GPIO_06) of digital output
	 */
	public Encoder(int pin) {

	}

	/**
	 * Used to divide raw edge counts down to spec'd counts.
	 *
	 * @return The encoding scale factor 1x, 2x, or 4x, per the requested encoding
	 *         type.
	 */
	public int getEncodingScale() {
		return 0;
		// return EncoderJNI.getEncoderEncodingScale(m_encoder);
	}

	public void close() {
//	    SendableRegistry.remove(this);
//	    if (m_aSource != null && m_allocatedA) {
//	      m_aSource.close();
//	      m_allocatedA = false;
//	    }
//	    if (m_bSource != null && m_allocatedB) {
//	      m_bSource.close();
//	      m_allocatedB = false;
//	    }
//	    if (m_indexSource != null && m_allocatedI) {
//	      m_indexSource.close();
//	      m_allocatedI = false;
//	    }
//
//	    m_aSource = null;
//	    m_bSource = null;
//	    m_indexSource = null;
//	    EncoderJNI.freeEncoder(m_encoder);
//	    m_encoder = 0;
	}

	/**
	 * Gets the raw value from the encoder. The raw value is the actual count
	 * unscaled by the 1x, 2x, or 4x scale factor.
	 *
	 * @return Current raw count from the encoder
	 */
	public int getRaw() {
		return 0;
		// return EncoderJNI.getEncoderRaw(m_encoder);
	}

	/**
	 * Gets the current count. Returns the current count on the Encoder. This method
	 * compensates for the decoding type.
	 *
	 * @return Current count from the Encoder adjusted for the 1x, 2x, or 4x scale
	 *         factor.
	 */
	public int get() {
		return 0;
		// return EncoderJNI.getEncoder(m_encoder);
	}

	/**
	 * Reset the Encoder distance to zero. Resets the current count to zero on the
	 * encoder.
	 */
	public void reset() {
		// EncoderJNI.resetEncoder(m_encoder);
	}

	/**
	 * Sets the maximum period for stopped detection. Sets the value that represents
	 * the maximum period of the Encoder before it will assume that the attached
	 * device is stopped. This timeout allows users to determine if the wheels or
	 * other shaft has stopped rotating. This method compensates for the decoding
	 * type.
	 *
	 * @param maxPeriod The maximum time between rising and falling edges before the
	 *                  FPGA will report the device stopped. This is expressed in
	 *                  seconds.
	 */
	public void setMaxPeriod(double maxPeriod) {
		// EncoderJNI.setEncoderMaxPeriod(m_encoder, maxPeriod);

	}

	/**
	 * Determine if the encoder is stopped. Using the MaxPeriod value, a boolean is
	 * returned that is true if the encoder is considered stopped and false if it is
	 * still moving. A stopped encoder is one where the most recent pulse width
	 * exceeds the MaxPeriod.
	 *
	 * @return True if the encoder is considered stopped.
	 */
	public boolean getStopped() {
		return false;
		// return EncoderJNI.getEncoderStopped(m_encoder);

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
	
	
	
	


	/**
=======
	}

	/**
	 * Determine if the encoder is stopped. Using the MaxPeriod value, a boolean is
	 * returned that is true if the encoder is considered stopped and false if it is
	 * still moving. A stopped encoder is one where the most recent pulse width
	 * exceeds the MaxPeriod.
	 *
	 * @return True if the encoder is considered stopped.
	 */
	public boolean getStopped() {
		return false;
		// return EncoderJNI.getEncoderStopped(m_encoder);
	}

	/**
>>>>>>> a1568608da67db493f88a24995992e6288c5576e
	 * The last direction the encoder value changed.
	 *
	 * @return The last direction the encoder value changed.
	 */
	public boolean getDirection() {
		return false;
		// return EncoderJNI.getEncoderDirection(m_encoder);
	}

	/**
	 * Get the distance the robot has driven since the last reset as scaled by the
	 * value from {@link #setDistancePerPulse(double)}.
	 *
	 * @return The distance driven since the last reset
	 */
	public double getDistance() {
		return 0;
		// return EncoderJNI.getEncoderDistance(m_encoder);
	}

	/**
	 * Get the current rate of the encoder. Units are distance per second as scaled
	 * by the value from setDistancePerPulse().
	 *
	 * @return The current rate of the encoder.
	 */
	public double getRate() {
		return 0;
		// return EncoderJNI.getEncoderRate(m_encoder);
	}

	/**
	 * Set the minimum rate of the device before the hardware reports it stopped.
	 *
	 * @param minRate The minimum rate. The units are in distance per second as
	 *                scaled by the value from setDistancePerPulse().
	 */
	public void setMinRate(double minRate) {
		// EncoderJNI.setEncoderMinRate(m_encoder, minRate);
	}

	/**
	 * Set the distance per pulse for this encoder. This sets the multiplier used to
	 * determine the distance driven based on the count value from the encoder. Do
	 * not include the decoding type in this scale. The library already compensates
	 * for the decoding type. Set this value based on the encoder's rated Pulses per
	 * Revolution and factor in gearing reductions following the encoder shaft. This
	 * distance can be in any units you like, linear or angular.
	 *
	 * @param distancePerPulse The scale factor that will be used to convert pulses
	 *                         to useful units.
	 */
	public void setDistancePerPulse(double distancePerPulse) {
		// EncoderJNI.setEncoderDistancePerPulse(m_encoder, distancePerPulse);
	}

	/**
	 * Get the distance per pulse for this encoder.
	 *
	 * @return The scale factor that will be used to convert pulses to useful units.
	 */
	public double getDistancePerPulse() {
		return 0;
		// return EncoderJNI.getEncoderDistancePerPulse(m_encoder);
	}

	/**
	 * Set the direction sensing for this encoder. This sets the direction sensing
	 * on the encoder so that it could count in the correct software direction
	 * regardless of the mounting.
	 *
	 * @param reverseDirection true if the encoder direction should be reversed
	 */
	public void setReverseDirection(boolean reverseDirection) {
		// EncoderJNI.setEncoderReverseDirection(m_encoder, reverseDirection);
	}

	/**
	 * Set the Samples to Average which specifies the number of samples of the timer
	 * to average when calculating the period. Perform averaging to account for
	 * mechanical imperfections or as oversampling to increase resolution.
	 *
	 * @param samplesToAverage The number of samples to average from 1 to 127.
	 */
	public void setSamplesToAverage(int samplesToAverage) {
		// EncoderJNI.setEncoderSamplesToAverage(m_encoder, samplesToAverage);
	}

	/**
	 * Get the Samples to Average which specifies the number of samples of the timer
	 * to average when calculating the period. Perform averaging to account for
	 * mechanical imperfections or as oversampling to increase resolution.
	 *
	 * @return SamplesToAverage The number of samples being averaged (from 1 to 127)
	 */
	public int getSamplesToAverage() {
		// return EncoderJNI.getEncoderSamplesToAverage(m_encoder);
		return 0;
	}

	/**
	 * Set which parameter of the encoder you are using as a process control
	 * variable. The encoder class supports the rate and distance parameters.
	 *
	 * @param pidSource An enum to select the parameter.
	 */
//	  @Override
//	  public void setPIDSourceType(PIDSourceType pidSource) {
//	    m_pidSource = pidSource;
//	  }

	/**
	 * Implement the PIDSource interface.
	 *
	 * @return The current value of the selected source parameter.
	 */
	public double pidGet() {
		return 0;
	}

	}}
