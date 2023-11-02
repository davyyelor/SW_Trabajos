<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.*,helper.info.*"%>
<%
	ArrayList<MessageInfo> messageList = (ArrayList) request.getAttribute("messageList");
	ServletContext context = request.getServletContext();
	HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
	HttpSession sesion=request.getSession();
%>
<html>
<head>
<title>Visor de Mensajes</title>
<link href="/ShareInfo/css/styleSheet.css" rel="stylesheet" />
</head>
<body>
	<header>
		<h1>Web para Compartir Mensajes Cortos</h1>
		<h3>Vista de Mensasjes</h3>
	</header>
	<section> 
		<a href="/ShareInfo/servlet/LogOutServlet">
			 <font>Cerrar sesion</font>
		</a> 
	</section>
	<section> 
		<a href="/ShareInfo/servlet/MainServlet">
			 <font>Actualizar</font>
		</a> 
	</section>
	<section> 
	<font>Usuarios Activos: </font> 
	<% 	for (Map.Entry<String, String> entry : loggedinUsers.entrySet()) { %>
		<%=entry.getKey()%>; 
	<% } %>
	 </section>
	 <section>
	 	<font>Tu usuario es:</font>
	 	<%=sesion.getAttribute("username")%>
	 
	 </section>
	<section>
		<form method="POST" action="/ShareInfo/servlet/MainServlet">
		<table>
			<tr>
				<td>Mensaje:</td>
				<td><textarea name="message" id="message"></textarea></td>
			</tr>
		</table>
		<button>Enviar</button>
	</form>
	</section>
	<section>
	<table>
		<tr>
			<th>Usuario</th>
			<th>Mensaje</th>
		</tr>
		<%
			for (int i = 0; i < messageList.size(); i++) {
				MessageInfo messageInfo = messageList.get(i);
		%>
		<tr>
			<td><%=messageInfo.getUsername()%></td>
			<td><%=messageInfo.getMessage()%></td>
		</tr>
		<%
			}
		%>
	</table>
	</section>
	<footer>Sistemas Web - Escuela de Ingenieria de Bilbao</footer>
</body>
</html>