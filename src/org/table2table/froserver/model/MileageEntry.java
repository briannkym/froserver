package org.table2table.froserver.model;

import java.sql.Date;

public class MileageEntry {
	private int van;
	private Date date;
	private int route;
	private int miles;
	private String description="";
	
	public int getVan() {
		return van;
	}
	
	public void setVan(int van) {
		this.van = van;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getRoute() {
		return route;
	}
	
	public void setRoute(int route) {
		this.route = route;
	}
	
	public int getMiles() {
		return miles;
	}
	
	public void setMiles(int miles) {
		this.miles = miles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
