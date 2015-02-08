import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class GpioControl {
	
	GpioController gpio = GpioFactory.getInstance();
	GpioPinDigitalOutput pin0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin10 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin12 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin13 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin14 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin15 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "GpioChannel0", PinState.LOW);
	GpioPinDigitalOutput pin16 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "GpioChannel0", PinState.LOW);

	public GpioControl(){
		pin0.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin1.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin2.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin3.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin4.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin5.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin10.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin11.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin12.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin13.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin14.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin15.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin16.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		pin0.low();
		pin1.low();
		pin2.low();
		pin3.low();
		pin4.low();
		pin5.low();
		pin10.low();
		pin11.low();
		pin12.low();
		pin13.low();
		pin14.low();
		pin15.low();
		pin16.low();
		System.out.println("Configuring GPIO control interface (thanks to Pi4j) [ OK ]");
	}
	
	public void restart(){
		pin0.low();
		pin1.low();
		pin2.low();
		pin3.low();
		pin4.low();
		pin5.low();
		pin10.low();
		pin11.low();
		pin12.low();
		pin13.low();
		pin14.low();
		pin15.low();
		pin16.low();
	}
	
	public void setPinState(int channel, boolean state){
		if (channel==0)
			if(state)
				pin0.toggle();
			else
				pin0.low();
		else if (channel==1)
			if(state)
				pin1.toggle();
			else
				pin1.low();
		else if (channel==2)
			if(state)
				pin2.toggle();
			else
				pin2.low();
		else if (channel==3)
			if(state)
				pin3.toggle();
			else
				pin3.low();
		else if (channel==4)
			if(state)
				pin4.toggle();
			else
				pin4.low();
		else if (channel==5)
			if(state)
				pin5.toggle();
			else
				pin5.low();
		else if (channel==6)
			System.out.println("This pin is used for I2C - 6 [ ERROR ]");
		else if (channel==7)
			System.out.println("This pin is used for I2C - 7 [ ERROR ]");
		else if (channel==8)
			System.out.println("This pin is used for I2C - 8 [ ERROR ]");
		else if (channel==9)
			System.out.println("This pin is used for I2C - 9 [ ERROR ]");
		else if (channel==10)
			if(state)
				pin10.toggle();
			else
				pin10.low();
		else if (channel==11)
			if(state)
				pin11.toggle();
			else
				pin11.low();
		else if (channel==12)
			if(state)
				pin12.toggle();
			else
				pin12.low();
		else if (channel==13)
			if(state)
				pin13.toggle();
			else
				pin13.low();
		else if (channel==14)
			if(state)
				pin14.toggle();
			else
				pin14.low();
		else if (channel==15)
			if(state)
				pin15.toggle();
			else
				pin15.low();
		else if (channel==16)
			if(state)
				pin16.toggle();
			else
				pin16.low();
			
	}
	
}
