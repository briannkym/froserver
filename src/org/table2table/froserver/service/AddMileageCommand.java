package org.table2table.froserver.service;

import java.io.IOException;

import org.table2table.froserver.model.IFRODatabase;
import org.table2table.froserver.model.MileageEntry;

/**
 * 
 * @author Brian Nakayama
 *
 */
public class AddMileageCommand implements IServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void accept(IFRODatabase i, CommunicationService c)
			throws IOException {

		try {
			MileageEntry m = (MileageEntry) c.getInputStream().readObject();
			c.getOutputStream().writeObject(i.addMileage(m));
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
