<%@page import="es.modelos.pedidos.ModeloPedidos"%>
<%@page import="es.modelos.productos.ModeloProductos"%>
<%@page import="es.modelos.pedidos.Pedido"%>
<%@page import="es.modelos.productos.Producto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.utilidades.Utility"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Menú Opciones</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">
		<link href="https://fonts.googleapis.com/css2?family=Titillium+Web:wght@300&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
		<link rel="stylesheet" href="css/estiloContenido.css">
	</head>
<body>

	<div id="menu">
		<jsp:include page="menuNav.jsp"></jsp:include>
	</div>

	<div id="container">
		<form class="estiloForm" action="" method="POST">
			<h2>PEDIDOS PENDIENTES</h2>
			<table id="tblData">
				<tr class="cabeceraTabla">
					<td>ID.</td>
					<td>Cliente</td>
					<td>Fecha</td>
					<td>Forma Pago</td>
					<td>Gastos de envío</td>
					<td>Total</td>
					<td>Estado</td>
				</tr>

				<%
					// Scriplet: Cargo los detalles del pedido
					List<Pedido> listado = ModeloPedidos.consultaPedidosPendientes();
					java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");

					if (!listado.isEmpty()) {
						Iterator<Pedido> i = listado.iterator();
						while (i.hasNext()) {
							Pedido detalle = i.next();
							out.println("<tr><td>" + detalle.getIdPedido() + "</td>");
							out.println("<td>" + detalle.getCliente().getNombre() + " " + detalle.getCliente().getApellidos()
									+ "</td>");
							out.println("<td>" + Utility.fechaATexto(detalle.getFecha()) + "</td>");
							out.println("<td>" + detalle.getFormaPago() + "</td>");
							out.println("<td>" + detalle.getEnvio() + " €</td>");
							out.println("<td>" + detalle.getTotalPedido() + " €</td>");
							out.println("<td>" + detalle.getEstado() + "</td></tr>");
						}
					} else {
						out.println("<tr><td colspan=9> No existen pedidos </td></tr>");
					}
				%>
			</table>

			<h2>PRODUCTOS BAJO STOCK MÍNIMO</h2>
			<table id="tblData">
				<tr class="cabeceraTabla">
					<td>ID.</td>
					<td>Nombre</td>
					<td>Descripción</td>
					<td>Precio</td>
					<td>Descuento</td>
					<td>Stock</td>
					<td>Stock Minimo</td>
					<td>Categoría</td>
				</tr>
				<%
					// Scriplet: Cargo los detalles del pedido
					List<Producto> listaProducto = ModeloProductos.consultaStockMinimo();

					if (!listado.isEmpty()) {
						Iterator<Producto> i = listaProducto.iterator();
						while (i.hasNext()) {
							Producto detalle = i.next();
							out.println("<tr><td>" + detalle.getIdProducto() + "</td>");
							out.println("<td>" + detalle.getNombre() + "</td>");
							out.println("<td>" + detalle.getDescripcion() + "</td>");
							out.println("<td>" + detalle.getPrecio() + " €</td>");
							out.println("<td>" + detalle.getRebaja() + " €</td>");
							out.println("<td>" + detalle.getStock() + "</td>");
							out.println("<td>" + detalle.getStockMinimo() + "</td>");
							out.println("<td>" + detalle.getCategoria().getNombre() + "</td></tr>");
						}
					} else {
						out.println("<tr><td colspan=12> No existen productos bajo stock mínimo </td></tr>");
					}
				%>
			</table>
		</form>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	<script src="js/cliente.js"></script>
	<script src="js/categoria.js"></script>
	<script src="js/producto.js"></script>
	<script src="js/pedido.js"></script>
	<script src="js/exportarExcel.js"></script>
	<script src="js/menuLateral.js"></script>
	<script src="js/mensajes.js"></script>	
</body>
</html>


