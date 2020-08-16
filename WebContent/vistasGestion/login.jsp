<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="css/estiloLogin.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<script src="js/mensajes.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
</head>
<body>
	<% if(request.getAttribute("mensaje") !=null){ %>
		<script> 
		var mensaje = '<%= request.getAttribute("mensaje") %>';
		mensajeAlert(mensaje);
		</script>
	<% } %>
	
	<div class="text-center">
		<form class="form-signin" method="POST" action="/Proyecto/loginGestion">
			<img class="logo img-fluid" src="img/logo.png" alt="">
			<h1 class="h3 mb-3 font-weight-normal"></h1>
			<label for="inputEmail" class="sr-only">Email</label> 
			<input type="email" id="inputEmail" name="usuario" class="form-control" placeholder="Correo electrónico" required autofocus> 
			<label for="inputPassword" class="sr-only">Contraseña</label>
			<input type="password" id="inputPassword" name="clave" class="form-control" placeholder="Contraseña" required>
			<button class="btn btn-block" type="submit">Entrar</button>
			<p class="mt-5 mb-3 text-muted">&copy; Rossita Paris 2020</p>
		</form>
	</div>
</body>
</html>