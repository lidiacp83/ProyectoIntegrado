package es.modelos.clientes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import es.utilidades.Utility;

public class ModeloClientes {

	// ALTA DE CLIENTES EN LA BASE DE DATOS
	public static void altaCliente(String nombre, String apellidos, String direccion, String codigoPostal,
			String localidad, String provincia, String email, String telefono, String clave, String perfil) {
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
			String sqlStr = "INSERT INTO clientes VALUES (null, '" + nombre + "', '" + apellidos + "', '" + direccion
					+ "','" + codigoPostal + "','" + localidad + "','" + provincia + "','" + email + "','" + telefono
					+ "', AES_ENCRYPT('" + clave + "','lidiacp'), " + perfil + ")";
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

	// BAJA DE CLIENTE EN LA BASE DE DATOS
	public static void bajaCliente(String idCliente) {
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
			String sqlStr = "DELETE FROM clientes WHERE clienteId = " + idCliente;
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

	// OBTENER CLIENTE POR EL NUMERO DE ID DADO
	public static Cliente obtenerCliente(String idCliente) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Iniciamos el cliente a nulo
		Cliente cliente = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Se obtiene los datos del Cliente por el id de Cliente que se ha indicado
			String sqlStr = "SELECT c.clienteId, c.clienteNombre, c.clienteApellidos, c.clienteDireccion, c.clienteCodigoPostal, c.clienteLocalidad, c.clienteProvincia, "
					+ "c.clienteEmail, c.clienteTelefono, AES_DECRYPT(c.clienteClave, 'lidiacp') AS clienteClave, c.perfilIdFK FROM clientes AS c WHERE c.clienteId = "
					+ idCliente;
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				// Iniciamos el cliente y cargamos sus datos
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString("clienteId"));
				cliente.setNombre(rs.getString("clienteNombre"));
				cliente.setApellidos(rs.getString("clienteApellidos"));
				cliente.setDireccion(rs.getString("clienteDireccion"));
				cliente.setCodigoPostal(rs.getString("clienteCodigoPostal"));
				cliente.setLocalidad(rs.getString("clienteLocalidad"));
				cliente.setProvincia(rs.getString("clienteProvincia"));
				cliente.setEmail(rs.getString("clienteEmail"));
				cliente.setTelefono(rs.getString("clienteTelefono"));
				cliente.setClave(rs.getString("clienteClave"));
				cliente.setPerfilIdFK(rs.getString("perfilIdFK"));
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
		return cliente;
	}

	// MODIFICAR CLIENTES EN LA BASE DE DATOS
	public static void modificarClientes(String id, String nombre, String apellidos, String direccion,
			String codigoPostal, String localidad, String provincia, String email, String telefono, String clave,
			String perfil) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Actualiza los datos del cliente indicado por el id del cliente
			String sqlStr = "UPDATE clientes SET clienteNombre='" + nombre + "', clienteApellidos= '" + apellidos
					+ "', clienteDireccion= '" + direccion + "', clienteCodigoPostal= '" + codigoPostal
					+ "', clienteLocalidad= '" + localidad + "', clienteProvincia= '" + provincia + "', clienteEmail= '"
					+ email + "', clienteTelefono='" + telefono + "', clienteClave= AES_ENCRYPT('" + clave
					+ "', 'lidiacp'),perfilIdFK= " + perfil + "  WHERE clienteId=" + id;
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

	// CONSULTA DE CLIENTES
	public static List<Cliente> consultaClientes() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de clientes vacía
		List<Cliente> listadoClientes = new ArrayList<Cliente>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el nombre del cliente
			// Obtiene el listado de clientes ordenados por nombre
			String sqlStr = "SELECT * FROM clientes ORDER BY clienteNombre";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos del cliente en la clase Cliente
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
				cliente.setClave(rs.getString("clienteClave"));
				cliente.setPerfilIdFK(rs.getString("perfilIdFK"));
				// Añadimos el cliente a la lista de clientes
				listadoClientes.add(cliente);
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
		return listadoClientes;
	}

	// COMPROBAR SI EL CLIENTE TIENE PEDIDOS
	public static int comprobarNumeroPedidos(String id) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		int count = 0;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el nombre del cliente
			// Obtiene el listado de clientes ordenados por nombre
			String sqlStr = "SELECT COUNT(*) FROM pedidos WHERE clienteIdFK = " + id;
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	// CONSULTA DE CLIENTES CON FILTRO
	public static List<Cliente> consultaClientesFiltro(String select, String texto) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de clientes vacía
		List<Cliente> listadoClientes = new ArrayList<Cliente>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el nombre del cliente
			// Obtiene el listado de clientes ordenados por nombre
			String sqlStr = "SELECT * FROM clientes WHERE " + select + " LIKE '%" + texto + "%' ORDER BY clienteNombre";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos del cliente en la clase Cliente
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
				cliente.setClave(rs.getString("clienteClave"));
				cliente.setPerfilIdFK(rs.getString("perfilIdFK"));
				// Añadimos el cliente a la lista de clientes
				listadoClientes.add(cliente);
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
		return listadoClientes;
	}

	// COMPROBAR SI EXISTE UN EMAIL REGISTRADO
	public static int comprobarEmail(String email) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		int count = 0;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el nombre del cliente
			// Obtiene el listado de clientes ordenados por nombre
			String sqlStr = "SELECT COUNT(*) FROM clientes WHERE clienteEmail = '" + email + "'";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
}
