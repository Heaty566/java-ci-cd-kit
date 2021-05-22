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
			Float total = (Float) Helper.getClientParams(request, "total",0f);
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
					<th>Is Sale</th>
					<th>Update</th>
					<th>Delete</th>
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
					<td>
						<%= mobiles.get(i).isNotSale()%>
					</td>
					<td>
						<a onclick="return sure()" href="/Test/ServletController?action=deleteCartPhone&mobileId=<%= mobiles.get(i).getMobileId()%>">delete</a>
					</td>
				</tr>
				<% }%>

			</tbody>
		</table>
		<p>Total: <%=total %></p>
		<script>
			function sure(){
				const value = confirm("Are you sure to delete item");
				return value;
			}
		
		</script>




	</body>
</html>
