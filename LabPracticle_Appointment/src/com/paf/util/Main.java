package com.paf.util;

import com.paf.controller.Appointment;

public class Main {

	public static void main(String[] args) {
		//DBConnection c = new DBConnection();
		DBConnection.getConnection();
		Appointment a = new Appointment();
		a.updateAppointment("1","d","5412","4-mar-2019","v","s","1");
		//a.updateAppointment(AID, Aname, Acontact, ADate, ADocName, Alocation, Atime,,)

	}

}

