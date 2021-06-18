<%@page import="dtos.ComputerDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<h1>Computer Manager</h1>
		<a href="CreateFormServlet">Add New</a>
		<br/>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>CPU</th>
					<th>VGA</th>
					<th>HardDisk</th>
					<th>RAM</th>
					<th>Monitor</th>
					<th>Room</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% 
				
					ArrayList<ComputerDTO> list = new ArrayList<ComputerDTO>();
					list = (ArrayList<ComputerDTO>) request.getAttribute("listComputers");
					if (list != null){
						for (ComputerDTO it : list) {%>
				<tr>
					<td><%=it.getId()%></td>
					<td><%=it.getCpu()%></td>
					<td><%=it.getVga()%></td>
					<td><%=it.getHardDisk()%></td>
					<td><%=it.getRam()%></td>
					<td><%=it.getMonitor()%></td>
					<td><%=it.getRoom().getName()%> - <%= it.getRoom().getBuilding()%></td>
					<td>Edit</td>
					<td>
						<a href="DeleteServlet?pid=<%= it.getId()%>" onclick="return confirm('Allow Delete?')">Delete</a></td>
				</tr>

				<% }
				}else { 
				} %>

			</tbody>
		</table>


	</body>
</html>
