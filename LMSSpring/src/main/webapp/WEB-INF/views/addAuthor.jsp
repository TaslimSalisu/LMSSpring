<%@ include file="include.html" %>

<title>Add Author</title>

<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>Hello Admin! Enter Author Details</h3></div>

<form role="form" action="addAuthor" method="post" >

<div class="form-group">
<label for="authorName"> Enter Author Name:</label> <input type="text" id="authorName" name="authorName" class="form-control" required />

</div>

<button type="submit" class="btn btn-primary" >Add Author</button>

</form>
<br>


<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>
</div>
