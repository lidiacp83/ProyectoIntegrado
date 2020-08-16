<%@page import="es.modelos.pedidos.ModeloPedidos"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="es.modelos.categorias.Categoria"%>
<%@page import="es.modelos.clientes.Cliente"%>
<%@page import="es.modelos.pedidos.DetallesPedido"%>
<%@page import="es.modelos.categorias.ModeloCategorias"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.HttpSession"%>


	<% if(request.getAttribute("mensaje") !=null){ %>
		<script> 
		var mensaje = '<%= request.getAttribute("mensaje") %>';
		mensajeAlert(mensaje);
		</script>
	<% } %>
	<header>
		<div class="container-fluid">
			<div class="row topbar">
				<div class="col-md-4">
					<div class="text-left">
						<a href="https://www.instagram.com/rossittaparis/?hl=es"><i class="fab fa-facebook-square"></i></a> <a href="https://www.facebook.com/RossittaParis"><i
							class="instagramCab fab fa-instagram"></i></a>
					</div>
				</div>

				<div class="col-md-4">
					<div class="text-center">
						<span class="d-none d-sm-none d-md-block "> Gastos de envío
							gratis a partir de 50€</span>
					</div>
				</div>

				<div class="col-md-4">
					<div class="text-right">
						<span class="d-none d-sm-none d-md-block ">rossittaparis.shop@gmail.com
						</span>
					</div>
				</div>
			</div>

			<div class="row cabeceraLogo">
				<div class="col-md-12 col-sm-6 text-center">
					<img class="logo" src="img/logo.png" />
				</div>
			</div>

			<div class="row menuBar">
				<div class="col-md-12 justify-content-center">
					<nav class="navbar navbar-expand-lg scrolling-navbar ">
						<button class="navbar-toggler d-md-none" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class=""><i class="fas fa-bars "></i></span>
						</button>

						<div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
							<ul class=" navbar-nav justify-content-center ">
								<li class="nav-item active">
									<a href="/Proyecto/" id="inicio" class="link">Inicio<span class="sr-only">(current)</span></a>
								</li>

								<li class ="dropdown">
									<a class="link dropdown-toggle" id="categorias" role="button" data-toggle="dropdown" aria-haspopup="true" 
									aria-expanded="false">Categorías</a>
									<div class="dropdown-menu">
								<%
									Iterator<Categoria> i = ModeloCategorias.consultaCategoria().iterator();
									while (i.hasNext()) {
									Categoria detalle = i.next();
								%>
										<a class="dropdown-item" href="#"
											onclick="seleccionarCategoria('<%=detalle.getIdCategoria()%>','<%=detalle.getNombre()%>')"><%=detalle.getNombre()%></a>
								<%
									}
								%>
									</div>
								</li>
								<li class="nav-item"><a class="link" id="tallas">Guía de tallas</a></li>
								<li class="nav-item"><a class="link" id="devoluciones">Envío y devoluciones</a></li>
								<li class="nav-item"><a class="link" id="rossita">Sobre Rossita</a></li>
								<li class="nav-item"><a class="link" id="contacto">Contacto</a></li>	
								<% 
									Cliente cliente = (Cliente) session.getAttribute("cliente");
									if (cliente != null){
								%>
								<li class ="nav-item dropdown">
									<a class="link dropdown-toggle" id="inicioSesion" role="button" data-toggle="dropdown" aria-haspopup="true" 
									aria-expanded="false"><i class="fas fa-user-circle"></i>&nbsp;&nbsp;Hola <%= cliente.getNombre() %></a>
									<div class="dropdown-menu">
										<a class="d-none d-sm-none d-sm-block dropdown-item" href="#" onclick="logoutWeb()"><i class="fa fa-power-off"></i>&nbsp;<span>Cerrar sesión</span></a>
									</div>
								</li>
									
								<%
								} else {
								%>
								<li class="dropdown"><a class="link dropdown-toggle" id="inicioSesion" role="button"
									data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user-circle"></i>&nbsp;
										&nbsp;Iniciar sesión</a>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<form id="LoginPopUp" onsubmit ="return false;" class="px-4 py-3 desplegableMenu"
											method="post" action="/Proyecto/login">
											<div class="form-group">
												<label for="exampleDropdownFormEmail1">Correo electrónico</label> 
												<input type="email" class="form-control" name="usuario" required id="username"
													id="exampleDropdownFormEmail1" placeholder="email@ejemplo.com">
											</div>
											<div class="form-group">
												<label for="exampleDropdownFormPassword1">Contraseña</label>
												<input type="password" class="form-control" name="password" required id="password" 
												id="exampleDropdownFormPassword1" placeholder="contraseña">
											</div>
											<div class="form-check">
												<input type="checkbox" class="form-check-input" id="dropdownCheck"> 
												<label class="form-check-label" for="dropdownCheck"> Recuérdame </label>
											</div>
											<button type="submit"
												class="btn btn-secondary py-2 btn-block" onclick="submitLoginPopUp()" value="Entrar">Entrar</button>
										</form>


									</div></li>
									<% }  %>
								<%
									List<DetallesPedido> listado = (List<DetallesPedido>) session.getAttribute("cesta");
								%>
								<li class="dropdown">
								<a class="cart-res link dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fas fa-shopping-bag"></i>&nbsp; &nbsp;Cesta <span class="badge badge-light"> 
										<%
	 										if (listado != null) {
										%>
										<%=listado.size()%> 
										<% } else {  %> 0 <% } %>
									</span></a>
									<div class="tablaProductos dropdown-menu">
										<table class="table">
											<thead class="thead-dark">
												<tr>
													<th>Producto</th>
													<th>Nombre</th>
													<th>Precio</th>
												</tr>
											</thead>

											<tbody class="align-items-center">
												<%
													if (listado != null && listado.size() > 0) {
														Iterator<DetallesPedido> j = listado.iterator();
														while (j.hasNext()) {
															DetallesPedido detalle = j.next();
												%>
													<tr>
														<td class="col"><img class="imagenCesta"
															src="<%=detalle.getImagenData()%>"></td>
														<td class="col align-middle">
															<strong><span> <%=detalle.getNombre()%> </span></strong></br>
															<span> Cantidad: <%=detalle.getCantidad()%> </span>
														</td>
														<td class="col align-middle"><span class="precio"><%=detalle.getPrecio()%>&nbsp;&nbsp;
																€</span></td>
													</tr>
												<%
														}
													} else {
												%>
												<tr>
													<td class="col">No hay artículos en la cesta</td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
										<hr>
										<div class="col text-center">
											<button class="btn btn-secondary btn-block" id="verCesta">Ver
												cesta</button><br>
										</div>
									</div>
									</li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</header>
<script src="js/web/web.js"></script>