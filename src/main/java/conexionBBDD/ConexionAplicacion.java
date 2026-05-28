package conexionBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionAplicacion {
    private static final String DRIVER = "org.postgresql.Driver";
    
    private static final String HOST = "localhost";
    private static final String PUERTO = "9334"; 
    private static final String BBDD = "aplicacion_db"; 
    private static final String USUARIO = "administrador";    
    private static final String PASSWORD = "123456";
    
    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PUERTO + "/" + BBDD;

    private Connection conexion = null;
    
    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión a BDDD OK con usuario alumno");
        } catch (SQLException e) {
            System.err.println("Error de login o puerto: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL no encontrado");
        }
        return conexion;
    }
    
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
