package com.paf.service;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paf.controller.Appointment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/AppointmentAPI")
public class AppointmentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Appointment AppointmentObj = new Appointment();
	
	public AppointmentAPI() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//no
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String output = AppointmentObj.insertAppointment(
				request.getParameter("AName"), 
				request.getParameter("AContact"),
				request.getParameter("ADate"), 
				request.getParameter("ADocName"),
				request.getParameter("Alocation"),
				request.getParameter("Atime")
				);

		response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);

		String output = AppointmentObj.updateAppointment(
				paras.get("hidAIDSave").toString(),
				paras.get("AName").toString(),
				paras.get("AContact").toString(), 
				paras.get("ADate").toString(), 
				paras.get("ADocName").toString(),
				paras.get("Alocation").toString(),
				paras.get("Atime").toString()
				);

		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);

		String output = AppointmentObj.deleteAppointment(paras.get("AID").toString());

		response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();  
		try  {  
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");  
			String queryString = scanner.hasNext() ?  
								scanner.useDelimiter("\\A").next() : "";  
			scanner.close(); 
	 
	  String[] params = queryString.split("&"); 
	  for (String param : params)  
	  { 
	     String[] p = param.split("=");  
	     map.put(p[0], p[1]);  
	  }  
	  }  
		catch (Exception e) 
		{  
			
		}  
		return map; 
		}

}




























