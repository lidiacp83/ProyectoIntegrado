package es.modelos.clientes;

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

@WebServlet(name = "ClienteConsultaController", urlPatterns = { "/clienteConsulta" })
public class ClienteConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClienteConsultaController() {
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
		List<Cliente> listado = new ArrayList<Cliente>();
		String nextPage = "";
		String todo = request.getParameter("todo");

		if (todo == null) {
			listado = ModeloClientes.consultaClientes();
			nextPage = "/vistasGestion/clientes/consultaCliente.jsp";
		} else if (todo.equals("eliminar")) {
			String mensaje = "";
			String id = request.getParameter("id");
			// Comprobamos si el cliente tiene pedidos antes de eliminarlo
			int pedidos = ModeloClientes.comprobarNumeroPedidos(id);
			if (pedidos == 0) {
				ModeloClientes.bajaCliente(id);
				mensaje = "El cliente se ha eliminado correctamente";
			} else {
				mensaje = "El cliente tiene pedidos y no puede ser eliminado";
			}
			request.setAttribute("mensaje", mensaje);
			nextPage = "/vistasGestion/clientes/consultaCliente.jsp";
			listado = ModeloClientes.consultaClientes();
		} else if (todo.equals("buscar")) {
			String select = request.getParameter("selectCliente");
			String txtBuscar = request.getParameter("txtBuscar");
			listado = ModeloClientes.consultaClientesFiltro(select, txtBuscar);
			nextPage = "/vistasGestion/clientes/consultaCliente.jsp";
		}

		request.setAttribute("listadoCliente", listado);

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}

}
