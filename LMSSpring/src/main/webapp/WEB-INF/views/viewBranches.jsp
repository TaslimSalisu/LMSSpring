<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@ include file="include.html"%>

<%@page import="com.gcit.lms.domain.Branch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%
	AdministrativeService service = (AdministrativeService) request.getAttribute("service");
	List<Branch> branches = new ArrayList<>();
	branches = service.viewBranches();
	List<Book> books;
%>
<title>View Branches</title>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
		<h3>List of Branches</h3>
	</div>
	<table class="table-striped table table-bordered table-hover">
		<tr class="success">
			<th>Branch Name</th>
			<th>Branch Address</th>
			<th>List of books in branch</th>
			<th>Edit Branch</th>
			<th>Delete Branch</th>
		</tr>
		<%
			for (Branch b : branches) {
		%>
		<tr>
			<%
			b.setBooks(service.getBooksByBranch(b));
				books = b.getBooks();
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

			<td align="center"><%=b.getName()%></td>
			<td align="center"><%=b.getAddress()%></td>
			<td align="center"><%=bookString%></td>
			<td align="center"><a type="button" class="btn btn-sm btn-primary"
					onclick="javascript:location.href='editAuthor?authorId=<%=b.getId()%>'">EDIT</a></td>
			<td align="center"><a type="button" class="btn btn-sm btn-danger"
					onclick="javascript:location.href='deleteAuthor?authorId=<%=b.getId()%>'">DELETE</a></td>
		</tr>
		<%
			}
		%>



	</table>


	<div class="text-center">
		<a class="btn btn-primary" href="admin">Go back to Admin page</a>
	</div>
</div>

</html>