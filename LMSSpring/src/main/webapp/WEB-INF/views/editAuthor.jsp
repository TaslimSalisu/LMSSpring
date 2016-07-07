<%@page import="com.gcit.lms.domain.Author"%>
<%

Author author = (Author) request.getAttribute("author");
%>


<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>
<title>Edit Author</title>
<div class="container">

<div class="page-header"> <h3>Hello Admin! Edit Author Details</h3></div>

<form role="form" action="editAuthorTwo?authorId=<%= author.getAuthorId() %>" method="post" >

<div class="form-group">
  <div class="col-xs-2">
<label for="authorName"> Edit Author Name:</label> <input type="text" id="authorName" name="authorName" class="form-control" value="<%= author.getAuthorName() %>" required />
</div>

<br>

 <div class="col-xs-2">
<button type="submit" class="btn btn-primary" >Edit Author</button>
</div>
</div>




</form>
<br>


<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>

</div>
