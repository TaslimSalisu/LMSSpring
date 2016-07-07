<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ include file="include.html" %>

<%@page import="com.gcit.lms.domain.Branch"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Genre"%>
<%
	LibrarianService service = (LibrarianService) request.getAttribute("service");
	List<Branch> branches = service.viewBranches();
%>

<title>Librarian</title>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
	
		<h3>Hello Librarian! Choose the library you manage</h3>
	</div>
	


<form role="form" action="librarian" method="post">


<div class="form-group">

	<label for="selectedBranches">	Select Branch You Manage </label><select  id="select" class="form-control" id="selectedBranches"  name="selectedBranches" required>
	<option disabled="disabled" value="" selected="selected"><-- Select The Branch You Manage --></option>
			<%
				for (Branch b : branches) {
			%>
			<option value="<%= b.getId() %>"><%=b.getName()%></option>
			<%
				}
			%>
		
		</select> <br>
		
		
</div>
	
	<button type="submit" class="btn btn-primary" >Select Branch</button>
	</form>

	<br>
</div>
