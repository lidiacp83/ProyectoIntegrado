package es.modelos.categorias;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.modelos.productos.ModeloProductos;
import es.modelos.productos.Producto;


@WebServlet(name = "CategoriaWebController", urlPatterns = { "/categoriaWeb" })
public class CategoriaWebController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CategoriaWebController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "/vistasWeb/articulos.jsp";
		
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		
		List<Producto> listaProductos = ModeloProductos.obtenerProductoPorCategoria(id);
		request.setAttribute("listaProductos", listaProductos);
		request.setAttribute("nombreCategoria", nombre);
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
