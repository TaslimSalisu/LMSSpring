<%@ include file="include.html" %>

<%@page import="com.gcit.lms.domain.Branch"%>
<%@page import="com.gcit.lms.domain.BookLoan"%>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%
	AdministrativeService service = (AdministrativeService) request.getAttribute("service");
	List<BookLoan> bookLoans = new ArrayList<BookLoan>();
	bookLoans = service.viewBookLoans();
	
%>
<title>Book Loan</title>

<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>

<h3 class=".text-success" style="color:green" >${message}</h3>
<div class="page-header"> <h3>List of Book Loans</h3></div>
<table class="table-striped table table-bordered table-hover">
	<tr class="success">
		<th>Book ID</th>
		<th>Borrower Name</th>
		<th>Borrower Address</th>
		<th>Borrower Phone Number</th>
		<th>Borrower Books Borrowed</th>
		<th>Override  Due Date</th>
	</tr>
	<%
		for (BookLoan b : bookLoans) {
			Integer bookId = b.getBookId();
			Book book = service.readOne(bookId);
			Branch branch = service.getBranch(b.getBranchId());
			Borrower borrower = service.getBorrower(b.getCardNo());
			
	%>
	<tr>
		<td align="center"><%= book.getTitle() %></td>
		<td align="center"><%= branch.getName() %></td>
		<td align="center"><%= borrower.getName() %></td>
		<td align="center"><%= b.getDateOut().toString() %></td>
		<td align="center"><%= b.getDueDate().toString() %></td>
		<td align="center"><a class="btn btn-primary" href="overrideDueDate?bookId=<%= b.getBookId() %>&branchId=<%= b.getBranchId() %>&cardNo=<%= b.getCardNo() %> " >Override Due Date</a></td>
<!-- 		Fix the href in the buttons in button to go to the appropriate places-->
<%-- 		<td align="center"><a href="editAuthorOne?authorId=<%=  %>" >Override Due Date</a></td> --%>
					<%-- <td align="center"><button type="button"
				onclick="javascript:location.href='editAuthor?authorId=<%=a.getAuthorId()%>'">EDIT</button></td>
		<td align="center"><button type="button"
				onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">DELETE</button></td> --%>
	</tr>
	<%
		}
	%>

</table>
<br>

<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>
</div>


</html>