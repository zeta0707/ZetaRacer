import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class RaspRacerXServer {

	UDPServer server = null;
	ServoControl sc = null;
	GpioControl gc = null;
	GPIOEventServer ges = null;
	int trim = 0;
	
	String sesionkey = "0000";
	
	private RaspRacerXServer(){
		System.out.println("RPi RC Server started... [Powered by Pylo]");
		server = new UDPServer(this);
		
		 try {
			BufferedReader in = new BufferedReader(new FileReader("st.trim"));
			trim = Integer.parseInt(in.readLine().trim());
			System.out.println("Trim point is: "+trim);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		gc = new GpioControl();
		sc = new ServoControl();
		ges = new GPIOEventServer(this);
		sesionkey = SessionKeyGenerator.generate();
		displaySessionKey();
		restart();		
		System.out.println("First session key generated: "+sesionkey+" (use button on board to make new one)");
	}
	
	public static void main(String[] args) {
		new RaspRacerXServer();//new bundle of server
	}
	
	public void restart(){
		sc.setServoPosition(0, 0);
		sc.setServoPosition(1, 0);
		gc.restart();
	}
	
	/*
	 *
	 * This function displays session key on gpio ports
	 * 
	 */
	public void displaySessionKey(){
		char[] key = sesionkey.toCharArray();
		boolean pin1 = false;
		boolean pin2 = false;
		boolean pin3 = false;
		boolean pin4 = false;
		if(key[0]=='1')
			pin1=true;
		if(key[1]=='1')
			pin2=true;
		if(key[2]=='1')
			pin3=true;
		if(key[3]=='1')
			pin4=true;
		gc.setPinState(0, pin1);
		gc.setPinState(1, pin2);
		gc.setPinState(2, pin3);
		gc.setPinState(3, pin4);
	}

	/*
	 * Handels udp action. Input:
	 * 
	 * SessionKey;DATA
	 * 
	 * Example:
	 * 
	 * 0110;I2C:0:65
	 * 
	 * 
	 * @param data
	 */
	void execute(String data) {
		String[] datas = data.split(";");
		//if(datas[0].trim().equals(sesionkey)){
			String vhod = datas[1].trim();
			if(vhod.startsWith("I2C:")){
				handleI2C(vhod);
			}else if(vhod.startsWith("GPIO:")){
				handleGPIO(vhod);
			}
		//}
	}
	
	/*
	 * Handles steering state. Data needs to be this shape:
	 * 
	 * I2C:0:39
	 * 
	 * Integers from -100 to 100
	 * 
	 * @param data
	 */
	private void handleI2C(String data){
		String[] datas = data.split(":");
		int channel = Integer.parseInt(datas[1]);
		int angle = Integer.parseInt(datas[2]);
		if(channel==1){
			angle = angle + trim;
		}
		sc.setServoPosition(channel, angle);
	}
	
	/*
	 * Handles gpio state. Data needs to be in this shape:
	 * 
	 *GPIO:4:true 
	 * 
	 * @param data
	 */
	private void handleGPIO(String data){
		String[] datas = data.split(":");
		int channel = Integer.parseInt(datas[1]);
		boolean state = Boolean.parseBoolean(datas[2]);
		gc.setPinState(channel, state);
	}
	
}
