package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC2 {
	
	@JsonProperty(value="oferta")
	private Long oferta;
	
	@JsonProperty(value="populares")
	private Integer populares;
	
	public RFC2(@JsonProperty(value="oferta")
				Long oferta,
				@JsonProperty(value="populares")
				Integer populares) 
	{
		this.oferta = oferta;
		this.populares = populares;
		
	}

	public Long getOferta() {
		return oferta;
	}

	public void setOferta(Long oferta) {
		this.oferta = oferta;
	}

	public Integer getPopulares() {
		return populares;
	}

	public void setPopulares(Integer populares) {
		this.populares = populares;
	}

}
