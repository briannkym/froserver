package org.table2table.froserver.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.table2table.froserver.model.IFRODatabase;
import org.table2table.froserver.model.MileageEntry;
import org.table2table.froserver.model.PoundEntry;
import org.table2table.froserver.model.RequestEntry;
import org.table2table.froserver.model.SiteEntry;
import org.table2table.froserver.service.ClientMessage;

public class FRODatabaseBatis implements IFRODatabase {

	private SqlSessionFactory sqlSessionFactory = null;
	private final String resource = "org/table2table/froserver/data/Config.xml";

	public FRODatabaseBatis() {
		try (InputStream inputStream = Resources.getResourceAsStream(resource);) {
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (PersistenceException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public List<SiteEntry> getSites() {
		List<SiteEntry> sites;
		try (SqlSession session = sqlSessionFactory.openSession();) {
			SiteMapper s = session.getMapper(SiteMapper.class);
			sites = s.getSites();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sites;
	}

	@Override
	public List<RequestEntry> getStandingRequests(Date d) {
		List<RequestEntry> requests;
		try (SqlSession session = sqlSessionFactory.openSession();) {
			SiteMapper s = session.getMapper(SiteMapper.class);
			requests = s.getRequests(d);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return requests;
	}

	@Override
	public List<MileageEntry> getVans() {
		List<MileageEntry> vans;
		try (SqlSession session = sqlSessionFactory.openSession();) {
			VanMapper s = session.getMapper(VanMapper.class);
			vans = s.getVans();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return vans;
	}

	@Override
	public List<String> getCategories() {
		List<String> categories;
		try (SqlSession session = sqlSessionFactory.openSession();) {
			SiteMapper s = session.getMapper(SiteMapper.class);
			categories = s.getCategories();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return categories;
	}

	@Override
	public Map<Integer, List<String>> getRoutes() {
		Map<Integer, List<String>> routes = new HashMap<Integer, List<String>>();
		try (SqlSession session = sqlSessionFactory.openSession();) {
			SiteMapper s = session.getMapper(SiteMapper.class);
			List<Integer> route_nums = s.getRoutes();
			for (int i : route_nums) {
				routes.put(i, s.getRoute(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return routes;
	}

	@Override
	public ClientMessage addMileage(MileageEntry m) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			VanMapper s = session.getMapper(VanMapper.class);
			s.addMileage(m.getVan(), m.getDate(), m.getRoute(), m.getMiles());
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return new ClientMessage(
					"Failed to enter mileage into database. Most likely due to an incorrect key.",
					false);
		}
		return new ClientMessage("Mileage for " + m.getVan() + " entered.",
				true);
	}

	@Override
	public ClientMessage addPounds(PoundEntry p) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			FROMapper t = session.getMapper(FROMapper.class);
			if (p.isPickup()) {
				t.addPickup(p.getSite(), p.getD(), p.getCategory(),
						p.getPounds());
			} else {
				t.addDropoff(p.getSite(), p.getD(), p.getCategory(),
						p.getPounds());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return new ClientMessage(
					"Failed to enter site "
							+ p.getSite()
							+ " dropoff/pickup into database. Most likely due to an incorrect key.",
					false);
		}
		return new ClientMessage("Pounds for " + p.getSite() + " entered.",
				true);
	}

	@Override
	public ClientMessage addTrip(MileageEntry m, List<PoundEntry> pounds) {
		try (SqlSession session = sqlSessionFactory.openSession();) {
			VanMapper s = session.getMapper(VanMapper.class);
			FROMapper t = session.getMapper(FROMapper.class);
			for (PoundEntry p : pounds){
				if (p.isPickup()) {
					t.addPickup(p.getSite(), p.getD(), p.getCategory(),
							p.getPounds());
				} else {
					t.addDropoff(p.getSite(), p.getD(), p.getCategory(),
							p.getPounds());
				}
			}
			s.addMileage(m.getVan(), m.getDate(), m.getRoute(), m.getMiles());
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return new ClientMessage(
					"Failed to enter trip on" + m.getDate() + "  into database.",
					false);
		}
		return new ClientMessage("Trip on" + m.getDate() + " entered.",
				true);
	}
}
