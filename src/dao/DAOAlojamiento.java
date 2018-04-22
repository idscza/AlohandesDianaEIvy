package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Alojamiento;
import vos.Operador;

public class DAOAlojamiento {
	
	public final static String USUARIO = "ISIS2304A651810";
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	public DAOAlojamiento() {
		recursos = new ArrayList<Object>();
     }

	//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE COMUNICACION CON LA BASE DE DATOS
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Metodo que obtiene la informacion de todos los alojamiento en la Base de Datos <br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
		 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
		 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public ArrayList<Alojamiento> getAlojamientos() throws SQLException, Exception {
			ArrayList<Alojamiento> alojamiento = new ArrayList<Alojamiento>();

			//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			String sql = String.format("SELECT * FROM %1$s.ALOJAMIENTOS WHERE ROWNUM <= 50", USUARIO);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				alojamiento.add(convertResultSetToAlojamiento(rs));
			}
			return alojamiento;
		}

		/**
		 * Metodo que obtiene la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
		 * @param id el identificador del bebedor
		 * @return la informacion del bebedor que cumple con los criterios de la sentecia SQL
		 * 			Null si no existe el bebedor conlos criterios establecidos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public Alojamiento findAlojamientoById(Long id) throws SQLException, Exception 
		{
			Alojamiento alojamiento = null;

			String sql = String.format("SELECT * FROM %1$s.ALOJAMIENTOS WHERE ID = %2$d", USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				alojamiento = convertResultSetToAlojamiento(rs);
			}

			return alojamiento;
		}
		
		/**
		 * Metodo que agregar la informacion de un nuevo bebedor en la Base de Datos a partir del parametro ingresado<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param bebedor Bebedor que desea agregar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void addAlojamiento(Alojamiento alojamiento) throws SQLException, Exception {

			String sql = String.format("INSERT INTO %1$s.ALOJAMIENTOS (ID, CAPACIDAD, TAMANIO, MENAJE, AMOBLADO, NUMHABITACIONES, DIRECCION, DIASUSO,CATEGORIA, NUMEROHABITACION, OPERADOR, TIPO ) "
					+ "VALUES (%2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s, %9$s, '%10$s', '%11$s', %12$s, '%13$s')", 
										USUARIO, 
										alojamiento.getId(),
										alojamiento.getCapacidad(),
										alojamiento.getTamanio(),
										alojamiento.getMenaje(),
										alojamiento.getAmoblado(),
										alojamiento.getNumhabitaciones(),
										alojamiento.getDireccion(),
										alojamiento.getDiasuso(),
										alojamiento.getCategoria(),
										alojamiento.getNumerohabitacion(),
										alojamiento.getOperador(),
										alojamiento.getTipo()
										);
			System.out.println(sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
		
		/**
		 * Metodo que actualiza la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param bebedor Bebedor que desea actualizar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void updateAlojamiento(Alojamiento alojamiento) throws SQLException, Exception {

			StringBuilder sql = new StringBuilder();
			sql.append(String.format("UPDATE %s.ALOJAMIENTOS SET ", USUARIO));
			sql.append(String.format("CAPACIDAD = %1$s , TAMANIO =  %3$s, MENAJE = %4$s, AMOBLADO = %5$s, NUMHABUTACIONES = %6$s, DIRECCION = '%7$s' , DIASUSO =  %8$s, CATEGORIA = '%9$s', NUMEROHABITACION = '%10$s', OPERADOR = %11$s , TIPO = '%2$s' ", 
					alojamiento.getCapacidad(),
					alojamiento.getTipo(),
					alojamiento.getTamanio(),
					alojamiento.getMenaje(),
					alojamiento.getAmoblado(),
					alojamiento.getNumhabitaciones(),
					alojamiento.getDireccion(),
					alojamiento.getDiasuso(),
					alojamiento.getCategoria(),
					alojamiento.getNumerohabitacion(),
					alojamiento.getOperador()
					));
			sql.append(String.format("WHERE ID = %s ", alojamiento.getId() ));
			
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}

		/**
		 * Metodo que actualiza la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param bebedor Bebedor que desea actualizar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void deleteAlojamiento(Alojamiento alojamiento) throws SQLException, Exception {

			String sql = String.format("DELETE FROM %1$s.ALOJAMIENTOS WHERE ID = %2$d", USUARIO, alojamiento.getId());

			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
			
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS AUXILIARES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro <br/>
		 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
		 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
		 */
		public void setConn(Connection connection){
			this.conn = connection;
		}
		
		/**
		 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
		 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
		 */
		public void cerrarRecursos() {
			for(Object ob : recursos){
				if(ob instanceof PreparedStatement)
					try {
						((PreparedStatement) ob).close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
		}
		
		/**
		 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla BEBEDORES) en una instancia de la clase Bebedor.
		 * @param resultSet ResultSet con la informacion de un bebedor que se obtuvo de la base de datos.
		 * @return Alojamiento cuyos atributos corresponden a los valores asociados a un registro particular de la tabla BEBEDORES.
		 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
		 */
		public Alojamiento convertResultSetToAlojamiento (ResultSet resultSet) throws SQLException {
			
			Long id = resultSet.getLong("ID");
			Integer capacidad= resultSet.getInt("CAPACIDAD");
			String tipo= resultSet.getString("TIPO");
			Integer tamanio = resultSet.getInt("TAMANIO");
			Integer menaje = resultSet.getInt("MENAJE");
			Integer amoblado= resultSet.getInt("AMOBLADO");
			Integer numhabitaciones= resultSet.getInt("NUMMHABITACIONES");
			String direccion = resultSet.getString("DIRECCION");
			Integer diasuso = resultSet.getInt("DIASUSO");
			String categoria = resultSet.getString("CATEGORIA");
			String numerohabitacion = resultSet.getString("NUMEROHABITACION");
			Long operador = resultSet.getLong("OPERADOR");


			Alojamiento alojamiento = new Alojamiento(id, capacidad,  tamanio, menaje, amoblado, numhabitaciones, direccion, diasuso, categoria, numerohabitacion, operador, tipo);

			return alojamiento;
		}

}
