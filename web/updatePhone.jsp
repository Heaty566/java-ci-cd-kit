<%-- 
    Document   : addPhone
    Created on : May 21, 2021, 8:53:30 PM
    Author     : heaty566
--%>

<%@page import="DTO.Mobile"%>
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
			String descriptionError = (String)  Helper.getClientParams(request, "descriptionError", "");
			String priceError = (String)  Helper.getClientParams(request, "priceError", "");
			String quantityError = (String)  Helper.getClientParams(request, "quantityError", "");
			String notSaleError = (String)  Helper.getClientParams(request, "notSaleError", "");
			Mobile mobile = (Mobile) Helper.getClientParams(request, "mobile", new Mobile());
		%>
                <form action="ServletController?action=updatePhone&mobileId=<%= mobile.getMobileId()%>" method="POST">
			Mobile's Id <input name="mobileId" type="text"  disabled="disabled" value="<%=mobile.getMobileId()%>"  />
			<br/>
                        Mobile's name <input name="mobileName" type="text"  disabled="disabled" value="<%=mobile.getMobileName() %>"  />
                        <br />
                        Mobile's description <textarea name="description"   ><%=mobile.getDescription()%></textarea><p><%= descriptionError %></p>
                        <br />
                        Mobile's price <input name="price" type="number" value="<%=mobile.getPrice()%>"   /><p><%= priceError %></p>
                        <br />
                        Mobile's quantity <input name="quantity" type="number" value="<%=mobile.getQuantity()%>"   /><p><%= quantityError %></p>
                        <br />
                        Mobile's year of production <input name="yearOfProduction" type="number" disabled="disabled"   value="<%=mobile.getYearOfProduction()%>"   />
                        <br />
                        Mobile's not Sale
                        <br />
                        Yes <input name="notSale" type="radio" value="1"  checked="checked" value="<%=mobile.getMobileName() %>"  /> No <input name="notSale" type="radio" value="0" /><p><%= notSaleError %></p>
                        <br />

                        <input type="submit" />
                </form>
        </body>
</html>
