package es.modelos.pedidos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import es.modelos.clientes.Cliente;
import es.utilidades.Utility;

public class ModeloPedidos {

	// ALTA DE PEDIDOS EN LA BASE DE DATOS
	public static void altaPedido(Cliente cliente, Float total, List<DetallesPedido> cesta) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Insertamos los datos del cliente
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fecha = format.format(new Date());
			
			String sqlStr = "INSERT INTO pedidos VALUES (null, '" + fecha + "', 'Pendiente', '" + total
					+ "','Tarjeta','6','" + cliente.getIdCliente() + "' )";
			stmt.executeUpdate(sqlStr, Statement.RETURN_GENERATED_KEYS);
			// Obtenemos el id del pedido insertado
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			long idPedido = rs.getLong(1);
			
			Iterator<DetallesPedido> iter = cesta.iterator();
			while (iter.hasNext()) {
				//Recorremos la cesta y vamos insertando en la tabla de ProductosPedidos el id de pedido, 
				// el id del producto y la cantidad para cada uno de los productos que hay en el pedido
				DetallesPedido detallePedido = (DetallesPedido) iter.next();
				sqlStr = "INSERT INTO productospedidos VALUES (null, '" + detallePedido.getNombre() + "'," + detallePedido.getPrecio() + "," + detallePedido.getPeso() +
						"," + detallePedido.getIva() + "," + detallePedido.getCantidad() + "," + detallePedido.getIdProducto() + "," + idPedido +  ")";
				stmt.executeUpdate(sqlStr);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// CONSULTA DE PEDIDOS
	public static List<Pedido> consultaPedidos() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de clientes vacía
		List<Pedido> listadoPedidos = new ArrayList<Pedido>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el id del pedido
			// Obtiene el listado de pedidos ordenados por nombre

			String sqlStr = "SELECT p.pedidoId, p.pedidoFecha, p.pedidoEstado, p.pedidoTotal, p.pedidoFormaPago, p.pedidoGastosEnvio, c.clienteId, "
					+ "c.clienteNombre, c.clienteApellidos, c.clienteDireccion, c.clienteCodigoPostal, c.clienteLocalidad, "
					+ "c.clienteProvincia, c.clienteEmail, c.clienteTelefono FROM pedidos p, clientes c WHERE p.clienteIdFK = c.clienteId ORDER BY p.pedidoFecha";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos del pedido en la clase Pedido
				Pedido pedido = new Pedido();
				pedido.setIdPedido(rs.getString("pedidoId"));
				pedido.setFecha(rs.getDate("pedidoFecha"));
				pedido.setEstado(rs.getString("pedidoEstado"));
				pedido.setTotalPedido(rs.getString("pedidoTotal"));
				pedido.setFormaPago(rs.getString("pedidoFormaPago"));
				pedido.setEnvio(rs.getString("pedidoGastosEnvio"));
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getString("clienteId"));
				cliente.setNombre(rs.getString("clienteNombre"));
				cliente.setApellidos(rs.getString("clienteApellidos"));
				cliente.setDireccion(rs.getString("clienteDireccion"));
				cliente.setCodigoPostal(rs.getString("clienteCodigoPostal"));
				cliente.setLocalidad(rs.getString("clienteLocalidad"));
				cliente.setProvincia(rs.getString("clienteProvincia"));
				cliente.setEmail(rs.getString("clienteEmail"));
				cliente.setTelefono(rs.getString("clienteTelefono"));
				// Añadimos el cliente al pedido
				pedido.setCliente(cliente);
				// Añadimos el pedido a la lista de pedidos
				listadoPedidos.add(pedido);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listadoPedidos;
	}

	// CONSULTA DE PEDIDOS PENDIENTES
	public static List<Pedido> consultaPedidosPendientes() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de clientes vacía
		List<Pedido> listadoPedidos = new ArrayList<Pedido>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el id del pedido
			// Obtiene el listado de pedidos ordenados por nombre

			String sqlStr = "SELECT p.pedidoId, p.pedidoFecha, p.pedidoEstado, p.pedidoTotal, p.pedidoFormaPago, p.pedidoGastosEnvio, c.clienteId, "
					+ "c.clienteNombre, c.clienteApellidos, c.clienteDireccion, c.clienteCodigoPostal, c.clienteLocalidad, "
					+ "c.clienteProvincia, c.clienteEmail, c.clienteTelefono FROM pedidos p, clientes c WHERE p.clienteIdFK = c.clienteId AND p.pedidoEstado = 'Pendiente'";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos del pedido en la clase Pedido
				Pedido pedido = new Pedido();
				pedido.setIdPedido(rs.getString("pedidoId"));
				pedido.setFecha(rs.getDate("pedidoFecha"));
				pedido.setEstado(rs.getString("pedidoEstado"));
				pedido.setTotalPedido(rs.getString("pedidoTotal"));
				pedido.setFormaPago(rs.getString("pedidoFormaPago"));
				pedido.setEnvio(rs.getString("pedidoGastosEnvio"));
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getString("clienteId"));
				cliente.setNombre(rs.getString("clienteNombre"));
				cliente.setApellidos(rs.getString("clienteApellidos"));
				cliente.setDireccion(rs.getString("clienteDireccion"));
				cliente.setCodigoPostal(rs.getString("clienteCodigoPostal"));
				cliente.setLocalidad(rs.getString("clienteLocalidad"));
				cliente.setProvincia(rs.getString("clienteProvincia"));
				cliente.setEmail(rs.getString("clienteEmail"));
				cliente.setTelefono(rs.getString("clienteTelefono"));
				// Añadimos el cliente al pedido
				pedido.setCliente(cliente);
				// Añadimos el pedido a la lista de pedidos
				listadoPedidos.add(pedido);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listadoPedidos;
	}

	// OBTENER PEDIDO POR EL NUMERO DE ID DADO
	public static Pedido obtenerPedido(String idPedido) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Iniciamos el cliente a nulo
		Pedido pedido = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Se obtiene los datos del pedido por el id de Pedido que se ha
			// indicado
			String sqlStr = "SELECT p.pedidoId, p.pedidoFecha, p.pedidoEstado, p.pedidoTotal, p.pedidoFormaPago, p.pedidoGastosEnvio, c.clienteId, "
					+ "c.clienteNombre, c.clienteApellidos, c.clienteDireccion, c.clienteCodigoPostal, c.clienteLocalidad, "
					+ "c.clienteProvincia, c.clienteEmail, c.clienteTelefono FROM pedidos p, clientes c WHERE p.clienteIdFK = c.clienteId AND p.pedidoId = "
					+ idPedido;
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				// Iniciamos el pedido y cargamos sus datos
				pedido = new Pedido();
				pedido.setIdPedido(rs.getString("pedidoId"));
				pedido.setFecha(rs.getDate("pedidoFecha"));
				pedido.setEstado(rs.getString("pedidoEstado"));
				pedido.setTotalPedido(rs.getString("pedidoTotal"));
				pedido.setFormaPago(rs.getString("pedidoFormaPago"));
				pedido.setEnvio(rs.getString("pedidoGastosEnvio"));
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getString("clienteId"));
				cliente.setNombre(rs.getString("clienteNombre"));
				cliente.setApellidos(rs.getString("clienteApellidos"));
				cliente.setDireccion(rs.getString("clienteDireccion"));
				cliente.setCodigoPostal(rs.getString("clienteCodigoPostal"));
				cliente.setLocalidad(rs.getString("clienteLocalidad"));
				cliente.setProvincia(rs.getString("clienteProvincia"));
				cliente.setEmail(rs.getString("clienteEmail"));
				cliente.setTelefono(rs.getString("clienteTelefono"));
				// Añadimos el cliente al pedido
				pedido.setCliente(cliente);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return pedido;
	}

	// CONSULTA DE PEDIDOS CON FILTRO
	public static List<Pedido> consultaPedidosFiltro(String select, String texto) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de clientes vacía
		List<Pedido> listadoPedidos = new ArrayList<Pedido>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el id del pedido
			// Obtiene el listado de pedidos ordenados por nombre
			String sqlStr = "";
			if ("cliente".equals(select)) {
				sqlStr = "SELECT p.pedidoId, p.pedidoFecha, p.pedidoEstado, p.pedidoTotal, p.pedidoFormaPago, p.pedidoGastosEnvio, c.clienteId, "
						+ "c.clienteNombre, c.clienteApellidos, c.clienteDireccion, c.clienteCodigoPostal, c.clienteLocalidad, "
						+ "c.clienteProvincia, c.clienteEmail, c.clienteTelefono FROM pedidos p, clientes c WHERE p.clienteIdFK = c.clienteId AND (c.clienteNombre LIKE '%"
						+ texto + "%' OR c.clienteApellidos LIKE '%" + texto + "%') ORDER BY p.pedidoFecha";
			} else {
				sqlStr = "SELECT p.pedidoId, p.pedidoFecha, p.pedidoEstado, p.pedidoTotal, p.pedidoFormaPago, p.pedidoGastosEnvio, c.clienteId, "
						+ "c.clienteNombre, c.clienteApellidos, c.clienteDireccion, c.clienteCodigoPostal, c.clienteLocalidad, "
						+ "c.clienteProvincia, c.clienteEmail, c.clienteTelefono FROM pedidos p, clientes c WHERE p.clienteIdFK = c.clienteId AND p."
						+ select + " LIKE '%" + texto + "%' ORDER BY p.pedidoFecha";
			}

			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos del pedido en la clase Pedido
				Pedido pedido = new Pedido();
				pedido.setIdPedido(rs.getString("pedidoId"));
				pedido.setFecha(rs.getDate("pedidoFecha"));
				pedido.setEstado(rs.getString("pedidoEstado"));
				pedido.setTotalPedido(rs.getString("pedidoTotal"));
				pedido.setFormaPago(rs.getString("pedidoFormaPago"));
				pedido.setEnvio(rs.getString("pedidoGastosEnvio"));
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getString("clienteId"));
				cliente.setNombre(rs.getString("clienteNombre"));
				cliente.setApellidos(rs.getString("clienteApellidos"));
				cliente.setDireccion(rs.getString("clienteDireccion"));
				cliente.setCodigoPostal(rs.getString("clienteCodigoPostal"));
				cliente.setLocalidad(rs.getString("clienteLocalidad"));
				cliente.setProvincia(rs.getString("clienteProvincia"));
				cliente.setEmail(rs.getString("clienteEmail"));
				cliente.setTelefono(rs.getString("clienteTelefono"));
				// Añadimos el cliente al pedido
				pedido.setCliente(cliente);
				// Añadimos el pedido a la lista de pedidos
				listadoPedidos.add(pedido);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listadoPedidos;
	}

	// CONSULTA DE PRODUCTOS DE PEDIDOS
	public static List<DetallesPedido> consultaProductoPedidos(String id) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de clientes vacía
		List<DetallesPedido> listadoProductosPedidos = new ArrayList<DetallesPedido>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el nombre del cliente
			// Obtiene el listado de clientes ordenados por nombre
			String sqlStr = "SELECT * FROM productospedidos WHERE pedidoIdFK = " + id;
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos del cliente en la clase Cliente
				DetallesPedido detalles = new DetallesPedido();
				detalles.setIdProducto(rs.getString("productoPedidoId"));
				detalles.setNombre(rs.getString("productoNombre"));
				detalles.setPrecio(rs.getString("productoPrecio"));
				detalles.setPeso(rs.getString("productoPeso"));
				detalles.setIva(rs.getString("productoIVA"));
				detalles.setCantidad(rs.getString("productoCantidad"));
				detalles.setProductoIdFK(rs.getString("productoIdFK"));
				detalles.setPedidoIdFK(rs.getString("pedidoIdFK"));
				// Añadimos el cliente a la lista de clientes
				listadoProductosPedidos.add(detalles);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listadoProductosPedidos;
	}

	// MODIFICAR PEDIDOS EN LA BASE DE DATOS
	public static void modificarPedidos(String idPedido, Date fecha, String estado, String totalPedido,
			String formaPago, String envio, String idClienteFK) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Actualiza los datos del producto indicado por el id del producto
			String sqlStr = "UPDATE pedidos SET pedidoFecha='" + fecha + "', pedidoEstado= '" + estado
					+ "', pedidoTotal= '" + totalPedido + "', pedidoFormaPago= '" + formaPago + "', pedidoGastosEnvio= "
					+ envio + ",  clienteIdFK= " + idClienteFK + " WHERE pedidoId=" + idPedido;
			stmt.executeUpdate(sqlStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// MODIFICAR PRODUCTO PEDIDO
	public static void modificarProductoPedido(String idProductoPedido, String cantidad) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Actualiza los datos del producto indicado por el id del producto
			String sqlStr = "UPDATE productospedidos SET productoCantidad=" + cantidad + " WHERE productoPedidoId="
					+ idProductoPedido;
			stmt.executeUpdate(sqlStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// ELIMINAR PRODUCTO PEDIDO

	public static void eliminarProductoPedido(String idProductoPedido) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Borramos el cliente según el id
			String sqlStr = "DELETE FROM productospedidos WHERE productoPedidoId = " + idProductoPedido;
			stmt.executeUpdate(sqlStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}


}
