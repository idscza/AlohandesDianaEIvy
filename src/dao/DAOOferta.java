package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.*;

public class DAOOferta {
	
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

	public DAOOferta() {
		recursos = new ArrayList<Object>();
    }
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que obtiene la informacion de todos los bebedores en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Oferta> getOfertas() throws SQLException, Exception {
		ArrayList<Oferta> oferta = new ArrayList<Oferta>();
	
		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.OFERTAS WHERE ROWNUM <= 50", USUARIO);
	
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
	
		while (rs.next()) {
			oferta.add(convertResultSetToOferta(rs));
		}
		return oferta;
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
	public Oferta findOfertaById(Long id) throws SQLException, Exception 
	{
		Oferta oferta = null;
	
		String sql = String.format("SELECT * FROM %1$s.OFERTAS WHERE ID = %2$d", USUARIO, id); 
	
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
	
		if(rs.next()) {
			oferta = convertResultSetToOferta(rs);
		}
	
		return oferta;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo bebedor en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param bebedor Bebedor que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addOferta(Oferta oferta) throws SQLException, Exception {
	
		String sql = String.format("INSERT INTO %1$s.OFERTAS (ID, COSTO, FECHARETIRO, NOMBRE, OPERADOR, ALOJAMIENTO) "
				+ "VALUES (%2$s, %3$s, '%4$s', '%5$s', %6$s, %7$s)", 
									USUARIO, 
									oferta.getId(), 
									oferta.getCosto(),
									oferta.getFecharetiro(),
									oferta.getNombre(),
									oferta.getAlojamiento()
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
	public void updateOferta(Oferta oferta) throws SQLException, Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.OFERTAS SET ", USUARIO));
		sql.append(String.format("COSTO = %1$s , FECHARETIRO = '%2$s' , NOMBRE = '%3$s', OPERADOR = %4$s, ALOJAMIENTO = %5$s ", 
				oferta.getCosto(),
				oferta.getFecharetiro(),
				oferta.getNombre(),
				oferta.getAlojamiento()
				));
		sql.append(String.format("WHERE ID = %s ", oferta.getId() ));
		
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
	public void deleteOferta(Oferta oferta) throws SQLException, Exception {
	
		String sql = String.format("DELETE FROM %1$s.OFERTAS WHERE ID = %2$d", USUARIO, oferta.getId());
	
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

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
	public Oferta convertResultSetToOferta(ResultSet resultSet) throws SQLException {
		
		Long id = resultSet.getLong("ID");
		int costo = resultSet.getInt("COSTO");
		Date fecharetiro = resultSet.getDate("FECHARETIRO");
		String nombre = resultSet.getString("NOMBRE");
		Long operador = resultSet.getLong("OPERADOR");
		Long alojamiento = resultSet.getLong("ALOJAMIENTO");

		Oferta user = new Oferta (id, costo, fecharetiro, nombre, operador, alojamiento);

		return user;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	//REQUERIMENTO DE CONSULTA RFC2
	
	public ArrayList<RFC2> getOfertasPopulares() throws SQLException, Exception {
		ArrayList<RFC2> ofertas = new ArrayList<RFC2>();
	
		String sql = String.format("SELECT * FROM "
				+ "(SELECT OFERTA , COUNT(OFERTAS) AS POPULARES FROM %1$s.RESERVAS GROUP BY OFERTA ORDER BY POPULARES DESC)" + 
				"WHERE ROWNUM <= 20;", USUARIO);
	
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
	
		while (rs.next()) {
			ofertas.add(convertResultSetToRFC2(rs));
		}
		return ofertas;
	}

	public RFC2 convertResultSetToRFC2(ResultSet rs) throws SQLException {
	
		Long oferta = rs.getLong("OFERTA");
		Integer populares = rs.getInt("POPULARES");
		
		RFC2 req = new RFC2(oferta,populares);
		return req;
	}
}
