package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC1 {
	
	
	@JsonProperty(value="operador")
	private Long operador;
	
	@JsonProperty(value="ganancias")
	private Double ganancias;
	
	public RFC1(@JsonProperty(value="operador")
				Long operador,
				@JsonProperty(value="ganancias")
				Double ganancias) 
	{
		this.operador = operador;
		this.ganancias = ganancias; 
		
	}

	public Long getOperador() {
		return operador;
	}

	public void setOperador(Long operador) {
		this.operador = operador;
	}

	public Double getGanancias() {
		return ganancias;
	}

	public void setGanancias(Double ganancias) {
		this.ganancias = ganancias;
	}
}
