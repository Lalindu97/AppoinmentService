$(document).ready(function() 
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// save=============================================================================
$(document).on("click", "#btnSave", function(event) 
		{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateAppointmentForm();
	if (status != true) 
{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	//If valid
	var type = ($("#hidAIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
	{
		url : "AppointmentAPI",
		type : type,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status) 
		{
			onSaveComplete(response.responseText, status);
		}
	});
		});

function onSaveComplete(response, status) 
{
	if (status == "success")  
	{  
		var resultSet = JSON.parse(response); 

if (resultSet.status.trim() == "success")   
{   
	$("#alertSuccess").text("Successfully saved.");  
	$("#alertSuccess").show(); 

 $("#divGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error")   
 {    
	 $("#alertError").text(resultSet.data);   
	 $("#alertError").show();  
	 } 

} else if (status == "error")  
{  
	$("#alertError").text("Error while saving.");  
	$("#alertError").show(); 
	} else 
	{   
		$("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show();  
 } 

$("#hidAIDSave").val(""); 
$("#formAppointment")[0].reset(); 
}

//update=================
$(document).on("click", ".btnUpdate", function(event) {  
    $("#hidAIDSave").val($(this).closest("tr").find('#hidAIDUpdate').val());  
    $("#AName").val($(this).closest("tr").find('td:eq(0)').text());    
    $("#AContact").val($(this).closest("tr").find('td:eq(1)').text());   
    $("#ADate").val($(this).closest("tr").find('td:eq(2)').text());    
    $("#ADocName").val($(this).closest("tr").find('td:eq(3)').text()); 
    $("#Alocation").val($(this).closest("tr").find('td:eq(4)').text()); 
    $("#Atime").val($(this).closest("tr").find('td:eq(5)').text()); 
}); 


//remove
$(document).on("click", ".btnRemove", function(event)
		{  
	$.ajax( 
			{   
				url : "AppointmentAPI", 
				type : "DELETE",  
				data : "AID=" + $(this).data("aid"),
				dataType : "text", 
				complete : function(response, status)   
				{    
					onDeleteComplete(response.responseText, status);  
					}
			}); 
	}); 

function onDeleteComplete(response, status)
{ 
	if (status == "success") 
	{  
		var resultSet = JSON.parse(response); 

if (resultSet.status.trim() == "success") 
{   
	$("#alertSuccess").text("Successfully deleted.");  
	$("#alertSuccess").show(); 

 $("#divGrid").html(resultSet.data);   
 } else if (resultSet.status.trim() == "error")  
 {   
	 $("#alertError").text(resultSet.data);  
	 $("#alertError").show();   
	 } 

} else if (status == "error") 
{ 
	$("#alertError").text("Error while deleting."); 
	$("#alertError").show();  
	} else 
	{   
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
		} 
	} 



//////////////////MODEL========================================================================= 
	function validateAppointmentForm() {  
		// CODE  
		if ($("#AName").val().trim() == "") 
		{  
			return "Insert Appointment Name."; 
			} 
	
	 
	
	if ($("#AContact").val().trim() == "")  
	{  
		return "Insert Appointment Contact."; 
	} 
	var tmpContact = $("#AContact").val().trim(); 
	if (!$.isNumeric(tmpContact)) 
	{  
		return "Insert a numerical value for Contact.";  
		} 
	 
	 // convert to decimal 
	$("#AContact").val(parseFloat(tmpContact).toFixed(1)); 
	 

	if ($("#ADate").val().trim() == "") 
	{  
		return "Insert Appointment Date.";  
		} 
	 
	
	if ($("#ADocName").val().trim() == "")  
	{   
		return "Insert Appointment Doctor Name."; 
		} 
	
	if ($("#Alocation").val().trim() == "")  
	{   
		return "Insert Appointment location."; 
		} 
	
	if ($("#Atime").val().trim() == "")  
	{   
		return "Insert Appointment Time ."; 
		} 
	 
	 return true;
	 } 
	 
	 
	






























