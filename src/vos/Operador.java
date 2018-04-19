package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Operador {
	
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="capacidad")
	private Integer capacidad;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="telefono")
	private String telefono;
	
	@JsonProperty(value="direccion")
	private String direccion;
	
	@JsonProperty(value="estrellas")
	private Integer estrellas;
	
	@JsonProperty(value="rut")
	private String rut;
	
	@JsonProperty(value="habDisponibles")
	private Integer habDisponibles;
	
	@JsonProperty(value="habOcupadas")
	private Integer habOcupadas;
	
	@JsonProperty(value="horaCierre")
	private Integer horaCierre;
	
	@JsonProperty(value="horaApertura")
	private Integer horaApertura;
	
	@JsonProperty(value="cedula")
	private String cedula;
	
	@JsonProperty(value="edad")
	private Integer edad;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	public Operador(
			@JsonProperty(value="id")
			Long id,
			@JsonProperty(value="capacidad")
			Integer capacidad,			
			@JsonProperty(value="nombre")
			String nombre,			
			@JsonProperty(value="telefono")
			String telefono,			
			@JsonProperty(value="direccion")
			String direccion,			
			@JsonProperty(value="estrellas")
			Integer estrellas,			
			@JsonProperty(value="rut")
			String rut,
			@JsonProperty(value="habDisponibles")
			Integer habDisponibles,
			@JsonProperty(value="habOcupadas")
			Integer habOcupadas,
			@JsonProperty(value="horaCierre")
			Integer horaCierre,
			@JsonProperty(value="horaApertura")
			Integer horaApertura,
			@JsonProperty(value="cedula")
			String cedula,
			@JsonProperty(value="edad")
			Integer edad,
			@JsonProperty(value="tipo")
			String tipo) {
		
		this.id = id;
		this.capacidad = capacidad;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.estrellas = estrellas;
		this.rut = rut;
		this.habDisponibles = habDisponibles;
		this.habOcupadas = habOcupadas;
		this.horaCierre = horaCierre;
		this.horaApertura = horaApertura;
		this.cedula = cedula;
		this.edad = edad;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(Integer estrellas) {
		this.estrellas = estrellas;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public Integer getHabDisponibles() {
		return habDisponibles;
	}

	public void setHabDisponibles(Integer habDisponibles) {
		this.habDisponibles = habDisponibles;
	}

	public Integer getHabOcupadas() {
		return habOcupadas;
	}

	public void setHabOcupadas(Integer habOcupadas) {
		this.habOcupadas = habOcupadas;
	}

	public Integer getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Integer horaCierre) {
		this.horaCierre = horaCierre;
	}

	public Integer getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Integer horaApertura) {
		this.horaApertura = horaApertura;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
