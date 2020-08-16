package es.modelos.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.modelos.clientes.Cliente;

@WebServlet(name = "LoginGestionController", urlPatterns = { "/loginGestion" })
public class LoginGestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LoginGestionController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost (request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "/vistasGestion/login.jsp";
		try {
			// Recuperar los parámetros usuario y password de la petición request
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("clave");
			if(usuario != null && password != null) {
				// Verificar que existe el usuario y su correspondiente clave
				Cliente cliente = ModeloLogin.acceso(usuario, password);
				if (cliente == null) {
					String mensaje = "El usuario o contraseña son incorrectos";
					request.setAttribute("mensaje", mensaje);
				} else {
					if (cliente.getPerfil().getTipoPerfil().equals("administrador")) {
						// Si los datos introducidos son correctos
						// Crear una sesión nueva y guardar el usuario como variable de sesión
						// Primero, invalida la sesión si ya existe
						HttpSession session = request.getSession(false);
						if (session != null) {
							session.invalidate();
						}
						session = request.getSession(true);
						synchronized (session) {
							session.setAttribute("cliente", cliente);
						}
						nextPage = "/vistasGestion/index.jsp";
					} else {
						String mensaje = "El usuario no tiene permisos de administrador";
						request.setAttribute("mensaje", mensaje);
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
