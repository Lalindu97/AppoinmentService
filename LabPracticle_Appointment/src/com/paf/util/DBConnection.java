package com.paf.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	
	public static Connection getConnection() {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appointmentservice", "root", "root");
			//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "root");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appointmentservice", "root", "root");
			System.out.println("Successfully connected");
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
		
	}

}