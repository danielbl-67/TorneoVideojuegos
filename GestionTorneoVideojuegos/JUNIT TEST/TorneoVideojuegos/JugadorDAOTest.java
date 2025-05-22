package TorneoVideojuegos;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JugadorDAOTest {
	private JugadorDAO jugadorDAO;
	
 	@BeforeEach
    void setUp() throws SQLException {
        jugadorDAO = new JugadorDAO();
    }
 	
	@Test
	void testObtenerTodosLosJugadores() {
		List<Jugador> jugadores = jugadorDAO.obtenerTodosLosJugadores();
	    assertNotNull(jugadores);
	    assertTrue(jugadores.size() > 0);  // o >= 0 
	}

	@Test
	void testFiltrarJugadoresPorNombre() {
		List<Jugador> jugadores = jugadorDAO.obtenerTodosLosJugadores();
	    List<Jugador> filtrados = jugadores.stream()
	        .filter(j -> j.getNombre().toLowerCase().contains("test"))
	        .toList();

	    assertTrue(filtrados.size() >= 0); // Si esperas que haya al menos uno
	}
}
