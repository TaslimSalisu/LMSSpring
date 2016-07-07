<%@ include file="include.html" %>


<script>
document.title = "Admin Page";
</script>

<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>Hello Admin! Choose your action</h3></div>

<h3 class=".text-success" style="color:green" >${message}</h3>
<%-- <h3 class=".text-success" style="color:green" ><%= request.getAttribute("message") %> </h3> This does the same thing as line above. Line above is jQuery?? (JSTL maybe). --%>

<br />
<a href="addAuthor">Add Author</a><br/>
<a href="viewauthors">View Authors</a><br/>
<a href="addPublisher" >Add Publisher</a><br/>
<a href="viewPublishers"> View Publishers</a><br/>
<a href="addGenre">Add Genre</a><br/>
<a href="viewGenres">View Genre</a><br/>
<a href="addBook">Add Book</a><br/>
<a href="viewBooks">View Books</a><br/>
<a href="addBranch">Add Branch</a><br/>
<a href="viewBranches">View Branches</a><br/>
<a href="addBorrower">Add Borrower</a><br/>
<a href="viewBorrowers">View Borrowers</a><br/>
<a href="bookLoan">Override Due Date For Book Loan</a><br/>
</div>
