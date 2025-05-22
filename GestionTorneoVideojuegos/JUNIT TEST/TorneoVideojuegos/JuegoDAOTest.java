package TorneoVideojuegos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JuegoDAOTest {
	private JuegoDAO juegoDAO;
	 @BeforeEach
	    void setUp() throws SQLException {
		 	juegoDAO= new JuegoDAO();
	    }
	
	@Test
	void testCrearJuego() throws SQLException {
		JuegoDAO juegoDAO = new JuegoDAO();
	    Juego juego = new Juego(0, "League of Legends", "MOBA");
	    try {
	    	juegoDAO.crearJuego(juego);
	    } catch (Exception e) {
	        fail("No se pudo crear el juego: " + e.getMessage());
	    }
	}

	@Test
	void testModificarJuego() throws SQLException {
		 	Juego juego= new Juego(0, "Juego modificar", "Aventura");
		 	JuegoDAO.crearJuego(juego);

		 	juego.setNombre("Juego actualizado");
		 	juego.setGenero("Rol");
		 	juegoDAO.modificarJuego(juego);

	        Juego actualizado = juegoDAO.buscarJuegoPorId(juego.getId());
	        assertNotNull(actualizado);
	        assertEquals("Juego actualizado", actualizado.getNombre());
	        assertEquals("Rol", actualizado.getGenero());
	}

	@Test
	void testEliminarJuego() throws SQLException {
	    Juego juego = new Juego(0, "Juego a eliminar", "Estrategia");
	    JuegoDAO.crearJuego(juego);

	    juegoDAO.eliminarJuego(juego.getId());

        Juego eliminado = juegoDAO.buscarJuegoPorId(juego.getId());
        assertNull(eliminado, "El juego debería haber sido eliminado.");
	}

	@Test
	void testObtenerTodosLosJuegos() throws SQLException {
		Juego juego1 = new Juego(0, "Juego1", "Plataformas");
        Juego juego2 = new Juego(0, "Juego2", "FPS");
        juegoDAO.crearJuego(juego1);
        juegoDAO.crearJuego(juego2);

        List<Juego> lista = juegoDAO.obtenerTodosLosJuegos();
        assertNotNull(lista);
        assertTrue(lista.size() >= 2, "Debería haber al menos 2 juegos registrados.");
    
	}
}
