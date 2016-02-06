package org.table2table.froserver;

import org.table2table.froserver.data.FRODatabaseBatis;
import org.table2table.froserver.model.IFRODatabase;
import org.table2table.froserver.service.ConnectionService;

public class Main {
	
	public static final int portNumber = 2000;

	public static void main(String[] args) {
		IFRODatabase database = new FRODatabaseBatis();
		ConnectionService c = new ConnectionService(portNumber, database);
		new Thread(c).start();
	}
}
