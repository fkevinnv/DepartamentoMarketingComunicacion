package conexionBBDD;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionEmpleados {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

	private static final String HOST = "localhost";
    private static final String PUERTO = "4998"; 
    private static final String BBDD = "empleados"; 
    private static final String USUARIO = "administrador";    
    private static final String PASSWORD = "123456";
	private static final String URL = "jdbc:postgresql://" + HOST + ":" + PUERTO + "/" + BBDD;


	private Connection conexion = null;
	
	public Connection conectar() {
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			System.out.println("Conexión a BDDD OK");
		} catch (SQLException e) {
			System.err.println("Error en la conexión a BBDD");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexion;
	}
	
	public void cerrarConexion() {
		try {
			conexion.close();
			System.out.println("¡¡Conexión con BBDD cerrada!!");
		} catch (SQLException e) {
			System.err.println("Error al cerrar la BBDD");
			e.printStackTrace();
		}
	}

}
