<%@ include file="include.html" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Publisher"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Genre"%>
<%
	AdministrativeService service =  (AdministrativeService) request.getAttribute("service");
	List<Author> authors = new ArrayList<>();
	List<Publisher> publishers = new ArrayList<>();
	List<Genre> genres = new ArrayList<>();

	authors = service.viewAllAuthors();
	publishers = service.viewPublishers();
	genres = service.viewGenres();
%>
<title>Add Book</title>

<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
		<h3>Hello Admin! Enter Book Details</h3>
	</div>

<form role="form" action="addBook" method="post">


<div class="form-group">

	<label for="bookTitle">	Enter Book Title: </label><input type="text" class="form-control" id="bookTitle" name="bookTitle" required> <br>
	
	<label for="selectedAuthors">	Select Book Author </label><select multiple class="form-control" id="selectedAuthors" multiple name="selectedAuthors">
			<%
				for (Author a : authors) {
			%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName()%></option>
			<%
				}
			%>
		</select>  <br>
		<label for="selectedGenres">Select Book Genre </label><select multiple class="form-control"  id="selectedGenres" multiple name="selectedGenres">
			<%
				for (Genre g : genres) {
					
			%>
			<option value=<%=g.getGenre_id()%>><%=g.getGenre_name()%></option>
			<%
				}
			%>
		</select> <br>
		<label for="selectedPublisher"> Select Book Publisher </label>
		<select  class="form-control" id="selectedPublisher" name="selectedPublisher">
		<option  value="" > -- select an option -- </option>
			<%
				for (Publisher p : publishers) {
			%>
			<option value=<%=p.getPublisherId()%>><%=p.getPublisherName()%></option>
			<%
				}
			%>
		</select> 
		
</div>
	
	<button type="submit" class="btn btn-primary" >Add Book</button>
	</form>

	<br>

	<div class="text-center">
		<a class="btn btn-primary" href="admin.jsp">Go back to Admin page</a>
	</div>
</div>
