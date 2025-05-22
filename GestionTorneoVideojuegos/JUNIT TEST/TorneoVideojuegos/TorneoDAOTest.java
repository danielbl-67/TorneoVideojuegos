package TorneoVideojuegos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TorneoDAOTest {
	 private TorneoDAO torneoDAO;

    @BeforeEach
    void setUp() throws SQLException {
    	torneoDAO = new TorneoDAO();
    }
	    
	@Test
	void testCrearTorneo() throws SQLException {
		 Torneo torneo = new Torneo(0,"Torneo RPG",Date.valueOf(LocalDate.of(2025, 7, 1)),
			        Date.valueOf(LocalDate.of(2025, 7, 10)),1);
		 		torneoDAO.crearTorneo(torneo);

			    Torneo recuperado = torneoDAO.buscarTorneoPorId(torneo.getId());
			    assertNotNull(recuperado, "El torneo debería haberse registrado y recuperado.");
			    assertEquals("Torneo RPG", recuperado.getNombre());
	}

	@Test
	void testEliminarTorneo() throws SQLException {
		 Torneo torneo = new Torneo(0,"Torneo RPG",Date.valueOf(LocalDate.of(2025, 7, 1)),
			        Date.valueOf(LocalDate.of(2025, 7, 10)),1);
	        torneoDAO.crearTorneo(torneo);

	        torneoDAO.eliminarTorneo(torneo.getId());

	        Torneo eliminado = torneoDAO.buscarTorneoPorId(torneo.getId());
	        assertNull(eliminado, "El torneo debería haber sido eliminado.");
	}

	@Test
	void testObtenerTodosLosTorneos() throws SQLException {
		Torneo torneo1 = new Torneo(0,"Torneo Uno", Date.valueOf(LocalDate.of(2025, 7, 1)),Date.valueOf(LocalDate.of(2025, 7, 10)),1);
		Torneo torneo2 = new Torneo(0,"Torneo Dos", Date.valueOf(LocalDate.of(2025, 7, 10)),Date.valueOf(LocalDate.of(2025, 7, 20)),2);
        torneoDAO.crearTorneo(torneo1);
        torneoDAO.crearTorneo(torneo2);

        List<Torneo> lista = torneoDAO.obtenerTodosLosTorneos();
        assertNotNull(lista);
        assertTrue(lista.size() >= 2, "Debería haber al menos 2 torneos registrados.");
	}

	@Test
	void testMostrarEstadisticasPorTorneo() {
		 int idTorneo = 1;
	        List<EstadisticaEquipo> estadisticas = torneoDAO.obtenerEstadisticasPorTorneo(idTorneo);
	        assertNotNull(estadisticas);
	        assertTrue(estadisticas.size() >= 0);  // Puede ser 0 si no hay partidas

	        estadisticas.forEach(e -> {
	            System.out.println("Equipo: " + e.getNombre());
	            System.out.println("Partidas jugadas: " + e.getPartidasJugadas());
	            System.out.println("Victorias: " + e.getVictorias());
	        });
	    }

}
