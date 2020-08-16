package es.modelos.menuInicio;

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

import es.modelos.pedidos.ModeloPedidos;
import es.modelos.pedidos.Pedido;
import es.modelos.productos.ModeloProductos;
import es.modelos.productos.Producto;
import es.utilidades.Utility;

@WebServlet(name = "MenuInicioController", urlPatterns = { "/inicio" })
public class MenuInicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MenuInicioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		listaPedido = ModeloPedidos.consultaPedidosPendientes();
		request.setAttribute("listaPedido", listaPedido);

		List<Producto> listaProducto = new ArrayList<Producto>();
		listaProducto = ModeloProductos.consultaStockMinimo();
		request.setAttribute("listaProducto", listaProducto);

		String nextPage = "/vistasGestion/menuInicio.jsp";

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
