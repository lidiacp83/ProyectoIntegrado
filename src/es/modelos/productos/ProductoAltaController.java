package es.modelos.productos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.modelos.categorias.Categoria;
import es.modelos.categorias.ModeloCategorias;
import es.utilidades.Utility;

@WebServlet(name = "ProductoAltaController", urlPatterns = { "/productoAlta" })
public class ProductoAltaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductoAltaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (Utility.comprobarLoginAdmin(request)) {
			doPost(request, response);
		} else {
			String nextPage = "/vistasGestion/login.jsp";
			ServletContext servletContext = getServletContext();
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextPage = "";
		String todo = request.getParameter("todo");

		List<Categoria> listadoCategoria = new ArrayList<Categoria>();
		listadoCategoria = ModeloCategorias.consultaCategoria();
		request.setAttribute("listadoCategoria", listadoCategoria);
		if (todo == null) {
			nextPage = "/vistasGestion/productos/addProducto.jsp";
		} else if (todo.equals("alta")) {
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			String[] imagenPath = request.getParameter("imagen").split(Pattern.quote(File.separator));
			String imagen = imagenPath[imagenPath.length - 1];
			String imagenData = request.getParameter("imagenData").replaceAll(" ", "+");
			String precio = request.getParameter("precio");
			String iva = request.getParameter("iva");
			String rebaja = request.getParameter("rebaja");
			String stock = request.getParameter("stock");
			String stockMinimo = request.getParameter("stockMinimo");
			String sexo = request.getParameter("sexo");
			String peso = request.getParameter("peso");
			String idCategoriaFK = request.getParameter("selectCategoria");
			
			ModeloProductos.altaProducto(nombre, descripcion, imagen, imagenData, precio, iva, rebaja, stock,
					stockMinimo, sexo, peso, idCategoriaFK);
			String mensaje = "El alta del producto se ha realizado correctamente";
			request.setAttribute("mensaje", mensaje);
			nextPage = "/vistasGestion/productos/addProducto.jsp";
		}

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}