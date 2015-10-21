package org.table2table.froserver.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import org.table2table.froserver.model.IFRODatabase;
import org.table2table.froserver.model.MileageEntry;
import org.table2table.froserver.model.PoundEntry;

/**
 * 
 * @author Brian Nakayama
 *
 */
public class AddTripCommand implements IServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void accept(IFRODatabase i, CommunicationService c)
			throws IOException {
		ObjectInputStream oIS = c.getInputStream();
		try {
			MileageEntry m = (MileageEntry) oIS.readObject();
			List<PoundEntry> p = (List<PoundEntry>) oIS.readObject();
			c.getOutputStream().writeObject(i.addTrip(m, p));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			c.getOutputStream()
					.writeObject(
							new ClientMessage(
									"Incorrect message type sent. Contact a programmer!",
									false));
		}

	}

}
