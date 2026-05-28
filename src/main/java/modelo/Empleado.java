package modelo;

public class Empleado {

	private int id;
	private String nombre;
	private String apellidos;
	private String correo;
	private String contrasena;
	private int idSede;
	private String rol;

	public Empleado() {
	}

	public Empleado(int id, String nombre, String apellidos, String correo, String contrasena, int idSede, String rol) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contrasena = contrasena;
		this.idSede = idSede;
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getIdSede() {
		return idSede;
	}

	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}
