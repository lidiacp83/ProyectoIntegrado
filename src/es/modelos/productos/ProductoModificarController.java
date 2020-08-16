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

@WebServlet(name = "ProductoModificarController", urlPatterns = { "/productoModificar" })
public class ProductoModificarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductoModificarController() {
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

		Producto producto = new Producto();
		List<Categoria> listadoCategoria = new ArrayList<Categoria>();
		listadoCategoria = ModeloCategorias.consultaCategoria();
		request.setAttribute("listadoCategoria", listadoCategoria);
		String nextPage = "";
		String todo = request.getParameter("todo");

		if (todo == null) {
			String id = request.getParameter("id");
			producto = ModeloProductos.obtenerProducto(id);
		} else if (todo.equals("modificar")) {
			String id = request.getParameter("id");
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			String[] imagenPath = request.getParameter("imagen").split(Pattern.quote(File.separator));
			String imagen = "";
			if (imagenPath == null || imagenPath[0].equals("")) {
				producto = ModeloProductos.obtenerProducto(id);
				imagen = producto.getImagen();
			} else {
				imagen = imagenPath[imagenPath.length - 1];
			}
			String imagenData = request.getParameter("imagenData").replaceAll(" ", "+");
			String precio = request.getParameter("precio");
			String iva = request.getParameter("iva");
			String rebaja = request.getParameter("rebaja");
			String stock = request.getParameter("stock");
			String stockMinimo = request.getParameter("stockMinimo");
			String sexo = request.getParameter("sexo");
			String peso = request.getParameter("peso");
			String idCategoriaFK = request.getParameter("selectCategoria");
			ModeloProductos.modificarProductos(id, nombre, descripcion, imagen, imagenData, precio, iva, rebaja, stock,
					stockMinimo, sexo, peso, idCategoriaFK);
			producto = ModeloProductos.obtenerProducto(id);
			String mensaje = "El producto se ha modificado correctamente";
			request.setAttribute("mensaje", mensaje);
		}

		request.setAttribute("producto", producto);

		nextPage = "/vistasGestion/productos/modifyProducto.jsp";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}