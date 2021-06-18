<%@page import="dtos.ComputerErrorObject"%>
<%@page import="dtos.ComputerDTO"%>
<%@page import="dtos.RoomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	ArrayList<RoomDTO> lstRooms = new ArrayList();
	lstRooms = (ArrayList<RoomDTO>) request.getAttribute("listRooms");
	ComputerDTO com = (ComputerDTO) request.getAttribute("computerObj");
	ComputerErrorObject errorObj = (ComputerErrorObject) request.getAttribute("INVALID");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<h1>Add New Computer Information</h1>
		<form action="CreateServlet" method="POST">
			<table>
				<tr>
					<td>ID</td>
					<td>: <input type="text" name="txtID" value="<% if (com != null) {out.print(com.getId());}%>"> 

						<font color="red"><% if (errorObj !=null) { out.print(errorObj.getIdError());}%></font>
					</td>
				</tr>
				<tr>
					<td>CPU</td>
					<td>: <input type="text" name="txtCPU" value="<% if (com != null) {out.print(com.getCpu());}%>">
						<font color="red"><% if (errorObj !=null) { out.print(errorObj.getCpuError());}%></font>
					</td>
				</tr>
				<tr>
					<td>Hard Disk</td>
					<td>: <input type="text" name="txtHardDisk" value="<% if (com != null) {out.print(com.getHardDisk());}%>">
						<font color="red"><% if (errorObj !=null) { out.print(errorObj.getHardDiskError());}%></font>
					</td>
				</tr>
				<tr>
					<td>Ram</td>
					<td>: <input type="text" name="txtRAM" value="<% if (com != null) {out.print(com.getRam());}%>">
						<font color="red"><% if (errorObj !=null) { out.print(errorObj.getRamError());}%></font>
					</td>
				</tr>
				<tr>
					<td>VGA</td>
					<td>: <input type="text" name="txtVGA" value="<% if (com != null) {out.print(com.getVga());}%>">
						<font color="red"><% if (errorObj !=null) { out.print(errorObj.getVgaError());}%></font>
					</td>
				</tr>
				<tr>
					<td>Monitor</td>
					<td>: <input type="text" name="txtMonitor" value="<% if (com != null) {out.print(com.getMonitor());}%>">
						<font color="red"><% if (errorObj !=null) { out.print(errorObj.getMonitorError());}%></font>
					</td>
				</tr>
				<tr>
					<td>Room</td>
					<td>: <select name="cboRoom">
							<% for (int i = 0; i < lstRooms.size(); i++) { %>
							<option><%= lstRooms.get(i).getId() %>-<%= lstRooms.get(i).getName() %></option>
							<% }%>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Create"/></td>
				</tr>
			</table>
		</form>

		<br/>
		<a href="LoadListComputerServlet">Computer List</a>
	</body>
</html>
