<%@page import="com.gcit.lms.domain.Publisher"%>
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
Publisher publisher = (Publisher) request.getAttribute("publisher");

%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>Edit Publisher</title>
<div class="container">
<div class="page-header"> <h3>Hello Admin! Enter Publisher Details</h3></div>

<form role="form" action="editPublisherTwo?publisherId=<%= publisher.getPublisherId() %>" method="post">

<div class="form-group">

<label for="publisherName"> Edit Publisher Name: </label>  <input style="width: 600px" type="text" id="publisherName" class="form-control" name="publisherName" value="<%= publisher.getPublisherName() %>" required /><br />
<label for="publisherAddress">Edit Publisher Address:</label>  <input style="width: 600px" type="text" class="form-control"  id="publisherAddress" name="publisherAddress" value="<%= publisher.getPublisherAddress() %>"  required /><br />
<label for="publisherPhoneNo">Edit Publisher Phone Number:</label> <input style="width: 600px" type="text"  class="form-control" id="publisherPhoneNo" name="publisherPhoneNo" value="<%= publisher.getPublisherPhone()%>"  required><br />

</div>
<button class="btn btn-primary" type="submit" >Edit Publisher</button> 


</form>

<br>

<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>

</div>