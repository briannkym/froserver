package org.table2table.froserver;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import org.table2table.froserver.service.ConnectionService;

public class Main {

	public static void main(String[] args) {
		
		String resource = "org/table2table/froserver/data/Config.xml";
		SqlSessionFactory sqlSessionFactory = null;
		try (InputStream inputStream = Resources.getResourceAsStream(resource);){
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PersistenceException e2){
			System.out.println(e2.getCause().toString());
			e2.printStackTrace();
		}
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			List<String> category = session.selectList("getCategories");
				System.out.println(category);
			session.commit();
		} finally {
		  session.close();
		}
		
		/*
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Face Slap");
			e.printStackTrace();
		}

		Statement st;
		try {
			Connection conn;
			String url = "jdbc:postgresql://localhost/frodb?user=root&password=root";
			conn = DriverManager.getConnection(url);
			st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM sites");
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				System.out.println(rs.getMetaData().getColumnName(i));
			}
			
			//root
			rs.close();
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		ConnectionService c = new ConnectionService(2000);
		new Thread(c).start();
	}
}
