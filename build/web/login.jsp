<%-- 
    Document   : login
    Created on : May 21, 2021, 4:50:28 PM
    Author     : heaty566
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>

		<%
			String nameError = (String) request.getAttribute("fullNameError");
			if (nameError == null){
				nameError = "";
			}
			
			String passwordError = (String) request.getAttribute("passwordError");
			if (passwordError == null){
				passwordError = "";
			}
			String errorMessage = (String) request.getAttribute("errorMessage");
			if (errorMessage == null){
				errorMessage = "";
			}
			
		%>
		<%@include file="navbar.jsp" %>
		<form action="ServletController?action=login" method="POST">
			<p><%= errorMessage %></p>
		
			Full Name <input name="fullName" type="text" />
			<p><%= nameError %></p>
			<br/>
			Password <input name="password" type="password"/>
			<p><%= passwordError %></p>
			<br/>
			<input type="submit"/>
		</form>
	</body>

</html>
