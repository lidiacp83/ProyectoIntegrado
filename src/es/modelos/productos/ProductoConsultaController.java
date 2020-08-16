package es.modelos.productos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.utilidades.Utility;

@WebServlet(name = "ProductoConsultaController", urlPatterns = { "/productoConsulta" })
public class ProductoConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductoConsultaController() {
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

		List<Producto> listado = new ArrayList<Producto>();
		String nextPage = "";
		String todo = request.getParameter("todo");

		if (todo == null) {
			listado = ModeloProductos.consultaProductos();
			nextPage = "/vistasGestion/productos/consultaProducto.jsp";
		} else if (todo.equals("buscar")) {
			String select = request.getParameter("selectProducto");
			String txtBuscar = request.getParameter("txtBuscar");
			listado = ModeloProductos.consultaProductosFiltro(select, txtBuscar);
			nextPage = "/vistasGestion/productos/consultaProducto.jsp";
		} else if (todo.equals("eliminar")) {
			String mensaje = "";
			String id = request.getParameter("id");
			// Comprobamos si el producto está en pedidos antes de eliminarlo
			int pedidos = ModeloProductos.comprobarProductoEnPedidos(id);
			if (pedidos == 0) {
				ModeloProductos.bajaProducto(id);
				mensaje = "El producto se ha eliminado correctamente";
				request.setAttribute("mensaje", mensaje);
			} else {
				mensaje = "El producto se encuentra en algunos pedidos y no puede ser eliminado";
				request.setAttribute("mensaje", mensaje);
			}
			listado = ModeloProductos.consultaProductos();
			nextPage = "/vistasGestion/productos/consultaProducto.jsp";
		}
		request.setAttribute("listadoProducto", listado);

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}
