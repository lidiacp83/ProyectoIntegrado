package es.modelos.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;

import es.modelos.clientes.Cliente;
import es.modelos.clientes.Perfil;
import es.utilidades.Utility;

public class ModeloLogin {

	public static Cliente acceso(String usuario, String password) throws ServletException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		Cliente cliente = null;

		try {
			Utility.iniciarPool();
			// Obtener una conexión del pool
			conn = Utility.getPool().getConnection();
			stmt = conn.createStatement();

			// Ejecuto la Query
			String sqlStr = "SELECT c.clienteId, c.clienteNombre, c.clienteApellidos, c.clienteDireccion, c.clienteCodigoPostal"
					+ ", c.clienteLocalidad, c.clienteProvincia, c.clienteEmail, c.clienteTelefono, c.clienteClave, c.perfilIdFK, p.perfilId, p.perfilTipo"
					+ " FROM clientes AS c, perfilesusuarios AS p WHERE c.clienteEmail = '" + usuario
					+ "' AND AES_DECRYPT(c.clienteClave,'lidiacp') = '" + password + "'"
					+ " AND c.perfilIdFK = p.perfilId";
			ResultSet rs = stmt.executeQuery(sqlStr.toString());
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString("clienteId"));
				cliente.setNombre(rs.getString("clienteNombre"));
				cliente.setApellidos(rs.getString("clienteApellidos"));
				cliente.setDireccion(rs.getString("clienteDireccion"));
				cliente.setCodigoPostal(rs.getString("clienteCodigoPostal"));
				cliente.setLocalidad(rs.getString("clienteLocalidad"));
				cliente.setProvincia(rs.getString("clienteProvincia"));
				cliente.setNombre(rs.getString("clienteNombre"));
				cliente.setEmail(rs.getString("clienteEmail"));
				cliente.setTelefono(rs.getString("clienteTelefono"));
				cliente.setClave(rs.getString("clienteClave"));
				cliente.setPerfilIdFK(rs.getString("perfilIdFK"));
				Perfil perfil = new Perfil();
				perfil.setIdPerfil(rs.getString("perfilId"));
				perfil.setTipoPerfil(rs.getString("perfilTipo"));
				cliente.setPerfil(perfil);
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					// Esto devolvería la conexión al pool
					conn.close();
				}
			} catch (SQLException ex) {
			}

		}
		return cliente;
	}
	
	
	
	

	
	

}
