<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!--  http://localhost:8080/prueba/jsp/cabecerasPeticion2.jsp -->

<!DOPCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cabeceras</title>
</head>
<body>
	JSP
	<table border="I">
		<tr>
			<th>Nombre Cabeceras</th>
			<th>Valor Cabecera</th>
		<tr>
		<%
				Enumeration<String> headerNames=request.getHeaderNames();
				while (headerNames.hasMoreElements()){
					String headerName = (String) headerNames.nextElement();
				
		%>
		<tr>
			<td><%=headerName%></td>
			<td><%=request.getHeader(headerName)%></td>
		<tr>
		<%} %>
	</table>
</body>
</html>