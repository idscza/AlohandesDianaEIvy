package dao;

import java.sql.Connection;
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
