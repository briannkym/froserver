package serverTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.table2table.froserver.model.MileageEntry;
import org.table2table.froserver.model.PoundEntry;
import org.table2table.froserver.service.*;

public class SendDataTest {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter IP Address:");
		String hostName = keyboard.nextLine();
		
		try (Socket socket = new Socket(hostName, org.table2table.froserver.Main.portNumber);
				ObjectOutputStream oOS = new ObjectOutputStream(
						socket.getOutputStream());
				ObjectInputStream oIS = new ObjectInputStream(
						socket.getInputStream());) {
			
			Date d = new Date(Calendar.getInstance().getTime().getTime());
			
			MileageEntry mileage = new MileageEntry(
					new Integer(1), d, new Integer(0), new Integer(12), "Example");
			
			LinkedList<PoundEntry> pounds = new LinkedList<PoundEntry>();
			pounds.add(new PoundEntry("Farmer's Market", d, 1, "Meat", 10));
			
			oOS.writeObject(new AddTripCommand());
			
			oOS.writeObject(mileage);
			
			oOS.writeObject(pounds);
			
			ClientMessage m;
			
			System.out.println(((ClientMessage)oIS.readObject()).getMessage());
			
			m = (ClientMessage) oIS.readObject();
			
			System.out.println(m.getMessage());
			
			oOS.writeObject(new CloseCommand());
			m = (ClientMessage) oIS.readObject();
			if (m.isSuccessful()) {
				System.out.println("Stream is closed.");
			}
			
			/*
			Object m = null;
			
			oOS.writeObject(new AddMileageCommand());
			
			System.out.print("Command Sent");
			
			oOS.writeObject(new MileageEntry(new Integer(1), d, new Integer(2), new Integer(50), "Example"));
			
			System.out.println("Mileage Sent");
			
			m = oIS.readObject();
			
			System.out.println(m);
			
			oOS.writeObject(new CloseCommand());
			
			System.out.println("Close sent");

			oIS.readObject();*/
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
