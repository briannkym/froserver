package org.table2table.froserver.model;

import java.io.Serializable;
import java.util.List;

public class SiteEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String site;
	private String address;
	private String information;
	private boolean pickup;
	private List<String> expectedCat;
	
	public SiteEntry(String site, String address, String information,
			boolean pickup, List<String> expectedCat) {
		this.site = site;
		this.address = address;
		this.information = information;
		this.pickup = pickup;
		this.expectedCat = expectedCat;
	}

	public boolean isPickup() {
		return pickup;
	}
	
	public String getInformation() {
		return information;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getSite() {
		return site;
	}
	
	public List<String> getExpectedCat() {
		return expectedCat;
	}
	
}
