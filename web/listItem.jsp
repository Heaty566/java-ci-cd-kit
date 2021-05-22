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
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
	</head>
	<body>
		<%
			Boolean firstLoad = (Boolean) request.getAttribute("firstLoad");
			ArrayList<Mobile> mobiles = (ArrayList<Mobile>) Helper.getClientParams(request,"mobiles", new ArrayList() );
		%>
		<%@include file="navbar.jsp" %>

		<div  class="m-2">
			<form method="POST" action="ServletController?action=listItemPage" class="my-2" >
				<div class="row my-2">
					<div class="col-6">
						Min Price	<input class="form-control" type="number" name="minPrice"  value="0"  />
					</div>

					<div class="col-6">
						Max Price	<input class="form-control" type="number" name="maxPrice"  value="999999" />
					</div>
				</div>

				<div class="row my-2">
					<div class="col-6">
						Mobile Name	<input class="form-control" type="text" name="mobileName"  value=""  />
					</div>

					<div class="col-6">
						Mobile Id	<input class="form-control" type="text" name="mobileId"  value=""  />
					</div>
				</div>


				<button type="submit" class="btn btn-primary">Search</button>
			</form>



			<table class="table table-hover text-capitalize">
				<thead>
					<tr class=" table-dark ">
						<th scope="col">No</th>
						<th scope="col">ID</th>
						<th scope="col">Name</th>
						<th scope="col">Description</th>
						<th scope="col">Price</th>
						<th scope="col">Quantity</th>
						<th scope="col">Year Of Production</th>
						<th scope="col">Is Sale</th>


						<% if (Helper.correctRole(request, 1,2)) { %>
						<th>Update</th>
						<th>Delete</th>
							<% } else { %>
						<th>Add</th>
							<% } %>
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
						<% if (Helper.correctRole(request, 1,2)) { %>
						<td>
							<a   class="btn btn-warning" href="/ServletController?action=updatePhonePage&mobileId=<%= mobiles.get(i).getMobileId()%>" >Update</a>
						</td>
						<td>
							<a  class="btn btn-danger" onclick="return sure()" href="/ServletController?action=deletePhone&mobileId=<%= mobiles.get(i).getMobileId()%>">delete</a>
						</td>

						<% } else if ( mobiles.get(i).isNotSale()) { %>
						<td>
							<a  class="btn btn-primary" href="/ServletController?action=addCartItem&mobileId=<%= mobiles.get(i).getMobileId()%>">Add</a>
						</td>
						<% } %>


					</tr>
					<% }%>

				</tbody>
			</table>
		</div>

		<script>
			function sure(){
				const value = confirm("Are you sure to delete item");
				return value;
			}
		
		</script>
		<%  if (firstLoad == null){ %>
		<script>
		      window.location.href = "/ServletController?action=listItemPage";	
		</script>
		<% } %>



	</body>
</html>
