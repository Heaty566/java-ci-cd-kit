<%-- 
    Document   : navbar
    Created on : May 21, 2021, 6:36:23 PM
    Author     : heaty566
--%>


<%@page import="Utils.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
	<% if (!Helper.isLogin(request)) { %>
	<a href="/Test/ServletController?action=registerPage">Register</a>
	<a href="/Test/ServletController?action=loginPage">Login</a>
	<% } else { %>
	<a href="/Test/ServletController?action=logout">Logout</a>
	<a href="/Test/ServletController?action=listItemPage">List</a>
	<% if (Helper.correctRole(request, 1,2)) { %>
	<a href="/Test/ServletController?action=addPhonePage">add Phone</a>
	<% } else {%>
	<a href="/Test/ServletController?action=cartItemPage">Cart</a>
	<% } %>
	<% } %>



</div>