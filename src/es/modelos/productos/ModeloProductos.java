package es.modelos.productos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.modelos.categorias.Categoria;
import es.utilidades.Utility;

public class ModeloProductos {

	/**
	 * Método que da de alta un producto.
	 * 
	 * @param nombre        nombre del producto
	 * @param descripcion
	 * @param imagen
	 * @param imagenData
	 * @param precio
	 * @param iva
	 * @param rebaja
	 * @param stock
	 * @param stockMinimo
	 * @param sexo
	 * @param peso
	 * @param idCategoriaFK
	 * 
	 * @return No devuelve nada.
	 */
	public static void altaProducto(String nombre, String descripcion, String imagen, String imagenData, String precio,
			String iva, String rebaja, String stock, String stockMinimo, String sexo, String peso,
			String idCategoriaFK) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Insertamos los datos de los productos
			String sqlStr = "INSERT INTO productos VALUES (null, '" + nombre + "', '" + descripcion + "', '" + imagen
					+ "', '" + imagenData + "', " + precio + "," + iva + "," + rebaja + "," + stock + "," + stockMinimo
					+ ",'" + sexo + "'," + peso + "," + idCategoriaFK + " )";
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

	// BAJA DE PRODUCTO EN LA BASE DE DATOS
	public static void bajaProducto(String idProducto) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Borramos el producto según el id
			String sqlStr = "DELETE FROM productos WHERE productoId = " + idProducto;
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

