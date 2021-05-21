<%-- 
    Document   : register
    Created on : May 21, 2021, 6:02:57 PM
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
			String confirmPasswordError = (String) request.getAttribute("confirmPasswordError");
			if (confirmPasswordError == null){
				confirmPasswordError = "";
			}
			String roleError = (String) request.getAttribute("roleError");
			if (roleError == null){
				roleError = "";
			}
			
		
		%>
		<%@include file="navbar.jsp" %>
		<form action="ServletController?action=register" method="POST">
			Full Name <input name="fullName" type="text" />
			<p><%= nameError %></p>
			<br/>
			Password <input name="password" type="password"/>
			<p><%= passwordError %></p>
			<br/>
			Confirm Password <input name="confirmPassword" type="password"/>
			<p><%= confirmPasswordError %></p>
			<br/>
			Role
			<p><%= roleError %></p>
			<br/>
			User	 <input name="role" type="radio" value="0" checked="checked"/>
			Manager	 <input name="role" type="radio" value="1"/>
			Staff	 <input name="role" type="radio" value="2"/>
			<br/>
			<input type="submit"/>
		</form>
	</body>
</html>
