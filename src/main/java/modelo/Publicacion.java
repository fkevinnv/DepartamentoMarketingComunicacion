package modelo;

public class Publicacion {
	
	//Atributos de la publicacion 
	private int id;
	private String titulo;
	private String tipo;
	private String estado;
	private String fechaCreacion;
	private String contenido;
	private int idSede;
	private int idAutor;

//Constructor vacio 
	public Publicacion() {		
	}

//Constructor con parametros 
	
	public Publicacion(int id, String titulo, String tipo, String estado, String fechaCreacion, String contenido, int idSede) {
		this.id = id;
		this.titulo = titulo;
		this.tipo = tipo;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.contenido = contenido;
		this.idSede = idSede;
	}
	
	
//Getters y setters 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getIdSede() {
		return idSede;
	}

	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	
}
