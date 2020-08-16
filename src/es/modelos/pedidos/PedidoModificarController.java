package es.modelos.pedidos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.utilidades.Utility;

@WebServlet(name = "PedidoModificarController", urlPatterns = { "/pedidoModificar" })
public class PedidoModificarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PedidoModificarController() {
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

		List<DetallesPedido> listado = new ArrayList<DetallesPedido>();
		Pedido pedido = new Pedido();
		String nextPage = "";
		String todo = request.getParameter("todo");
		String id = "";

		if (todo == null) {
			id = request.getParameter("id");
		} else if (todo.equals("modificar")) {
			id = request.getParameter("id");
			String estado = request.getParameter("estado");
			pedido = ModeloPedidos.obtenerPedido(id);
			listado = ModeloPedidos.consultaProductoPedidos(id);
			Float total = Utility.calcularTotalPedido(listado);
			ModeloPedidos.modificarPedidos(id, pedido.getFecha(), estado, String.valueOf(total),
					pedido.getFormaPago(), pedido.getEnvio(), pedido.getCliente().getIdCliente());
			String mensaje = "El pedido se ha actualizado correctamente";
			request.setAttribute("mensaje", mensaje);
		} else if (todo.equals("modificarProducto")) {
			id = request.getParameter("id");
			String idProductoPedido = request.getParameter("idProductoPedido");
			String cantidad = request.getParameter("cantidad");
			ModeloPedidos.modificarProductoPedido(idProductoPedido, cantidad);
			listado = ModeloPedidos.consultaProductoPedidos(id);
			Float total = Utility.calcularTotalPedido(listado);
			pedido = ModeloPedidos.obtenerPedido(id);
			ModeloPedidos.modificarPedidos(id, pedido.getFecha(), pedido.getEstado(), String.valueOf(total),
					pedido.getFormaPago(), pedido.getEnvio(), pedido.getCliente().getIdCliente());
		} else if (todo.equals("eliminarProducto")) {
			id = request.getParameter("id");
			String idProductoPedido = request.getParameter("idProductoPedido");
			ModeloPedidos.eliminarProductoPedido(idProductoPedido);
			listado = ModeloPedidos.consultaProductoPedidos(id);
			Float total = Utility.calcularTotalPedido(listado);
			pedido = ModeloPedidos.obtenerPedido(id);
			ModeloPedidos.modificarPedidos(id, pedido.getFecha(), pedido.getEstado(), String.valueOf(total),
					pedido.getFormaPago(), pedido.getEnvio(), pedido.getCliente().getIdCliente());
		}
		
		pedido = ModeloPedidos.obtenerPedido(id);
		listado = ModeloPedidos.consultaProductoPedidos(id);
		
		Float subtotal = Utility.calcularSubTotalPedido(listado);
		Float iva = Utility.calcularIvaPedido(listado);
		request.setAttribute("subtotal", subtotal);
		request.setAttribute("iva", iva);
		request.setAttribute("pedido", pedido);
		request.setAttribute("listado", listado);

		nextPage = "/vistasGestion/pedidos/modifyPedido.jsp";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
