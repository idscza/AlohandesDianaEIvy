package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Servicio {
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	   
	/**
	 * id del servicio
	 */
	@JsonProperty(value = "id")
	private Long id;
	
	/**
	 * costo del servicio
	 */
	@JsonProperty(value = "costo")
	private Double costo;
	
	/**
	 * descripcion del servicio
	 */
	@JsonProperty(value = "descripcion")
	private String descripcion;
	
	/**
	 * nombre del servicio
	 */
	@JsonProperty(value = "nombre")
	private String nombre;
	
	/**
	 * oferta relacionada al servicio
	 */
	@JsonProperty(value = "oferta")
	private Long oferta;

	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @param id
	 * @param costo
	 * @param descripcion
	 * @param nombre
	 * @param oferta
	 */
	public Servicio(@JsonProperty(value = "id")Long id, 
			@JsonProperty(value = "costo") Double costo, 
			@JsonProperty(value = "descripcion")String descripcion, 
			@JsonProperty(value = "nombre")String nombre,
			@JsonProperty(value = "oferta")Long oferta) {
		this.id = id;
		this.costo = costo;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.oferta = oferta;
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
	 * @return the costo
	 */
	public Double getCosto() {
		return costo;
	}


	/**
	 * @param costo the costo to set
	 */
	public void setCosto(Double costo) {
		this.costo = costo;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the oferta
	 */
	public Long getOferta() {
		return oferta;
	}


	/**
	 * @param oferta the oferta to set
	 */
	public void setOferta(Long oferta) {
		this.oferta = oferta;
	}
	
	
	

}
