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

@WebServlet(name = "ClienteAltaController", urlPatterns = { "/clienteAlta" })
public class ClienteAltaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClienteAltaController() {
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

		List<Cliente> listadoCliente = new ArrayList<Cliente>();
		listadoCliente = ModeloClientes.consultaClientes();
		request.setAttribute("listadoCliente", listadoCliente);
		if (todo == null) {
			nextPage = "/vistasGestion/clientes/addCliente.jsp";
		} else if (todo.equals("alta")) {
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String codigoPostal = request.getParameter("cpostal");
			String localidad = request.getParameter("localidad");
			String provincia = request.getParameter("provincia");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");
			String clave = request.getParameter("clave");
			String perfil = request.getParameter("perfil");
			// Comprobamos que existe o no otro email igual en la base de datos
			if (ModeloClientes.comprobarEmail(email) > 0) {
				String mensaje = "Ya existe un usuario registrado con el mismo correo electrónico";
				request.setAttribute("mensaje", mensaje);
			} else {
				ModeloClientes.altaCliente(nombre, apellidos, direccion, codigoPostal, localidad, provincia, email,
						telefono, clave, perfil);
				String mensaje = "El alta del cliente se ha realizado correctamente";
				request.setAttribute("mensaje", mensaje);
			}
			nextPage = "/vistasGestion/clientes/addCliente.jsp";
		} else if (todo.equals("altaWeb")) {
			nextPage = "/vistasWeb/inicioSesion.jsp";
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String codigoPostal = request.getParameter("cpostal");
			String localidad = request.getParameter("localidad");
			String provincia = request.getParameter("provincia");
			String telefono = request.getParameter("telefono");
			String email = request.getParameter("email");
			String clave = request.getParameter("clave");
			// Comprobamos que existe o no otro email igual en la base de datos
			if (ModeloClientes.comprobarEmail(email) > 0) {
				String mensaje = "Ya existe un cliente registrado con el mismo correo electrónico";
				request.setAttribute("mensaje", mensaje);
			} else {
				ModeloClientes.altaCliente(nombre, apellidos, direccion, codigoPostal, localidad, provincia, email, telefono, clave, "2");
				String mensaje = "¡Gracias por registrarse!";
				request.setAttribute("mensaje", mensaje);
			}
		}

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}