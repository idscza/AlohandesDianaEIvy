package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Oferta {

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
    
	@JsonProperty(value="id")
	private String id;
	
	@JsonProperty(value="costo")
	private int costo;
	
	@JsonProperty(value="fecharetiro")
	private String fecharetiro;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="operador")
	private String operador;
	
	@JsonProperty(value="alojamiento")
	private Alojamiento alojamiento;

	
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
	public Oferta(String id, int costo, String fecharetiro, String nombre,
			String operador, Alojamiento alojamiento) {
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
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the costo
	 */
	public int getCosto() {
		return costo;
	}


	/**
	 * @param costo the costo to set
	 */
	public void setCosto(int costo) {
		this.costo = costo;
	}


	/**
	 * @return the fecharetiro
	 */
	public String getFecharetiro() {
		return fecharetiro;
	}


	/**
	 * @param fecharetiro the fecharetiro to set
	 */
	public void setFecharetiro(String fecharetiro) {
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
	public String getOperador() {
		return operador;
	}


	/**
	 * @param operador the operador to set
	 */
	public void setOperador(String operador) {
		this.operador = operador;
	}


	/**
	 * @return the alojamiento
	 */
	public Alojamiento getAlojamiento() {
		return alojamiento;
	}


	/**
	 * @param alojamiento the alojamiento to set
	 */
	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}
	
	
	
	
}
