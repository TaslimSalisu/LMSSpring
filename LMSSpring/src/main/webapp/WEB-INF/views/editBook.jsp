<%@page import="com.gcit.lms.domain.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Genre"%>

<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    });
		});
</script>

<%
	AdministrativeService service = (AdministrativeService) request.getAttribute("service");
	List<Author> authors = new ArrayList<>();
	List<Publisher> publishers = new ArrayList<>();
	List<Genre> genres = new ArrayList<>();

	authors = service.viewAllAuthors();
	publishers = service.viewPublishers();
	genres = service.viewGenres();

	Book book = (Book) request.getAttribute("book");
	book.setAuthors(service.getAuthorsOfBook(book));
	book.setGenres(service.getGenreOfBook(book));
	List<Author> bAuthors = book.getAuthors();
	Publisher bPublisher = book.getPublisher();
	List<Genre> bGenres = book.getGenres();
	
%>
<title>Edit Book</title>



	
	<div class="page-header">
		<h3>Hello Admin! Edit Book Details</h3>
	</div>

	<form role="form" action="editBookTwo?bookId=<%=book.getBookId()%>"
		method="post">


		<div class="form-group">

			<label for="bookTitle"> Edit Book Title: </label><input type="text"
				class="form-control" id="bookTitle" name="bookTitle" style="width: 600px"  value="<%= book.getTitle() %>" required>
			<br> <label for="selectedAuthors" > Edit Book Author </label><select
				multiple class="form-control" id="selectedAuthors" multiple
				name="selectedAuthors" style="width: 600px" required>
				<%
				for (Author a : authors) { 
						if (bAuthors.contains(a)) { 
 				%> 
				<option  value="<%=a.getAuthorId()%>" selected> <%=a.getAuthorName()%>  </option>
				<%
					} else { 
				%> 
				<option value="<%=a.getAuthorId()%>" ><%=a.getAuthorName()%></option>
				<%
					}
 					} 
 				%> 
			</select> <br> <label for="selectedGenres">Edit Book Genre </label><select
				multiple class="form-control" id="selectedGenres" multiple
				name="selectedGenres" style="width: 600px" required>
				<%
			for (Genre g : genres) { 
 						if(bGenres.contains(g)) { 
 				%> 
							<option  value="<%=g.getGenre_id()%>" selected><%=g.getGenre_name()%></option>
				<%			
					} else { 
				%> 
				<option value=<%=g.getGenre_id()%>><%=g.getGenre_name()%></option>
				<%
					} 
 					} 
				%> 
			</select> <br> <label for="selectedPublisher"> Edit Book
				Publisher </label> <select class="form-control" id="selectedPublisher"
				name="selectedPublisher" style="width: 600px" >
				<option value="noPub">-- select an option --</option>
				<%
					for (Publisher p : publishers) {		
 						if(bPublisher != null && bPublisher.equals(p)) { 
 				%>
							<option  value="<%=p.getPublisherId()%>" selected><%=p.getPublisherName()%></option>
				<%	
				} else { 
 				%> 
				<option value=<%=p.getPublisherId()%>  ><%=p.getPublisherName()%></option>
				<%
					} 
						
				} 
				%> 
			</select>

		</div>

		<button type="submit" class="btn btn-primary" name="form"
			value="addBook">Edit Book</button>
	</form>

	<br>

	<div class="text-center">
		<a class="btn btn-primary" href="admin">Go back to Admin page</a>
	</div>
