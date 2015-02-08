import java.io.IOException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


public class GPIOEventServer {

	public GPIOEventServer(final RaspRacerXServer bundle){
		GpioController gpio = GpioFactory.getInstance();
		System.out.println("Configuring GPIO events interface [ OK ]");
        GpioPinDigitalInput bt1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, PinPullResistance.PULL_DOWN);
        bt1.addListener(new GpioPinListenerDigital()
        {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
            {
                PinState state = event.getState();
                boolean ison = state.isHigh();
                if(ison){
                	bundle.sesionkey=SessionKeyGenerator.generate();
                	bundle.displaySessionKey();
                	System.out.println("New session key generated: "+ bundle.sesionkey);
                }
            }
            
        });
        GpioPinDigitalInput bt2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN);
        bt2.addListener(new GpioPinListenerDigital()
        {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
            {
                PinState state = event.getState();
                boolean ison = state.isHigh();
                if(ison){
                	try {
						Runtime.getRuntime().exec(new String[]{"bash","-c","sudo shutdown -h now"});
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
            
        });
	}
	
}
