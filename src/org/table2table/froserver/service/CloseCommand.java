package org.table2table.froserver.service;

import java.io.IOException;

import org.table2table.froserver.model.IFRODatabase;

public class CloseCommand implements IServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void accept(IFRODatabase i, CommunicationService c)
			throws IOException {
		c.close();
	}
	
}
