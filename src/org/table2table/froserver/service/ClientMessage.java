package org.table2table.froserver.service;

import java.io.Serializable;

public class ClientMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean successful = true;
	private String message;

	public ClientMessage(String message){
		this.message = message;
	}
	
	public ClientMessage(String message, boolean successful){
		this.message = message;
		this.successful = successful;
	}
	
	public String getMessage(){
		return message;
	}
	
	public boolean isSuccessful(){
		return successful;
	}
}
