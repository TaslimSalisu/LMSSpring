<%@ include file="include.html" %>

<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.domain.Branch"%>

<title>Add Copies</title>
<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>Hello Librarian! Add Copies of <span style="font-weight:bold;color:green"> <%= request.getParameter("bookTitle") %>  </span> to <span style="font-weight:bold;color:green">  <%= request.getParameter("branchName") %></span>  branch. You currently have <span style="font-weight:bold;color:green">  <%= request.getParameter("copies") %> </span>copies. </h3></div>

<form role="form" action="addCopiesTwo?bookId=<%=request.getParameter("bookId")%>&branchId=<%= request.getParameter("branchId")  %>" method="post" >

<div class="form-group">

<%-- value="<%= request.getParameter("copies") %>" --%>
<label for="bookCopies"> Add book copies :</label> <input type="text" id="bookCopies" name="bookCopies" class="form-control"  required />

</div>

<button type="submit" class="btn btn-primary" >Add Copies</button>

</form>´
<br>


<div class="text-center"> 
<a class="btn btn-primary" href="viewLibrary?branchId=<%= request.getParameter("branchId")  %>">Go back to Librarian page</a>
</div>

</div>
