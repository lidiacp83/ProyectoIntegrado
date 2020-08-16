<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="es.utilidades.Utility"%>
<%@page import="es.modelos.pedidos.Pedido"%>
<%@page import="es.modelos.productos.Producto"%>
<%@page import="es.modelos.productos.ModeloProductos"%>
<%@page import="es.modelos.pedidos.ModeloPedidos"%>


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
				List<Pedido> listado = new ArrayList<Pedido>();
				java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
				
				if(request.getAttribute("listaPedido")!= null) {
					listado = (List<Pedido>) request.getAttribute("listaPedido");
				}

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
					out.println("<tr><td colspan=9> No existen pedidos pendientes </td></tr>");
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
			<td>Rebaja</td>
			<td>Stock</td>
			<td>Stock Minimo</td>
			<td>Categoría</td>
		</tr>
		<%
				// Scriplet: Cargo los detalles del pedido
				List<Producto> listaProducto = ModeloProductos.consultaStockMinimo();
			
			if(request.getAttribute("listaProducto")!= null) {
				listaProducto = (List<Producto>) request.getAttribute("listaProducto");
			}
				
				if (!listaProducto.isEmpty()) {
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
