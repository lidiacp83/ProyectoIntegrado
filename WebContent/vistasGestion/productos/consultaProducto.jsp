<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.modelos.productos.Producto"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<% if(request.getAttribute("mensaje") !=null){ %>
		<script> 
		var mensaje = '<%= request.getAttribute("mensaje") %>';
		mensajeAlert(mensaje);
		</script>
	<% } %>

	<form id="ProductoForm" class="estiloForm"
		action="/Proyecto/productoConsulta" method="POST"
		onsubmit="return false;">

		<h2>AÑADIR PRODUCTO</h2>

		<select name="selectProducto" class="select-css">
			<option value="productoId">Id</option>
			<option value="productoNombre" selected>Nombre</option>
			<option value="productoPrecio">Precio</option>
			<option value="productoDescripcion">Descripción</option>
			<option value="productoStock">Stock</option>
			<option value="productoSexo">Sexo</option>
			<option value="categoriaNombre">Categoría</option>
		</select> <input name="txtBuscar" type="text" value="">&nbsp; &nbsp;
		<button class="btnBuscar" title="Buscar" type="submit"
			onclick="submitBuscarProducto();">
			<i class="fas fa-search"> <span class="txtBtnBlanco">Buscar
			</span></i>
		</button>
		<button class="btnExcel" title="Imprimir listado" type="submit"
			onclick="exportTableToExcel('tblData', 'Listado_Productos')">
			<i class="fas fa-file-excel"> <span class="txtBtnBlanco">Generar
					listado </span></i>
		</button>


		<table id="tblData">
			<tr class="cabeceraTabla">
				<td>ID.</td>
				<td>Nombre</td>
				<td>Descripción</td>
				<td>Imagen</td>
				<td>Precio</td>
				<td>IVA %</td>
				<td>Descuento</td>
				<td>Stock</td>
				<td>Sexo</td>
				<td>Peso</td>
				<td>Categoría</td>
				<td>Acciones</td>
			</tr>
			<%
				// Scriplet: Cargo los detalles del pedido
				List<Producto> listado = new ArrayList<Producto>();
				if (request.getAttribute("listadoProducto") != null) {
					listado = (List<Producto>) request.getAttribute("listadoProducto");
				}
				if (!listado.isEmpty()) {
					Iterator<Producto> i = listado.iterator();
					while (i.hasNext()) {
						Producto detalle = i.next();
						out.println("<tr><td>" + detalle.getIdProducto() + "</td>");
						out.println("<td>" + detalle.getNombre() + "</td>");
						out.println("<td>" + detalle.getDescripcion() + "</td>");
						out.println("<td>" + detalle.getImagen() + "</td>");
						out.println("<td>" + detalle.getPrecio() + " €</td>");
						out.println("<td>" + detalle.getIva() + " %</td>");
						out.println("<td>" + detalle.getRebaja() + " €</td>");
						out.println("<td>" + detalle.getStock() + "</td>");
						out.println("<td>" + detalle.getSexo() + "</td>");
						out.println("<td>" + detalle.getPeso() + " Kg.</td>");
						out.println("<td>" + detalle.getCategoria().getNombre() + "</td>");
						out.println(
								"<td><button class='btnAcciones' title='Editar' type='submit' onclick=submitEditarProducto("
										+ detalle.getIdProducto()
										+ ")><i class='fas fa-edit'></i></button><button class='btnAcciones' title='Eliminar' type='submit' onclick=\"mensajeConfirm('¿Desea eliminar el producto?', function(){submitEliminarProducto("
										+ detalle.getIdProducto()
										+ ")})\"><i class='fas fa-trash-alt'></i></button></td></tr>");
					}
				} else {
					out.println("<tr><td colspan=12> No existen productos </td></tr>");
				}
			%>
		</table>
	</form>
	<script src="js/producto.js"></script>