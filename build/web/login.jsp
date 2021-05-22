<%-- 
    Document   : login
    Created on : May 21, 2021, 4:50:28 PM
    Author     : heaty566
--%>

<%@page import="Utils.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>

		<%
			String nameError = (String) Helper.getClientParams(request, "fullNameError","");
			String passwordError = (String) Helper.getClientParams(request, "confirmPasswordError","");
			String errorMessage = (String) Helper.getClientParams(request, "errorMessage","");
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
