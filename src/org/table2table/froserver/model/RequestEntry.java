package org.table2table.froserver.model;

import java.io.Serializable;

public class RequestEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String category;
	private int pounds;
	
	
	public RequestEntry(String category, int pounds) {
		this.category = category;
		this.pounds = pounds;
	}

	public String getCategory() {
		return category;
	}
	
	public int getPounds() {
		return pounds;
	}
}
