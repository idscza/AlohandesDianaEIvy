package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import dao.*;
import vos.*;

public class AloHandesTransactionManager {
	
	//----------------------------------------------------------------------------------------------------------------------------------
		// CONSTANTES
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Constante que contiene el path relativo del archivo que tiene los datos de la conexion
		 */
		private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

		/**
		 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
		 */
		private static String CONNECTION_DATA_PATH;
		

		//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
		 */
		private String user;

		/**
		 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
		 */
		private String password;

		/**
		 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
		 */
		private String url;

		/**
		 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
		 */
		private String driver;
		
		/**
		 * Atributo que representa la conexion a la base de datos
		 */
		private Connection conn;

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE CONEXION E INICIALIZACION
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * <b>Metodo Contructor de la Clase ParranderosTransactionManager</b> <br/>
		 * <b>Postcondicion: </b>	Se crea un objeto  ParranderosTransactionManager,
		 * 						 	Se inicializa el path absoluto del archivo de conexion,
		 * 							Se inicializna los atributos para la conexion con la Base de Datos
		 * @param contextPathP Path absoluto que se encuentra en el servidor del contexto del deploy actual
		 * @throws IOException Se genera una excepcion al tener dificultades con la inicializacion de la conexion<br/>
		 * @throws ClassNotFoundException 
		 */
		public AloHandesTransactionManager(String contextPathP) {
			
			try {
				CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
				initializeConnectionData();
			} 
			catch (ClassNotFoundException e) {			
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Metodo encargado de inicializar los atributos utilizados para la conexion con la Base de Datos.<br/>
		 * <b>post: </b> Se inicializan los atributos para la conexion<br/>
		 * @throws IOException Se genera una excepcion al no encontrar el archivo o al tener dificultades durante su lectura<br/>
		 * @throws ClassNotFoundException 
		 */
		private void initializeConnectionData() throws IOException, ClassNotFoundException {

			FileInputStream fileInputStream = new FileInputStream(new File(AloHandesTransactionManager.CONNECTION_DATA_PATH));
			Properties properties = new Properties();
			
			properties.load(fileInputStream);
			fileInputStream.close();
			
			this.url = properties.getProperty("url");
			this.user = properties.getProperty("usuario");
			this.password = properties.getProperty("clave");
			System.out.println(password);
			this.driver = properties.getProperty("driver");
			
			//Class.forName(driver);
		}

		/**
		 * Metodo encargado de generar una conexion con la Base de Datos.<br/>
		 * <b>Precondicion: </b>Los atributos para la conexion con la Base de Datos han sido inicializados<br/>
		 * @return Objeto Connection, el cual hace referencia a la conexion a la base de datos
		 * @throws SQLException Cualquier error que se pueda llegar a generar durante la conexion a la base de datos
		 */
		private Connection darConexion() throws SQLException {
			System.out.println("[ALOHANDES APP] Attempting Connection to: " + url + " - By User: " + user);
			return DriverManager.getConnection(url, user, password);
		}

		public List<Usuario> getAllUsuarios() throws Exception{
			DAOUsuario daoUsuario = new DAOUsuario();
			List<Usuario> usuarios;
			try 
			{
				this.conn = darConexion();
				daoUsuario.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				usuarios = daoUsuario.getUsuarios();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoUsuario.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return usuarios;
		}

		public Usuario getUsuarioById(Long id) throws Exception{
			DAOUsuario daoUsuario = new DAOUsuario();
			Usuario usuario = null;
			try 
			{
				this.conn = darConexion();
				daoUsuario.setConn(conn);
				usuario = daoUsuario.findUsuarioById(id);
				if(usuario == null)
				{
					throw new Exception("El Usuario con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoUsuario.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return usuario;
		}

		public void addUsuario(Usuario usuario) throws Exception{
			DAOUsuario daoUsuario = new DAOUsuario( );
			try
			{
				this.conn = darConexion();
				daoUsuario.setConn(conn);
				daoUsuario.addUsuario(usuario);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoUsuario.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
		}

		public void updateUsuario(Usuario usuario) throws Exception{
			DAOUsuario daoUsuario = new DAOUsuario( );
			try
			{
				this.conn = darConexion();
				daoUsuario.setConn( conn );
				Usuario exists = daoUsuario.findUsuarioById(usuario.getId());
				if(exists != null) {
					daoUsuario.updateUsuario(usuario);
				}else
					throw new Exception("Este Usuario no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoUsuario.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}

		public void deleteUsuario(Usuario usuario) throws Exception{
			DAOUsuario daoUsuario = new DAOUsuario( );
			try
			{
				this.conn = darConexion();
				daoUsuario.setConn( conn );
				Usuario exists = daoUsuario.findUsuarioById(usuario.getId());
				if(exists != null) {
					daoUsuario.deleteUsuario(usuario);
				}else
					throw new Exception("Este Usuario no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoUsuario.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
			
		}

		public List<Reserva> getAllReservas() throws Exception{
			DAOReserva daoReserva = new DAOReserva();
			List<Reserva> reservas;
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				reservas = daoReserva.getReservas();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reservas;
		}

		public Reserva getReservaById(Long id) throws Exception{
			DAOReserva daoReserva = new DAOReserva();
			Reserva reserva = null;
			try 
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				reserva = daoReserva.findReservaById(id);
				if(reserva == null)
				{
					throw new Exception("La reserva con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return reserva;
		}

		public void addReserva(Reserva reserva) throws Exception{
			DAOReserva daoReserva = new DAOReserva( );
			try
			{
				this.conn = darConexion();
				daoReserva.setConn(conn);
				daoReserva.addReserva(reserva);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
		}

		public void updateReserva(Reserva reserva) throws Exception{
			DAOReserva daoReserva = new DAOReserva( );
			try
			{
				this.conn = darConexion();
				daoReserva.setConn( conn );
				Reserva exists = daoReserva.findReservaById(reserva.getId());
				if(exists != null) {
					daoReserva.updateReserva(reserva);
				}else
					throw new Exception("Esta Reserva no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
			
		}

		public void deleteReserva(Reserva reserva) throws Exception{
			DAOReserva daoReserva = new DAOReserva( );
			try
			{
				this.conn = darConexion();
				daoReserva.setConn( conn );
				Reserva exists = daoReserva.findReservaById(reserva.getId());
				if(exists != null) {
					daoReserva.deleteReserva(reserva);
				}else
					throw new Exception("Esta Reserva no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
			
		}
		
		public void cancelarReserva(Long id, boolean cobrar) throws Exception{
			DAOReserva daoReserva = new DAOReserva( );
			try
			{
				this.conn = darConexion();
				daoReserva.setConn( conn );
				Reserva exists = daoReserva.findReservaById(id);
				if(exists != null) {
					
					double cobro = 0;
					if(cobrar) {
						
						Date hoy = Calendar.getInstance().getTime();
						if(exists.getFechaInicio().compareTo(hoy) <= 0) {
							
							cobro = getOfertaById(exists.getOferta()).getCosto() * 0.5;
						}
						else {
							
							long duracion = exists.getFechaFin().getTime() - exists.getFechaInicio().getTime(); 
							if(duracion > 691200000) {
								
								if(exists.getFechaInicio().getTime()-hoy.getTime() > 691200000) {
									cobro = getOfertaById(exists.getOferta()).getCosto() * 0.1;
								}
								else {
									cobro = getOfertaById(exists.getOferta()).getCosto() * 0.3;
								}	
							}
							else {
								
								if(exists.getFechaInicio().getTime()-hoy.getTime() > 345600000) {
									cobro = getOfertaById(exists.getOferta()).getCosto() * 0.1;
								}
								else {
									cobro = getOfertaById(exists.getOferta()).getCosto() * 0.3;
								}
								
							}
						}	
						
					}
					daoReserva.cancelarReserva(id,cobro);
				}else
					throw new Exception("Esta Reserva no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoReserva.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
			
		}
		
		public List<Operador> getAllOperadores() throws Exception{
			DAOOperador daoOperador = new DAOOperador();
			List<Operador> operadores;
			try 
			{
				this.conn = darConexion();
				daoOperador.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				operadores = daoOperador.getOperadores();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operadores;
		}

		public Operador getOperadorById(Long id) throws Exception{
			DAOOperador daoOperador = new DAOOperador();
			Operador operador = null;
			try 
			{
				this.conn = darConexion();
				daoOperador.setConn(conn);
				operador = daoOperador.findOperadorById(id);
				if(operador == null)
				{
					throw new Exception("El Operador con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operador;
		}

		public void addOperador(Operador operador) throws Exception{
			DAOOperador daoOperador = new DAOOperador( );
			try
			{
				this.conn = darConexion();
				daoOperador.setConn(conn);
				daoOperador.addOperador(operador);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
		}

		public void updateOperador(Operador operador) throws Exception{
			DAOOperador daoOperador = new DAOOperador( );
			try
			{
				this.conn = darConexion();
				daoOperador.setConn( conn );
				Operador exists = daoOperador.findOperadorById(operador.getId());
				if(exists != null) {
					daoOperador.updateOperador(operador);
				}else
					throw new Exception("Este Operador no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}

		public void deleteOperador(Operador operador) throws Exception{
			DAOOperador daoOperador = new DAOOperador( );
			try
			{
				this.conn = darConexion();
				daoOperador.setConn( conn );
				Operador exists = daoOperador.findOperadorById(operador.getId());
				if(exists != null) {
					daoOperador.deleteOperador(operador);
				}else
					throw new Exception("Este Operador no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
			
		}

		public List<RFC2> getOfertasPopulares() throws Exception{
			DAOOferta daoOferta = new DAOOferta();
			List<RFC2> ofertas;
			try 
			{
				this.conn = darConexion();
				daoOferta.setConn(conn);
				
				ofertas = daoOferta.getOfertasPopulares();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOferta.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return ofertas;
		}

		
		
		public List<RFC1> getGananciaOperadores() throws Exception{
			DAOOperador daoOperador = new DAOOperador();
			List<RFC1> operadores;
			try 
			{
				this.conn = darConexion();
				daoOperador.setConn(conn);
				
				operadores = daoOperador.getGananciaOperadores();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operadores;
		}
		
		public List<RFC4> getAlojamientoConServicios() throws Exception{
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
			List<RFC4> alojamientos;
			try 
			{
				this.conn = darConexion();
				daoAlojamiento.setConn(conn);
				
				alojamientos = daoAlojamiento.getAlojamientoConServicios();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return alojamientos;
		}
		
		
		public List<RFC3> getIndiceOcupacion() throws Exception{
			DAOOperador daoOperador = new DAOOperador();
			List<RFC3> operadores;
			try 
			{
				this.conn = darConexion();
				daoOperador.setConn(conn);
				
				operadores = daoOperador.getIndiceOcupacion();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOperador.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return operadores;
		}
		
		
		
		public List<Alojamiento> getAllAlojamientos() throws Exception{
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
			List<Alojamiento> alojamientos;
			try 
			{
				this.conn = darConexion();
				daoAlojamiento.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				alojamientos = daoAlojamiento.getAlojamientos();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return alojamientos;
		}
		
		public Alojamiento getAlojamientoById(Long id) throws Exception{
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
			Alojamiento alojamiento = null;
			try 
			{
				this.conn = darConexion();
				daoAlojamiento.setConn(conn);
				alojamiento = daoAlojamiento.findAlojamientoById(id);
				if(alojamiento == null)
				{
					throw new Exception("El Alojamiento con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return alojamiento;
		}
		
		public void addAlojamiento(Alojamiento alojamiento) throws Exception{
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
			try
			{
				this.conn = darConexion();
				daoAlojamiento.setConn(conn);
				daoAlojamiento.addAlojamiento(alojamiento);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
		}
		
		public void updateAlojamiento(Alojamiento alojamiento) throws Exception{
			DAOAlojamiento daoAlojamiento= new DAOAlojamiento( );
			try
			{
				this.conn = darConexion();
				daoAlojamiento.setConn( conn );
				Alojamiento exists = daoAlojamiento.findAlojamientoById(alojamiento.getId());
				if(exists != null) {
					daoAlojamiento.updateAlojamiento(alojamiento);
				}else
					throw new Exception("Este Alojamiento no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}

		public void deleteAlojamiento(Alojamiento alojamiento) throws Exception{
			DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
			try
			{
				this.conn = darConexion();
				daoAlojamiento.setConn( conn );
				Alojamiento exists = daoAlojamiento.findAlojamientoById(alojamiento.getId());
				if(exists != null) {
					daoAlojamiento.deleteAlojamiento(alojamiento);
				}else
					throw new Exception("Este Alojamiento no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoAlojamiento.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
			
		}
		
		
		public List<Servicio> getAllServicios() throws Exception{
			DAOServicios daoServicios= new DAOServicios();
			List<Servicio> servicios;
			try 
			{
				this.conn = darConexion();
				daoServicios.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				servicios = daoServicios.getServicios();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoServicios.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return servicios;
		}
		
		public Servicio getServicioById(Long id) throws Exception{
			DAOServicios daoServicios = new DAOServicios();
			Servicio servicio= null;
			try 
			{
				this.conn = darConexion();
				daoServicios.setConn(conn);
				servicio = daoServicios.findServicioById(id);
				if(servicio == null)
				{
					throw new Exception("El servicio con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoServicios.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return servicio;
		}

		public void addServicio(Servicio servicio) throws Exception{
			DAOServicios daoServicios= new DAOServicios( );
			try
			{
				this.conn = darConexion();
				daoServicios.setConn(conn);
				daoServicios.addServicio(servicio);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoServicios.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
		}

		public void updateServicio(Servicio servicio) throws Exception{
			DAOServicios daoServicios = new DAOServicios( );
			try
			{
				this.conn = darConexion();
				daoServicios.setConn( conn );
				Servicio exists = daoServicios.findServicioById(servicio.getId());
				if(exists != null) {
					daoServicios.updateServicio(servicio);
				}else
					throw new Exception("Este Servicio  no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoServicios.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}

		public void deleteServicio(Servicio servicio) throws Exception{
			DAOServicios  daoServicios = new DAOServicios( );
			try
			{
				this.conn = darConexion();
				daoServicios.setConn( conn );
				Servicio exists = daoServicios.findServicioById(servicio.getId());
				if(exists != null) {
					daoServicios.deleteServicio(servicio);
				}else
					throw new Exception("Este Servicio no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoServicios.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}
		
		
		
		public List<Oferta> getAllOfertas() throws Exception{
			DAOOferta daoOferta = new DAOOferta();
			List<Oferta> oferta;
			try 
			{
				this.conn = darConexion();
				daoOferta.setConn(conn);
				
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				oferta = daoOferta.getOfertas();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOferta.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return oferta;
		}

		public Oferta getOfertaById(Long id) throws Exception{
			DAOOferta daoOferta = new DAOOferta();
			Oferta oferta = null;
			try 
			{
				this.conn = darConexion();
				daoOferta.setConn(conn);
				oferta = daoOferta.findOfertaById(id);
				if(oferta == null)
				{
					throw new Exception("El Oferta con el id = " + id + " no se encuentra persistido en la base de datos.");				
				}
			} 
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOferta.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return oferta;
		}

		public void addOferta(Oferta oferta) throws Exception{
			DAOOferta daoOferta = new DAOOferta( );
			try
			{
				this.conn = darConexion();
				daoOferta.setConn(conn);
				daoOferta.addOferta(oferta);

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOferta.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			
		}

		public void updateOferta(Oferta oferta) throws Exception{
			DAOOferta daoOferta = new DAOOferta( );
			try
			{
				this.conn = darConexion();
				daoOferta.setConn( conn );
				Oferta exists = daoOferta.findOfertaById(oferta.getId());
				if(exists != null) {
					daoOferta.updateOferta(oferta);
				}else
					throw new Exception("Este Oferta no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOferta.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
		}

		public void deleteOferta(Oferta oferta) throws Exception{
			DAOOferta daoOferta = new DAOOferta( );
			try
			{
				this.conn = darConexion();
				daoOferta.setConn( conn );
				Oferta exists = daoOferta.findOfertaById(oferta.getId());
				if(exists != null) {
					daoOferta.deleteOferta(oferta);
				}else
					throw new Exception("Este Oferta no se encuentra en la base de datos");

			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					daoOferta.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}	
			
		}
}
