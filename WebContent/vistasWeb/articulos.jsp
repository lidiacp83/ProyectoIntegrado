<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.Iterator"%>
	<%@page import="es.modelos.productos.Producto"%>
	<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
	
	<div class="container texto">
		<div class="row">
			<div class="col-md-12">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li><i class="fas fa-home"></i></li>
						<li class="breadcrumb-item"><a href="">
								&nbsp;&nbsp;Inicio</a></li>
								<li class="breadcrumb-item"><a id="categoriasMiga" href="#">
								&nbsp;&nbsp;Categorías</a></li>
						<li class="breadcrumb-item active" aria-current="page"><%=request.getAttribute("nombreCategoria")%></li>
					</ol>
				</nav>

				<div class="titulo">
					<h1>
						<small><%=request.getAttribute("nombreCategoria")%></small>
					</h1>
				</div>
			</div>
				<div class="row">
				<% 
				List<Producto> listado = new ArrayList<Producto>();
				if (request.getAttribute("listaProductos") != null) {
					listado = (List<Producto>) request.getAttribute("listaProductos");
				} 
				if (!listado.isEmpty()) {
					Iterator<Producto> i = listado.iterator();
					while (i.hasNext()) {
						Producto detalle = i.next();
				%>
						<div class="col-md-3 col-sm-6">

							<div class="product-grid6">
								<div class="contenidoImagen"> <img class="imagenCateg" src="<%=detalle.getImagenData() %>" alt="" /> </div>
								<div class="product-content">
									<h3 class="title">
										<a href="#"><%=detalle.getNombre() %></a>
									</h3>
									<div class="price">
										<% if(Float.parseFloat(detalle.getRebaja()) > 0) { %>
											<%=detalle.getPrecio()%> € <span><%=detalle.getRebaja()%> €</span>
										<% } else { %>
											<%=detalle.getPrecio()%> €
										<% } %>
									</div>
								</div>
								<ul class="social">
									<li><a href="#" onclick="seleccionarProducto('<%=detalle.getIdProducto()%>')" data-tip="Ver detalles"><i
											class="fa fa-search"></i></a></li>
									<li><a href="#"  onclick="addCart('<%=detalle.getIdProducto()%>', 1)"  data-tip="Añadir al carrito"><i
											class="fa fa-shopping-cart"></i></a></li>
								</ul>
							</div>
						</div>
				<%
					}
				}
				%>
			</div>
		</div>
	</div>
