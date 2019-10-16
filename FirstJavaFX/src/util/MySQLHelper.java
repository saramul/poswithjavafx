package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLHelper {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/productdb";
	private static String user = "root";
	private static String password = "root";
	private static Connection conn = null;
	
	public static Connection openDB() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeDB() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
