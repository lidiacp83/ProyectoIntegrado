<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script src="js/mensajes.js"></script>

	<% if(request.getAttribute("mensaje") !=null){ %>
		<script> 
			var mensaje = '<%= request.getAttribute("mensaje") %>';
			mensajeAlert(mensaje);
		</script>
	<% } %>
	
	<div class="container texto">

		<div class="row">
			<div class="col-md-12 col-md-6">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li><i class="fas fa-home"></i></li>
						<li class="breadcrumb-item"><a href="">
								&nbsp;&nbsp;Inicio</a></li>
						<li class="breadcrumb-item active" aria-current="page">Iniciar
							sesión</li>
					</ol>
				</nav>
			</div>


			<div class="col-md-12 col-md-6">
				<div class="titulo">
					<h1>
						<small>Inicio sesión / Registro</small>
					</h1>
				</div>
			</div>
		</div>

		<div class="row justify-content-center">
			<div class="col-md-8 col-sm-6 col-sm-2 form-wrap">

				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link active" href="#login"
						aria-controls="login" role="tab" data-toggle="tab">Entrar</a></li>
					<li class="nav-item"><a class="nav-link" href="#register"
						aria-controls="register" role="tab" data-toggle="tab">Crear
							cuenta</a></li>
				</ul>

				<div class="tab-content">

					<div class="tab-pane active" id="login">
						<h3 class="tituloForm">Iniciar sesión</h3>
						<hr>
						<form id="LoginWebForm" tabindex="500" method="POST" action="/Proyecto/login">
							<label class="sr-only" for="user">Correo electrónico</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user"></i></span>
								<input type="email" name="usuario" required id="username"
									class="form-control" placeholder="Correo electrónico">
							</div>
							<label class="sr-only" for="inputPassword">Contraseña</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input type="password" class="form-control" name="password"
									required id="password" placeholder="Contraseña">
							</div>
							<div class="checkbox">
								<label> <input type="checkbox"> Recuérdame
								</label>
							</div>
							<a href="#" onclick="submitLoginWeb()" class="btn btn-secondary py-2 btn-block ">Enviar</a>

						</form>
					</div>
					<div class="tab-pane" id="register">
						<h3 class="tituloForm">Crear una nueva cuenta</h3>
						<hr>
						<form method="POST" id="ClienteAltaWebForm" action="/Proyecto/clienteAlta" onsubmit="return false;">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user"></i></span>
								<input type="text" class="form-control" id="nombre" name="nombre"
									placeholder="Nombre">
							</div>
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fas fa-file-signature"></i></span> <input type="email"
									class="form-control" id="apellidos" name="apellidos" placeholder="Apellidos">
							</div>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-home"></i></span>
								<input type="text" class="form-control" name="direccion" id="direccion"
									placeholder="Dirección">
							</div>
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fas fa-mail-bulk"></i></span> <input type="text"
									class="form-control" id="cpostal" name="cpostal"
									placeholder="Código Postal"> <span
									class="input-group-addon"><i
									class="fas fa-location-arrow"></i></span> <input type="text"
									class="form-control" id="localidad" name="localidad" placeholder="Localidad">
							</div>
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fas fa-map-marked-alt"></i></span> <input type="text"
									class="form-control" id="provincia"name="provincia" placeholder="Provincia">

								<span class="input-group-addon"><i
									class="fas fa-phone-volume"></i></span> <input type="text"
									class="form-control" id="telefono" name="telefono" placeholder="Teléfono">
							</div>

							<div class="input-group">
								<span class="input-group-addon"><i
									class="far fa-envelope"></i></span> <input type="email"
									class="form-control" id="email" name="email" placeholder="Email">
							</div>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input type="password" class="form-control" id="clave" name="clave"
									placeholder="Contraseña">
							</div>
							<a href="#" onclick="submitAddClienteWeb()" class="btn btn-secondary py-2 btn-block ">Enviar</a>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
