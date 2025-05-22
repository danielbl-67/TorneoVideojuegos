package TorneoVideojuegos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Clase CRUD con conectividad a la BBDD
 * @return
 * @throws SQLException
 */
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
public class JugadorDAO {
	
private Connection conexion;
/**
 * Clase que crea conexion con BBDD
 * @throws SQLException
 */
public  JugadorDAO() throws SQLException {
	conexion = Database.getConnection();
		if(conexion!=null) {
		try {
			Statement st=conexion.createStatement();
			ResultSet rs=st.executeQuery("Select database()");
		}catch(SQLException ex) {
			System.out.println("Error conectar al BaseDatos"+ ex.getMessage());
		}
	}
}
	
/**
 * Clase CRUD para crear/agregar un jugador 
 * @param nombre
 * @param email
 * @param nombreUsuario
 * @throws SQLException
 */
 public void agregarJugador(String nombre, String email, String nombreUsuario) throws SQLException {
    String sqlPersona = "INSERT INTO PERSONAS(nombre, email, tipo) VALUES (?, ?, 'jugador')";
    String sqlJugador = "INSERT INTO JUGADORES(id_jugador, nombre_usuario) VALUES (LAST_INSERT_ID(), ?)";
    try (Connection conn = Database.getConnection()) {
        conn.setAutoCommit(false);
        try (PreparedStatement ps1 = conn.prepareStatement(sqlPersona);
             PreparedStatement ps2 = conn.prepareStatement(sqlJugador)) {
            ps1.setString(1, nombre);
            ps1.setString(2, email);
            ps1.executeUpdate();

            ps2.setString(1, nombreUsuario);
            ps2.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }
}
 
/**
 * Clase CRUD para eliminar/borrar un jugador
 * @param idJugador
 * @throws SQLException
 */
public void eliminarJugador(int idJugador) throws SQLException {
    String sql = "DELETE FROM PERSONAS WHERE id_persona = ? AND tipo = 'jugador'";
    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idJugador);
        ps.executeUpdate();
    }
}

/**
 *  Clase CRUD para modificar/actualizar un jugador
 * @param idJugador
 * @param nombre
 * @param email
 * @param nombreUsuario
 * @throws SQLException
 */
public void modificarJugador(int idJugador, String nombre, String email, String nombreUsuario) throws SQLException {
    String sql1 = "UPDATE PERSONAS SET nombre = ?, email = ? WHERE id_persona = ?";
    String sql2 = "UPDATE JUGADORES SET nombre_usuario = ? WHERE id_jugador = ?";
    try (Connection conn = Database.getConnection()) {
        conn.setAutoCommit(false);
        try (PreparedStatement ps1 = conn.prepareStatement(sql1);
             PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            ps1.setString(1, nombre);
            ps1.setString(2, email);
            ps1.setInt(3, idJugador);
            ps1.executeUpdate();

            ps2.setString(1, nombreUsuario);
            ps2.setInt(2, idJugador);
            ps2.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }
}
/**
 * Clase que muestra todos los jugadores 
 * @return
 */
public List<Jugador> obtenerTodosLosJugadores() {
    List<Jugador> lista = new ArrayList<>();
    String sql = "SELECT j.id_jugador, p.nombre, p.email, j.nombre_usuario " +
                 "FROM JUGADORES j JOIN PERSONAS p ON j.id_jugador = p.id_persona";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Jugador j = new Jugador(0, sql, sql, sql);
            j.setId(rs.getInt("id_jugador"));
            j.setNombre(rs.getString("nombre"));
            j.setEmail(rs.getString("email"));
            j.setNombreUsuario(rs.getString("nombre_usuario"));
            lista.add(j);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener jugadores: " + e.getMessage());
    }
    return lista;
}

/**
 * Clase que filtra los jugadores por el nombre
 * @param texto
 */
public void filtrarJugadoresPorNombre(String texto) {
    List<Jugador> jugadores = obtenerTodosLosJugadores();

    List<Jugador> filtrados = jugadores.stream()
        .filter(j -> j.getNombreUsuario().toLowerCase().contains(texto.toLowerCase()))
        .collect(Collectors.toList());

    if (filtrados.isEmpty()) {
        System.out.println("No se encontraron jugadores que coincidan con: " + texto);
    } else {
        filtrados.forEach(j -> {
            System.out.println("ID: " + j.getId());
            System.out.println("Nombre: " + j.getNombre());
            System.out.println("Usuario: " + j.getNombreUsuario());
            System.out.println("Email: " + j.getEmail());
            System.out.println("------------------------");
        });
    }
}
/**
 * Clase que busca por id
 * @param id
 * @return
 */
public Jugador buscarJugadorPorId(int id) {
    String sql = "SELECT j.id_jugador, p.nombre, p.email, j.nombre_usuario " +
                 "FROM JUGADORES j JOIN PERSONAS p ON j.id_jugador = p.id_persona WHERE j.id_jugador = ?";
    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Jugador j = new Jugador(id, sql, sql, sql);
                j.setId(id);
                j.setNombre(rs.getString("nombre"));
                j.setEmail(rs.getString("email"));
                j.setNombreUsuario(rs.getString("nombre_usuario"));
                return j;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}







}

