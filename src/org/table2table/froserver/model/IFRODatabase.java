package org.table2table.froserver.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.table2table.froserver.service.ClientMessage;

public interface IFRODatabase {
	public List<SiteEntry> getSites();
	public List<RequestEntry> getStandingRequests(Date d);
	public List<MileageEntry> getVans();
	public List<String> getCategories();
	public Map<Integer,List<String>> getRoutes();
	
	public ClientMessage addMileage(MileageEntry m);
	public ClientMessage addPounds(PoundEntry p);
	public ClientMessage addTrip(MileageEntry m, List<PoundEntry> pounds);
}