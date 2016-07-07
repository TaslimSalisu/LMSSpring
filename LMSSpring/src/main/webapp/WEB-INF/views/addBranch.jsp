<%@ include file="include.html" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>Add Branch</title>
<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>Hello Admin! Enter Branch Details</h3></div>

<form role="form" action="addBranch" method="post">

<div class="form-group">

<label for="branchName"> Enter Branch Name: </label>  <input type="text" id="branchName" class="form-control" name="branchName" required /><br />
<label for="branchAddress">Enter Branch Address:</label>  <input type="text" class="form-control"  id="branchAddress" name="branchAddress" required /><br />

</div>
<button class="btn btn-primary" type="submit">Add Branch</button> 


</form>

<br>

<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>


</div>
