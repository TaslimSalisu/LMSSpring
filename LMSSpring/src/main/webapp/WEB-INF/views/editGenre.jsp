<%@page import="com.gcit.lms.domain.Genre"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>

<%
Genre genre = (Genre) request.getAttribute("genre");
%>
<title>Edit Genre</title>

	<div class="page-header">
		<h3>Hello Admin! Enter Genre Details</h3>
	</div>
	

<form role="form" action="editGenreTwo?genreId= <%= genre.getGenre_id() %>" method="post">

<div class="form-group">


	<label for="genreName">	Enter Genre Name:</label>  <input class="form-control"  id="genreName" type="text" name="genreName" value="<%= genre.getGenre_name() %>" required><br />
		
		</div>
		<button type="submit"  class="btn btn-primary" name="form">Edit Genre</button>

	</form>
	<br>

<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>
