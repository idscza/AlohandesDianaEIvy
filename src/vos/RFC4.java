package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC4 {
	@JsonProperty(value="alojamiento")
	private Long alojamiento;
	
	@JsonProperty(value="oferta")
	private Long oferta;


	public RFC4(@JsonProperty(value="alojamiento")Long alojamiento,
			@JsonProperty(value="oferta") Long oferta
			) {
		this.alojamiento = alojamiento;
		this.oferta = oferta;
	}

	public Long getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Long alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Long getOferta() {
		return oferta;
	}

	public void setOferta(Long oferta) {
		this.oferta = oferta;
	}

	

}
