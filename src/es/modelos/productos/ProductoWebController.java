package es.modelos.productos;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ProductoWebController", urlPatterns = { "/productoWeb" })
public class ProductoWebController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ProductoWebController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "/vistasWeb/detallesProducto.jsp";
		
		String id = request.getParameter("id");
		
		Producto producto = ModeloProductos.obtenerProducto(id);
		
		request.setAttribute("producto", producto);
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
