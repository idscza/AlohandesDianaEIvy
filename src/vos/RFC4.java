package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC4 {
	@JsonProperty(value="alojamiento")
	private Long alojamiento;
	
	@JsonProperty(value="servicios")
	private Long servicios;
	
	@JsonProperty(value="fechaInicio")
	private Date fechaInicio;
  
	@JsonProperty(value="fechaFin")
	private Date fechaFin;
	
	
	
	

	public RFC4(@JsonProperty(value="alojamiento")Long alojamiento,
			@JsonProperty(value="servicios") Long servicios, 
			@JsonProperty(value="fechaInicio") Date fechaInicio,
			@JsonProperty(value="fechaFin") Date fechaFin) {
		this.alojamiento = alojamiento;
		this.servicios = servicios;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Long getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Long alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Long getServicios() {
		return servicios;
	}

	public void setServicios(Long servicios) {
		this.servicios = servicios;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	

}
