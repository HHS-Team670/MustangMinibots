package pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class PiTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J--> GPIO Control Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalOutput a= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "Motor A", PinState.LOW);
        final GpioPinDigitalOutput b = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "Motor B", PinState.LOW);
        final GpioPinDigitalOutput ic = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "IC Pin", PinState.HIGH);

        
        // set shutdown state for this pin
        a.setShutdownOptions(true, PinState.LOW);
        b.setShutdownOptions(true, PinState.LOW);
        ic.setShutdownOptions(true, PinState.HIGH);

        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(5000);

        // turn off gpio pin #01
        a.low();
        b.low();
        System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(5000);

        // toggle the current state of gpio pin #01 (should turn on)
        a.toggle();
        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(5000);

        // toggle the current state of gpio pin #01  (should turn off)
        a.toggle();
        System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(5000);

        // turn on gpio pin #01 for 1 second and then off
        System.out.println("--> GPIO state should be: ON for only 1 second");
        a.pulse(1000, true); // set second argument to 'true' use a blocking call

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();

        System.out.println("Exiting ControlGpioExample");
    }
}