<%@page import="com.paf.controller.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Appointment.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Appointment Management </h1>

				<form id="formAppointment" name="formAppointment">
				
					Appointment Name: 
					<input id="AName" name="AName" type="text" class="form-control form-control-sm"> <br> 
					Appointment Contact:
					<input id="AContact" name="AContact" type="text" class="form-control form-control-sm"> <br> 
					Appointment Date: 
					<input id="ADate" name="ADate" type="text" class="form-control form-control-sm"> <br> 
					Appointment Doctor Name: 
					<input id="ADocName" name="ADocName" type="text" class="form-control form-control-sm"> <br> 
					Appointment Location: 
					<input id="Alocation" name="Alocation" type="text" class="form-control form-control-sm"> <br> 
					Appointment Time: 
					<input id="Atime" name="Atime" type="text" class="form-control form-control-sm"> <br> 
					
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidAIDSave" name="hidAIDSave" value="">
					
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divGrid">
					<%
						Appointment AppointmentObj = new Appointment();
						out.print(AppointmentObj.readAppointment());
					%>
				</div>


			</div>
		</div>
	</div>
</body>
</html>