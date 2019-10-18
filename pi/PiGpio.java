package pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class PiGpio {
	
	static GpioController gpio;
	static GpioPinDigitalOutput[] pins;
	
	public PiGpio()  {
		gpio = GpioFactory.getInstance();
		pins = new GpioPinDigitalOutput[] {
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, PinState.LOW),
		};
        gpio.setShutdownOptions(true, PinState.LOW, pins);

	}
	

	
}
