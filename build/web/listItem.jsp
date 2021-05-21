<%-- 
    Document   : listItem
    Created on : May 21, 2021, 6:28:33 PM
    Author     : heaty566
--%>

<%@page import="DTO.Mobile"%>
<%@page import="java.util.ArrayList"%>
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
			Helper.protectedRouter(request, response, 0, "login.jsp");
			Boolean firstLoad = (Boolean) request.getAttribute("firstLoad");
			ArrayList<Mobile> mobiles =(ArrayList<Mobile>)  request.getAttribute("mobiles");
			if (mobiles == null){
				mobiles = new ArrayList() ;
			}
		%>
		<%@include file="navbar.jsp" %>



		<table>
			<thead>
				<tr>
					<th>No</th>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Year Of Production</th>
				</tr>
			</thead>
			<tbody>
				<% for (int i = 0; i < mobiles.size(); i++) { %>
				<tr>
					<td>
						<%= i+1 %>
					</td>
					<td>
						<%= mobiles.get(i).getMobileId()%>
					</td>
					<td>
						<%= mobiles.get(i).getMobileName() %>
					</td>
					<td>
						<%= mobiles.get(i).getDescription()%>
					</td>
					<td>
						<%= mobiles.get(i).getPrice() %>
					</td>
					<td>
						<%= mobiles.get(i).getQuantity()%>
					</td>
					<td>
						<%= mobiles.get(i).getYearOfProduction()%>
					</td>
				</tr>
				<% }%>

			</tbody>
		</table>

		<%  if (firstLoad == null){ %>
		<script>
		      window.location.href = "http://localhost:8088/Test/ServletController?action=listItem&minPrice=0&maxPrice=90000000";	
		</script>
		<% } %>



	</body>
</html>
