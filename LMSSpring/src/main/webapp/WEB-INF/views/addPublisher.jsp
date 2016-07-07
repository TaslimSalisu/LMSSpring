<%@ include file="include.html" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>Add Publisher</title>
<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>Hello Admin! Enter Publisher Details</h3></div>

<form role="form" action="addPublisher" method="post">

<div class="form-group">

<label for="publisherName"> Enter Publisher Name: </label>  <input type="text" id="publisherName" class="form-control" name="publisherName" required /><br />
<label for="publisherAddress">Enter Publisher Address:</label>  <input type="text" class="form-control"  id="publisherAddress" name="publisherAddress" required /><br />
<label for="publisherPhoneNo">Enter Publisher Phone Number:</label> <input type="text"  class="form-control" id="publisherPhoneNo" name="publisherPhoneNo" required><br />

</div>
<button class="btn btn-primary" type="submit" >Add Publisher</button> 


</form>

<br>

<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>

</div>