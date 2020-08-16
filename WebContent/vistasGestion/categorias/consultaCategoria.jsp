<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.modelos.categorias.Categoria"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<% if(request.getAttribute("mensaje") !=null){ %>
	<script> 
	var mensaje = '<%= request.getAttribute("mensaje") %>';
	mensajeAlert(mensaje);
	</script>
<% } %>

<form id="CategoriaForm" class="estiloForm"
	action="/Proyecto/categoriaConsulta" method="POST"
	onsubmit="return false;">

	<h2>DATOS CATEGORÍA</h2>

	<table>

		<tr class="cabeceraTabla">
			<td>ID.</td>
			<td>Nombre</td>
			<td>Imagen</td>
			<td>Acciones</td>
		</tr>
		<%
			// Scriplet: Cargo los detalles del cliente
			List<Categoria> listado = new ArrayList<Categoria>();
			if (request.getAttribute("listadoCategoria") != null) {
				listado = (List<Categoria>) request.getAttribute("listadoCategoria");
			}
			if (!listado.isEmpty()) {
				Iterator<Categoria> i = listado.iterator();
				while (i.hasNext()) {
					Categoria detalle = i.next();
					out.println("<tr><td>" + detalle.getIdCategoria() + "</td>");
					out.println("<td>" + detalle.getNombre() + "</td>");
					out.println("<td>" + detalle.getImagen() + "</td>");

					out.println(
							"<td><button class='btnAcciones' title='Editar' type='submit' onclick=submitEditarCategoria("
									+ detalle.getIdCategoria()
									+ ")><i class='fas fa-edit'></i></button><button class='btnAcciones' title='Eliminar' type='submit' onclick=\"mensajeConfirm('¿Desea eliminar la categoría?', function() {submitEliminarCategoria("
									+ detalle.getIdCategoria()
									+ ")})\"><i class='fas fa-trash-alt'></i></button></td></tr>");
				}
			} else {
				out.println("<tr><td colspan=9> No existen categorías </td></tr>");
			}
		%>
	</table>
</form>

