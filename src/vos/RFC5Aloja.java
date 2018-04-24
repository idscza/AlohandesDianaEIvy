package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC5Aloja {
	
	//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------
	    
		@JsonProperty(value = "tipoOperador")
		private String tipoOperador;
	
		/**
	     * id del alojamiento 
	     */
		@JsonProperty(value="id")
		private Long id;
		
		/**
		 * capacidad maxima del alojamiento
		 */
		@JsonProperty(value = "capacidad")
		private Integer capacidad;
		
		
		/**
		 * tamaño e metros cuadrados dedl alojamiento
		 */
		@JsonProperty(value = "tamanio")
		private Integer tamanio;
		
		/**
		 * indica si el alojamiento tiene menaje
		 */
		@JsonProperty(value = "menaje")
		private Integer menaje;
		
		/**
		 * indica si el alojamiento está amoblado
		 */
		@JsonProperty(value = "amoblado")
		private Integer amoblado;
		
		
		/**
		 * numero de habitaciones del alojamiento
		 */
		@JsonProperty(value = "numhabitaciones")
		private Integer numhabitaciones;
		
		/**
		 * dirección del alojamiento
		 */
		@JsonProperty(value = "direccion")
		private String direccion;
		
		/**
		 * dias de uso que ha tenido el inmueble
		 */
		@JsonProperty(value = "diasuso")
		private Integer diasuso;
		
		/**
		 * Categoria de la habitacion
		 */
		@JsonProperty(value = "categoria")
		private String categoria;
		
		/**
		 * numero de la habitacion
		 */
		@JsonProperty(value = "numerohabitacion")
		private String numerohabitacion;
		
		/**
		 * operador a cargo del alojamiento
		 */
		@JsonProperty(value = "operador")
		private Long operador;
		
		/**
		 * tipo de alojamiento
		 */
		@JsonProperty(value = "tipo")
		private String tipo;

		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Metodo constructor de los alojamientos
		 * @param id
		 * @param capacidad
		 * @param tipo
		 * @param tamanio
		 * @param menaje
		 * @param amoblado
		 * @param numhabitaciones
		 * @param direccion
		 * @param diasuso
		 * @param categoria
		 * @param numerohabitacion
		 * @param operador2
		 */
		public RFC5Aloja(@JsonProperty(value = "tipoOperador")String tipoOperador,
				@JsonProperty(value="id") Long id, 
				@JsonProperty(value = "capacidad")Integer capacidad,
				@JsonProperty(value = "tamanio")Integer tamanio,
				@JsonProperty(value = "menaje") Integer menaje,
				@JsonProperty(value = "amoblado")Integer amoblado, 
		        @JsonProperty(value = "numhabitaciones")Integer numhabitaciones, 
		        @JsonProperty(value = "direccion")String direccion,
		        @JsonProperty(value = "diasuso") Integer diasuso, 
		        @JsonProperty(value = "categoria")String categoria,
		        @JsonProperty(value = "numerohabitacion") String numerohabitacion,
		        @JsonProperty(value = "operador") Long operador,
				@JsonProperty(value = "tipo") String tipo) {
			
			this.tipoOperador = tipoOperador;
			this.id = id;
			this.capacidad = capacidad;
			this.tipo = tipo;
			this.tamanio = tamanio;
			this.menaje = menaje;
			this.amoblado = amoblado;
			this.numhabitaciones = numhabitaciones;
			this.direccion = direccion;
			this.diasuso = diasuso;
			this.categoria = categoria;
			this.numerohabitacion = numerohabitacion;
			this.operador = operador;
		}

		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------
				

		public String getTipoOperador() {
			return tipoOperador;
		}


		public void setTipoOperador(String tipoOperador) {
			this.tipoOperador = tipoOperador;
		}


		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * @return the capacidad
		 */
		public Integer getCapacidad() {
			return capacidad;
		}
	     
		/**
		 * @param capacidad the capacidad to set
		 */
		public void setCapacidad(Integer capacidad) {
			this.capacidad = capacidad;
		}

		/**
		 * @return the tipo
		 */
		public String getTipo() {
			return tipo;
		}

		/**
		 * @param tipo the tipo to set
		 */
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		/**
		 * @return the tamanio
		 */
		public Integer getTamanio() {
			return tamanio;
		}



		/**
		 * @param tamanio the tamanio to set
		 */
		public void setTamanio(Integer tamanio) {
			this.tamanio = tamanio;
		}



		/**
		 * @return the menaje
		 */
		public Integer getMenaje() {
			return menaje;
		}



		/**
		 * @param menaje the menaje to set
		 */
		public void setMenaje(Integer menaje) {
			this.menaje = menaje;
		}

		/**
		 * @return the amoblado
		 */
		public Integer getAmoblado() {
			return amoblado;
		}

		/**
		 * @param amoblado the amoblado to set
		 */
		public void setAmoblado(Integer amoblado) {
			this.amoblado = amoblado;
		}

		/**
		 * @return the numhabitaciones
		 */
		public Integer getNumhabitaciones() {
			return numhabitaciones;
		}



		/**
		 * @param numhabitaciones the numhabitaciones to set
		 */
		public void setNumhabitaciones(Integer numhabitaciones) {
			this.numhabitaciones = numhabitaciones;
		}



		/**
		 * @return the direccion
		 */
		public String getDireccion() {
			return direccion;
		}



		/**
		 * @param direccion the direccion to set
		 */
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}



		/**
		 * @return the diasuso
		 */
		public Integer getDiasuso() {
			return diasuso;
		}



		/**
		 * @param diasuso the diasuso to set
		 */
		public void setDiasuso(Integer diasuso) {
			this.diasuso = diasuso;
		}



		/**
		 * @return the categoria
		 */
		public String getCategoria() {
			return categoria;
		}



		/**
		 * @param categoria the categoria to set
		 */
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}



		/**
		 * @return the numerohabitacion
		 */
		public String getNumerohabitacion() {
			return numerohabitacion;
		}

		/**
		 * @param numerohabitacion the numerohabitacion to set
		 */
		public void setNumerohabitacion(String numerohabitacion) {
			this.numerohabitacion = numerohabitacion;
		}

		/**
		 * @return the operador
		 */
		public Long getOperador() {
			return operador;
		}

		/**
		 * @param operador the operador to set
		 */
		public void setOperador(Long operador) {
			this.operador = operador;
		}

}
