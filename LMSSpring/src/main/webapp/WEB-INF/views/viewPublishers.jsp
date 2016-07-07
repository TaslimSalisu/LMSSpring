<%@ include file="include.html"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%
	AdministrativeService service = (AdministrativeService) request.getAttribute("service");
	List<Publisher> publishers = new ArrayList<Publisher>();
	publishers = service.viewPublishers();
	List<Book> books;
%>

<script>
	function onBtnClick(id) {
		var answer = confirm("Are you sure you want to delete publisher?");
		if (answer == true) {
			location.href = "deletePublisher?publisherId=".concat(id);
		}

	}
</script>
<title>View Publishers</title>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
		<h3>List of Publishers</h3>
	</div>
	<table class="table-striped table table-bordered table-hover">
		<tr class="success">
			<th>Publisher Name</th>
			<th>Publisher Address</th>
			<th>Publisher Phone</th>
			<th>Publisher Books</th>
			<th>Edit Publisher</th>
			<th>Delete Publisher</th>
		</tr>
		<%
			for (Publisher p : publishers) {
		%>
		<tr>
			<%
			p.setBooks(service.getBooksByPublisher(p));
				books = p.getBooks();
					String bookString = "";
					if (books != null) {
						for (Book b : books) {
							bookString += b.getTitle() + " , ";
						}
						if (bookString.length() > 1) {
							bookString = bookString.substring(0, bookString.length() - 2);
						}

					}
			%>

			<td align="center"><%=p.getPublisherName()%></td>
			<td align="center"><%=p.getPublisherAddress()%></td>
			<td align="center"><%=p.getPublisherPhone()%></td>
			<td align="center"><%=bookString%></td>
			<td align="center"><a type="button"
				href="editPublisher?publisherId=<%=p.getPublisherId()%>"
				class="btn btn-sm btn-primary" data-toggle="modal"
				data-target="#myModal1">EDIT</a></td>
			<td align="center"><a type="button"	class="btn btn-sm btn-danger" onclick="onBtnClick(<%=p.getPublisherId()%>)">DELETE</a></td>
		</tr>
		<%
			}
		%>



	</table>


	<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"></div>
		</div>
	</div>

	<div class="text-center">
		<a class="btn btn-primary" href="admin">Go back to Admin page</a>
	</div>
</div>