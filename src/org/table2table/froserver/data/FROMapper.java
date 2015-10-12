package org.table2table.froserver.data;

import java.sql.Date;

public interface FROMapper {
	 public void addPickup(String site, Date date, String category, int pounds);
 
	 public void addDropoff(String site, Date date, String category, int pounds);
	 
}
