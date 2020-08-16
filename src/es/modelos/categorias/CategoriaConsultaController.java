package es.modelos.categorias;

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

@WebServlet(name = "CategoriaConsultaController", urlPatterns = { "/categoriaConsulta" })
public class CategoriaConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoriaConsultaController() {
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

		List<Categoria> listado = new ArrayList<Categoria>();
		String nextPage = "";
		String todo = request.getParameter("todo");

		if (todo == null) {
			listado = ModeloCategorias.consultaCategoria();
			nextPage = "/vistasGestion/categorias/consultaCategoria.jsp";
		} else if (todo.equals("eliminar")) {
			String mensaje = "";
			String id = request.getParameter("id");
			// Comprobamos la categoría tiene productos en pedidos
			int pedidos = ModeloCategorias.comprobarProductoEnCategoria(id);
			if (pedidos == 0) {
				ModeloCategorias.bajaProductosCategoria(id);
				ModeloCategorias.bajaCategoria(id);
				mensaje = "La categoría y sus productos se han eliminado correctamente";

			} else {
				mensaje = "Hay productos de la categoría incluídos en pedidos. La categoría no se puede eliminar.";

			}
			request.setAttribute("mensaje", mensaje);
			listado = ModeloCategorias.consultaCategoria();
			nextPage = "/vistasGestion/categorias/consultaCategoria.jsp";
		}

		request.setAttribute("listadoCategoria", listado);

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
