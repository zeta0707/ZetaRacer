import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class TestSender {

	public static void main(String[] args) {
		try {
			Socket vticnica = new Socket("192.168.0.1", 1234);
			PrintWriter izhod = new PrintWriter(vticnica.getOutputStream(), true);
			izhod.println("klopetremnd");
			izhod.close();
			vticnica.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
