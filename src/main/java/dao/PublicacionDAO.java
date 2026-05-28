package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionBBDD.ConexionAplicacion;
import modelo.Publicacion;

public class PublicacionDAO {

	private ConexionAplicacion conexion = new ConexionAplicacion();
	private Connection connection;

	public PublicacionDAO() {
		this.connection = conexion.conectar();
	}

	private void asegurarConexion() {
		try {
			if (this.connection == null || this.connection.isClosed()) {
				this.connection = conexion.conectar();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Publicacion> obtenerPublicados() {
		asegurarConexion();
		ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
		String querySelect = "SELECT * FROM publicaciones";
		try (Statement sentencia = connection.createStatement(); ResultSet rs = sentencia.executeQuery(querySelect)) {
			while (rs.next()) {
				lista.add(new Publicacion(rs.getInt("id_publicacion"), rs.getString("titulo"), rs.getString("tipo"),
						rs.getString("estado"), rs.getString("fecha_creacion"), rs.getString("descripcion"),
						rs.getInt("id_sede")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList<Publicacion> obtenerTodasPublicaciones() {
		asegurarConexion();
		ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
		String querySelect = "SELECT * FROM publicaciones;";
		try (Statement sentencia = connection.createStatement(); ResultSet rs = sentencia.executeQuery(querySelect)) {
			while (rs.next()) {
				lista.add(new Publicacion(rs.getInt("id_publicacion"), rs.getString("titulo"), rs.getString("tipo"),
						rs.getString("estado"), rs.getString("fecha_creacion"), rs.getString("descripcion"),
						rs.getInt("id_sede")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Publicacion obtenerPublicacion(int id) {
		asegurarConexion();
		Publicacion p = null;
		String querySelect = "SELECT * FROM publicaciones WHERE id_publicacion = ?;";
		try (PreparedStatement ps = connection.prepareStatement(querySelect)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					p = new Publicacion(rs.getInt("id_publicacion"), rs.getString("titulo"), rs.getString("tipo"),
							rs.getString("estado"), rs.getString("fecha_creacion"), rs.getString("descripcion"),
							rs.getInt("id_sede"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public boolean insertar(Publicacion p) {
		asegurarConexion();
		if (p != null && connection != null) {
			String queryInsert = "INSERT INTO publicaciones (titulo, tipo, estado, descripcion, id_autor, id_sede, fecha_creacion) "
					+ "VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE);";
			try (PreparedStatement ps = connection.prepareStatement(queryInsert)) {
				ps.setString(1, p.getTitulo());
				ps.setString(2, p.getTipo());
				ps.setString(3, p.getEstado());
				ps.setString(4, p.getContenido());
				ps.setInt(5, p.getIdAutor());
				ps.setInt(6, p.getIdSede());
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean editar(Publicacion p) {
		asegurarConexion();
		if (p != null && connection != null) {
			String queryUpdate = "UPDATE publicaciones SET titulo = ?, tipo = ?, estado = ?, descripcion = ?, id_sede = ? "
					+ "WHERE id_publicacion = ?;";
			try (PreparedStatement ps = connection.prepareStatement(queryUpdate)) {
				ps.setString(1, p.getTitulo());
				ps.setString(2, p.getTipo());
				ps.setString(3, p.getEstado());
				ps.setString(4, p.getContenido());
				ps.setInt(5, p.getIdSede());
				ps.setInt(6, p.getId());
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean eliminar(int id) {
		asegurarConexion();
		if (connection != null) {
			String queryDelete = "DELETE FROM publicaciones WHERE id_publicacion = ?;";
			try (PreparedStatement ps = connection.prepareStatement(queryDelete)) {
				ps.setInt(1, id);
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}