package TorneoVideojuegos;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TorneoDAO {
	private Connection conexion;
	/**
	 * Clase CRUD con conexion con BBDD
	 * @throws SQLException
	 */
	public  TorneoDAO() throws SQLException {
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
     * Metodo CRUD para crear/añadir un torneo 
     * @param nombre
     * @param fechaInicio
     * @param fechaFin
     * @param idJuego
     * @throws SQLException
     */
    public static void crearTorneo(Torneo torneo) throws SQLException {
        String sql = "INSERT INTO TORNEOS(nombre, fecha_inicio, fecha_fin, id_juego) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, torneo.getNombre());
            ps.setDate(2, new Date(torneo.getFechaInicio().getTime()));
            ps.setDate(3, new Date(torneo.getFechaFin().getTime()));
            ps.setInt(4, torneo.getIdJuego());
            ps.executeUpdate();
        }
    }

    /**
     * Metodo CRUD para modificar/actualizar un torneo 
     * @param idTorneo
     * @param nombre
     * @param hoy
     * @param hoy2
     * @param idJuego
     * @throws SQLException
     */
    public void modificarTorneo(int idTorneo, String nombre, java.util.Date hoy, java.util.Date hoy2, int idJuego) throws SQLException {
        String sql = "UPDATE TORNEOS SET nombre = ?, fecha_inicio = ?, fecha_fin = ?, id_juego = ? WHERE id_torneo = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setDate(2, new java.sql.Date(hoy.getTime()));
            ps.setDate(3, new java.sql.Date(hoy2.getTime()));
            ps.setInt(4, idJuego);
            ps.setInt(5, idTorneo);
            ps.executeUpdate();
        }
    }

    /**
     * Metodo CRUD para eliminar/borrar un torneo 
     * @param idTorneo
     * @throws SQLException
     */
    public void eliminarTorneo(int idTorneo) throws SQLException {
        String sql = "DELETE FROM TORNEOS WHERE id_torneo = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTorneo);
            ps.executeUpdate();
        }
    }
    
/**
 * Metodo para obtener los torneos
 * @param torneoId 
 * @return
 */
    public List<Torneo> obtenerTodosLosTorneos() {
        List<Torneo> lista = new ArrayList<>();
        String sql = "SELECT * FROM TORNEOS";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Torneo t = new Torneo(0, sql, null, null, 0);
                t.setId(rs.getInt("id_torneo"));
                t.setNombre(rs.getString("nombre"));
                t.setFechaInicio(rs.getDate("fecha_inicio"));
                t.setFechaFin(rs.getDate("fecha_fin"));
                t.setIdJuego(rs.getInt("id_juego"));
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    /**
     * Metodo que busca los torneos por su id
     * @param id
     * @return
     */
    public Torneo buscarTorneoPorId(int id) {
        String sql = "SELECT * FROM TORNEOS WHERE id_torneo = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Torneo(
        		rs.getInt("id_torneo"),
    	        rs.getString("nombre"),
    	        rs.getDate("fecha_inicio"),  
    	        rs.getDate("fecha_fin"),    
    	        rs.getInt("id_juego")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }
    /**
     * Metodo que muestra loos torneo estadisticamente
     * @param idTorneo
     * @return
     */
    public List<EstadisticaEquipo> obtenerEstadisticasPorTorneo(int idTorneo) {
        List<EstadisticaEquipo> lista = new ArrayList<>();

        String sql = "SELECT e.nombre AS equipo, " +
                     "COUNT(*) AS partidas_jugadas, " +
                     "SUM(CASE WHEN p.resultado = e.nombre THEN 1 ELSE 0 END) AS victorias " +
                     "FROM PARTIDAS p " +
                     "JOIN EQUIPOS e ON p.id_equipo1 = e.id_equipo OR p.id_equipo2 = e.id_equipo " +
                     "WHERE p.id_torneo = ? " +
                     "GROUP BY e.nombre";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTorneo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new EstadisticaEquipo(
                    rs.getString("equipo"),
                    rs.getInt("partidas_jugadas"),
                    rs.getInt("victorias")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return lista;
    }

    
}

