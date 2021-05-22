<%-- Document : addPhone Created on : May 21, 2021, 8:53:30 PM Author : heaty566 --%> 
<%@page import="DTO.Mobile"%>
<%@page import="Utils.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                <title>JSP Page</title>
                <link
		    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
		    rel="stylesheet"
		    integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
		    crossorigin="anonymous"
		    />
        </head>
        <body>
                <%@include file="navbar.jsp" %>
		<% 
			String descriptionError = (String) Helper.getClientParams(request, "descriptionError", ""); 
			String priceError = (String) Helper.getClientParams(request, "priceError", "");
			String quantityError = (String) Helper.getClientParams(request, "quantityError", "");
			Mobile mobile = (Mobile) Helper.getClientParams(request, "mobile", new Mobile()); 
		%>
                <div class="row m-2">
                        <div class="col-12 col-md-4">
                                <form action="ServletController?action=updatePhone&mobileId=<%= mobile.getMobileId()%>" method="POST">
                                        <div>
                                                <label for="mobileId" class="form-label">Mobile's Id</label>
                                                <input
						    class="form-control"
						    id="mobileId"
						    name="mobileId"
						    type="text"
						    value="<%=mobile.getMobileId()%>"
						    disabled="disabled"
						    />

                                        </div>
                                        <div>
                                                <label for="mobileName" class="form-label">Mobile's name</label>
                                                <input
						    class="form-control"
						    id="mobileName"
						    name="mobileName"
						    type="text"
						    value="<%=mobile.getMobileName() %>"
						    disabled="disabled"
						    />

                                        </div>

                                        <div>
                                                <label for="description" class="form-label">Mobile's description</label>
                                                <textarea class="form-control" id="description" name="description"><%= mobile.getDescription() %></textarea>
						<p class="text-danger"><%= descriptionError %></p>
                                        </div>
                                        <div>
                                                <label for="price" class="form-label">Mobile's price</label>
                                                <input class="form-control" id="price" name="price" type="number" value="<%=mobile.getPrice()%>" />
						<p class="text-danger"><%= priceError %></p>
                                        </div>
                                        <div>
                                                <label for="quantity" class="form-label">Mobile's quantity</label>
                                                <input
						    class="form-control"
						    id="quantity"
						    name="quantity"
						    type="number"
						    value="<%=mobile.getQuantity()%>"
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
						    value="<%=mobile.getYearOfProduction()%>"
						    disabled="disabled"
						    />
                                        </div>

                                        Mobile's not Sale

                                        <div class="form-check">
                                                <input class="form-check-input" type="radio" name="notSale" id="notSale1" value="1" />
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
                <script>
                                             let isCheck = false;
                        <% if (mobile.isNotSale())  {%>
						isCheck = true;
                        <%}%>

                                      const isSaleTrue =  document.getElementById("notSale1");
                                      const isSaleFalse =  document.getElementById("notSale2");
                                      if (isCheck)
                                              isSaleTrue.checked = true;
                                      else
                                         isSaleFalse.checked = true;
                </script>
        </body>
</html>
