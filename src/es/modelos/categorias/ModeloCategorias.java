package es.modelos.categorias;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.utilidades.Utility;

public class ModeloCategorias {

	// ALTA DE CATEGORIAS EN LA BASE DE DATOS
	public static void altaCategoria(String nombre, String imagen, String imagenData) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Insertamos los datos de la categoria
			String sqlStr = "INSERT INTO categorias VALUES (null, '" + nombre + "', '" + imagen 
					+ "' , '" + imagenData + "' )";
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

	// BAJA DE CATEGORIAS EN LA BASE DE DATOS
	public static void bajaCategoria(String idCategoria) {
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
			String sqlStr = "DELETE FROM categorias WHERE categoriaId = " + idCategoria;
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

	// OBTENER CATEGORIA POR EL NUMERO DE ID DADO
	public static Categoria obtenerCategoria(String idCategoria) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Iniciamos el cliente a nulo
		Categoria categoria = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Se obtiene los datos de la categoria por el id de Categoria que se ha
			// indicado
			String sqlStr = "SELECT * FROM categorias WHERE categoriaId = " + idCategoria;
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				// Iniciamos la categoria y cargamos sus datos
				categoria = new Categoria();
				categoria.setIdCategoria(rs.getString("categoriaId"));
				categoria.setNombre(rs.getString("categoriaNombre"));
				categoria.setImagen(rs.getString("categoriaImagen"));
				categoria.setImagenData(rs.getString("categoriaImagenData"));
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
		return categoria;
	}

	// MODIFICAR CATEGORIAS EN LA BASE DE DATOS
	public static void modificarCategoria(String id, String nombre, String imagen, String imagenData) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Actualiza los datos de la categoria indicado por el id de categoria
			String sqlStr = "UPDATE categorias SET categoriaNombre='" + nombre + "', categoriaImagen= '" + imagen
					+ "' , categoriaImagenData='" + imagenData + "' WHERE categoriaId=" + id;
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

	// CONSULTA DE CATEGORIAS
	public static List<Categoria> consultaCategoria() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de categorias vacía
		List<Categoria> listadoCategorias = new ArrayList<Categoria>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el nombre de la categoria
			// Obtiene el listado de categorias ordenados por Id
			String sqlStr = "SELECT * FROM categorias ORDER BY categoriaId";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos de la categoria en la clase Categoria
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getString("categoriaId"));
				categoria.setNombre(rs.getString("categoriaNombre"));
				categoria.setImagen(rs.getString("categoriaImagen"));
				categoria.setImagenData(rs.getString("categoriaImagenData"));

				// Añadimos la categoria a la lista de categorias
				listadoCategorias.add(categoria);
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
		return listadoCategorias;
	}

	// COMPROBAR SI HAY PRODUCTOS DE LA CATEGORIA INCLUÍDOS EN PEDIDOS
	public static int comprobarProductoEnCategoria(String id) {
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
			String sqlStr = "SELECT COUNT(*) FROM productospedidos WHERE productoIdFK IN (SELECT productoId FROM productos WHERE categoriaIdFK = "
					+ id + ")";
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

	// BAJA DE PRODUCTOS EN CATEGORÍA
	public static void bajaProductosCategoria(String idCategoria) {
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
			String sqlStr = "DELETE FROM productos WHERE categoriaIdFK = " + idCategoria;
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
