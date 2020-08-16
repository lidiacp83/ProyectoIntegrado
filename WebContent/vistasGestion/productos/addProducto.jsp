<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="es.modelos.productos.Producto"%>
<%@page import="es.modelos.categorias.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>


	<% if(request.getAttribute("mensaje") !=null){ %>
		<script> 
		var mensaje = '<%= request.getAttribute("mensaje") %>';
		mensajeAlert(mensaje);
		</script>
	<% } %>

	<form id="ProductoForm" class="estiloFormBorderNo"
		action="/Proyecto/productoAlta" method="POST" onsubmit="return false;">

		<h2>AÑADIR PRODUCTO</h2>

		<table class="contentTable">

			<tr>
				<td>Nombre: &nbsp;&nbsp;</td>
				<td><input type="text" name="nombre" size="20" value="" required></td>
			</tr>

			<tr>
				<td>Descripción: &nbsp;&nbsp;</td>
				<td><input class="descripcion" type="text" name="descripcion"
					size="30" height="60px" value="" required></td>
			</tr>

			<tr>
				<td>Precio: &nbsp;&nbsp;</td>
				<td><input type="text" name="precio" size="30" value="" required></td>
			</tr>

			<tr>
				<td>IVA: &nbsp;&nbsp;</td>
				<td><input type="text" name="iva" size="30" value="" required></td>
			</tr>

			<tr>
				<td>Descuento: &nbsp;&nbsp;</td>
				<td><input type="text" name="rebaja" size="30" value="" required></td>
			</tr>

			<tr>
				<td>Stock: &nbsp;&nbsp;</td>
				<td><input type="text" name="stock" size="30" value="" required></td>
			</tr>

			<tr>
				<td>Stock mínimo: &nbsp;&nbsp;</td>
				<td><input type="text" name="stockMinimo" size="30" value="" required></td>
			</tr>


			<tr>
				<td>Peso: &nbsp;&nbsp;</td>
				<td><input type="text" name="peso" size="30" value="" required></td>
			</tr>

			<tr>
				<td>Sexo: &nbsp;&nbsp;</td>
				<td><select class="select-css" name="sexo">
						<option value="niña">Niña</option>
						<option value="niño">Niño</option>
				</select></td>
			</tr>

			<tr>
				<td>Categoría:&nbsp;&nbsp;</td>
				<td><select class="select-css" name="selectCategoria">
						<%
							// Scriplet 1: Carga los autores en el SELECT
							List<Categoria> listado = new ArrayList<Categoria>();
							if (request.getAttribute("listadoCategoria") != null) {
								listado = (List<Categoria>) request.getAttribute("listadoCategoria");
							}
							if (!listado.isEmpty()) {
								Iterator<Categoria> i = listado.iterator();
								while (i.hasNext()) {
									Categoria detalle = i.next();
									out.println("<option value='" + detalle.getIdCategoria() + "'>");
									out.println(detalle.getNombre());
									out.println("</option>");
								}
							}
						%>
				</select></td>
			</tr>

			<tr>
				<td>Imagen: &nbsp;&nbsp;</td>
				<td><label for="imagen" class="subir"> <i
						class="fas fa-cloud-upload-alt" required></i> Cargar imagen
				</label> <input id="imagen" onchange='cambiar()' type="file" name="imagen"
					style="display: none;" required/></td>
			</tr>

			<tr>
				<td><div id="info"></div></td>
				<td><img id="imagenData" name="imagenData" /></td>
			</tr>

		</table>

		<button class="btnAdd" title="Añadir producto" type="submit"
			onclick="submitAddProducto();">
			<i class="fas fa-plus-circle"> <span class="txtBtn">Añadir
					producto </span></i>
		</button>

	</form>

	<br>
	<br>
	
	<script src="js/producto.js"></script>
