<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="es.modelos.categorias.Categoria"%>

	<% if(request.getAttribute("mensaje") !=null){ %>
		<script> 
		var mensaje = '<%= request.getAttribute("mensaje") %>';
		mensajeAlert(mensaje);
		</script>
	<% } %>

	<form id="CategoriaForm" class="estiloFormBorderNo"
		action="/Proyecto/categoriaModificar" method="POST"
		onsubmit="return false">

		<h2>MODIFICAR CATEGORÍA</h2>

		<table class="contentTable">

			<%
				Categoria categoria = new Categoria();
				if (request.getAttribute("categoria") != null) {
					categoria = (Categoria) request.getAttribute("categoria");
				}
			%>

			<tr>
				<td>Id. Categoría: &nbsp;&nbsp;</td>
				<td><input type="text"  name="id" size="20"
					value="<%=categoria.getIdCategoria()%>" readonly="readonly"
					onkeypress="return false;" disabled></td>
			</tr>

			<tr>
				<td>Nombre Categoría: &nbsp;&nbsp;</td>
				<td><input type="text" name="nombre" size="20"
					value="<%=categoria.getNombre()%>"></td>
			</tr>

			<tr>
				<td>Imagen: &nbsp;&nbsp;</td>
				<td><label for="imagen" class="subir"> <i
						class="fas fa-cloud-upload-alt"></i> Cargar imagen
				</label> <input id="imagen" onchange='cambiar()' type="file" name="imagen"
					style="display: none;" /></td>
			</tr>

			<tr>
				<td><div id="info"></div></td>
				<td><img id="imagenData" name="imagenData"
					src="<%=categoria.getImagenData()%>" /></td>
			</tr>

		</table>

		<button class="btnEditar" title="Editar categoría" type="submit"
			onclick="submitModificarCategoria(<%=categoria.getIdCategoria()%>);">
			<i class="fas fa-edit"> <span class="txtBtn">Editar
					categoría </span></i>
		</button>

		<button class="btnBuscar" title="Volver" type="submit"
			onclick="submitVolver();">
			<i class="fas fa-arrow-circle-left"> <span class="txtBtnBlanco">Volver
			</span></i>
		</button>

	</form>

	<script src="js/categoria.js"></script>
