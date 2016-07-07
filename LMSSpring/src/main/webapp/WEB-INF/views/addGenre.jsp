<%@ include file="include.html" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>Add Genre</title>
<div class="container">
	<div class="jumbotron">
		<h2>Welcome to GCIT Library Management System</h2>
	</div>
	<div class="page-header">
		<h3>Hello Admin! Enter Genre Details</h3>
	</div>

<form role="form" action="addGenre" method="post">

<div class="form-group">


	<label for="genreName">	Enter Genre Name:</label>  <input class="form-control"  id="genreName" type="text" name="genreName" required><br />
		
		</div>
		<button type="submit"  class="btn btn-primary" name="form" value="addGenre">Add Genre</button>

	</form>
	<br>

<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>

</div>
