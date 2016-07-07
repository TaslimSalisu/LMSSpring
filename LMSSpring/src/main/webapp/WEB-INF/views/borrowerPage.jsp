<%@ include file="include.html" %>

<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.domain.Borrower"%>
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
Borrower borrower = (Borrower) request.getAttribute("borrower");

AdministrativeService service = (AdministrativeService) request.getAttribute("service");

borrower.setBooksBorrowed(service.getBooksByBorrower(borrower));
	List<Book> books = borrower.getBooksBorrowed();
	String bookString = "";

	if (books != null) {
		for (Book b : books) {
			bookString += b.getTitle() + " , ";
		}
	}
		if (bookString.length() > 1) {
			bookString = bookString.substring(0, bookString.length() - 2);
		}
%>
<title>Borrower Page</title>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
		<h3 class=".text-success" style="color: green">${message}</h3>
		<br>
		<h3>Your Details</h3>
	</div>
	<table class="table-striped table table-bordered table-hover">
		<tr class="success">
			<th>Borrower Name</th>
			<th>Borrower Address</th>
			<th>Borrower Phone</th>
			<th>Books Borrowed</th>

		</tr>
		<tr>
			<td><%= borrower.getName() %></td>
			<td><%= borrower.getAddress() %></td>
			<td><%= borrower.getPhoneNumber() %></td>
			<td><%= bookString %></td>
			<!-- 			<td align="center"><button type="button" class="btn btn-primary" -->
			<%-- 					onclick="javascript:location.href='editBranch.jsp?branchId=<%= branch.getId()%>&branchName=<%= branch.getName() %>&branchAddress=<%= branch.getAddress() %>'">EDIT</button></td> --%>
		</tr>



	</table>
	
		<div class="container">
	<div class="row">
	<div><a href="bookCheckOut?name=<%= borrower.getName() %>&cardNo=<%= borrower.getCardNumber()  %>" class="btn btn-primary col-lg-6"  > Check Out A Book</a> </div>
	<div><a href="returnBook?cardNo=<%= borrower.getCardNumber() %>" class="btn btn-primary col-lg-6" > Return A Book</a> </div>
	</div>
	
	</div>
	
	<br><br>
	<div class="text-center">
		<a class="btn btn-primary" href="borrowerLogin">Go back to
			Borrower page</a>
	</div>
</div>

</html>