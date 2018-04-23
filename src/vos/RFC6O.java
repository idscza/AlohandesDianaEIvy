package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC6O {
	
	@JsonProperty(value="operador")
	private Long operador;

	@JsonProperty(value="dineroGanado")
	private Double dineroGanado;

	@JsonProperty(value="nochesAlquiladas")
	private Integer nochesAlquiladas;

	public RFC6O(@JsonProperty(value="operador")
				Long operador,
				@JsonProperty(value="dineroGanado")
				Double dineroGanado,
				@JsonProperty(value="nochesAlquiladas")
				Integer nochesAlquiladas){

		this.operador = operador;
		this.dineroGanado = dineroGanado;
		this.nochesAlquiladas = nochesAlquiladas;
	}

	public Long getOperador() {
		return operador;
	}

	public void setOperador(Long operador) {
		this.operador = operador;
	}

	public Double getDineroGanado() {
		return dineroGanado;
	}

	public void setDineroGanado(Double dineroGanado) {
		this.dineroGanado = dineroGanado;
	}

	public Integer getNochesAlquiladas() {
		return nochesAlquiladas;
	}

	public void setNochesAlquiladas(Integer nochesAlquiladas) {
		this.nochesAlquiladas = nochesAlquiladas;
	}

}
