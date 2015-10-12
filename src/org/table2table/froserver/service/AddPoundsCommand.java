package org.table2table.froserver.service;

import java.io.IOException;

import org.table2table.froserver.model.IFRODatabase;
import org.table2table.froserver.model.PoundEntry;

public class AddPoundsCommand implements IServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void accept(IFRODatabase i, CommunicationService c)
			throws IOException {
		try {
			PoundEntry p = (PoundEntry) c.getInputStream().readObject();
			c.getOutputStream().writeObject(i.addPounds(p));
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
