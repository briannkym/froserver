package org.table2table.froserver.data;

import java.sql.Date;
import java.util.List;

import org.table2table.froserver.model.RequestEntry;
import org.table2table.froserver.model.SiteEntry;

public interface SiteMapper {

  public List<String> getCategories();
  
  public List<String> getSiteNames();
  
  public List<SiteEntry> getSites();
  
  public List<String> getSiteCategories(String site);
  
  public List<Integer> getRoutes();
  
  public List<String> getRoute(int route);
  
  public List<RequestEntry> getRequests(Date date);
  
  public void addCategory(String category);
  
  public void addSite(String site, String address, String information, boolean pickup);
  
  public void addStop(int route,int stop,String site);
  
  public void removeRoute(int route);
}
