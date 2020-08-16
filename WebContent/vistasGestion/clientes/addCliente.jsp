<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.modelos.clientes.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>

<script src="js/mensajes.js"></script>

<% if(request.getAttribute("mensaje") !=null){ %>
	<script> 
	var mensaje = '<%= request.getAttribute("mensaje") %>';
	mensajeAlert(mensaje);
	</script>
<% } %>

<form id="ClienteForm" class="estiloFormBorderNo"
	action="/Proyecto/clienteAlta" method="POST" onsubmit="return false;">

	<h2>AÑADIR CLIENTE</h2>

	<table class="contentTable">
		<tr>
			<td>Nombre: &nbsp;&nbsp;</td>
			<td><input type="text" id="nombre" name="nombre" size="20"
				value="" required /></td>
		</tr>

		<tr>
			<td>Apellidos: &nbsp;&nbsp;</td>
			<td><input type="text" name="apellidos" size="30" height="60px"
				value="" required></td>
		</tr>

		<tr>
			<td>Dirección: &nbsp;&nbsp;</td>
			<td><input type="text" name="direccion" size="30" height="60px"
				value="" required></td>
		</tr>

		<tr>
			<td>Código Postal: &nbsp;&nbsp;</td>
			<td><input type="text" name="cpostal" size="30" value=""
				required></td>
		</tr>

		<tr>
			<td>Localidad: &nbsp;&nbsp;</td>
			<td><input type="text" name="localidad" size="30" value=""
				required></td>
		</tr>

		<tr>
			<td>Provincia: &nbsp;&nbsp;</td>
			<td><input type="text" name="provincia" size="30" value=""
				required></td>
		</tr>

		<tr>
			<td>Email: &nbsp;&nbsp;</td>
			<td><input type="text" name="email" size="30" value="" required></td>
		</tr>

		<tr>
			<td>Teléfono: &nbsp;&nbsp;</td>
			<td><input type="text" name="telefono" size="30" value=""
				required></td>
		</tr>

		<tr>
			<td>Tipo perfil: &nbsp;&nbsp;</td>
			<td><select class="select-css" name="perfil">
					<option value="1">Administrador</option>
					<option value="2">Cliente</option>
			</select></td>
		</tr>

		<tr>
			<td>Clave: &nbsp;&nbsp;</td>
			<td><input type="text" id="clave" name="clave" size="30"
				value="" required></td>
			<td><button class="btnKeyGen1" name="btnclave"
					onclick="generarClave(8);">
					<i class="fas fa-key"> <span class="txtBtnBlanco">Generar
							clave</span></i>
				</button></td>
		</tr>

	</table>

	<button class="btnAdd" title="Añadir cliente" type="submit"
		onclick="submitAddCliente();">
		<i class="fas fa-plus-circle"> <span class="txtBtn">Añadir
				cliente </span></i>
	</button>

</form>

