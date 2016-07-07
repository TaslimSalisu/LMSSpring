<%@ include file="include.html"%>

<%@page import="com.gcit.lms.domain.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Genre"%>
<%
	AdministrativeService service = (AdministrativeService) request.getAttribute("service");
	List<Genre> genres = new ArrayList<Genre>();
	genres = service.viewGenres();
	List<Book> books;
%>

<script>
	function onBtnClick(id) {
		var answer = confirm("Are you sure you want to delete genre?");
		if (answer == true) {
			location.href = "deleteGenre?genreId=".concat(id);
		}

	}
</script>
<title>View Genres</title>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
		<h3>List of Genres</h3>
	</div>
	<table class="table-striped table table-bordered table-hover">
		<tr class="success">
			<th>Genre Name</th>
			<th>Books in Genre</th>
			<th>Edit Genre</th>
			<th>Delete Genre</th>

		</tr>
		<%
			for (Genre g : genres) {
		%>
		<tr>
			<td align="center"><%=g.getGenre_name()%></td>
			<%
			g.setBooks(service.getBooksByGenre(g));
				books = g.getBooks();
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

			<td align="center"><%=bookString%></td>

			<td align="center"><a type="button" class="btn btn-sm btn-primary"  href="editGenre?genreId=<%=g.getGenre_id()%>"  data-toggle="modal" data-target="#myModal1">EDIT</a></td>
			<td align="center"><a type="button" class="btn btn-sm btn-danger" onclick="onBtnClick(<%= g.getGenre_id() %>)">DELETE</a></td>
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
