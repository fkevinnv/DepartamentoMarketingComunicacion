package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Solicitud;
import conexionBBDD.ConexionAplicacion;

public class SolicitudDAO {
    private ConexionAplicacion conexion = new ConexionAplicacion();
    private Connection connection;
    private Statement sentencia;
    private PreparedStatement sentenciaParametrizada;
    private ResultSet rs;

    public SolicitudDAO() {
        this.connection = conexion.conectar();
    }

    public ArrayList<Solicitud> obtenerTodasSolicitudes() {
        ArrayList<Solicitud> lista = new ArrayList<Solicitud>();
        String querySelect = "SELECT * FROM solicitudes";
        try {
            sentencia = connection.createStatement();
            rs = sentencia.executeQuery(querySelect);
            while (rs.next()) {
                lista.add(new Solicitud(rs.getInt("id"), rs.getInt("id_empleado"), rs.getString("asunto"),
                        rs.getString("descripcion"), rs.getString("estado"), rs.getString("fecha_solicitud")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Solicitud> obtenerSolicitudesEmpleado(int idEmpleado) {
        ArrayList<Solicitud> lista = new ArrayList<Solicitud>();
        String querySelect = "SELECT * FROM solicitudes WHERE id_empleado = ?;";
        try {
            // CORRECCIÓN: se usa prepareStatement en vez de createStatement
            sentenciaParametrizada = connection.prepareStatement(querySelect);
            sentenciaParametrizada.setInt(1, idEmpleado);
            rs = sentenciaParametrizada.executeQuery();
            while (rs.next()) {
                lista.add(new Solicitud(rs.getInt("id"), rs.getInt("id_empleado"), rs.getString("asunto"),
                        rs.getString("descripcion"), rs.getString("estado"), rs.getString("fecha_solicitud")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Solicitud obtenerSolicitud(int id) {
        Solicitud s = null;
        String querySelect = "SELECT * FROM solicitudes WHERE id = ?;";
        try {
            sentenciaParametrizada = connection.prepareStatement(querySelect);
            sentenciaParametrizada.setInt(1, id);
            rs = sentenciaParametrizada.executeQuery();
            if (rs.next()) {
                s = new Solicitud(rs.getInt("id"), rs.getInt("id_empleado"), rs.getString("asunto"),
                        rs.getString("descripcion"), rs.getString("estado"), rs.getString("fecha_solicitud"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public boolean insertar(Solicitud s) {
        if (s != null) {
            String queryInsert = "INSERT INTO solicitudes (id_empleado, asunto, descripcion, estado, fecha_solicitud)"
                    + " VALUES (?, ?, ?, 'pendiente', CURRENT_DATE);";
            try {
                sentenciaParametrizada = connection.prepareStatement(queryInsert);
                sentenciaParametrizada.setInt(1, s.getIdEmpleado());
                sentenciaParametrizada.setString(2, s.getAsunto());
                sentenciaParametrizada.setString(3, s.getDescripcion());
                sentenciaParametrizada.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean actualizarEstado(int id, String estado) {
        String queryUpdate = "UPDATE solicitudes SET estado = ? WHERE id = ?;";
        try {
            sentenciaParametrizada = connection.prepareStatement(queryUpdate);
            sentenciaParametrizada.setString(1, estado);
            sentenciaParametrizada.setInt(2, id);
            sentenciaParametrizada.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String queryDelete = "DELETE FROM solicitudes WHERE id = ?;";
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