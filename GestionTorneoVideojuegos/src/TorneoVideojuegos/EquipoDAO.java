package TorneoVideojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {
	private Connection conexion;
	/**
	 * Clase CRUD con conexion con BBDD
	 * @throws SQLException
	 */
	public  EquipoDAO() throws SQLException {
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
     * Metodo CRUD para crear/añadir un equipo 
     * @param nombre
     * @param idOrganizador
     * @throws SQLException
     */
    public static void crearEquipo(Equipo equipo) {
        String sql = "INSERT INTO EQUIPOS (nombre, id_organizador) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, equipo.getNombre());
            ps.setInt(2, equipo.getIdOrganizador());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                equipo.setId(rs.getInt(1));  // guardar ID generado
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Metodo CRUD para modificar/actualizar un equipo 
     * @param idEquipo
     * @param nuevoNombre
     * @throws SQLException
     */
    public void modificarEquipo(Equipo equipo) {
        String sql = "UPDATE EQUIPOS SET nombre = ?, id_organizador = ? WHERE id_equipo = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, equipo.getNombre());
            ps.setInt(2, equipo.getIdOrganizador());
            ps.setInt(3, equipo.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Metodo CRUD para eliminar/borrar un equipo 
     * @param idEquipo
     * @throws SQLException
     */
    public void eliminarEquipo(int idEquipo) {
        String sql = "DELETE FROM EQUIPOS WHERE id_equipo = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEquipo);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
 * Metodo que muestra los equipos y numero de jugadores
 * @return
 */
    public List<EquipoInfo> obtenerEquiposConConteoDeJugadores() {
        List<EquipoInfo> lista = new ArrayList<>();

        String sql = "SELECT e.nombre AS nombre_equipo, COUNT(je.id_jugador) AS cantidad " +
                     "FROM EQUIPOS e " +
                     "LEFT JOIN JUGADORES_EQUIPOS je ON e.id_equipo = je.id_equipo " +
                     "GROUP BY e.nombre";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	EquipoInfo estadistica = new EquipoInfo(
                    rs.getString("nombre_equipo"),
                    rs.getInt("cantidad")
                );
                lista.add(estadistica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return lista;
    }

    
/**
 * Metodo que busca un equipo por id
 * @param idEquipo
 * @return
 */
    public Equipo buscarEquipoPorId(int idEquipo) {
        String sql = "SELECT * FROM EQUIPOS WHERE id_equipo = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setInt(1, idEquipo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Equipo(
                    rs.getInt("id_equipo"),
                    rs.getString("nombre"),
                    rs.getInt("id_organizador")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }
}
