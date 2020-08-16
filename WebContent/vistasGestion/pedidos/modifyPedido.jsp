<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="es.modelos.pedidos.Pedido"%>
<%@page import="es.modelos.productos.Producto"%>
<%@page import="es.modelos.pedidos.DetallesPedido"%>
<%@page import="es.modelos.clientes.Cliente"%>
<%@page import="es.utilidades.Utility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>


	<% if(request.getAttribute("mensaje") !=null){ %>
		<script> 
		var mensaje = '<%= request.getAttribute("mensaje") %>';
		mensajeAlert(mensaje);
		</script>
	<% } %>

	<form id="PedidoForm" class="estiloFormBorderNo"
		action="/Proyecto/pedidoModificar" method="POST"
		onsubmit="return false;">

		<h2>MODIFICAR PEDIDO</h2>
		
		<%
			Pedido pedido = new Pedido();
			if (request.getAttribute("pedido") != null) {
				pedido = (Pedido) request.getAttribute("pedido");
			}
		%>
		
		<input type="hidden" id="pedidoId" value="<%=pedido.getIdPedido() %>">
		
		<table class="contentTable">

			<%
				DetallesPedido detallePedido = new DetallesPedido();
				if (request.getAttribute("detallesPedido") != null) {
					detallePedido = (DetallesPedido) request.getAttribute("detallesPedido");
				}
			%>

			<tr>
				<td>Id. Pedido: &nbsp;&nbsp;</td>
				<td><input type="text" name="id" size="20"
					value="<%=pedido.getIdPedido()%>" readonly="readonly"
					onkeypress="return false;" disabled></td>
			</tr>

			<tr>
				<td>Cliente: &nbsp;&nbsp;</td>
				<td><input type="text" name="cliente" size="20"
					value="<%=pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellidos()%>"
					disabled></td>
			</tr>

			<tr>
				<td>Fecha: &nbsp;&nbsp;</td>
				<td><input type="text" name="fecha" size="20"
					value="<%=Utility.fechaATexto(pedido.getFecha())%>" disabled></td>
			</tr>

			<tr>
				<td>Forma de pago: &nbsp;&nbsp;</td>
				<td><input type="text" name="formaPago" size="20"
					value="<%=pedido.getFormaPago()%>" disabled></td>
			</tr>

			<tr>
				<td>Gastos de envío: &nbsp;&nbsp;</td>
				<td><input type="text" name="envio" size="20"
					value="<%=pedido.getEnvio()%>" disabled></td>
			</tr>

			<tr>
				<td>Total: &nbsp;&nbsp;</td>
				<td><input type="text" name="total" size="20"
					value="<%=pedido.getTotalPedido()%>" disabled></td>
			</tr>

			<tr>
				<td>Estado:&nbsp;&nbsp;</td>
				<td><select class="select-css" name="estado">
						<%
							if (pedido.getEstado().equals("Pendiente")) {
								out.println("<option selected value='Pendiente'>Pendiente</option>");
								out.println("<option value='En proceso'>En proceso</option>");
								out.println("<option value='Enviado'>Enviado</option>");
							} else if (pedido.getEstado().equals("En proceso")) {
								out.println("<option value='Pendiente'>Pendiente</option>");
								out.println("<option selected value='En proceso'>En proceso</option>");
								out.println("<option value='Enviado'>Enviado</option>");
							} else if (pedido.getEstado().equals("Enviado")) {
								out.println("<option value='Pendiente'>Pendiente</option>");
								out.println("<option value='En proceso'>En proceso</option>");
								out.println("<option selected value='Enviado'>Enviado</option>");
							}
						%>
				</select></td>
			</tr>

		</table>

		<h3>RESUMEN DEL PEDIDO</h3>

		<table id="tblData2">
			<thead>
				<tr class="cabeceraTabla">
					<td>N. Producto Pedido</td>
					<td>Nombre</td>
					<td>Cantidad</td>
					<td>Precio (€)</td>
					<td>Acciones</td>
				</tr>
			</thead>
			<tbody>
				<%
					// Scriplet: Cargo los detalles del producto
					List<DetallesPedido> listadoDetalles = new ArrayList<DetallesPedido>();
					if (request.getAttribute("listado") != null) {
						listadoDetalles = (List<DetallesPedido>) request.getAttribute("listado");
					}
					if (!listadoDetalles.isEmpty()) {
						Iterator<DetallesPedido> i = listadoDetalles.iterator();
						while (i.hasNext()) {
							DetallesPedido detalleProducto = i.next();
							out.println("<tr><td class='campoId'>" + detalleProducto.getIdProducto() + "</td>");
							out.println("<td>" + detalleProducto.getNombre() + "</td>");
							out.println("<td class='data'>" + detalleProducto.getCantidad() + "</td>");
							out.println("<td>" + detalleProducto.getPrecio() + "</td>");
							out.println(
									"<td><button class='save'><i class='fas fa-save'></i></button><button class='edit'><i class='fas fa-edit'></i></button><button class='delete'><i class='fas fa-trash'></i></button></td></tr>");
						}
					} else {
						out.println("<tr><td colspan=5> No existen productos </td></tr>");
					}
				%>
						
			</tbody>
		</table>
		
		<table id="tblData">
			<thead>
				<tr class="cabeceraTabla">
					<td>Subtotal</td>
					<td>IVA (%)</td>
					<td>Total</td>
				</tr>
			</thead>
			<tbody>
		
				<%
					Float subtotal = (Float)request.getAttribute("subtotal");
					Float iva = (Float)request.getAttribute("iva");
				%>
	
				<tr>
					<th class="cantidadTotal" ><%=String.format("%.2f", subtotal)%> &nbsp;€</th>
					<th class="cantidadTotal" ><%=String.format("%.2f", iva)%> &nbsp;€</th>
					<th class="cantidadTotal" ><%=String.format("%.2f", Float.parseFloat(pedido.getTotalPedido()))%> &nbsp;€</th>
				</tr>
			</tbody>
		</table>

		<button class="btnEditar" title="Editar pedido" type="submit"
			onclick="submitModificarPedido();">
			<i class="fas fa-edit"> <span class="txtBtn">Editar pedido
			</span></i>
		</button>

		<button class="btnExcel" title="Imprimir listado" type="submit"
			onclick="exportTableToExcel('tblData', 'Detalles_Pedido')">
			<i class="fas fa-print"> <span class="txtBtnBlanco">Imprimir
					detalle </span></i>
		</button>

		<button class="btnBuscar" title="Volver" type="submit"
			onclick="submitVolver();">
			<i class="fas fa-arrow-circle-left"> <span class="txtBtnBlanco">Volver
			</span></i>
		</button>

	</form>
