package org.table2table.froserver.service;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import org.table2table.froserver.model.IFRODatabase;

/**
 * 
 * @author Brian Nakayama
 *
 */
public class GetRequestsCommand implements IServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date d;
	
	public GetRequestsCommand(){
		d = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}
	
	public GetRequestsCommand(Date d){
		this.d = d;
	}
	
	@Override
	public void accept(IFRODatabase i, CommunicationService c)
			throws IOException {
		c.getOutputStream().writeObject(i.getStandingRequests(d));	
	}
	
	
	
}
