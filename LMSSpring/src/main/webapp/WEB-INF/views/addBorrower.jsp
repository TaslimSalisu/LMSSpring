<%@ include file="include.html" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>Add Borrower</title>
<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>Hello Admin! Enter Borrower Details</h3></div>

<form role="form" action="addBorrower" method="post">

<div class="form-group">

<label for="cardNo"> Enter Borrower Card Number: </label>  <input type="text" id="cardNo" class="form-control" name="cardNo" required /><br />
<label for="borrowerName"> Enter Borrower Name: </label>  <input type="text" id="borrowerName" class="form-control" name="borrowerName" required /><br />
<label for="borrowerAddress">Enter Borrower Address:</label>  <input type="text" class="form-control"  id="borrowerAddress" name="borrowerAddress" required /><br />
<label for="borrowerPhoneNo">Enter Borrower Phone Number:</label> <input type="text"  class="form-control" id="borrowerPhoneNo" name="borrowerPhoneNo" required><br />

</div>
<button class="btn btn-primary" type="submit" >Add Borrower</button> 


</form>

<br>

<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>

</div>
