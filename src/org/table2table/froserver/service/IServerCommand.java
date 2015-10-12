package org.table2table.froserver.service;

import java.io.IOException;
import java.io.Serializable;

import org.table2table.froserver.model.IFRODatabase;

public interface IServerCommand extends Serializable{
	
	public void accept(IFRODatabase i, CommunicationService c) throws IOException ;
}
