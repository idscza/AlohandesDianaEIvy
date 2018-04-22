package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC3 {

	@JsonProperty(value="operador")
	private Long operador;
	
	@JsonProperty(value="indiceOcupacion")
	private String indiceOcupacion;
	
	public RFC3(@JsonProperty(value="operador")
				Long operador,
				@JsonProperty(value="indiceOcupacion")
				String indiceOcupacion) {
		
		this.operador = operador;
		this.indiceOcupacion = indiceOcupacion;
	}

	public Long getOperador() {
		return operador;
	}

	public void setOperador(Long operador) {
		this.operador = operador;
	}

	public String getIndiceOcupacion() {
		return indiceOcupacion;
	}

	public void setIndiceOcupacion(String indiceOcupacion) {
		this.indiceOcupacion = indiceOcupacion;
	}
}
