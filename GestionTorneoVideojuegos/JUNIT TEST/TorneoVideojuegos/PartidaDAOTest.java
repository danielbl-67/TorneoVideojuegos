package TorneoVideojuegos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartidaDAOTest {
	private PartidaDAO partidaDAO;
	@BeforeEach
    void setUp() throws SQLException {
	 	partidaDAO= new PartidaDAO();
    }

	@Test
	void testProgramarPartida() {
		Partida partida = new Partida(0, 1, 1, 2, Date.valueOf(LocalDate.now()), (Integer) null);
		partidaDAO.programarPartida(partida);

        Partida recuperada = partidaDAO.buscarPartidaPorId(partida.getId());
        assertNotNull(recuperada, "La partida debería haberse programado y recuperado.");
        assertEquals(1, recuperada.getIdEquipo1());
	}

	@Test
	void testRegistrarResultado() {
		Partida partida = new Partida(0, 1, 1, 2, Date.valueOf(LocalDate.now()), (Integer) null);
		partidaDAO.programarPartida(partida);

		partidaDAO.registrarResultado(partida.getId(), "Equipo 1");

        Partida actualizada = partidaDAO.buscarPartidaPorId(partida.getId());
        assertEquals("Equipo 1", actualizada.getResultado());
	}

}
