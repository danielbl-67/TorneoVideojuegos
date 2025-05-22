package TorneoVideojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JuegoDAO {
	/**
     * Metodo CRUD para crear/agregar un juego 
     * @param nombre
     * @param genero
     * @throws SQLException
     */
	public static void crearJuego(Juego juego) throws SQLException {
	    String sql = "INSERT INTO JUEGOS(nombre, genero) VALUES (?, ?)";
	    try (Connection conn = Database.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, juego.getNombre()); 
	        ps.setString(2, juego.getGenero());  
	        ps.executeUpdate();
	    }
	}

    /**
     * Metodo CRUD para modificar/actualizar un juego 
     * @param idJuego
     * @param nuevoNombre
     * @param nuevoGenero
     * @throws SQLException
     */
	public static void modificarJuego(Juego juego) throws SQLException {
	    String sql = "UPDATE JUEGOS SET nombre = ?, genero = ? WHERE id = ?";
	    try (Connection conn = Database.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, juego.getNombre());
	        ps.setString(2, juego.getGenero());
	        ps.setInt(3, juego.getId());  
	        ps.executeUpdate();
	    }
	}

    /**
     * Metodo CRUD para eliminar/borrar un juego 
     * @param idJuego
     * @throws SQLException
     */
    public void eliminarJuego(int idJuego) throws SQLException {
        String sql = "DELETE FROM JUEGOS WHERE id_juego = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idJuego);
            ps.executeUpdate();
        }
    }
    
    /**
     * Metodo que obtiene los juegos 
     * @return
     */
    public List<Juego> obtenerTodosLosJuegos() {
        List<Juego> lista = new ArrayList<>();
        String sql = "SELECT * FROM JUEGOS";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Juego j = new Juego(0, sql, sql);
                j.setId(rs.getInt("id_juego"));
                j.setNombre(rs.getString("nombre"));
                j.setGenero(rs.getString("genero"));
                lista.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    /**
     * Metodo que busca el juego por su id
     * @param id
     * @return
     */
    public Juego buscarJuegoPorId(int id) {
        String sql = "SELECT * FROM JUEGOS WHERE id_juego = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Juego(
                    rs.getInt("id_juego"),
                    rs.getString("nombre"),
                    rs.getString("genero")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return null; 
}
}
