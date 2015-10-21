package org.table2table.froserver.model;

import java.io.Serializable;
import java.util.List;

public class SiteEntry implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String site;
	private String address;
	private String information;
	private boolean pickup;
	private List<String> expectedCat;
	
	/**
	 * Construct a simple java object for sites.
	 * @param site The site's name 
	 * @param address The address
	 * @param information Information specific to this site.
	 * @param pickup True for pickups, false for dropoffs.
	 * @param expectedCat A list of the expected categories for this site.
	 */
	public SiteEntry(String site, String address, String information,
			Boolean pickup, List expectedCat) {
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
	
	@Override
	public String toString(){
		String s;
		if(pickup){
			s = "Pickup";
		} else {
			s = "Dropoff";
		}
		s+= " site, " + site + ": " + address +". ";
		s+= information + ".\n";
		s+= expectedCat.toString();
		return s;
	}
	
}
