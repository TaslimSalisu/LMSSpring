<%@ include file="include.html" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%
	AdministrativeService service = (AdministrativeService) request.getAttribute("service");
	List<Author> authors = new ArrayList<Author>();
	List<Book> books;
	
	
	
	Integer count = service.getAuthorsCount();
	Integer pageCount = 0;
	if(count != null && count > 0){
		int rem = count % 10;
		if(rem == 0){
			pageCount = count/3;
		}else{
			pageCount = count/3+1;
		}
	}
	
	if(request.getAttribute("authors") != null){
		authors = (List<Author>) request.getAttribute("authors");	
	}else{
		authors = service.viewAuthors(1);	
	}
	
%>
<title>View Authors</title>

<script>

function onBtnClick(id) {
	var answer = confirm("Are you sure you want to delete author?");
	if(answer == true) {
		location.href="deleteAuthor?authorId=".concat(id);
	}
	
}

function pageAuthors(val){
	$.ajax({url: "pageAuthors",data: { pageNo: val},
		})
		  .done(function( data ) {
		    $('#authorsTable').html(data);
		  });
}

function searchAuthors(val){
 	$.ajax({url: "searchAuthors",data: { search: val},
		})
		  .done(function( data ) {
		    $('#authorsTable').html(data);
		  });
}



</script>

<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>List of Authors</h3></div>
<input type="text" placeholder="search authors" class="form-control" onkeyup="searchAuthors(value)" />
<nav>
  <ul class="pagination">
    <%for(int i=1; i<=pageCount;i++){ %>
    <li><a id="pageNo" onclick="pageAuthors(<%=i%>)"><%=i%></a></li>
	<%} %>
  </ul>
</nav>
<table class="table-striped table table-bordered table-hover" id="authorsTable">
	<tr class="success">
		<th>Author Name</th>
		<th>Author Book</th>
		<th>Edit Author</th>
		<th>Delete Author</th>
	</tr>
	<%
		for (Author a : authors) {
			a.setBooks(service.getBooksByAuthor(a));
			books = a.getBooks();
			//books = service.getBooksByAuthor(a);

			String bookString = "";

			if (books != null) {
				for (Book b : books) {
					bookString += b.getTitle() + " , ";
				}
				if (bookString.length() > 1) {
					bookString = bookString.substring(0, bookString.length() - 2);
				}

			}
	%>
	<tr>
		<td align="center"><%=a.getAuthorName()%></td>
		<td align="center"><%=bookString%></td>
		<td align="center"><a href="editAuthorOne?authorId=<%= a.getAuthorId() %>" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1">EDIT</a></td>
		<td align="center"><a class="btn btn-sm btn-danger"  onclick="onBtnClick(<%= a.getAuthorId() %>)">DELETE</a></td>
				
	</tr>
	<%
		}
	%>

</table>
<br>


				
<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>


<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>
</div>

