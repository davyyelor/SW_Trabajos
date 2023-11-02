<!DOCTYPE html>
<html>
	<head>
		<title>Formulario de Acceso</title>
		<link href="/ShareInfo/css/styleSheet.css" rel="stylesheet" />
		<script src="/ShareInfo/js/time.js"></script>
	</head>
	<body onload="GetTimeIO()">
		<header>
			<h1>Web para Compartir Mensajes Cortos</h1>
			<h2>Formulario Login</h2>
		</header>
		<%if (request.getAttribute("error")!=null) { %>
		<section>
			<font>Información:
				<%=request.getAttribute("error") %>
			</font>
		</section>
		<%} %>
		
		<section>
			<form method="POST" action="/ShareInfo/servlet/LoginServlet">
				<table>
	   				<tr><td>Email:</td><td><input name="email"/></td></tr>
	   				<tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
	 			</table>
				<button>Enviar</button>
			</form>
		</section>
		<section>
			<a href="/ShareInfo/html/signupForm.html"><font>Registrarse</font></a>
		</section>
		<footer>Sistemas Web - Escuela Ingeniería de Bilbao</footer> 
		<p>
			hour is <span class="hour">0</span><br>
			minute is <span class="minute">0</span><br>
			second is <span class="second">0</span><br>
		</p>
	</body>
</html>