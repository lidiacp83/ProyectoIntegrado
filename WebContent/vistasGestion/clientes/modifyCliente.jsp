<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="es.modelos.clientes.Cliente"%>


<% if(request.getAttribute("mensaje") !=null){ %>
<script> 
	var mensaje = '<%= request.getAttribute("mensaje") %>';
	mensajeAlert(mensaje);
	</script>
<% } %>

<form id="ClienteForm" class="estiloFormBorderNo"
	action="/Proyecto/clienteModificar" method="POST"
	onsubmit="return false">

	<h2>MODIFICAR CLIENTE</h2>

	<table class="contentTable">

		<%
				Cliente cliente = new Cliente();
				if (request.getAttribute("cliente") != null) {
					cliente = (Cliente) request.getAttribute("cliente");
				}
			%>

		<tr>
			<td>Id. Cliente: &nbsp;&nbsp;</td>
			<td><input type="text" name="id" size="20"
				value="<%=cliente.getIdCliente()%>" readonly="readonly"
				onkeypress="return false;" disabled></td>
		</tr>

		<tr>
			<td>Nombre: &nbsp;&nbsp;</td>
			<td><input type="text" name="nombre" size="20"
				value="<%=cliente.getNombre()%>" required></td>
		</tr>

		<tr>
			<td>Apellidos: &nbsp;&nbsp;</td>
			<td><input type="text" name="apellidos" size="30" height="60px"
				value="<%=cliente.getApellidos()%>" required></td>
		</tr>

		<tr>
			<td>Direccion: &nbsp;&nbsp;</td>
			<td><input type="text" name="direccion" size="30"
				value="<%=cliente.getDireccion()%>" required></td>
		</tr>

		<tr>
			<td>Código Postal: &nbsp;&nbsp;</td>
			<td><input type="text" name="codigoPostal" size="30"
				value="<%=cliente.getCodigoPostal()%>" required></td>
		</tr>

		<tr>
			<td>Localidad: &nbsp;&nbsp;</td>
			<td><input type="text" name="localidad" size="30"
				value="<%=cliente.getLocalidad()%>" required></td>
		</tr>

		<tr>
			<td>Provincia: &nbsp;&nbsp;</td>
			<td><input type="text" name="provincia" size="30"
				value="<%=cliente.getProvincia()%>" required></td>
		</tr>

		<tr>
			<td>Email: &nbsp;&nbsp;</td>
			<td><input type="text" name="email" size="30"
				value="<%=cliente.getEmail()%>" required></td>
		</tr>

		<tr>
			<td>Teléfono: &nbsp;&nbsp;</td>
			<td><input type="text" name="telefono" size="30"
				value="<%=cliente.getTelefono()%>" required></td>
		</tr>

		<tr>
			<td>Tipo perfil: &nbsp;&nbsp;</td>
			<td><select class="select-css" name="perfil">
					<%
							if (cliente.getPerfilIdFK().equals("2")) {
								out.println("<option value='1'>Administrador</option>");
								out.println("<option selected value='2'>Cliente</option>");
							} else {
								out.println("<option selected value='1'>Administrador</option>");
								out.println("<option value='2'>Cliente</option>");
							}
						%>
			</select></td>
		</tr>

		<tr>
			<td>Clave: &nbsp;&nbsp;</td>
			<td><input type="text" id="clave" name="clave" size="30"
				value="<%=cliente.getClave()%>" required></td>
			<td><button class="btnKeyGen2" name="btnclave"
					onclick="generarClave(8);">
					<i class="fas fa-key"> <span class="txtBtnBlanco">Generar
							clave</span></i>
				</button></td>
		</tr>

	</table>

	<button class="btnEditar" title="Editar cliente" type="submit"
		onclick="submitModificarCliente(<%=cliente.getIdCliente()%>);">
		<i class="fas fa-edit"> <span class="txtBtn">Editar cliente
		</span></i>
	</button>

	<button class="btnBuscar" title="Volver" type="submit"
		onclick="submitVolver();">
		<i class="fas fa-arrow-circle-left"> <span class="txtBtnBlanco">Volver
		</span></i>
	</button>
</form>

