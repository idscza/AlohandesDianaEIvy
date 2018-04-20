package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Alojamiento {

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
    
	/**
     * id del alojamiento 
     */
	@JsonProperty(value="id")
	private Long id;
	
	/**
	 * capacidad maxima del alojamiento
	 */
	@JsonProperty(value = "capacidad")
	private int capacidad;
	
	/**
	 * tipo de alojamiento
	 */
	@JsonProperty(value = "tipo")
	private String tipo;
	
	/**
	 * tamaño e metros cuadrados dedl alojamiento
	 */
	@JsonProperty(value = "tamanio")
	private int tamanio;
	
	/**
	 * indica si el alojamiento tiene menaje
	 */
	@JsonProperty(value = "menaje")
	private int menaje;
	
	/**
	 * indica si el alojamiento está amoblado
	 */
	@JsonProperty(value = "amoblado")
	private int amoblado;
	
	
	/**
	 * numero de habitaciones del alojamiento
	 */
	@JsonProperty(value = "numhabitaciones")
	private int numhabitaciones;
	
	/**
	 * dirección del alojamiento
	 */
	@JsonProperty(value = "direccion")
	private String direccion;
	
	/**
	 * dias de uso que ha tenido el inmueble
	 */
	@JsonProperty(value = "diasuso")
	private int diasuso;
	
	/**
	 * Categoria de la habitacion
	 */
	@JsonProperty(value = "categoria")
	private String categoria;
	
	/**
	 * numero de la habitacion
	 */
	@JsonProperty(value = "numerohabitacion")
	private String numerohabitacion;
	
	/**
	 * operador a cargo del alojamiento
	 */
	@JsonProperty(value = "operador")
	private Long operador;

	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de los alojamientos
	 * @param id
	 * @param capacidad
	 * @param tipo
	 * @param tamanio
	 * @param menaje
	 * @param amoblado
	 * @param numhabitaciones
	 * @param direccion
	 * @param diasuso
	 * @param categoria
	 * @param numerohabitacion
	 * @param operador2
	 */
	public Alojamiento(@JsonProperty(value="id") Long id, 
			@JsonProperty(value = "capacidad")int capacidad,
			@JsonProperty(value = "tipo") String tipo,
			@JsonProperty(value = "tamanio")int tamanio,
			@JsonProperty(value = "menaje") int menaje,
			@JsonProperty(value = "amoblado")int amoblado, 
	        @JsonProperty(value = "numhabitaciones")int numhabitaciones, 
	        @JsonProperty(value = "direccion")String direccion,
	        @JsonProperty(value = "diasuso") int diasuso, 
	        @JsonProperty(value = "categoria")String categoria,
	        @JsonProperty(value = "numerohabitacion") String numerohabitacion,
	        @JsonProperty(value = "operador") Long operador2) {
		this.id = id;
		this.capacidad = capacidad;
		this.tipo = tipo;
		this.tamanio = tamanio;
		this.menaje = menaje;
		this.amoblado = amoblado;
		this.numhabitaciones = numhabitaciones;
		this.direccion = direccion;
		this.diasuso = diasuso;
		this.categoria = categoria;
		this.numerohabitacion = numerohabitacion;
		this.operador = operador2;
	}

	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------
			

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}
     
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tamanio
	 */
	public int getTamanio() {
		return tamanio;
	}



	/**
	 * @param tamanio the tamanio to set
	 */
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}



	/**
	 * @return the menaje
	 */
	public int getMenaje() {
		return menaje;
	}



	/**
	 * @param menaje the menaje to set
	 */
	public void setMenaje(int menaje) {
		this.menaje = menaje;
	}

	/**
	 * @return the amoblado
	 */
	public int getAmoblado() {
		return amoblado;
	}

	/**
	 * @param amoblado the amoblado to set
	 */
	public void setAmoblado(int amoblado) {
		this.amoblado = amoblado;
	}

	/**
	 * @return the numhabitaciones
	 */
	public int getNumhabitaciones() {
		return numhabitaciones;
	}



	/**
	 * @param numhabitaciones the numhabitaciones to set
	 */
	public void setNumhabitaciones(int numhabitaciones) {
		this.numhabitaciones = numhabitaciones;
	}



	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}



	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	/**
	 * @return the diasuso
	 */
	public int getDiasuso() {
		return diasuso;
	}



	/**
	 * @param diasuso the diasuso to set
	 */
	public void setDiasuso(int diasuso) {
		this.diasuso = diasuso;
	}



	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}



	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	/**
	 * @return the numerohabitacion
	 */
	public String getNumerohabitacion() {
		return numerohabitacion;
	}

	/**
	 * @param numerohabitacion the numerohabitacion to set
	 */
	public void setNumerohabitacion(String numerohabitacion) {
		this.numerohabitacion = numerohabitacion;
	}

	/**
	 * @return the operador
	 */
	public Long getOperador() {
		return operador;
	}

	/**
	 * @param operador the operador to set
	 */
	public void setOperador(Long operador) {
		this.operador = operador;
	}
	
}
