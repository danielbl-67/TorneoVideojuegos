package TorneoVideojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {
	private Connection conexion;
	/**
	 * Clase CRUD con conexion con BBDD
	 * @throws SQLException
	 */
	public  PartidaDAO() throws SQLException {
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
     * Metodo para poner en fecha una partida 
     * @param idTorneo
     * @param idEquipo1
     * @param idEquipo2
     * @param fecha
     * @throws SQLException
     */
    public void programarPartida(Partida partida) {
        String sql = "INSERT INTO PARTIDAS (id_torneo, id_equipo1, id_equipo2, fecha, resultado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, partida.getIdTorneo());
            ps.setInt(2, partida.getIdEquipo1());
            ps.setInt(3, partida.getIdEquipo2());
            ps.setDate(4, partida.getFecha());
            ps.setInt(5, partida.getResultado());  // puede ser null al programar
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                partida.setId(rs.getInt(1));  // guarda el id generado
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para registar el resultado final de la partida
     * @param idPartida
     * @param resultado
     * @throws SQLException
     */
    public void registrarResultado(int idPartida, String resultado) {
        String sql = "UPDATE PARTIDAS SET resultado = ? WHERE id_partida = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, resultado);
            ps.setInt(2, idPartida);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para mostrar las estadisticas por torneos 
     * @param idTorneo
     * @throws SQLException
     */
    public List<EstadisticaEquipo > obtenerEstadisticasPorTorneo(int idTorneo) {
        List<EstadisticaEquipo > lista = new ArrayList<>();

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
            	EstadisticaEquipo  e = new EstadisticaEquipo (
                    rs.getString("equipo"),
                    rs.getInt("partidas_jugadas"),
                    rs.getInt("victorias")
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return lista;
    }

    /**
     * Metodo que busca por id la partida
     * @param idPartida
     * @return
     */
    public Partida buscarPartidaPorId(int idPartida) {
        String sql = "SELECT * FROM PARTIDAS WHERE id_partida = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPartida);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Partida(
                    rs.getInt("id_partida"),
                    rs.getInt("id_torneo"),
                    rs.getInt("id_equipo1"),
                    rs.getInt("id_equipo2"),
                    rs.getDate("fecha"),
                    rs.getInt("resultado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }
}
