package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionBBDD.ConexionAplicacion;
import modelo.Mensaje;

public class MensajeDAO {
	private ConexionAplicacion conexion = new ConexionAplicacion();
	private Connection connection;
	private Statement sentencia;
	private PreparedStatement sentenciaParametrizada;
	private ResultSet rs;

	public MensajeDAO() {
		this.connection = conexion.conectar();
	}

	// obtener mensajes de una solicitud
	public ArrayList<Mensaje> obtenerMensajesSolicitud(int idSolicitud) {
		ArrayList<Mensaje> lista = new ArrayList<Mensaje>();
		String querySelect = "SELECT * FROM mensajes WHERE id_solicitud = ?;";
		try {
			sentenciaParametrizada = connection.prepareStatement(querySelect);
			sentenciaParametrizada.setInt(1, idSolicitud);
			rs = sentenciaParametrizada.executeQuery();
			while (rs.next()) {
				lista.add(new Mensaje(rs.getInt("id"), rs.getInt("id_solicitud"), rs.getInt("id_empleado"),
						rs.getString("contenido"), rs.getString("fecha_envio")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// Insertamos mensaje nuevo
	public boolean insertar(Mensaje m) {
		if (m != null) {
			String queryInsert = "INSERT INTO mensajes (id_solicitud, id_empleado, contenido, fecha_envio)"
					+ "VALUES (?, ?, ?, CURRENT_TIMESTAMP);";
			try {
				sentenciaParametrizada = connection.prepareStatement(queryInsert);
				sentenciaParametrizada.setInt(1, m.getIdSolicitud());
				sentenciaParametrizada.setInt(2, m.getIdEmpleado());
				sentenciaParametrizada.setString(3, m.getContenido());
				sentenciaParametrizada.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Eliminar el mensaje por id
	public boolean eliminar(int id) {
		String queryDelete = "DELETE FROM mensajes WHERE id = ?;";
		try {
			sentenciaParametrizada = connection.prepareStatement(queryDelete);
			sentenciaParametrizada.setInt(1, id);
			sentenciaParametrizada.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
