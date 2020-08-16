<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% if(request.getAttribute("mensaje") !=null){ %>
	<script> 
	var mensaje = '<%= request.getAttribute("mensaje") %>';
	mensajeAlert(mensaje);
	</script>
<% } %>

<form id="CategoriaForm" class="estiloFormBorderNo" action="/Proyecto/categoriaAlta" method="POST" onsubmit="return false;">
	<h2>AÑADIR CATEGORÍA</h2>
	<table class="contentTable">
		<tr>
			<td>Nombre Categoría: &nbsp;&nbsp;</td>
			<td><input type="text" name="nombre" size="20" value="" required><br></td>
		</tr>
		<tr>
			<td>Imagen: &nbsp;&nbsp;</td>
			<td><label for="imagen" class="subir"> <i class="fas fa-cloud-upload-alt"></i> Cargar imagen</label>
				<input id="imagen" onchange='cambiar()' type="file" name="imagen"style="display: none;"/></td>
		</tr>
		<tr>
			<td id="info"></td>
			<td><img id="imagenData" name="imagenData" /></td>
		</tr>
	</table>

	<button class="btnAdd" title="Añadir categoría" onclick="submitAddCategoria();">
		<i class="fas fa-plus-circle"> <span class="txtBtn" >Añadir categoría </span></i>
	</button>
</form>

<script src="js/categoria.js"></script>