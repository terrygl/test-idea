<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>    	
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Customers</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/site.css"/>	
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script>
	$(function() {
				function deleteData() {
						$.ajax({
									method: "POST",
									dataType : "json",
									url : "deleteCustomerAccountData",
									success: function(result) {		
										if (result.status == "OK") {
											alert("Accounts and Customers deleted");											
										} else {
											alert("An error occurred while deleting accounts and customers");											
										}
									}
							});
			    }				
				function createData() {
						$.ajax({
									method: "POST",
									dataType : "json",
									url : "createCustomerAccountData",
									success: function(result) {		
										if (result.status == "OK") {
											alert("Accounts and Customers created");											
										} else {
											alert("An error occurred while creating accounts and customers");											
										}
									}
							});
			    }				
			    $( "#delete-cust-account-data" ).button().on( "click", function() {
			    	event.preventDefault();
			    	deleteData();
				});
			    $( "#create-cust-account-data" ).button().on( "click", function() {
			    	event.preventDefault();
			    	createData();
				});
			    
			    
	});								
</script>
</head>
<body>
<header>Admin</header>
<nav><a href="index.jsp">Home</a></nav>
<p id="description">This page can create or delete test customers and accounts.</p>	
<hr/>	
<div class="table-contain">
<button class="ui-button" id="delete-cust-account-data">Delete All Accounts and Customers</button>
<br/>
<button class="ui-button" id="create-cust-account-data">Create Customers and Accounts</button>
</div>
<footer>Page Generated: <%= new Date() %></footer>
</body>
</html>