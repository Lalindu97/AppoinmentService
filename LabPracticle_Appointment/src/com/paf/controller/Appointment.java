package com.paf.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.paf.model.AppoinmentClass;
import com.paf.util.DBConnection;

public class Appointment {

//	public Connection connect() {
//		Connection con = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appointmentservice", "root", "Lalindu97");
//			//?verifyServerCertificate=false&useSSL=true");
//			// For testing
//			System.out.println("Successfully connected");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		return con;
//	}
	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	public String insertAppointment(String name, String contact,String date,String docname,String location,String time) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			 ps = connection.prepareStatement(
						"INSERT INTO  appointment(AID,AName,AContact,ADate,ADocName,Alocation,Atime) VALUES (?,?,?,?,?,?,?)");

			ps.setInt(1, 0);
			ps.setString(2, name);
			ps.setString(3, contact);
			ps.setString(4, date);
			ps.setString(5,docname);
			ps.setString(6, location);
			ps.setString(7, time);

			

			ps.execute();
			 connection.close();
			 output = "Inserted successfully"; 

		}
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output; 

		
	}

	
	public String readAppointment() {
		String output = "";
		System.out.println("----------------------------------------------");
		// Add into the html table
		try {
			//Connection con = connect();
			Connection con = DBConnection.getConnection();
			
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// System.out.println("------------b---------------------------------");
			// Add into the html table
			// Prepare the html table to be displayed
			output = "<table border=\"1\">" 
					+ "<tr>" 
					+ "<th>Patient Name</th>" 
					+ "<th>Contact Number</th>"
					+ "<th>Appointment Date</th>"
					+ "<th>Doctor Name</th>" 
					+ "<th>Location</th>" 
					+ "<th>Time</th>" 
					+ "<th>Update</th>" 
					+ "<th>Remove</th>"
					+ "</tr>";
			// System.out.println("------------c--------------------------------");
			String query = "select * from appointment";
			// Statement stmt = (Statement) con.createStatement();
			PreparedStatement sts = con.prepareStatement(query);
			
			ResultSet rss = sts.executeQuery();

			while (rss.next()) {
				// System.out.println("------------e---------------------------------");
				//b.setCusNo(Integer.toString(rss.getInt("cno")));
				//b.setCusName(rss.getString("cname"));
				
				AppoinmentClass a = new AppoinmentClass();
				
				a.setAID(rss.getInt("AID"));
				a.setAName(rss.getString("AName"));
				a.setAContact(Integer.toString(rss.getInt("AContact")));
				a.setADate(rss.getString("ADate"));
				a.setADocName(rss.getString("ADocName"));
				a.setAlocation(rss.getString("Alocation"));
				a.setAtime(rss.getString("Atime"));
				
				int AID = a.getAID();
				String AName = a.getAName();
				String AContact = a.getAContact();
				String ADate = a.getADate();
				String ADocName = a.getADocName();
				String Alocation = a.getAlocation();
				String Atime = a.getAtime();
				
				
				System.out.println("Patient Name : " + AName);

				output += "<tr><td><input id='hidAIDUpdate' name='hidAIDUpdate' type='hidden' value='" + AID
						+ "'>" + AName + "</td>";
				output += "<td>" + AContact + "</td>";
				output += "<td>" + ADate + "</td>";
				output += "<td>" + ADocName + "</td>";
				output += "<td>" + Alocation + "</td>";
				output += "<td>" + Atime + "</td>";

				
				
				
				
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-aid='" + AID + "'>" + "</td>"
								+ "</tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Appointment.";
			System.err.println(e.getMessage());

		}

		return output;

	}

	public String updateAppointment(String AID, String Aname, String Acontact, String ADate,String ADocName,String Alocation,String Atime) {
		String output = "";
		try {
			//Connection con = connect();
			
			Connection con = DBConnection.getConnection();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
		
			// create a prepared statement
			String query = "UPDATE appointment SET AName = ?, AContact = ?, ADate = ?, ADocName = ?, Alocation = ?, Atime = ? WHERE AID = ?";


			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			// binding values
			System.out.println("----------update start---------");
			preparedStmt.setInt(1, Integer.parseInt(AID));
			preparedStmt.setString(2, Aname);
			preparedStmt.setString(3,Acontact);
			preparedStmt.setString(4, ADate);
			preparedStmt.setString(5, ADocName);
			preparedStmt.setString(6, Alocation);
			preparedStmt.setString(7, Atime);
			
			System.out.println("----------update ---------");
			System.out.println(AID);
			System.out.println( Aname);
			System.out.println(Acontact);
			System.out.println(ADate);
			System.out.println(ADocName);
			// execute the statement
			preparedStmt.execute();

			con.close();
			String newItems = readAppointment();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":         \"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteAppointment(String AID) {
		String output = "";
		try {
			//Connection con = connect();
		
			Connection con = DBConnection.getConnection();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from appointment where AID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(AID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			String newItems = readAppointment();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":   \"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
