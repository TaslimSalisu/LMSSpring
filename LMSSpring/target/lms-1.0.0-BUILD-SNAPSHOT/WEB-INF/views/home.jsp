<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<% String time = (String) request.getAttribute("serverTime"); %>

<P>  The time on the server is ${serverTime}. </P>
<P>  The time on the server is <%= time %>. </P>
</body>
</html>
