package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.*;

public class DAOUsuario {
	
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

	public DAOUsuario() {
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
	public ArrayList<Usuario> getUsuarios() throws SQLException, Exception {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.USUARIOS WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			usuarios.add(convertResultSetToUsuario(rs));
		}
		return usuarios;
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
	public Usuario findUsuarioById(Long id) throws SQLException, Exception 
	{
		Usuario usuario = null;

		String sql = String.format("SELECT * FROM %1$s.USUARIOS WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			usuario = convertResultSetToUsuario(rs);
		}

		return usuario;
	}
	
	/**
	 * Metodo que agregar la informacion de un nuevo bebedor en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param bebedor Bebedor que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addUsuario(Usuario usuario) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.USUARIOS (ID, LOGIN, CONTRASENIA, CEDULA, EDAD, NOMBRE, TELEFONO, TIPO) "
				+ "VALUES (%2$s, '%3$s', '%4$s', '%5$s', %6$s,'%7$s','%8$s', %9$s, '%10$s')", 
									USUARIO, 
									usuario.getId(), 
									usuario.getLogin(),
									usuario.getContrasenia(),
									usuario.getCedula(),
									usuario.getEdad(),
									usuario.getNombre(),
									usuario.getTelefono(),
									usuario.getOperador(),
									usuario.getTipo()
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
	public void updateUsuario(Usuario usuario) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.BEBEDORES SET ", USUARIO));
		sql.append(String.format("LOGIN = '%1$s' , CONTRASENIA = '%2$s' , CEDULA = '%3$s', EDAD = %4$s, NOMBRE = '%5$s', TELEFONO = '%6$s', OPERADOR = %7$s,TIPO = '%8$s' ", 
				usuario.getLogin(),
				usuario.getContrasenia(),
				usuario.getCedula(),
				usuario.getEdad(),
				usuario.getNombre(),
				usuario.getTelefono(),
				usuario.getOperador(),
				usuario.getTipo()
				));
		sql.append(String.format("WHERE ID = %s ", usuario.getId() ));
		
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
	public void deleteUsuario(Usuario usuario) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.USUARIOS WHERE ID = %2$d", USUARIO, usuario.getId());

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
	public Usuario convertResultSetToUsuario(ResultSet resultSet) throws SQLException {
		
		Long id = resultSet.getLong("ID");
		String login = resultSet.getString("LOGIN");
		String contrasenia = resultSet.getString("CONTRASENIA");
		String cedula = resultSet.getString("CEDULA");
		Integer edad = resultSet.getInt("EDAD");
		String nombre = resultSet.getString("NOMBRE");
		String telefono = resultSet.getString("TELEFONO");
		Long operador = resultSet.getLong("OPERADOR");
		String tipo = resultSet.getString("TIPO");

		Usuario user = new Usuario(id, login, contrasenia, cedula, edad, nombre, telefono, operador, tipo);

		return user;
	}

}
