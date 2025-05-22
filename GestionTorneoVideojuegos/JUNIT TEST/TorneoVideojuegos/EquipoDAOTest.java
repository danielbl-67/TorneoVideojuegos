package TorneoVideojuegos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EquipoDAOTest {
	private EquipoDAO equipoDAO;
	
	@BeforeEach
    void setUp() throws SQLException {
    	equipoDAO = new EquipoDAO();
    }
	
	@Test
	void testCrearEquipo() {
		Equipo equipo = new Equipo(0, "Equipo Test", 1); // idOrganizador = 1
		EquipoDAO.crearEquipo(equipo);

        Equipo recuperado = equipoDAO.buscarEquipoPorId(equipo.getId());
        assertNotNull(recuperado, "El equipo debería haberse creado y recuperado.");
        assertEquals("Equipo Test", recuperado.getNombre());
	}

	@Test
	void testModificarEquipo() {
		 Equipo equipo = new Equipo(0, "Equipo Antiguo", 1);
		 EquipoDAO.crearEquipo(equipo);

	        equipo.setNombre("Equipo Nuevo");
	        equipoDAO.modificarEquipo(equipo);

	        Equipo actualizado = equipoDAO.buscarEquipoPorId(equipo.getId());
	        assertEquals("Equipo Nuevo", actualizado.getNombre(), "El nombre del equipo debería haberse actualizado.");
	   
	}

	@Test
	void testEliminarEquipo() {
		  Equipo equipo = new Equipo(0, "Equipo Borrar", 1);
		  equipoDAO.crearEquipo(equipo);

		  equipoDAO.eliminarEquipo(equipo.getId());

        Equipo eliminado = equipoDAO.buscarEquipoPorId(equipo.getId());
        assertNull(eliminado, "El equipo debería haberse eliminado.");
	}

	@Test
	void testObtenerEquiposConConteoDeJugadores() {
		 List<EquipoInfo> equiposConConteo = equipoDAO.obtenerEquiposConConteoDeJugadores();

        assertNotNull(equiposConConteo, "La lista no debe ser nula.");
        assertTrue(equiposConConteo.size() >= 0, "La lista debe contener resultados o estar vacía.");
        // Para imprimir para verificar el contenido 
        equiposConConteo.forEach(System.out::println);
	}

}
