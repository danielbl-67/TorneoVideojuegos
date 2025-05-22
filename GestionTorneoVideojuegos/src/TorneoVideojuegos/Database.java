package TorneoVideojuegos;

import java.sql.*;
/**
 * Clase que conecta la BBDD con el programa
 * @author Daniel
 * @serial 19/05/2025
 * @version 1.0
 * 
 */
public class Database {
	
  	private static final String URL = "jdbc:mysql://localhost:3306/TorneoVideojuego";
    private static final String USER = "root";
    private static final String PASS = "root";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
