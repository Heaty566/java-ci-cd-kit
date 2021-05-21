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
	</head>
	<body>
           
		<%@include file="navbar.jsp" %>

		<%
			
			Helper.protectedRouter(request, response, 1, "login.jsp"); 
			String mobileNameError = (String) request.getAttribute("mobileNameError");
			if (mobileNameError == null){
				mobileNameError = "";
			}
			String descriptionError = (String) request.getAttribute("descriptionError");
			if (descriptionError == null){
				descriptionError = "";
			}
			String priceError = (String) request.getAttribute("priceError");
			if (priceError == null){
				priceError = "";
			}
			String quantityError = (String) request.getAttribute("quantityError");
			if (quantityError == null){
				quantityError = "";
			}
			String yearOfProductionError = (String) request.getAttribute("yearOfProductionError");
			if (yearOfProductionError == null){
				yearOfProductionError = "";
			}
			String notSaleError = (String) request.getAttribute("notSaleError");
			if (notSaleError == null){
				notSaleError = "";
			}
			
			
			
		%>
                <form action="ServletController?action=addPhone" method="POST">
                        Mobile's name <input name="mobileName" type="text" /><p><%= mobileNameError %></p>
                        <br />
                        Mobile's description <textarea name="description"></textarea><p><%= descriptionError %></p>
                        <br />
                        Mobile's price <input name="price" type="number" /><p><%= priceError %></p>
                        <br />
                        Mobile's quantity <input name="quantity" type="number" /><p><%= quantityError %></p>
                        <br />
                        Mobile's year of production <input name="yearOfProduction" type="number" /><p><%= yearOfProductionError %></p>
                        <br />
                        Mobile's not Sale
                        <br />
                        Yes <input name="notSale" type="radio" value="1"  checked="checked"/> No <input name="notSale" type="radio" value="0" /><p><%= notSaleError %></p>
                        <br />

                        <input type="submit" />
                </form>
        </body>
</html>
