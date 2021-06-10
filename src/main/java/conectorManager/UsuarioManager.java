/*
 * @author Daniel Mijens Tutor
 */
package conectorManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLTimeoutException;

import api.Usuario;
import conector.Conector;

/**
 * The Class UsuarioManager.
 */
public class UsuarioManager {

	/**
	 * Find by id.
	 *
	 * @param idUsuario the id usuario
	 * @return the mensaje
	 * @throws SQLTimeoutException the SQL timeout exception
	 * @throws SQLException        the SQL exception
	 */
	public static Usuario findById(int idUsuario) throws SQLTimeoutException, SQLException {
		try (Connection con = new Conector().getMySQLConnection()) {
			PreparedStatement query = con.prepareStatement("SELECT * FROM Usuario WHERE idUsuario = ?");
			query.setInt(1, idUsuario);
			query.execute();
			ResultSet result = query.getResultSet();
			if (!result.next()) {
				System.out.println("No existe el usuario con esa id");
			}
			return new Usuario(result.getInt("idUsuario"), result.getString("nombre_usuario"),
					result.getString("email"));
		}
	}

	/**
	 * Find by nombre.
	 *
	 * @param nombreUsu the nombre usu
	 * @return the mensaje
	 * @throws SQLTimeoutException the SQL timeout exception
	 * @throws SQLException        the SQL exception
	 * @throws CustomException     the custom exception
	 */
	public static Usuario findByNombre(String nombreUsu) throws SQLTimeoutException, SQLException {
		try (Connection con = new Conector().getMySQLConnection()) {
			PreparedStatement query = con.prepareStatement("SELECT * FROM Usuario WHERE nombre_usuario = ?");
			query.setString(1, nombreUsu);
			query.execute();
			ResultSet result = query.getResultSet();
			result.next();
			return new Usuario(result.getInt("idUsuario"), result.getString("nombre_usuario"),
					result.getString("email"));
		}
	}

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the usuario
	 * @throws SQLTimeoutException the SQL timeout exception
	 * @throws SQLException        the SQL exception
	 * @throws CustomException     the custom exception
	 */
	public static Usuario findByEmail(String email) throws SQLTimeoutException, SQLException {

		try (Connection con = new Conector().getMySQLConnection()) {
			PreparedStatement query = con.prepareStatement("SELECT * FROM Usuario WHERE email = ?");
			query.setString(1, email);
			ResultSet result = query.executeQuery();
			result.next();
			return new Usuario(result.getInt("idUsuario"), result.getString("nombre_usuario"),
					result.getString("email"));
		}
	}

	/**
	 * Creates the usuario in database.
	 *
	 * @param nombreUsu the nombre usu
	 * @param pass      the pass
	 * @param email     the email
	 * @return true, if successful
	 * @throws SQLIntegrityConstraintViolationException the SQL integrity constraint
	 *                                                  violation exception
	 * @throws SQLTimeoutException                      the SQL timeout exception
	 * @throws SQLException                             the SQL exception
	 * @throws CustomException                          the custom exception
	 */
	public static Usuario create(Usuario usuario)
			throws SQLIntegrityConstraintViolationException, SQLTimeoutException, SQLException {
		try (Connection con = new Conector().getMySQLConnection()) {
			PreparedStatement query = con.prepareStatement(
					"INSERT INTO Usuario(nombre_usuario, password , email) VALUES (?, ?,  ?)", new String[] { "id" });
			query.setString(1, usuario.getNombreUsuario());
			query.setString(2, usuario.getPassword());
			query.setString(3, usuario.getEmail());
			query.executeUpdate();
			ResultSet result = query.getGeneratedKeys();
			result.next();
			return findByEmail(usuario.getEmail());

		}
	}

	public static String deleteByID(int idUsuario)
			throws SQLIntegrityConstraintViolationException, SQLTimeoutException, SQLException {
		try (Connection con = new Conector().getMySQLConnection()) {
			PreparedStatement query = con.prepareStatement(
					"DELETE FROM Usuario WHERE idUsuario = ?");
			query.setInt(1, idUsuario);
			return query.executeUpdate() > 0 ? "Borrado con exito el usuario con id " + idUsuario
					: "No se encuentra la id " + idUsuario;

		}
	}
}
