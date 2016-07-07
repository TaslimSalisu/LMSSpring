<%@ include file="include.html" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<title>Override Due Date</title>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
		<h3>Hello Admin! Override due date</h3>
	</div>
<h3 class=".text-success" style="color:red" >${message}</h3>
<%-- <form role="form" action="override?bookId=<%= request.getParameter("bookId") %>&branchId=<%= request.getParameter("branchId") %>&cardNo=<%= request.getParameter("cardNo")%>" method="post"> --%>
<form role="form" action="override?bookId=${bookId}&branchId=${branchId}&cardNo=${cardNo}" method="post">
<div class="form-group">

	<label for="number">	Enter Number (more than zero): </label><input type="text" class="form-control" id="number" name="number" required> <br>
	
	
		
		<label for="amount"> Select Amount </label>
		<select  class="form-control" id="amount" name="amount" required>
		<option  value="" disabled selected > -- select an option -- </option>
		<option  value="day" > Days </option>
		<option  value="week" > Weeks </option>
		<option  value="month" > Months </option>
		<option  value="year" > Years </option>
			
		</select> 
		
</div>
	
	<button type="submit" class="btn btn-primary" >Add Book</button>
	</form>

	<br>

	<div class="text-center">
		<a class="btn btn-primary" href="admin">Go back to Admin page</a>
	</div>
</div>
