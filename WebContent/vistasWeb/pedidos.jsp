<%@page import="es.modelos.pedidos.ModeloPedidos"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="es.modelos.categorias.Categoria"%>
<%@page import="es.modelos.pedidos.DetallesPedido"%>
<%@page import="es.modelos.categorias.ModeloCategorias"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<script src="js/mensajes.js"></script>

	<%
		if (request.getAttribute("mensaje") != null) {
	%>
	<script> 
		var mensaje = '<%=request.getAttribute("mensaje")%>';
		mensajeAlert(mensaje);
	</script>
	<%
		}
	%>

	<div class="container texto">
		<div class="row miga">
			<div class="col-md-12 col-md-6">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li><i class="fas fa-home"></i></li>
						<li class="breadcrumb-item"><a href="">
								&nbsp;&nbsp;Inicio</a></li>
						<li class="breadcrumb-item active" aria-current="page">Carrito
							de compras</li>
					</ol>
				</nav>
			</div>
			<div class="col-md-12 col-md-6">
				<div class="titulo">
					<h1>
						<small>Cesta</small>
					</h1>
				</div>
			</div>
		</div>
		<div class="pb-5">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 p-5 border shadow-sm mb-5">

						<!-- Shopping cart table -->
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th scope="col" class="border-0 bg-light">
											<div class="p-2 px-3 text-uppercase">Producto</div>
										</th>
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase">Precio</div>
										</th>
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase">Cantidad</div>
										</th>
										<th scope="col" class="border-0 bg-light">
											<div class="py-2 text-uppercase">Eliminar</div>
										</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<DetallesPedido> listado = (List<DetallesPedido>) session.getAttribute("cesta");
										Float subtotal = (Float) session.getAttribute("subtotal");
										Float iva = (Float) session.getAttribute("iva");
										Float total = new Float(0.00f);
										if(subtotal != null || iva != null) {
											total = subtotal + iva;
										} else {
											subtotal = new Float(0.00f);
											iva = new Float(0.00f);
										}
									%>
									<%
										if (listado != null && listado.size() > 0) {
											Iterator<DetallesPedido> j = listado.iterator();
											while (j.hasNext()) {
												DetallesPedido detalle = j.next();
									%>
									<tr class="product">
										<th scope="row" class="border-0">
											<div class="p-2">
												<img src="<%=detalle.getImagenData()%>" alt="" width="70"
													class="img-fluid rounded shadow-sm">
												<div class="ml-3 d-inline-block align-middle">
													<h5 class="mb-0">
														<a href="#" class="text-dark d-inline-block align-middle"><%=detalle.getNombre()%></a>
													</h5>
													<span
														class="text-muted font-weight-normal font-italic d-block">Id.
														Producto: &nbsp;<%=detalle.getIdProducto()%></span>
												</div>
											</div>
										</th>
										<td class="border-0 align-middle"><strong><%=detalle.getPrecio()%></strong></td>
										<td class="border-0 align-middle"><strong><%=detalle.getCantidad()%></strong></td>
										<td class="border-0 align-middle"><a href="#"
											class="removefromcart text-dark" onclick="eliminarProductoCesta(<%=detalle.getIdProducto()%>)"><i class="fa fa-trash"></i></a></td>
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
						</div>
						<!-- End -->
					</div>
				</div>
				
				<div class="row py-5 p-4 bg-white rounded shadow-sm">
					<div class="col-lg-6">
						<div class="bg-light px-4 py-3 text-uppercase font-weight-bold">Código
							promocional</div>
						<div class="p-4">
							<p class="font-italic mb-4">Si tienes un cupón de descuento,
								introduzca el código en la caja de abajo</p>
							<div class="input-group mb-4 border p-2">
								<input type="text" placeholder="Aplicar cupón"
									aria-describedby="button-addon3" class="form-control border-0">
								<div class="input-group-append border-0">
									<button id="button-addon3" type="button"
										class="btn btn-secondary px-4">
										<i class="fa fa-gift mr-2"></i>Aplicar
									</button>
								</div>
							</div>
						</div>
						<div class="bg-light px-4 py-3 text-uppercase font-weight-bold">Mensaje
							para el vendedor</div>
						<div class="p-4">
							<p class="font-italic mb-4">Si tienes algún tipo de
								información para el vendedor, por favor deje sus instrucciones
								aquí abajo</p>
							<textarea name="" cols="30" rows="2" class="form-control"></textarea>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="bg-light px-4 py-3 text-uppercase font-weight-bold">Resumen
							pedido</div>
						<div class="p-4">
							<p class="font-italic mb-4">Los gastos de envío se verán
								reflejados automáticamente</p>
							<ul class="list-unstyled mb-4">
								<li class="d-flex justify-content-between py-3 border-bottom"><strong
									class="text-muted">SubTotal </strong><strong><%=String.format("%.2f", subtotal)%> € </strong></li>
								<!-- <li class="d-flex justify-content-between py-3 border-bottom"><strong
									class="text-muted">Gastos de envío</strong><strong>0.00
										€ </strong></li> -->
								<li class="d-flex justify-content-between py-3 border-bottom"><strong
									class="text-muted">IVA (21%)</strong><strong><%=String.format("%.2f", iva)%> € </strong></li>
								<li class="d-flex justify-content-between py-3 border-bottom"><strong
									class="text-muted">Total</strong>
									<h5 class="font-weight-bold"><%=String.format("%.2f", total)%> € </h5></li>
							</ul>
							<a href="#" class="btn btn-secondary py-2 btn-block" onclick="confirmarCompra();">Confirmar
								compra</a>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
<script>
	$(".removefromcart").on("click", function(e) {
		var fadeDelete = $(this).parents('.product');
		$(fadeDelete).fadeOut(function() {
			$(this).remove();
		});

		e.stopPropagation();
	});
</script>
