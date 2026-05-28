package modelo;

public class Solicitud {

	// Atributos
	private int id;
	private int idEmpleado;
	private String asunto;
	private String descripcion;
	private String estado;
	private String fechaSolicitud;

	// Constructor vacio
	public Solicitud() {

	}

	// Constructor parametrizado
	public Solicitud(int id, int idEmpleado, String asunto, String descripcion, String estado, String fechaSolicitud) {
		this.id = id;
		this.idEmpleado = idEmpleado;
		this.asunto = asunto;
		this.descripcion = descripcion;
		this.estado = estado;
		this.fechaSolicitud = fechaSolicitud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

}