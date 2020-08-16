package es.modelos.pedidos;

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

@WebServlet(name = "PedidoConsultaController", urlPatterns = { "/pedidoConsulta" })
public class PedidoConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PedidoConsultaController() {
		super();
		// TODO Auto-generated constructor stub
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

		List<Pedido> listado = new ArrayList<Pedido>();
		String nextPage = "";
		String todo = request.getParameter("todo");

		if (todo == null) {
			listado = ModeloPedidos.consultaPedidos();
			nextPage = "/vistasGestion/pedidos/consultaPedido.jsp";
		} else if (todo.equals("buscar")) {
			String select = request.getParameter("selectPedido");
			String txtBuscar = request.getParameter("txtBuscar");
			listado = ModeloPedidos.consultaPedidosFiltro(select, txtBuscar);
			nextPage = "/vistasGestion/pedidos/consultaPedido.jsp";
		}

		request.setAttribute("listadoPedido", listado);

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}

}
