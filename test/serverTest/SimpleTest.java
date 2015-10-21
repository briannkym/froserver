package serverTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.table2table.froserver.model.*;
import org.table2table.froserver.service.ClientMessage;
import org.table2table.froserver.service.CloseCommand;
import org.table2table.froserver.service.GetCategoriesCommand;
import org.table2table.froserver.service.GetRequestsCommand;
import org.table2table.froserver.service.GetRoutesCommand;
import org.table2table.froserver.service.GetSitesCommand;
import org.table2table.froserver.service.GetVansCommand;

/**
 * Example code for retrieving information from the database.
 * @author Brian Nakayama
 *
 */
public class SimpleTest {
	public static void main(String[] args) {
		String hostName = "127.0.0.1";
		int portNumber = 2000;

		try (Socket socket = new Socket(hostName, portNumber);
				ObjectOutputStream oOS = new ObjectOutputStream(
						socket.getOutputStream());
				ObjectInputStream oIS = new ObjectInputStream(
						socket.getInputStream());) {
			ClientMessage m;
			System.out.println("STARTING SIMPLE CLIENT");
			
			
			// Get a list of all the categories
			System.out.println("\n\n All categories:");
			oOS.writeObject(new GetCategoriesCommand());
			List<String> l1 = (List<String>) oIS.readObject();
			m = (ClientMessage) oIS.readObject();
			if (m.isSuccessful()) {
				for (String s : l1) {
					System.out.println(s);
				}
			}
			System.out.println(m.getMessage());

			// Get a list of standing requests for a particular day
			System.out.println("\n\n Standing Requests:");
			Calendar c = new GregorianCalendar();
			c.set(2015, 10, 19);
			oOS.writeObject(new GetRequestsCommand(new Date(c.getTime()
					.getTime())));
			List<RequestEntry> l2 = (List<RequestEntry>) oIS.readObject();
			m = (ClientMessage) oIS.readObject();
			if (m.isSuccessful()) {
				for (RequestEntry r : l2) {
					System.out.println(r.toString());
				}
			}
			System.out.println(m.getMessage());
			
			// Get a list of vans
			System.out.println("\n\n All Vans:");
			oOS.writeObject(new GetVansCommand());
			List<MileageEntry> l3 = (List<MileageEntry>) oIS.readObject();
			m = (ClientMessage) oIS.readObject();
			if (m.isSuccessful()) {
				for (MileageEntry car : l3) {
					System.out.println(car.toString());
				}
			}
			System.out.println(m.getMessage());
			
			
			// Get a list of all the sites
			System.out.println("\n\n All sites:");
			oOS.writeObject(new GetSitesCommand());
			List<SiteEntry> l4 = (List<SiteEntry>) oIS.readObject();
			m = (ClientMessage) oIS.readObject();
			if (m.isSuccessful()) {
				for (SiteEntry s : l4) {
					System.out.println(s.toString());
				}
			}
			System.out.println(m.getMessage());

			// Get a map containing all of the trips
			System.out.println("\n\n All trips:");
			oOS.writeObject(new GetRoutesCommand());
			Map<Integer, List<String>> routes = (Map<Integer, List<String>>) oIS
					.readObject();
			m = (ClientMessage) oIS.readObject();
			if (m.isSuccessful()) {
				Set<Entry<Integer, List<String>>> routeset = routes.entrySet();
				for (Entry<Integer, List<String>> r : routeset) {
					System.out.println("Route " + r.getKey() + ": "
							+ r.getValue().toString());
				}
			}
			System.out.println(m.getMessage());

			// Close the stream
			oOS.writeObject(new CloseCommand());
			m = (ClientMessage) oIS.readObject();
			if (m.isSuccessful()) {
				System.out.println("Stream is closed.");
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
