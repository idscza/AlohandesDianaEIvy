package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC5 {

	@JsonProperty(value="tipoUsuario")
	private String tipoUsuario;

	@JsonProperty(value="dinero")
	private Double dinero;

	@JsonProperty(value="noches")
	private Integer noches;
	
	public RFC5(@JsonProperty(value="tipoUsuario")
				String tipoUsuario, 
				@JsonProperty(value="dinero")
				Double dinero,
				@JsonProperty(value="noches")
				Integer noches){
		
		this.tipoUsuario = tipoUsuario;
		this.dinero = dinero;
		this.noches = noches;	
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Double getDinero() {
		return dinero;
	}

	public void setDinero(Double dinero) {
		this.dinero = dinero;
	}

	public Integer getNoches() {
		return noches;
	}

	public void setNoches(Integer noches) {
		this.noches = noches;
	}

}
