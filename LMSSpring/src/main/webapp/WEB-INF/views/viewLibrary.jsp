<%@ include file="include.html" %>

<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.domain.Branch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%
	Branch branch = (Branch) request.getAttribute("branch");
	
	LibrarianService service = (LibrarianService) request.getAttribute("service");
	if(branch == null) {
		branch = new Branch();
		branch.setId(Integer.parseInt(request.getParameter("branchId")));
		branch = service.getBranch(branch);
	}
	List<Book> books = service.viewBooksCopiesPerBranch(branch);
	
	
%>
<title>View Library</title>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
	<h3 class=".text-success" style="color:green" >${message}</h3>
	<br>
		<h3>Your Branch Details</h3>
	</div>
	<table class="table-striped table table-bordered table-hover">
		<tr class="success">
			<th>Branch Name</th>
			<th>Branch Address</th>
			<th>Edit Branch</th>

		</tr>
		<tr>
			<td><%= branch.getName() %></td>
			<td><%= branch.getAddress() %></td>
			<td align="center"><a type="button" class="btn btn-primary" href="editBranch?branchId=<%= branch.getId()%>&branchName=<%= branch.getName() %>&branchAddress=<%= branch.getAddress() %>">EDIT</a></td>
		</tr>



	</table>

	<div class="page-header">
		<h3>Books in your branch</h3>
	</div>

	<table class="table-striped table table-bordered table-hover">
		<tr class="success">
			<th>Book Name</th>
			<th>Number Of Copies</th>
			<th>Add Copies</th>

		</tr>

		<%
			for(Book b : books) {
				
			
		%>
		<tr>
			<td><%= b.getTitle() %></td>
			<td><%= b.getCopyPerBranch() %></td>
			<td align="center"><a type="button" class="btn btn-primary" href="addCopies?branchId=<%= branch.getId()%>&bookId=<%=b.getBookId()%>&bookTitle=<%=b.getTitle()%>&branchName=<%= branch.getName()%>&copies=<%= b.getCopyPerBranch()%>">ADD COPIES</a></td>
		</tr>

		<%
			}
		%>

	</table>


	<div class="text-center">
		<a class="btn btn-primary" href="librarian.jsp">Go back to
			Librarian page</a>
	</div>
</div>

</html>