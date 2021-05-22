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
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
	</head>
	<body>
		<%
			String nameError = (String) Helper.getClientParams(request, "fullNameError","");
			String passwordError = (String) Helper.getClientParams(request, "passwordError","");
			String errorMessage = (String) Helper.getClientParams(request, "errorMessage","");
		%>
		<%@include file="navbar.jsp" %>
		<div class="row m-2">
			<div class="col-12 col-md-4">
				<form action="ServletController?action=login" method="POST">
					<p class="text-danger text-capitalize"><%= errorMessage %></p>
					<div>
						<label for="fullName" class="form-label">Full Name</label>
						<input class="form-control" id="fullName" name="fullName" type="text"   />
						<p class="text-danger"><%= nameError %></p>
					</div>
					<div>
						<label for="password" class="form-label">Password</label>
						<input class="form-control" id="password" name="password" type="password"   />
						<p class="text-danger"><%= passwordError %></p>
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>	
			</div>
		</div>
	</body>

</html>
