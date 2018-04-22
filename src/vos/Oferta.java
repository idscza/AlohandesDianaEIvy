package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Oferta {

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
    
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="costo")
	private Integer costo;
	
	@JsonProperty(value="fecharetiro")
	private Date fecharetiro;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="operador")
	private Long operador;
	
	@JsonProperty(value="alojamiento")
	private Long alojamiento;

	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @param id
	 * @param costo
	 * @param fecharetiro
	 * @param nombre
	 * @param operador
	 * @param alojamiento
	 */
	public Oferta(@JsonProperty(value="id") Long id,
			@JsonProperty(value="costo")Integer costo, 
			@JsonProperty(value="fecharetiro")Date fecharetiro,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="operador") Long operador, 
			@JsonProperty(value="alojamiento")Long alojamiento) {
		this.id = id;
		this.costo = costo;
		this.fecharetiro = fecharetiro;
		this.nombre = nombre;
		this.operador = operador;
		this.alojamiento = alojamiento;
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
	public Integer getCosto() {
		return costo;
	}


	/**
	 * @param costo the costo to set
	 */
	public void setCosto(Integer costo) {
		this.costo = costo;
	}


	/**
	 * @return the fecharetiro
	 */
	public Date getFecharetiro() {
		return fecharetiro;
	}


	/**
	 * @param fecharetiro the fecharetiro to set
	 */
	public void setFecharetiro(Date fecharetiro) {
		this.fecharetiro = fecharetiro;
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


	/**
	 * @return the alojamiento
	 */
	public Long getAlojamiento() {
		return alojamiento;
	}


	/**
	 * @param alojamiento the alojamiento to set
	 */
	public void setAlojamiento(Long alojamiento) {
		this.alojamiento = alojamiento;
	}
	
	
	
	
}
