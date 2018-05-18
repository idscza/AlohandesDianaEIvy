package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Reserva {
	
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="cobro")
	private Double cobro;
	
	@JsonProperty(value="fechaRealizacion")
	private Date fechaRealizacion;
	
	@JsonProperty(value="fechaInicio")
	private Date fechaInicio;
	
	@JsonProperty(value="fechaFin")
	private Date fechaFin;
	
	@JsonProperty(value="personas")
	private Integer personas;
	
	@JsonProperty(value="operador")
	private Long operador;
	
	@JsonProperty(value="oferta")
	private Long oferta;
	
	@JsonProperty(value="cliente")
	private Long cliente;
	
	@JsonProperty(value="estado")
	private String estado;
	
	@JsonProperty(value="idMaestro")
	private Long idMaestro;
	
	public Reserva(
			@JsonProperty(value="id")
			Long id,
			@JsonProperty(value="cobro")
			Double cobro,
			@JsonProperty(value="fechaRealizacion")
			Date fechaRealizacion,
			@JsonProperty(value="fechaInicio")
			Date fechaInicio,
			@JsonProperty(value="fechaFin")
			Date fechaFin,
			@JsonProperty(value="personas")
			Integer personas,
			@JsonProperty(value="operador")
			Long operador,
			@JsonProperty(value="oferta")
			Long oferta,
			@JsonProperty(value="cliente")
			Long cliente,
			@JsonProperty(value="estado")
			String estado,
			@JsonProperty(value="idMaestro")
			Long idMaestro) {
		
		this.id = id;
		this.cobro = cobro;
		this.fechaRealizacion = fechaRealizacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.operador = operador;
		this.oferta = oferta;
		this.cliente = cliente;
		this.idMaestro = idMaestro;
		this.estado = estado;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCobro() {
		return cobro;
	}

	public void setCobro(Double cobro) {
		this.cobro = cobro;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
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

	public Integer getPersonas() {
		return personas;
	}

	public void setPersonas(Integer personas) {
		this.personas = personas;
	}

	public Long getOperador() {
		return operador;
	}

	public void setOperador(Long operador) {
		this.operador = operador;
	}

	public Long getOferta() {
		return oferta;
	}

	public void setOferta(Long oferta) {
		this.oferta = oferta;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public Long getIdMaestro() {
		return idMaestro;
	}

	public void setIdMaestro(Long idMaestro) {
		this.idMaestro = idMaestro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

}
