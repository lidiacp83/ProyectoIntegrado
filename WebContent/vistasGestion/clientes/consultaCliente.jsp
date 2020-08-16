<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.modelos.clientes.Cliente"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<% if(request.getAttribute("mensaje") !=null){ %>
	<script> 
		var mensaje = '<%= request.getAttribute("mensaje") %>';
		mensajeAlert(mensaje);
		</script>
	<% } %>

	<form id="ClienteForm" class="estiloForm"
		action="/Proyecto/clienteConsulta" method="POST"
		onsubmit="return false;">

		<h2>DATOS CLIENTE</h2>

		<select name="selectCliente" class="select-css">
			<option value="clienteId">Id</option>
			<option value="clienteNombre" selected>Nombre</option>
			<option value="clienteApellidos">Apellidos</option>
			<option value="clienteDireccion">Dirección</option>
			<option value="clienteCodigoPostal">Código Postal</option>
			<option value="clienteLocalidad">Localidad</option>
			<option value="clienteProvincia">Provincia</option>
			<option value="clienteEmail">Email</option>
			<option value="clienteTelefono">Teléfono</option>
		</select> 
		
		<input name="txtBuscar" type="text" value="">&nbsp; &nbsp;
		<button class="btnBuscar" title="Buscar" type="submit"
			onclick="submitBuscarCliente();">
			<i class="fas fa-search"> <span class="txtBtnBlanco">Buscar
			</span></i>
		</button>
		
		<button class="btnExcel" title="Imprimir listado" type="submit"
			onclick="exportTableToExcel('tblData', 'Listado_Clientes')">
			<i class="fas fa-file-excel"> <span class="txtBtnBlanco">Generar Excel </span></i>
		</button>

		<table id="tblData">
			<tr class="cabeceraTabla">
				<td>ID.</td>
				<td>Nombre</td>
				<td>Apellidos</td>
				<td>Dirección</td>
				<td>C.Postal</td>
				<td>Localidad</td>
				<td>Provincia</td>
				<td>Email</td>
				<td>Teléfono</td>
				<td>Acciones</td>
			</tr>
			<%
				// Scriplet: Cargo los detalles del cliente
				List<Cliente> listado = new ArrayList<Cliente>();
				if (request.getAttribute("listadoCliente") != null) {
					listado = (List<Cliente>) request.getAttribute("listadoCliente");
				}
				if (!listado.isEmpty()) {
					Iterator<Cliente> i = listado.iterator();
					while (i.hasNext()) {
						Cliente detalle = i.next();
						out.println("<tr><td>" + detalle.getIdCliente() + "</td>");
						out.println("<td>" + detalle.getNombre() + "</td>");
						out.println("<td>" + detalle.getApellidos() + "</td>");
						out.println("<td>" + detalle.getDireccion() + "</td>");
						out.println("<td>" + detalle.getCodigoPostal() + "</td>");
						out.println("<td>" + detalle.getLocalidad() + "</td>");
						out.println("<td>" + detalle.getProvincia() + "</td>");
						out.println("<td>" + detalle.getEmail() + "</td>");
						out.println("<td>" + detalle.getTelefono() + "</td>");
						out.println(
								"<td><button class='btnAcciones' title='Editar' type='submit' onclick=submitEditarCliente("
										+ detalle.getIdCliente() + ")><i class='fas fa-user-edit'></i></button>"
										+ "<button class='btnAcciones' title='Eliminar' type='submit' onclick=\"mensajeConfirm('¿Desea eliminar el cliente?' , function() {submitEliminarCliente("
										+ detalle.getIdCliente()
										+ ")})\"><i class='fas fa-trash-alt'></i></button></td></tr>");
					}
				} else {
					out.println("<tr><td colspan=10> No existen clientes </td></tr>");
				}
			%>
		</table>
	</form>
