<%@ include file="include.html" %>

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
	List<Borrower> borrowers = new ArrayList<Borrower>();
	borrowers = service.viewBorrowers();
	List<Book> books;
%>
<title>View Borrowers</title>

<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>List of Authors</h3></div>
<table class="table-striped table table-bordered table-hover">
	<tr class="success">
		<th>Borrower Card Number</th>
		<th>Borrower Name</th>
		<th>Borrower Address</th>
		<th>Borrower Phone Number</th>
		<th>Borrower Books Borrowed</th>
		<th>Edit Borrower</th>
		<th>Delete Borrower</th>
	</tr>
	<%
		for (Borrower b : borrowers) {
			b.setBooksBorrowed(service.getBooksByBorrower(b));
			books = b.getBooksBorrowed();

			String bookString = "";

			if (books != null) {
				for (Book bk : books) {
					bookString += bk.getTitle() + " , ";
				}
				if (bookString.length() > 1) {
					bookString = bookString.substring(0, bookString.length() - 2);
				}

			}
	%>
	<tr>
		<td align="center"><%= b.getCardNumber() %></td>
		<td align="center"><%= b.getName() %></td>
		<td align="center"><%= b.getAddress() %></td>
		<td align="center"><%= b.getPhoneNumber() %></td>
		<td align="center"><%=bookString%></td>
		<td align="center"><a href="editAuthorOne?authorId=<%= b.getCardNumber() %>" >EDIT</a></td>
		<td align="center"><a  href="deleteAuthor?authorId=<%= b.getCardNumber()%>">DELETE</a></td>
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
<a class="btn btn-primary" href="admin.jsp">Go back to Admin page</a>
</div>
</div>


</html>