package Test;

import static org.junit.Assert.assertTrue;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import es.modelos.clientes.Cliente;
import es.modelos.clientes.ModeloClientes;

public class ObtenerClienteTest {
	
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
	}
	
	@After
	public void setUpFinal() throws Exception {
		context.close();
	}
	
	@Test
	public void testObtenerCliente() {
		Cliente cliente = ModeloClientes.obtenerCliente("14");
		assertTrue(cliente != null);
	}
	
}
