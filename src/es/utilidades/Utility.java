package es.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import es.modelos.clientes.Cliente;
import es.modelos.pedidos.DetallesPedido;

public class Utility {

	// POOL DE CONEXIONES A LA BASE DE DATOS
	private static DataSource pool;

	public static void iniciarPool() throws ServletException {
		try {
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql_gestiontienda");
			if (pool == null) {
				throw new ServletException("DataSource desconocida 'mysql_gestiontienda'");
			}
		} catch (NamingException ex) {
		}
	}

	public static DataSource getPool() {
		return pool;
	}

	public static void setPool(DataSource p) {
		pool = p;
	}

	// FUNCIÓN COMPROBAR LOGIN ADMINISTRACIÓN
	public static boolean comprobarLoginAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		if (cliente == null || !cliente.getPerfil().getTipoPerfil().equals("administrador")) {
			return false;
		} else {
			return true;
		}
	}

	// FUNCIÓN FORMATO FECHA
	public static String fechaATexto(Date fecha) {
		if (fecha == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(fecha);
	}

	public static Float calcularTotalPedido(List<DetallesPedido> listado) {
		// Recorro el listado de productos para calcular el total del pedido
		Float total = new Float(0.00f);
		Float subtotal = new Float(0.00f);
		Float iva = new Float(0.00f);
		if(listado != null) {
			Iterator<DetallesPedido> i = listado.iterator();
			while (i.hasNext()) {
				DetallesPedido detalles = i.next();
				subtotal = subtotal + (Float.parseFloat(detalles.getPrecio()) * Integer.parseInt(detalles.getCantidad()));
				iva = iva + ((Float.parseFloat(detalles.getPrecio()) * Integer.parseInt(detalles.getCantidad()) * new Float(0.21)));
			}
		} 
		total = subtotal + iva;
		return total;
	}
	
	public static Float calcularSubTotalPedido(List<DetallesPedido> listado) {
		// Recorro el listado de productos para calcular el subtotal del pedido
		Float subtotal = new Float(0.00f);
		if (listado != null) {
			Iterator<DetallesPedido> i = listado.iterator();
			while (i.hasNext()) {
				DetallesPedido detalles = i.next();
				subtotal = subtotal + (Float.parseFloat(detalles.getPrecio()) * Integer.parseInt(detalles.getCantidad()));
			}
		}
		return subtotal;
	}
	
	public static Float calcularIvaPedido(List<DetallesPedido> listado) {
		// Recorro el listado de productos para calcular el iva del pedido
		Float iva = new Float(0.00f);
		if (listado != null) {
			Iterator<DetallesPedido> i = listado.iterator();
			while (i.hasNext()) {
				DetallesPedido detalles = i.next();
				iva = iva + ((Float.parseFloat(detalles.getPrecio()) * Integer.parseInt(detalles.getCantidad()) * new Float(0.21)));
			}
		}
		return iva;
	}
}
