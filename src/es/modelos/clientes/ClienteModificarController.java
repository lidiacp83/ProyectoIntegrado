package es.modelos.clientes;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.utilidades.Utility;

@WebServlet(name = "ClienteModificarController", urlPatterns = { "/clienteModificar" })
public class ClienteModificarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClienteModificarController() {
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

		Cliente cliente = new Cliente();
		String nextPage = "";

		String todo = request.getParameter("todo");

		if (todo == null) {
			String id = request.getParameter("id");
			cliente = ModeloClientes.obtenerCliente(id);
		} else if (todo.equals("modificar")) {
			String id = request.getParameter("id");
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String codigoPostal = request.getParameter("codigoPostal");
			String localidad = request.getParameter("localidad");
			String provincia = request.getParameter("provincia");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");
			String clave = request.getParameter("clave");
			String perfil = request.getParameter("perfil");
			ModeloClientes.modificarClientes(id, nombre, apellidos, direccion, codigoPostal, localidad, provincia,
					email, telefono, clave, perfil);
			cliente = ModeloClientes.obtenerCliente(id);
			String mensaje = "El cliente se ha modificado correctamente";
			request.setAttribute("mensaje", mensaje);
		}

		request.setAttribute("cliente", cliente);

		nextPage = "/vistasGestion/clientes/modifyCliente.jsp";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
