package TorneoVideojuegos;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrganizadorDAOTest {
	
	private OrganizadorDAO organizadorDAO;

	@BeforeEach
    void setUp() throws SQLException {
        organizadorDAO = new OrganizadorDAO();
    }

	@Test
	void testAgregarOrganizador() throws SQLException {
		Organizador organizador = new Organizador(0, "Ana Organizadora", "ana@email.com", "Admin");
	    organizadorDAO.agregarOrganizador(organizador);
	
	    Organizador recuperado = organizadorDAO.buscarOrganizadorPorId(organizador.getId());
	    assertNotNull(recuperado, "El organizador debería haberse registrado y recuperado.");
	    assertEquals("Ana Organizadora", recuperado.getNombre());
	}

	@Test
	void testEliminarOrganizador() throws SQLException {
	    OrganizadorDAO dao = new OrganizadorDAO();

	    // Primero agregamos un organizador de prueba
	    Organizador org = new Organizador(0, "Juan", "juan@example.com", "Coordinador");
	    try {
	        dao.agregarOrganizador(org);

	        // Suponiendo que el ID se asigna automáticamente, deberías obtenerlo )
	        // Aquí lo buscaremos por email como alternativa
	        Organizador recuperado = dao.buscarOrganizadorPorEmail("juan@example.com");
	        assertNotNull(recuperado);

	        dao.eliminarOrganizador(recuperado.getId());

	        Organizador eliminado = dao.buscarOrganizadorPorId(recuperado.getId());
	        assertNull(eliminado);
	    } catch (SQLException e) {
	        fail("Excepción inesperada: " + e.getMessage());
	    }
	}


	@Test
	void testModificarOrganizador() throws SQLException {
	    OrganizadorDAO dao = new OrganizadorDAO();

	    Organizador org = new Organizador(0, "Ana", "ana@example.com", "Asistente");
	    try {
	        dao.agregarOrganizador(org);
	        Organizador recuperado = dao.buscarOrganizadorPorEmail("ana@example.com");
	        assertNotNull(recuperado);

	        // Modificamos
	        recuperado.setNombre("Ana María");
	        recuperado.setRol("Jefa de Área");

	        dao.modificarOrganizador(recuperado);

	        Organizador modificado = dao.buscarOrganizadorPorId(recuperado.getId());
	        assertEquals("Ana María", modificado.getNombre());
	        assertEquals("Jefa de Área", modificado.getRol());
	    } catch (SQLException e) {
	        fail("Excepción inesperada: " + e.getMessage());
	    }
	}


	@Test
	void testObtenerTodosOrganizadores() throws SQLException {
		Organizador org1 = new Organizador(0, "Org1", null, null);
	    Organizador org2 = new Organizador(0, "Org2", null, null);
	    organizadorDAO.agregarOrganizador(org1);
	    organizadorDAO.agregarOrganizador(org2);
	
	    List<Organizador> lista = organizadorDAO.obtenerTodosOrganizadores();
	    assertNotNull(lista);
	    assertTrue(lista.size() >= 2, "Debería haber al menos 2 organizadores registrados.");
	   
	}

}
