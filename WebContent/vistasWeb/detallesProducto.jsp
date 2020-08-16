<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="es.modelos.productos.Producto"%>
<%@page import="es.modelos.categorias.Categoria"%>

<% Producto producto = (Producto) request.getAttribute("producto"); %>
<div class="container texto">
	<div class="row">
		<div class="col-md-12 col-md-6">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li><i class="fas fa-home"></i></li>
					<li class="breadcrumb-item"><a href=""> &nbsp;&nbsp;Inicio</a></li>
					<li class="breadcrumb-item"><a id="categoriasMiga" href="#">
							&nbsp;&nbsp;Categorías</a></li>
					<li class="breadcrumb-item"><a href="#"
						onclick="seleccionarCategoria('<%=producto.getCategoria().getIdCategoria()%>','<%=producto.getCategoria().getNombre()%>')">
							&nbsp;&nbsp;<%=producto.getCategoria().getNombre()%></a></li>
					<li class="breadcrumb-item active" aria-current="page"><%=producto.getNombre() %></li>
				</ol>
			</nav>
		</div>

		<div class="card">
			<div class="row">
				<div class="detalleImagen col-md-4">
					<div class="contenidoImagen">
						<a data-toggle="modal" data-target="#myModal"><img id="myImg"
							src="<%=producto.getImagenData()%>" alt="Snow"></a>
						<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<button type="button" class="close text-right"
										data-dismiss="modal" aria-label="Close">
										<span class="btnCerrar" aria-hidden="true">&times;</span>
									</button>
									<div class="modal-body">
										<img src="<%=producto.getImagenData()%>"
											class="img-responsive">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h3 class="title"><%=producto.getNombre() %></h3>
						<p>
							<span class="precio"><%=producto.getPrecio() %>&nbsp; €</span>
						</p>
						<dl>
							<dt>Descripción</dt>
							<dd>
								<p><%=producto.getDescripcion() %></p>
							</dd>
						</dl>
						<dl>
							<dt>Id. Producto</dt>
							<dd><%=producto.getIdProducto() %></dd>
						</dl>
						<hr>
						<div class="row">
							<div class="col-sm-5">
								<dl>
									<dt>Cantidad:</dt>
									<dd>
										<select id="cantidad" class="form-control form-control-sm"
											style="width: 70px;">
											<option>1</option>
											<option>2</option>
											<option>3</option>
										</select>
									</dd>
								</dl>
							</div>

							<div class="col-sm-7">
								<dl>
									<dt>Talla:</dt>
									<dd>
										<select class="form-control form-control-sm"
											style="width: 150px;">
											<option>0 mes</option>
											<option>3 meses</option>
											<option>6 meses</option>
											<option>9 meses</option>
											<option>12 meses</option>
										</select>
									</dd>
								</dl>
							</div>
						</div>
						<hr>
						<a href="#"
							onclick="addCart(<%=producto.getIdProducto()%>, document.getElementById('cantidad').value);"
							class="btn btn-secondary addCesta"> Añadir al carrito </a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

