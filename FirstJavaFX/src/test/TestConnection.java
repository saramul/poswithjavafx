package test;

import util.MySQLHelper;

public class TestConnection {
	public static void main(String[] args) {
		if(MySQLHelper.openDB()!=null) {
			System.out.println("Connected to DB");
			MySQLHelper.closeDB();
		}else {
			System.out.println("Unable connect to DB");
		}
	}
}
