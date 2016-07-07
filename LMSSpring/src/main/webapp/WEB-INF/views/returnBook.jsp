<%@ include file="include.html" %>

<%@page import="com.gcit.lms.domain.Branch"%>
<%@page import="com.gcit.lms.domain.BookLoan"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%@ page import="com.gcit.lms.domain.Book"%>
<%
	BorrowerService service = (BorrowerService) request.getAttribute("service");
	Integer borrowerCardNo = Integer.parseInt(request.getAttribute("cardNo").toString());
	Borrower borrower = service.getBorrower(borrowerCardNo);
	List<BookLoan> bookLoans = service.getBooksBorrowedByBorrower(borrower);
	Book b = null;
	Branch branch = null;
	
%>

<title>Return Books</title>

<div class="container">
<div class="jumbotron">
<h2>Welcome to GCIT Library Management System</h2>
</div>
<div class="page-header"> <h3>List of Authors</h3></div>
<table class="table-striped table table-bordered table-hover">
	<tr class="success">
		<th>Book Name</th>
		<th>Branch Borrowed From </th>
		<th>Date Due</th>
		<th>Return Book</th>
	</tr>
	<%
		for (BookLoan bk : bookLoans) {
			b = service.getBook(bk.getBookId());
			branch = service.getBranch(bk.getBranchId());
			

	%>
	<tr>
	<td align="center"><%=b.getTitle()%></td>
		<td align="center"><%=branch.getName() %></td>
		<td align="center"> <%= bk.getDueDate() %> </td>
		<td align="center"><a class="btn btn-primary" href="returnBookTwo?bookId=<%= bk.getBookId() %>&cardNo=<%= bk.getCardNo() %>&branchId=<%= bk.getBranchId() %>"> Return Book</a></td>
					<%-- <td align="center"><button type="button"
				onclick="javascript:location.href='editAuthor?authorId=<%=a.getAuthorId()%>'">EDIT</button></td>
		<td align="center"><button type="button"
				onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">DELETE</button></td> --%>
	</tr>
	<%
		}
	%>

</table>
<br>

<div class="text-center"> 
<a class="btn btn-primary" href="admin">Go back to Admin page</a>
</div>
</div>
