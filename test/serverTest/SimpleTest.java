package serverTest;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.table2table.froserver.model.ServerCommand;

public class SimpleTest {
	public static void main(String[] args) {
		String hostName = "127.0.0.1";
		int portNumber = 2000;

		try (Socket socket = new Socket(hostName, portNumber);
				BufferedOutputStream bOS = new BufferedOutputStream(socket.getOutputStream());
				ObjectOutputStream oOS = new ObjectOutputStream(
						bOS);) {
			oOS.writeObject(new ServerCommand("Hello World!"));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		}
	}
}
