<%@ include file="include.html" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%
List<Book> books = (List<Book>) request.getAttribute("books");

%>
<title>Book Check Out</title>

<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>List of Authors</h3></div>
<table class="table-striped table table-bordered table-hover">
	<tr class="success">
		<th>Author Name</th>
		<th>Author Book</th>
		<th>Edit Author</th>
	</tr>
	<%
	AdministrativeService service = (AdministrativeService) request.getAttribute("service");
				for (Book b : books) {
					b.setAuthors(service.getAuthorsOfBook(b));
	%>
	<tr>
		<td align="center"><%=b.getTitle()  %></td>
		<td align="center">
		<% 
		if(!b.getAuthors().isEmpty()) {
			
		%>
		<%= b.getAuthors() %>
		<%
		}
		
		else {
			
			%>
			
			No Author.
			
			<%
		}
			%>
		</td>
		<td align="center"><a class="btn btn-primary" href="bookCheckOutTwo?bookId=<%= b.getBookId() %>&cardNo=<%= request.getAttribute("cardNo") %>&branchId=<%= request.getAttribute("branchId") %> " >Check Out Book</a></td>
					
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