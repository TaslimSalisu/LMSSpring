<%@ include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Genre"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%
	AdministrativeService service = (AdministrativeService) request.getAttribute("service");
	List<Book> books = new ArrayList<Book>();
	books = service.viewBooks();
	List<Author> authors;
	List<Genre> genres;
	String publisher = "";
%>
<title>View Books</title>

<script>

function onBtnClick(id) {
	var answer = confirm("Are you sure you want to delete book?");
	if(answer == true) {
		location.href="deleteBook?bookId=".concat(id);
	}
	
}


</script>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
		<h3>List of Books</h3>
	</div>
	<table class="table-striped table table-bordered table-hover">
		<tr class="success">
			<th>Book Title</th>
			<th>Book Author(s)</th>
			<th>Book Genre(s)</th>
			<th>Book Publisher</th>
			<th>Edit Book</th>
			<th>Delete Book</th>
		</tr>
		<%
			for (Book b : books) {
		%>
		<tr>
			<%
				b.setAuthors(service.getAuthorsOfBook(b));
					b.setGenres(service.getGenreOfBook(b));
					authors = b.getAuthors();
					genres = b.getGenres();

					if (b.getPublisher() != null) {
						publisher = b.getPublisher().getPublisherName();
					}

					String authorString = "";

					if (authors != null) {

						for (Author a : authors) {
							authorString += a.getAuthorName() + " , ";
						}
						if (authorString.length() > 1) {
							authorString = authorString.substring(0, authorString.length() - 2);
						}

					}

					String genreString = "";

					if (genres != null) {
						for (Genre g : genres) {
							genreString += g.getGenre_name() + " , ";
						}
						if (genreString.length() > 1) {
							genreString = genreString.substring(0, genreString.length() - 2);
						}
					}
			%>
			<td align="center"><%=b.getTitle()%></td>
			<td align="center"><%=authorString%></td>
			<td align="center"><%=genreString%></td>
			<td align="center"><%=publisher%></td>
			<%
				
			%>
			<td align="center"><a
				href="editBookOne?bookId=<%=b.getBookId()%>"
				class="btn btn-sm btn-primary" data-toggle="modal"
				data-target="#myModal1">EDIT</a></td>
			<td align="center"><a class="btn btn-sm btn-danger"
				onclick="onBtnClick(<%=b.getBookId()%>)">DELETE</a></td>
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
		<a class="btn btn-primary" href="admin.jsp">Go back to Admin page</a>
	</div>
</div>
