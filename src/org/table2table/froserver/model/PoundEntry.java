package org.table2table.froserver.model;

import java.sql.Date;

public class PoundEntry {

	private String site;
	private Date d;
	private String category;
	private int pounds;
	boolean pickup = false;
	
	public PoundEntry(String site, Date d, String category, int pounds){
		this.site = site;
		this.d = d;
		this.category = category;
		this.pounds = pounds;
	}
	
	public PoundEntry(String site, Date d, String category, int pounds, boolean pickup){
		this.site = site;
		this.d = d;
		this.category = category;
		this.pounds = pounds;
		this.pickup = pickup;
	}

	public String getSite() {
		return site;
	}

	public Date getD() {
		return d;
	}

	public String getCategory() {
		return category;
	}

	public int getPounds() {
		return pounds;
	}
	
	public boolean isPickup(){
		return pickup;
	}
	
}
