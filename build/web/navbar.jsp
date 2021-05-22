<%-- 
    Document   : navbar
    Created on : May 21, 2021, 6:36:23 PM
    Author     : heaty566
--%>


<%@page import="Utils.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="/ServletController?action=listItemPage">Mobile Store</a>

		<div class=" navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<% if (!Helper.isLogin(request)) { %>
				<li class="nav-item">
					<a  class="nav-link" href="/ServletController?action=registerPage">Register</a>
				</li>
				<li class="nav-item">
					<a   class="nav-link"href="/ServletController?action=loginPage">Login</a>
				</li>
				<% } else { %>
				<li class="nav-item">
					<a  class="nav-link" href="/ServletController?action=listItemPage">List</a>
				</li>
				<li class="nav-item">
					<a  class="nav-link" href="/ServletController?action=logout">Logout</a>
				</li>
				<% if (Helper.correctRole(request, 1,2)) { %>
				<li class="nav-item">
					<a  class="nav-link" href="/ServletController?action=addPhonePage">Add Phone</a>
				</li>
				<% } else {%>
				<li class="nav-item">
					<a  class="nav-link" href="/ServletController?action=cartItemPage">Cart</a>
				</li>
				<% } %>
				<% } %>

			</ul>

		</div>
	</div>


</nav>




