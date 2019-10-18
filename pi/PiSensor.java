package pi;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class PiSensor {
	
	enum type{
		ULTRASONIC, IR
	};
	
	double distanceBound;
	
	public PiSensor() {
		
	}
	
	public void check() {
		
	}

	

}
