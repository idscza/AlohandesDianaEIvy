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
	
		String sql = String.format("INSERT INTO %1$s.OFERTAS (ID, COSTO, FECHARETIRO, NOMBRE, OPERADOR, ALOJAMIENTO, DESHABILITADA) "
				+ "VALUES (%2$s, %3$s, '%4$s', '%5$s', %6$s, %7$s, %8$s)", 
									USUARIO, 
									oferta.getId(), 
									oferta.getCosto(),
									oferta.getFecharetiro(),
									oferta.getNombre(),
									oferta.getOperador(),
									oferta.getAlojamiento(),
									oferta.getDeshabilitada()
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
		sql.append(String.format("COSTO = %1$s , FECHARETIRO = '%2$s' , NOMBRE = '%3$s', OPERADOR = %4$s, ALOJAMIENTO = %5$s, DESHABILITADA = %6$s ", 
				oferta.getCosto(),
				oferta.getFecharetiro(),
				oferta.getNombre(),
				oferta.getOperador(),
				oferta.getAlojamiento(),
				oferta.getDeshabilitada()
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
	
	public void retirarOferta(Oferta oferta, Date fecharetiro) throws SQLException, Exception {
		
		String[] hue = fecharetiro.toString().split("-");
		
		String hue1 = hue[2]+"/"+hue[1]+"/"+hue[0];
		
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.OFERTAS SET ", USUARIO));
		sql.append(String.format("FECHARETIRO = '%1$s' ", 
				hue1
				));
		sql.append(String.format("WHERE ID = %s ", oferta.getId() ));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Reserva buscarReservasActivas(Oferta oferta) throws SQLException, Exception {
		Reserva reserva = null;
	
		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.RESERVAS WHERE OFERTA = %2$s and ESTADO = 'activa' and ROWNUM = 1 ORDER BY FECHAFIN DESC", USUARIO, oferta.getId() );
	
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
	
		while (rs.next()) {
			reserva = convertResultSetToReserva(rs);
		}
		return reserva;
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
		Integer costo = resultSet.getInt("COSTO");
		Date fecharetiro = resultSet.getDate("FECHARETIRO");
		String nombre = resultSet.getString("NOMBRE");
		Long operador = resultSet.getLong("OPERADOR");
		Long alojamiento = resultSet.getLong("ALOJAMIENTO");
		Integer deshabilitada = resultSet.getInt("DESHABILITADA");

		Oferta user = new Oferta (id, costo, fecharetiro, nombre, operador, alojamiento,deshabilitada);

		return user;
	}
	
	public Reserva convertResultSetToReserva(ResultSet resultSet) throws SQLException {
		
		Long id = resultSet.getLong("ID");
		Double cobro = resultSet.getDouble("COBRO");
		Date fechaRealizacion= resultSet.getDate("FECHAREALIZACION");
		Date fechaInicio = resultSet.getDate("FECHAINICIO");
		Date fechaFin = resultSet.getDate("FECHAFIN");
		Integer personas = resultSet.getInt("PERSONAS");
		Long operador = resultSet.getLong("OPERADOR");
		Long oferta = resultSet.getLong("OFERTA");
		Long cliente = resultSet.getLong("CLIENTE");
		String estado = resultSet.getString("ESTADO");
		Long idMaestro = resultSet.getLong("IDMAESTRO");

		Reserva reserva = new Reserva(id, cobro, fechaRealizacion, fechaInicio, fechaFin, personas, operador, oferta, cliente, estado, idMaestro);

		return reserva;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	//REQUERIMENTO DE CONSULTA RFC2
	
	public ArrayList<RFC2> getOfertasPopulares() throws SQLException, Exception {
		ArrayList<RFC2> ofertas = new ArrayList<RFC2>();
	
		String sql = String.format("SELECT * FROM "
				+ "(SELECT OFERTA , COUNT(OFERTA) AS POPULARES FROM %1$s.RESERVAS GROUP BY OFERTA ORDER BY POPULARES DESC) LASPOPULARES " + 
				"WHERE ROWNUM <= 20", USUARIO);
		
		System.out.println(sql);
	
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

	public ArrayList<Oferta> findOfertasValidas(String hospedaje, String[] losServicios, String inicio, String fin) throws Exception {
		
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		
		int goal = losServicios.length;
		StringBuilder sql = new StringBuilder();
		
		sql.append(String.format("select oferta from %1$s.alojamientos join ", USUARIO));
		sql.append(" (select alojamiento, oferta from (Select alojamiento, oferta, count(nombre) as servicios from (select * from (Select id, nombre, oferta, operador, alojamiento ");
		sql.append(String.format(" From %1$s.SERVICIOS join (SELECT OFERTAS.id as elid, ofertas.operador, OFERTAS.ALOJAMIENTO,filtro.fecharealizacion FROM %1$s.OFERTAS LEFT OUTER JOIN", USUARIO));
		sql.append(String.format("(SELECT * FROM RESERVAS WHERE FECHAFIN > '%1$s' and FECHAINICIO <= '%2$s' and ESTADO = 'activa' )FILTRO ON OFERTAS.ID = FILTRO.OFERTA WHERE deshabilitada = 0) hello on elid = servicios.oferta where fecharealizacion is null)validos ", inicio,fin));
		sql.append(String.format("where nombre = '%1$s' ", losServicios[0]));
		
		int i = 1;
		while(i<goal){
			sql.append(String.format("or nombre = '%1$s' ", losServicios[i]));
			i++;
		}
		sql.append(String.format("group by alojamiento, oferta) where servicios = %1$s )final on alojamiento = id ",goal));
		sql.append(String.format("where tipo = '%1$s'", hospedaje));

		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			Long machete = rs.getLong("OFERTA");
			ofertas.add(findOfertaById(machete));
		}
		return ofertas;
	}

	public void habilitarOferta(Long id) throws SQLException {
		
		
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.OFERTAS SET ", USUARIO));
		sql.append("DESHABILITADA = 0 "); 
		sql.append(String.format("WHERE ID = %s ", id ));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
}
