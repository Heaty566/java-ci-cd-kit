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
		<form method="POST" action="ServletController?action=listItemPage">

			Min Price	<input type="number" name="minPrice"  value="0" />
			<br/>
			Max Price	<input type="number" name="maxPrice"  value="999999" />
			<br/>
			Mobile Name<input type="text" name="mobileName"  value="" />
			<br/>
			Mobile Id<input type="text" name="mobileId"  value="" />
			<br/>
			<input type="submit" value="Search"/>
		</form>



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
						<a  href="/Test/ServletController?action=updatePhonePage&mobileId=<%= mobiles.get(i).getMobileId()%>" >Update</a>
					</td>
					<td>
						<a onclick="return sure()" href="/Test/ServletController?action=deletePhone&mobileId=<%= mobiles.get(i).getMobileId()%>">delete</a>
					</td>
					<td>
						<a  href="/Test/ServletController?action=addCartItem&mobileId=<%= mobiles.get(i).getMobileId()%>">Add</a>
					</td>
				</tr>
				<% }%>

			</tbody>
		</table>
		<script>
			function sure(){
				const value = confirm("Are you sure to delete item");
				return value;
			}
		
		</script>
		<%  if (firstLoad == null){ %>
		<script>
		      window.location.href = "/Test/ServletController?action=listItemPage";	
		</script>
		<% } %>



	</body>
</html>
