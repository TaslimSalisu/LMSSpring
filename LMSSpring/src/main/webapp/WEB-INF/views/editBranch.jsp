<%@ include file="include.html" %>

<%@page import="com.gcit.lms.domain.Branch"%>
<%

String branchId = request.getParameter("branchId");
String branchName = request.getParameter("branchName");
String branchAddress = request.getParameter("branchAddress");


%>

<title>Edit Branch </title>
<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>Hello Librarian! Edit Branch Details</h3></div>

<form role="form" action="editLibrary?branchId=<%=branchId %>" method="post" >

<div class="form-group">
<label for="branchName"> Edit Branch Name:</label> <input type="text" id="branchName" name="branchName" class="form-control" value="<%= branchName %>" required />
<br>
<label for="branchAddress"> Edit Branch Address:</label> <input type="text" id="branchAddress" name="branchAddress" class="form-control" value="<%= branchAddress %>" required />
</div>

<button type="submit" class="btn btn-primary" >Edit Branch</button>

</form>
<br>


<div class="text-center"> 
<a class="btn btn-primary" href="viewLibrary?branchId=<%=branchId %>">Cancel and Go back to your branch page</a>
</div>

</div>
</html>