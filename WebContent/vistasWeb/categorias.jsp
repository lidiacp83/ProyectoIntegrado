<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="es.modelos.categorias.Categoria"%>
<%@page import="es.modelos.categorias.ModeloCategorias"%>
<%@page import="java.util.Iterator"%>


<div class="container texto">
	<div class="row">
		<div class="col-md-12">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li><i class="fas fa-home"></i></li>
					<li class="breadcrumb-item"><a href=""> &nbsp;&nbsp;Inicio</a></li>
					<li class="breadcrumb-item active" aria-current="page">Categorías</li>
				</ol>
			</nav>

			<div class="titulo">
				<h1>
					<small>Categorías</small>
				</h1>
			</div>
		</div>
	</div>
	<div class="row">

		<% Iterator<Categoria> i = ModeloCategorias.consultaCategoria().iterator();
			while (i.hasNext()) {
			Categoria detalle = i.next(); %>

		<div class="col-md-3 col-sm-3">
			<div class="box15">
				<div class="contenidoImagen">
					<img class="imagenCateg" src="<%=detalle.getImagenData() %>" alt="" />
				</div>
				<div class="box-content">
					<h3 class="title"><%=detalle.getNombre() %></h3>
					<div class="icon">
						<a href="#"
							onclick="seleccionarCategoria('<%=detalle.getIdCategoria()%>','<%=detalle.getNombre()%>')"><i
							class="fa fa-search"></i></a>
					</div>
				</div>
			</div>
		</div>
		<% } %>
	</div>
</div>
