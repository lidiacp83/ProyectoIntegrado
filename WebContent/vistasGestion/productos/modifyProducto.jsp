<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	action="/Proyecto/productoModificar" method="POST"
	onsubmit="return false">

	<h2>MODIFICAR PRODUCTO</h2>

	<table class="contentTable">
		<%
			Producto producto = new Producto();
			if (request.getAttribute("producto") != null) {
				producto = (Producto) request.getAttribute("producto");
			}
		%>

		<tr>
			<td>Id. Producto: &nbsp;&nbsp;</td>
			<td><input type="text"  name="id" size="20"
				value="<%=producto.getIdProducto()%>" readonly="readonly"
				onkeypress="return false;" required disabled></td>
		</tr>


		<tr>
			<td>Nombre: &nbsp;&nbsp;</td>
			<td><input type="text" name="nombre" size="20"
				value="<%=producto.getNombre()%>" required></td>
		</tr>

		<tr>
			<td>Descripción: &nbsp;&nbsp;</td>
			<td><input class="descripcion" type="text" name="descripcion"
				size="30" height="60px" value="<%=producto.getDescripcion()%>" required></td>
		</tr>

		<tr>
			<td>Precio: &nbsp;&nbsp;</td>
			<td><input type="text" name="precio" size="30"
				value="<%=producto.getPrecio()%>" required></td>
		</tr>

		<tr>
			<td>IVA: &nbsp;&nbsp;</td>
			<td><input type="text" name="iva" size="30"
				value="<%=producto.getIva()%>" required></td>
		</tr>

		<tr>
			<td>Descuento: &nbsp;&nbsp;</td>
			<td><input type="text" name="rebaja" size="30"
				value="<%=producto.getRebaja()%>" required></td>
		</tr>

		<tr>
			<td>Stock: &nbsp;&nbsp;</td>
			<td><input type="text" name="stock" size="30"
				value="<%=producto.getStock()%>" required></td>
		</tr>

		<tr>
			<td>Stock mínimo: &nbsp;&nbsp;</td>
			<td><input type="text" name="stockMinimo" size="30"
				value="<%=producto.getStockMinimo()%>" required></td>
		</tr>

		<tr>
			<td>Peso: &nbsp;&nbsp;</td>
			<td><input type="text" name="peso" size="30"
				value="<%=producto.getPeso()%>" required></td>
		</tr>

		<tr>
			<td>Sexo: &nbsp;&nbsp;</td>
			<td><select class="select-css" name="sexo">
					<%
						if (producto.getSexo().equals("niño")) {
							out.println("<option value='niña'>Niña</option>");
							out.println("<option selected value='niño'>Niño</option>");
						} else {
							out.println("<option selected value='niña'>Niña</option>");
							out.println("<option value='niño'>Niño</option>");
						}
					%>
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
								if (detalle.getIdCategoria().equals(producto.getIdcategoriaFK())) {
									out.println("<option selected value='" + detalle.getIdCategoria() + "'>");
								} else {
									out.println("<option value='" + detalle.getIdCategoria() + "'>");
								}
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
					class="fas fa-cloud-upload-alt"></i> Cargar imagen
			</label> <input id="imagen" onchange='cambiar()' type="file" name="imagen"
				style="display: none;"/></td>
		</tr>

		<tr>
			<td><div id="info"></div></td>
			<td><img id="imagenData" name="imagenData"
				src="<%=producto.getImagenData()%>" /></td>
		</tr>

	</table>

	<button class="btnEditar" title="Editar producto" type="submit"
		onclick="submitModificarProducto(<%=producto.getIdProducto()%>);">
		<i class="fas fa-edit"> <span class="txtBtn">Editar
				producto </span></i>
	</button>

	<button class="btnBuscar" title="Volver" type="submit"
		onclick="submitVolver();">
		<i class="fas fa-arrow-circle-left"> <span class="txtBtnBlanco">Volver
		</span></i>
	</button>

</form>

<script src="js/producto.js"></script>
