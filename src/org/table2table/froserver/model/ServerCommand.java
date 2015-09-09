package org.table2table.froserver.model;

import java.io.Serializable;

public class ServerCommand implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String s;
	
	public ServerCommand(String s){
		this.s = s;
	}
	
	@Override
	public String toString(){
		return s;
	}
	
}