	// OBTENER PRODUCTO POR EL NUMERO DE ID DADO
	public static Producto obtenerProducto(String idProducto) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Iniciamos el producto a nulo
		Producto producto = null;
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			// Se obtiene los datos del Producto por el id de Producto que se ha indicado
			String sqlStr = "SELECT p.productoId, p.productoNombre, p.productoDescripcion, p.productoImagen, p.productoImagenData, p.productoPrecio, p.productoIVA "
					+ ",p.productoPvpRebajado, p.productoStock, p.productoStockMinimo, p.productoSexo, p.productoPeso, p.categoriaIdFK, c.categoriaId, c.categoriaNombre"
					+ ", c.categoriaImagen, c.categoriaImagenData FROM productos p, categorias c "
					+ "WHERE p.categoriaIdFK = c.categoriaId AND p.productoId = " + idProducto + " ORDER BY p.productoNombre";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				// Iniciamos el producto y cargamos sus datos
				producto = new Producto();
				producto.setIdProducto(rs.getString("productoId"));
				producto.setNombre(rs.getString("productoNombre"));
				producto.setDescripcion(rs.getString("productoDescripcion"));
				producto.setImagen(rs.getString("productoImagen"));
				producto.setImagenData(rs.getString("productoImagenData"));
				producto.setPrecio(rs.getString("productoPrecio"));
				producto.setIva(rs.getString("productoIVA"));
				producto.setRebaja(rs.getString("productoPvpRebajado"));
				producto.setStock(rs.getString("productoStock"));
				producto.setStockMinimo(rs.getString("productoStockMinimo"));
				producto.setSexo(rs.getString("productoSexo"));
				producto.setPeso(rs.getString("productoPeso"));
				producto.setIdcategoriaFK(rs.getString("categoriaIdFK"));
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getString("categoriaId"));
				categoria.setNombre(rs.getString("categoriaNombre"));
				categoria.setImagen(rs.getString("categoriaImagen"));
				categoria.setImagenData(rs.getString("categoriaImagenData"));
				// Añadimos la categoria al producto
				producto.setCategoria(categoria);
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
		return producto;
	}

	// OBTENER PRODUCTO POR CATEGORIA
	public static List<Producto> obtenerProductoPorCategoria(String idCategoria) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de productos vacía
		List<Producto> listadoProductos = new ArrayList<Producto>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias
			String sqlStr = "SELECT * FROM productos WHERE categoriaIdFK = " + idCategoria;
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				// Iniciamos el producto y cargamos sus datos
				Producto producto = new Producto();
				producto.setIdProducto(rs.getString("productoId"));
				producto.setNombre(rs.getString("productoNombre"));
				producto.setDescripcion(rs.getString("productoDescripcion"));
				producto.setImagen(rs.getString("productoImagen"));
				producto.setImagenData(rs.getString("productoImagenData"));
				producto.setPrecio(rs.getString("productoPrecio"));
				producto.setIva(rs.getString("productoIVA"));
				producto.setRebaja(rs.getString("productoPvpRebajado"));
				producto.setStock(rs.getString("productoStock"));
				producto.setStockMinimo(rs.getString("productoStockMinimo"));
				producto.setSexo(rs.getString("productoSexo"));
				producto.setPeso(rs.getString("productoPeso"));
				producto.setIdcategoriaFK(rs.getString("categoriaIdFK"));
				// Añadimos el cliente a la lista de clientes
				listadoProductos.add(producto);
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
		return listadoProductos;
	}

	// MODIFICAR PRODUCTOS EN LA BASE DE DATOS
	public static void modificarProductos(String id, String nombre, String descripcion, String imagen,
			String imagenData, String precio, String iva, String rebaja, String stock, String stockMinimo, String sexo,
			String peso, String idCategoriaFK) {
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
			String sqlStr = "UPDATE productos SET productoNombre='" + nombre + "', productoDescripcion= '" + descripcion
					+ "', productoImagen= '" + imagen + "', productoImagenData= '" + imagenData + "', productoPrecio= "
					+ precio + ", productoIVA= " + iva + ", productoPvpRebajado= " + rebaja + ", productoStock= "
					+ stock + ", productoStockMinimo= " + stockMinimo + ", productoSexo='" + sexo + "', productoPeso= "
					+ peso + ", categoriaIdFK= " + idCategoriaFK + " WHERE productoId=" + id;
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

	// CONSULTA DE PRODUCTOS
	public static List<Producto> consultaProductos() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de productos vacía
		List<Producto> listadoProductos = new ArrayList<Producto>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el nombre del producto
			// Obtiene el listado de productos ordenados por nombre
			String sqlStr = "SELECT p.productoId, p.productoNombre, p.productoDescripcion, p.productoImagen,p.productoImagenData, p.productoPrecio, p.productoIVA "
					+ ",p.productoPvpRebajado, p.productoStock, p.productoStockMinimo, p.productoSexo, p.productoPeso, p.categoriaIdFK, c.categoriaId, c.categoriaNombre"
					+ ", c.categoriaImagen, c.categoriaImagenData FROM productos p, categorias c "
					+ "WHERE p.categoriaIdFK = c.categoriaId ORDER BY productoNombre";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos del producto en la clase Producto
				Producto producto = new Producto();
				producto.setIdProducto(rs.getString("productoId"));
				producto.setNombre(rs.getString("productoNombre"));
				producto.setDescripcion(rs.getString("productoDescripcion"));
				producto.setImagen(rs.getString("productoImagen"));
				producto.setImagenData(rs.getString("productoImagenData"));
				producto.setPrecio(rs.getString("productoPrecio"));
				producto.setIva(rs.getString("productoIVA"));
				producto.setRebaja(rs.getString("productoPvpRebajado"));
				producto.setStock(rs.getString("productoStock"));
				producto.setStockMinimo(rs.getString("productoStockMinimo"));
				producto.setSexo(rs.getString("productoSexo"));
				producto.setPeso(rs.getString("productoPeso"));
				producto.setIdcategoriaFK(rs.getString("categoriaIdFK"));
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getString("categoriaId"));
				categoria.setNombre(rs.getString("categoriaNombre"));
				categoria.setImagen(rs.getString("categoriaImagen"));
				categoria.setImagenData(rs.getString("categoriaImagenData"));
				// Añadimos la categoria al producto
				producto.setCategoria(categoria);
				// Añadimos el producto a la lista de productos
				listadoProductos.add(producto);
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
		return listadoProductos;
	}

	// CONSULTA DE PRODUCTO CON FILTRO
	public static List<Producto> consultaProductosFiltro(String select, String texto) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de productos vacía
		List<Producto> listadoProductos = new ArrayList<Producto>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el nombre del cliente
			// Obtiene el listado de productos ordenados por nombre
			String sqlStr = "";
			if ("categoriaNombre".equals(select)) {
				sqlStr = "SELECT p.productoId, p.productoNombre, p.productoDescripcion, p.productoImagen, p.productoImagenData, p.productoPrecio"
						+ ", p.productoIVA, p.productoPvpRebajado, p.productoStock, p.productoStockMinimo, p.productoSexo, "
						+ "p.productoPeso, p.categoriaIdFK, c.categoriaId, c.categoriaNombre, c.categoriaImagen, c.categoriaImagenData"
						+ " FROM productos p, categorias c WHERE p.categoriaIdFK = c.categoriaId AND c." + select
						+ " LIKE '%" + texto + "%' ORDER BY p.productoNombre";
			} else {
				sqlStr = "SELECT p.productoId, p.productoNombre, p.productoDescripcion, p.productoImagen,p.productoImagenData, p.productoPrecio"
						+ ", p.productoIVA, p.productoPvpRebajado, p.productoStock, p.productoStockMinimo, p.productoSexo, "
						+ "p.productoPeso, p.categoriaIdFK, c.categoriaId, c.categoriaNombre, c.categoriaImagen, c.categoriaImagenData"
						+ " FROM productos p, categorias c WHERE p.categoriaIdFK = c.categoriaId AND p." + select
						+ " LIKE '%" + texto + "%' ORDER BY p.productoNombre";
			}
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos del producto en la clase Producto
				Producto producto = new Producto();
				producto.setIdProducto(rs.getString("productoId"));
				producto.setNombre(rs.getString("productoNombre"));
				producto.setDescripcion(rs.getString("productoDescripcion"));
				producto.setImagen(rs.getString("productoImagen"));
				producto.setImagenData(rs.getString("productoImagenData"));
				producto.setPrecio(rs.getString("productoPrecio"));
				producto.setIva(rs.getString("productoIVA"));
				producto.setRebaja(rs.getString("productoPvpRebajado"));
				producto.setStock(rs.getString("productoStock"));
				producto.setStockMinimo(rs.getString("productoStockMinimo"));
				producto.setSexo(rs.getString("productoSexo"));
				producto.setPeso(rs.getString("productoPeso"));
				producto.setIdcategoriaFK(rs.getString("categoriaIdFK"));
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getString("categoriaId"));
				categoria.setNombre(rs.getString("categoriaNombre"));
				categoria.setImagen(rs.getString("categoriaImagen"));
				categoria.setImagenData(rs.getString("categoriaImagenData"));
				// Añadimos la categoria al producto
				producto.setCategoria(categoria);
				// Añadimos el producto a la lista de productos
				listadoProductos.add(producto);
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
		return listadoProductos;
	}

	// COMPROBAR SI EL PRODUCTO ESTÁ EN ALGÚN PEDIDOS
	public static int comprobarProductoEnPedidos(String id) {
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
			String sqlStr = "SELECT COUNT(*) FROM productospedidos WHERE productoIdFK = " + id;
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

	// CONSULTA PRODUCTOS STOCK MINIMO
	public static List<Producto> consultaStockMinimo() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		// Creamos la lista de productos vacía
		List<Producto> listadoProductos = new ArrayList<Producto>();
		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();
			// Ejecutar las sentencias ordenada por el nombre del producto
			// Obtiene el listado de productos ordenados por nombre
			String sqlStr = "SELECT p.productoId, p.productoNombre, p.productoDescripcion, p.productoImagen, p.productoImagenData, p.productoPrecio, p.productoIVA "
					+ ",p.productoPvpRebajado, p.productoStock, p.productoStockMinimo, p.productoSexo, p.productoPeso, p.categoriaIdFK, c.categoriaId, c.categoriaNombre"
					+ ", c.categoriaImagen, c.categoriaImagenData FROM productos p, categorias c "
					+ "WHERE p.categoriaIdFK = c.categoriaId AND p.productoStock <= p.productoStockMinimo ORDER BY productoNombre";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Recoger los resultados y procesarlos
			// Cargamos los datos
			while (rs.next()) {
				// Cargamos los datos del producto en la clase Producto
				Producto producto = new Producto();
				producto.setIdProducto(rs.getString("productoId"));
				producto.setNombre(rs.getString("productoNombre"));
				producto.setDescripcion(rs.getString("productoDescripcion"));
				producto.setImagen(rs.getString("productoImagen"));
				producto.setImagenData(rs.getString("productoImagenData"));
				producto.setPrecio(rs.getString("productoPrecio"));
				producto.setIva(rs.getString("productoIVA"));
				producto.setRebaja(rs.getString("productoPvpRebajado"));
				producto.setStock(rs.getString("productoStock"));
				producto.setStockMinimo(rs.getString("productoStockMinimo"));
				producto.setSexo(rs.getString("productoSexo"));
				producto.setPeso(rs.getString("productoPeso"));
				producto.setIdcategoriaFK(rs.getString("categoriaIdFK"));
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getString("categoriaId"));
				categoria.setNombre(rs.getString("categoriaNombre"));
				categoria.setImagen(rs.getString("categoriaImagen"));
				categoria.setImagenData(rs.getString("categoriaImagenData"));
				// Añadimos la categoria al producto
				producto.setCategoria(categoria);
				// Añadimos el producto a la lista de productos
				listadoProductos.add(producto);
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
		return listadoProductos;
	}

}
