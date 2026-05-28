package modelo;

public class Mensaje {
	
	//atributos de mensaje 
	private int id;
	private int idSolicitud;
	private int idEmpleado;
	private String contenido;
	private String fechaEnvio;
	
	
	//constructor vacio 
	public Mensaje() {
		
	}
	
	public Mensaje(int id, int idSolicitud, int idEmpleado, String contenido, String fechaEnvio) {
		this.id = id;
		this.idSolicitud = idSolicitud;
		this.idEmpleado = idEmpleado;
		this.contenido = contenido;
		this.fechaEnvio = fechaEnvio;
	}

	// getters y setters de los atributos 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	

}
