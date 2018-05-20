package vos;

import org.codehaus.jackson.annotate.*;

public class Usuario {
	
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="login")
	private String login;
	
	@JsonProperty(value="contrasenia")
	private String contrasenia;
	
	@JsonProperty(value="cedula")
	private String cedula;
	
	@JsonProperty(value="edad")
	private Integer edad;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="telefono")
	private String telefono;
	
	@JsonProperty(value="genero")
	private String genero;
	
	@JsonProperty(value="ciudad")
	private String ciudad;
	
	@JsonProperty(value="operador")
	private Long operador;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	public Usuario(@JsonProperty(value="id")Long id,
			@JsonProperty(value="login")
			String login,
			@JsonProperty(value="contrasenia")
			String contrasenia,
			@JsonProperty(value="cedula")
			String cedula,
			@JsonProperty(value="edad")
			Integer edad,
			@JsonProperty(value="nombre")
	 		String nombre,
	 		@JsonProperty(value="telefono")
			String telefono,
			@JsonProperty(value="genero")
			String genero,
			@JsonProperty(value="ciudad")
			String ciudad,
			@JsonProperty(value="operador")
			Long operador,
			@JsonProperty(value="tipo")
	 		String tipo) {

		this.id = id;
		this.login = login;
		this.contrasenia = contrasenia;
		this.cedula = cedula;
		this.edad = edad;
		this.nombre = nombre;
		this.telefono = telefono;
		this.genero = genero;
		this.ciudad = ciudad;
		this.operador = operador;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Long getOperador() {
		return operador;
	}
	
	public void setOperador(Long operador) {
		this.operador = operador;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
