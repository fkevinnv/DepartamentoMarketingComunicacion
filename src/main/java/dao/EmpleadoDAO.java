package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionBBDD.ConexionEmpleados;
import modelo.Empleado;

public class EmpleadoDAO {

	private ConexionEmpleados conexion = new ConexionEmpleados();
	private Connection connection;

	public EmpleadoDAO() {
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

	public ArrayList<Empleado> obtenerTodos() {
		asegurarConexion();
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		String querySelect = "SELECT * FROM empleados ORDER BY id;";
		try (Statement sentencia = connection.createStatement(); ResultSet rs = sentencia.executeQuery(querySelect)) {
			while (rs.next()) {
				lista.add(new Empleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("correo"), rs.getString("contrasena"), rs.getInt("id_sede"), rs.getString("rol")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Empleado obtenerEmpleado(int id) {
		asegurarConexion();
		Empleado e = null;
		String querySelect = "SELECT * FROM empleados WHERE id = ?;";
		try (PreparedStatement ps = connection.prepareStatement(querySelect)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					e = new Empleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
							rs.getString("correo"), rs.getString("contrasena"), rs.getInt("id_sede"),
							rs.getString("rol"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	public boolean actualizarRol(int id, String rol) {
		asegurarConexion();
		if (connection != null) {
			String queryUpdate = "UPDATE empleados SET rol = ? WHERE id = ?;";
			try (PreparedStatement ps = connection.prepareStatement(queryUpdate)) {
				ps.setString(1, rol);
				ps.setInt(2, id);
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
			String queryDelete = "DELETE FROM empleados WHERE id = ?;";
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
