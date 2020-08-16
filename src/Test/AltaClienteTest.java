package Test;

import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import es.modelos.clientes.Cliente;
import es.modelos.clientes.ModeloClientes;

public class AltaClienteTest {
	
	Cliente usuario = new Cliente();
	
	Context context;
	
	@Before
	public void setUp() throws Exception {
		// CONFIGURO EL CONTEXTO PARA LA CONEXIÓN CON LA BASE DE DATOS
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

        final BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://servletUser:Studium2020;@localhost:3306/gestiontienda");

        context = new InitialContext();
        context.createSubcontext("java:");
        context.createSubcontext("java:comp");
        context.createSubcontext("java:comp/env");
        context.createSubcontext("java:comp/env/jdbc");
        context.bind("java:comp/env/jdbc/mysql_gestiontienda", ds);
		
        // CREAMOS UN OBJETO CLIENTE CON DATOS PARA LAS PRUEBAS
        usuario.setNombre("Annabel");
        usuario.setApellidos("Márquez Rivas");
        usuario.setDireccion("Calle Rey Gaspar, 32");
        usuario.setCodigoPostal("41008");
        usuario.setLocalidad("Sevilla");
        usuario.setProvincia("Sevilla");
        usuario.setEmail("annabelmarquez@gmail.com");
        usuario.setTelefono("652104713");
        usuario.setClave("test");
        usuario.setPerfilIdFK("1");
	}
	
	@After
	public void setUpFinal() throws Exception {
		context.close();
	}

	@Test
	public void testAltaCliente() {
		ModeloClientes.altaCliente(usuario.getNombre(), usuario.getApellidos(), usuario.getDireccion(),
				usuario.getCodigoPostal(), usuario.getLocalidad(), usuario.getProvincia(), usuario.getEmail(),
				usuario.getTelefono(), usuario.getClave(), usuario.getPerfilIdFK());
		assertTrue(ModeloClientes.comprobarEmail(usuario.getEmail())  > 0);
	}

}
