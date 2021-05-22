<%-- 
    Document   : register
    Created on : May 21, 2021, 6:02:57 PM
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
			String passwordError = (String) Helper.getClientParams(request, "confirmPasswordError","");
			String confirmPasswordError = (String) Helper.getClientParams(request, "fullNameError","");
			String roleError = (String) Helper.getClientParams(request, "roleError","");
		%>
		<%@include file="navbar.jsp" %>
		<div class="row m-2">
			<div class="col-12 col-md-4">
				<form action="ServletController?action=register" method="POST">
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
					<div>
						<label for="confirmPassword" class="form-label">Confirm Password</label>
						<input class="form-control" id="confirmPassword" name="confirmPassword" type="password"   />
						<p class="text-danger"><%= confirmPasswordError %></p>
					</div>
					Role
					<div class="form-check ">
						<input class="form-check-input" type="radio" name="role" id="role1" value="0" checked="checked"/>
						<label class="form-check-label" for="role1">User</label>
					</div>
					<div class="form-check ">
						<input class="form-check-input" type="radio" name="role" id="role2" value="1""/>
						<label class="form-check-label" for="role2">Manager</label>
					</div>
					<div class="form-check ">
						<input class="form-check-input" type="radio" name="role" id="role2" value="2" />
						<label class="form-check-label" for="role2">Staff</label>
					</div>
					<p class="text-danger"><%= roleError %></p>

					<button type="submit" class="btn btn-primary">Register</button>
				</form>
			</div>
		</div>
	</body>
</html>
