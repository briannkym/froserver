package org.table2table.froserver.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * @author Brian Nakayama
 *
 */
public class PoundEntry implements Serializable{

	/**
	 * Default ID added December 9th, 2015.
	 */
	private static final long serialVersionUID = 1L;

	private String site;
	private Date d;
	private String category;
	private int pounds;
	private int route = 0;
	private boolean pickup = false;
	
	/**
	 * Constructor for a pickup entry. Use this to enter pickup records into the database.
	 * @param site The site (must match a site in the database)
	 * @param d The date (SQL Date)
	 * @param category The category (must match a category in the database)
	 * @param pounds The pounds (>0)
	 */
	public PoundEntry(String site, Date d, String category, int pounds){
		this.site = site;
		this.d = d;
		this.category = category;
		this.pounds = pounds;
		this.pickup = true;
	}
	
	/**
	 * Constructor for a dropoff entry. Use this to enter dropoff records into the database.
	 * @param site The site (must match a site in the database)
	 * @param d The date (SQL Date)
	 * @param route The route (The tuple (route,site) must exist in the routes table of the database)
	 * @param category The category (must match a category in the database)
	 * @param pounds The pounds (>0)
	 */
	public PoundEntry(String site, Date d, int route, String category, int pounds){
		this.site = site;
		this.d = d;
		this.route = route;
		this.category = category;
		this.pounds = pounds;
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
	
	public int getRoute() {
		return route;
	}

	public int getPounds() {
		return pounds;
	}
	
	public boolean isPickup(){
		return pickup;
	}
	
}
