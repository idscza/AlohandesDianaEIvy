package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import vos.*;

public class DAOOperador {
	
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

	public DAOOperador() {
			recursos = new ArrayList<Object>();
	}
	
	/**
	 * Metodo que obtiene la informacion de todos los bebedores en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Operador> getOperadores() throws SQLException, Exception {
		ArrayList<Operador> operadores = new ArrayList<Operador>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.OPERADORES WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			operadores.add(convertResultSetToOperador(rs));
		}
		return operadores;
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
	public Operador findOperadorById(Long id) throws SQLException, Exception 
	{
		Operador operador = null;

		String sql = String.format("SELECT * FROM %1$s.OPERADORES WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			operador = convertResultSetToOperador(rs);
		}

		return operador;
	}
	
	/**
	 * Metodo que agregar la informacion de un nuevo bebedor en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param bebedor Bebedor que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addOperador(Operador operador) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.OPERADORES (ID, CAPACIDAD, NOMBRE, TELEFONO, DIRECCION, ESTRELLAS, RUT, HABDISPONIBLES, HABOCUPADAS, HORACIERRE, HORAAPERTURA, CEDULA, EDAD, TIPO) "
				+ "VALUES (%2$s, %3$s, '%4$s', '%5$s', '%6$s', %7$s, '%8$s', %9$s, %10$s, %11$s, %12$s, '%13$s', %14$s, '%15$s')", 
									USUARIO, 
									operador.getId(),
									operador.getCapacidad(),
									operador.getNombre(),
									operador.getTelefono(),
									operador.getDireccion(),
									operador.getEstrellas(),
									operador.getRut(),
									operador.getHabDisponibles(),
									operador.getHabOcupadas(),
									operador.getHoraCierre(),
									operador.getHoraApertura(),
									operador.getCedula(),
									operador.getEdad(),
									operador.getTipo()
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
	public void updateOperador(Operador operador) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.OPERADORES SET ", USUARIO));
		sql.append(String.format("CAPACIDAD = %2$s, NOMBRE = '%3$s', TELEFONO = '%4$s', DIRECCION = '%5$s', ESTRELLAS = %6$s, RUT = '%6$s', HABDISPONIBLES = %7$s, HABOCUPADAS = %8$s, HORACIERRE = %9$s, HORAAPERTURA = %10$s, CEDULA = '%11$s', EDAD = %12$s, TIPO = '%13$s'", 
				operador.getCapacidad(),
				operador.getNombre(),
				operador.getTelefono(),
				operador.getDireccion(),
				operador.getEstrellas(),
				operador.getRut(),
				operador.getHabDisponibles(),
				operador.getHabOcupadas(),
				operador.getHoraCierre(),
				operador.getHoraApertura(),
				operador.getCedula(),
				operador.getEdad(),
				operador.getTipo()
				));
		sql.append(String.format("WHERE ID = %s ", operador.getId() ));
		
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
	public void deleteOperador(Operador operador) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.OPERADORES WHERE ID = %2$d", USUARIO, operador.getId());

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
	 * @return Bebedor cuyos atributos corresponden a los valores asociados a un registro particular de la tabla BEBEDORES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Operador convertResultSetToOperador(ResultSet resultSet) throws SQLException {
		
		Long id = resultSet.getLong("ID");
		Integer capacidad = resultSet.getInt("CAPACIDAD");
		String nombre = resultSet.getString("NOMBRE");
		String telefono = resultSet.getString("TELEFONO");
		String direccion = resultSet.getString("DIRECCION");
		Integer estrellas = resultSet.getInt("ESTRELLAS");
		String rut = resultSet.getString("RUT");
		Integer habDisponibles = resultSet.getInt("HABDISPONIBLES");
		Integer habOcupadas = resultSet.getInt("HABOCUPADAS");
		Integer horaCierre = resultSet.getInt("HORACIERRE");
		Integer horaApertura = resultSet.getInt("HORAAPERTURA");
		String cedula = resultSet.getString("CEDULA");
		Integer edad = resultSet.getInt("EDAD");
		String tipo = resultSet.getString("TIPO");

		Operador operador = new Operador(id, capacidad, nombre, 
				telefono, direccion, estrellas, rut, habDisponibles, habOcupadas, 
				horaCierre, horaApertura, cedula, edad, tipo);

		return operador;
	}
	
	//REQUERIMENTO DE CONSULTA RFC1

		public ArrayList<RFC1> getGananciaOperadores() throws SQLException, Exception {
			ArrayList<RFC1> operadores = new ArrayList<RFC1>();

			String sql = String.format("SELECT OPERADOR, SUM(COBRO) AS GANANCIAS " + 
					"FROM " + 
					"(SELECT OPERADOR, COBRO FROM %1$s.RESERVAS " + 
					"WHERE FECHAFIN > '1/1/2018')FILTRO " + 
					"GROUP BY OPERADOR " + 
					"ORDER  BY GANANCIAS DESC", USUARIO);

			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				operadores.add(convertResultSetToRFC1(rs));
			}
			return operadores;
		}
		
		public ArrayList<RFC3> getIndiceOcupacion() throws SQLException, Exception {
			ArrayList<RFC3> operadores = new ArrayList<RFC3>();

			Date xd = new Date();
			String d = ""+xd.getDate();
			String m = ""+(xd.getMonth()+1);
			String a = ""+(xd.getYear()-100);
			String fecha = d+"/"+m+"/"+a;
			
			String sql = String.format("SELECT operador, floor((sum(case when fecharealizacion is null then 0 else 1 end) )/ (count(elid))*100)||' Por ciento' Indiceocupacion " + 
					"from(SELECT OFERTAS.id as elid, ofertas.operador ,filtro.fecharealizacion FROM %1$s.OFERTAS LEFT OUTER JOIN " + 
					"(SELECT * FROM %1$s.RESERVAS WHERE FECHAFIN > '%2$s' and FECHAINICIO <= '%2$s' and ESTADO = 'activa' )FILTRO " + 
					"ON OFERTAS.ID = FILTRO.OFERTA)info group by operador", USUARIO, fecha);

			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				operadores.add(convertResultSetToRFC3(rs));
			}
			return operadores;
		}
		
		

		public RFC1 convertResultSetToRFC1(ResultSet rs) throws SQLException {
		
			Long operador = rs.getLong("OPERADOR");
			Double ganancia = rs.getDouble("GANANCIA");
			
			RFC1 req = new RFC1(operador,ganancia);
			return req;
		}
		
		public RFC3 convertResultSetToRFC3(ResultSet rs) throws SQLException {
			
			Long operador = rs.getLong("OPERADOR");
			String indiceOcupacion = rs.getString("INDICEOCUPACION");
			
			RFC3 req = new RFC3(operador,indiceOcupacion);
			return req;
		}
		
		

}
