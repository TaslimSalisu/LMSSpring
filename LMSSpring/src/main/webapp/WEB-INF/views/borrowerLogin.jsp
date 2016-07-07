<%@ include file="include.html" %>

<title>Borrower Login</title>
<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>Hello Borrower! Enter Your Card Number.</h3></div>

<form role="form" action="borrowerLogin" method="post" >
<h3 class=".text-success" style="color:red" >${message}</h3>
<div class="form-group">
<label for="cardNo"> Enter Your Card Number:</label> <input type="text" id="cardNo" name="cardNo" class="form-control" required />

</div>

<button type="submit" class="btn btn-primary" >Login</button>

</form>
<br>


<div class="text-center"> 
<a class="btn btn-primary" href="borrower">Go back to Borrower page</a>
</div>

</div>
</html>