<%-- 
    Document   : addPhone
    Created on : May 21, 2021, 8:53:30 PM
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

		<%@include file="navbar.jsp" %>

		<%
			String mobileNameError = (String) Helper.getClientParams(request, "mobileNameError", "");
			String descriptionError = (String)  Helper.getClientParams(request, "descriptionError", "");
			String priceError = (String)  Helper.getClientParams(request, "priceError", "");
			String quantityError = (String)  Helper.getClientParams(request, "quantityError", "");
			String yearOfProductionError = (String)  Helper.getClientParams(request, "yearOfProductionError", "");
		%>
		<div class="row m-2">
			<div class="col-12 col-md-4">
				<form action="ServletController?action=addPhone" method="POST">

					<div>
						<label for="mobileName" class="form-label">Mobile's name</label>
						<input
						    class="form-control"
						    id="mobileName"
						    name="mobileName"
						    type="text"


						    />
						<p class="text-danger"><%= mobileNameError %></p>
					</div>

					<div>
						<label for="description" class="form-label">Mobile's description</label>
						<textarea class="form-control" id="description" name="description"></textarea>
						<p class="text-danger"><%= descriptionError %></p>
					</div>
					<div>
						<label for="price" class="form-label">Mobile's price</label>
						<input class="form-control" id="price" name="price" type="number"  />
						<p class="text-danger"><%= priceError %></p>
					</div>
					<div>
						<label for="quantity" class="form-label">Mobile's quantity</label>
						<input
						    class="form-control"
						    id="quantity"
						    name="quantity"
						    type="number"

						    />
						<p class="text-danger"><%= quantityError %></p>
					</div>
					<div>
						<label for="yearOfProduction" class="form-label">Mobile's year of production</label>
						<input
						    class="form-control"
						    id="yearOfProduction"
						    name="yearOfProduction"
						    type="number"


						    />
						<p class="text-danger"><%= yearOfProductionError %></p>
					</div>

					Mobile's not Sale

					<div class="form-check">
						<input class="form-check-input" type="radio" name="notSale" id="notSale1" value="1" checked />
						<label class="form-check-label" for="notSale1">Yes</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="notSale" id="notSale2" value="0" />
						<label class="form-check-label" for="notSale2">No</label>
					</div>

					<button type="submit" class="btn btn-primary">Update</button>
				</form>
			</div>
		</div>

        </body>
</html>
