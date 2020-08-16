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

@WebServlet(name = "CategoriaController", urlPatterns = { "/categoriaAlta" })
public class CategoriaAltaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoriaAltaController() {
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
		if (todo == null) {
			nextPage = "/vistasGestion/categorias/addCategoria.jsp";
		} else if (todo.equals("alta")) {
			String nombre = request.getParameter("nombre");
			String[] imagenPath = request.getParameter("imagen").split(Pattern.quote(File.separator));
			String imagen = imagenPath[imagenPath.length - 1];
			String imagenData = request.getParameter("imagenData").replaceAll(" ", "+");
			ModeloCategorias.altaCategoria(nombre, imagen, imagenData);
			String mensaje = "El alta de la categoría se ha realizado correctamente";
			request.setAttribute("mensaje", mensaje);
			nextPage = "/vistasGestion/categorias/addCategoria.jsp";
		}
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}

