package org.table2table.froserver.model;

import java.sql.Date;

import java.io.Serializable;

public class MileageEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int van;
	private Date date;
	private int route;
	private int miles;
	private String description = "";

	public MileageEntry(Integer van, Date date, Integer route, Integer miles) {
		this.van = van;
		this.date = date;
		this.route = route;
		this.miles = miles;
	}

	public MileageEntry(Integer van, Date date, Integer route, Integer miles,
			String description) {
		this.van = van;
		this.date = date;
		this.route = route;
		this.miles = miles;
		this.description = description;
	}

	public int getVan() {
		return van;
	}

	public Date getDate() {
		return date;
	}

	public int getRoute() {
		return route;
	}

	public int getMiles() {
		return miles;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Van #" + van + ", mileage: " + miles + "; " + description
				+ "\n" + "Last updated on trip " + route + " on date "
				+ date.toString() + ".";
	}
}
