import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class UDPServer {

	ServerSocket streznik = null;
	
	public UDPServer(final RaspRacerXServer bundle){
		try {
			streznik = new ServerSocket(1234);
			System.out.println("UDPServer staring...");
		} catch (IOException e) {
			System.err.println("UDPServer error [ ERROR ]");
			System.exit(-1);
		}
		Thread thread = new Thread(){
			public void run(){
				while ( true ) {
					try {
						Socket vticnica = streznik.accept();
						BufferedReader is = new BufferedReader(
								new InputStreamReader(vticnica.getInputStream())
								);						
						String vhod;
						System.out.println("New client with IP "+vticnica.getInetAddress()+" [ OK ]");
						while ((vhod = is.readLine())!=null) {
							
							if(vhod.equals("DONE")){
								break;
							}else{
								System.out.println(vhod);
								bundle.execute(vhod);
							}
						}
						bundle.restart();
						System.out.println("Connection lost [ OK ]");
						is.close();
						vticnica.close();
					} catch (IOException e) {
						System.err.println("Error in UDPServer: " + e);
					}
				}
			}
		};
		System.out.println("UDPServer thread running on port 1234 [ OK ]");
		thread.start();
	}
	
	public static void main(String[] args) {
		new UDPServer(null);
	}
	
}
