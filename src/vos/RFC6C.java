package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC6C {

	@JsonProperty(value="cliente")
	private Long cliente;

	@JsonProperty(value="dineroPagado")
	private Double dineroPagado;

	@JsonProperty(value="nochesPasadas")
	private Integer nochesPasadas;

	public RFC6C(@JsonProperty(value="cliente")
				Long cliente,
				@JsonProperty(value="dineroPagado")
				Double dineroPagado,
				@JsonProperty(value="nochesPasadas")
				Integer nochesPasadas){

		this.cliente = cliente;
		this.dineroPagado = dineroPagado;
		this.nochesPasadas = nochesPasadas;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public Double getDineroPagado() {
		return dineroPagado;
	}

	public void setDineroPagado(Double dineroPagado) {
		this.dineroPagado = dineroPagado;
	}

	public Integer getNochesPasadas() {
		return nochesPasadas;
	}

	public void setNochesPasadas(Integer nochesPasadas) {
		this.nochesPasadas = nochesPasadas;
	}
}
