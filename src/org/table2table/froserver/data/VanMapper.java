package org.table2table.froserver.data;

import org.table2table.froserver.model.MileageEntry;

import java.sql.Date;
import java.util.List;

public interface VanMapper {
	   
	  public List<MileageEntry> getMileage();

	  public List<MileageEntry> getVanHistory(int van);
	  
	  public MileageEntry getVanInfo(int van);

	  public List<MileageEntry> getVans();
	    
	  public void addVan(int van, String description);
	  
	  public void addMileage(int van, Date date, int route, int miles);
	  
}
