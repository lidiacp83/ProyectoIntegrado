<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.modelos.pedidos.Pedido"%>
<%@page import="es.utilidades.Utility"%>
<%@page import="java.util.Iterator"%>
<%@page import="es.modelos.clientes.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


	<% if(request.getAttribute("mensaje") !=null){ %>
		<script> 
		var mensaje = '<%= request.getAttribute("mensaje") %>';
		mensajeAlert(mensaje);
		</script>
	<% } %>

	<form id="PedidoForm" class="estiloForm"
		action="/Proyecto/pedidoConsulta" method="POST"
		onsubmit="return false;">
		
		<h2>DATOS PEDIDO</h2>
		
			<select name="selectPedido" class="select-css">
				<option value="pedidoId">Id</option>
				<option value="cliente" selected>Cliente</option>
				<option value="pedidoFecha">Fecha</option>
				<option value="pedidoFormaPago">Forma de pago</option>
				<option value="pedidoGastosEnvio">Gastos de envío</option>
				<option value="pedidoTotal">Total pedido</option>
				<option value="pedidoEstado">Estado</option>
			</select>
			<input name="txtBuscar" type="text" value=""> &nbsp; &nbsp; <button class="btnBuscar" title="Buscar" type="submit">
			<i class="fas fa-search" onclick="submitBuscarPedido();"> <span class="txtBtnBlanco">Buscar </span></i> </button>
			<button class="btnExcel" title="Imprimir listado" type="submit"
				onclick="exportTableToExcel('tblData', 'Listado_Pedidos')">
				<i class="fas fa-file-excel"> <span class="txtBtnBlanco">Generar
						Excel </span></i>
			</button>


			<table id="tblData">
			<tr class="cabeceraTabla">
				<td>ID.</td>
				<td>Cliente</td>
				<td>Fecha</td>
				<td>Forma Pago</td>
				<td>Gastos de envío</td>
				<td>Total</td>
				<td>Estado</td>
				<td>Acciones</td>
			</tr>

			<%
				// Scriplet: Cargo los detalles del pedido
				List<Pedido> listado = new ArrayList<Pedido>();
				java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
			  	
				if (request.getAttribute("listadoPedido") != null) {
					listado = (List<Pedido>) request.getAttribute("listadoPedido");
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
						out.println("<td>" + detalle.getEstado() + "</td>");
						out.println("<td><button class='btnAcciones' title='Ver detalles' type='submit' onclick=submitEditarPedido("
								+ detalle.getIdPedido() + ")><i class='fas fa-edit'></i></button></td></tr>");
					}
				} else {
					out.println("<tr><td colspan=9> No existen pedidos </td></tr>");
				}
			%>
		</table>
	</form>
