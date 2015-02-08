import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class ServoControl {
	
	I2CBus i2c = null;
	I2CDevice i2cdevice = null;

	
	/**
	 * Use this constructor to properly initalize ServoControl class
	 */
	public ServoControl(){
		try {
			i2c = I2CFactory.getInstance(1);
			i2cdevice = i2c.getDevice(0x40);
			i2cdevice.write(0x00, (byte) 0x00);
			double prescale = Math.floor((((25000000.0/4096.0)/60) - 1.0) + 0.5);
			int oldmode = i2cdevice.read(0x00);
			int newmode = (oldmode & 0x7F) | 0x10;
			System.out.println("Servo frequency variable is "+ prescale);
			i2cdevice.write(0x00, (byte) newmode);
			i2cdevice.write(0xFE, (byte) Math.floor(prescale));
			i2cdevice.write(0x00, (byte) oldmode);
			Thread.sleep(5);
			i2cdevice.write(0x00, (byte)(oldmode | 0x80));
			System.out.println("Configuring servo control interface [ OK ]");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void setPosition(int channel, int on, int off){
		try {
			i2cdevice.write(0x06+4*channel, (byte) (on & 0xFF));
			i2cdevice.write(0x07+4*channel, (byte) (on >> 8));
			i2cdevice.write(0x08+4*channel, (byte) (off & 0xFF));
			i2cdevice.write(0x09+4*channel, (byte) (off >> 8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the position:
	 * 
	 * 
	 * par1 = Channel to control (staring with 0)
	 * 
	 * par2 = position defined as:
	 * 
	 * 0 = neutral
	 * -256 = left
	 * 256 = right
	 * 
	 * @param angle
	 */
	public void setServoPosition(int channel, int angle){
		int abs = angle+256;
		float percentage = ((float)(abs)/512.0f);
		int value = (int)(((600-150)*percentage)+150);
		setPosition(channel, 0, value);
	}
	
}
