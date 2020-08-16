package es.modelos.categorias;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.utilidades.Utility;

@WebServlet(name = "CategoriaModificarController", urlPatterns = { "/categoriaModificar" })
public class CategoriaModificarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoriaModificarController() {
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

		Categoria categoria = new Categoria();
		String nextPage = "";
		String todo = request.getParameter("todo");

		if (todo == null) {
			String id = request.getParameter("id");
			categoria = ModeloCategorias.obtenerCategoria(id);
		} else if (todo.equals("modificar")) {
			String id = request.getParameter("id");
			String nombre = request.getParameter("nombre");
			String[] imagenPath = request.getParameter("imagen").split(Pattern.quote(File.separator));
			String imagen = "";
			if (imagenPath == null || imagenPath[0].equals("")) {
				categoria = ModeloCategorias.obtenerCategoria(id);
				imagen = categoria.getImagen();
			} else {
				imagen = imagenPath[imagenPath.length - 1];
			}
			String imagenData = request.getParameter("imagenData").replaceAll(" ", "+");
			ModeloCategorias.modificarCategoria(id, nombre, imagen, imagenData);
			categoria = ModeloCategorias.obtenerCategoria(id);
			String mensaje = "La categoría se ha modificado correctamente";
			request.setAttribute("mensaje", mensaje);
		}

		request.setAttribute("categoria", categoria);

		nextPage = "/vistasGestion/categorias/modifyCategoria.jsp";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
