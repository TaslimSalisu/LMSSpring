<%@ include file="include.html" %>

<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.Branch"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.LibrarianService"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Genre"%>
<%
	LibrarianService service = (LibrarianService) request.getAttribute("service");
	List<Branch> branches = service.viewBranches();
	
%>
<title>Book Check Out</title>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
	
		<h3>Hello <%= request.getParameter("name") %> ! Choose branch you want to check out from.</h3>
	</div>
	


<form role="form" action="branchSelect?cardNo=<%=request.getParameter("cardNo")  %>" method="post">


<div class="form-group">

	<label for="selectedBranch">	Select Branch You Want to check out from </label>
	<select  class="form-control" id="selectedBranch"  name="selectedBranch" required>
	<option disabled="disabled" value="" selected="selected"><-- Select Branch You Want to check out from  --></option>
			<%
				for (Branch b : branches) {
			%>
			<option value=<%= b.getId() %>><%=b.getName()%></option>
			<%
				}
			%>
		
		</select> <br>
		
		
</div>


	
	<button type="submit" class="btn btn-primary" >Select Branch</button>
	</form>

	<br>
	
</div>
</body>
</html>