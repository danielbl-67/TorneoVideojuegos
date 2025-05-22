package TorneoVideojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrganizadorDAO {
	
private Connection conexion;
/**
 * Clase que crea conexion con BBDD
 * @throws SQLException
 */
public  OrganizadorDAO() throws SQLException {
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
 * Clase CRUD para agregar/añadir a un organizador
 * @param nombre
 * @param email
 * @param rol
 * @throws SQLException
 */
public void agregarOrganizador(Organizador organizador) throws SQLException {
    String sqlPersona = "INSERT INTO PERSONAS(nombre, email, tipo) VALUES (?, ?, 'organizador')";
    String sqlOrg = "INSERT INTO ORGANIZADORES(id_organizador, rol) VALUES (LAST_INSERT_ID(), ?)";

    try (Connection conn = Database.getConnection()) {
        conn.setAutoCommit(false);

        try (PreparedStatement ps1 = conn.prepareStatement(sqlPersona);
             PreparedStatement ps2 = conn.prepareStatement(sqlOrg)) {

            ps1.setString(1, organizador.getNombre());
            ps1.setString(2, organizador.getEmail());
            ps1.executeUpdate();

            ps2.setString(1, organizador.getRol());
            ps2.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }
}

 
/**
 * Clase CRUD para eliminar/borrar a un organizador
 * @param idOrganizador
 * @throws SQLException
 */
public void eliminarOrganizador(int idOrganizador) throws SQLException {
    String sql = "DELETE FROM PERSONAS WHERE id_persona = ? AND tipo = 'organizador'";
    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idOrganizador);
        ps.executeUpdate();
    }
}

/**
 * Clase CRUD para modificar/actualizar a un organizador
 * @param idOrganizador
 * @param nombre
 * @param email
 * @param rol
 * @throws SQLException
 */
public void modificarOrganizador(Organizador organizador) throws SQLException {
    String sqlPersona = "UPDATE PERSONAS SET nombre = ?, email = ? WHERE id = ?";
    String sqlOrg = "UPDATE ORGANIZADORES SET rol = ? WHERE id_organizador = ?";

    try (Connection conn = Database.getConnection()) {
        conn.setAutoCommit(false);

        try (PreparedStatement ps1 = conn.prepareStatement(sqlPersona);
             PreparedStatement ps2 = conn.prepareStatement(sqlOrg)) {

            // Actualizar en PERSONAS
            ps1.setString(1, organizador.getNombre());
            ps1.setString(2, organizador.getEmail());
            ps1.setInt(3, organizador.getId());
            ps1.executeUpdate();

            // Actualizar en ORGANIZADORES
            ps2.setString(1, organizador.getRol());
            ps2.setInt(2, organizador.getId());
            ps2.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }
}

/**
 * Clase que enseña todos los organizadores
 * @return
 */
public List<Organizador> obtenerTodosOrganizadores() {
    List<Organizador> lista = new ArrayList<>();
    String sql = "SELECT o.id_organizador, p.nombre, p.email, o.rol FROM ORGANIZADORES o JOIN PERSONAS p ON o.id_organizador = p.id_persona";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Organizador o = new Organizador(0, sql, sql, sql);
            o.setId(rs.getInt("id_organizador"));
            o.setNombre(rs.getString("nombre"));
            o.setEmail(rs.getString("email"));
            o.setRol(rs.getString("rol"));
            lista.add(o);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}
/**
 * Clase que busca por 	ID
 * @param id
 * @return
 */
public Organizador buscarOrganizadorPorId(int id) {
    String sql = "SELECT p.id, p.nombre, p.email, o.rol " +
                 "FROM PERSONAS p " +
                 "JOIN ORGANIZADORES o ON p.id = o.id_organizador " +
                 "WHERE p.id = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int idOrg = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String rol = rs.getString("rol");

                return new Organizador(idOrg, nombre, email, rol);
            }
        }
    }catch (SQLException e) {
        e.printStackTrace(); 
    }return null;
}

/**
 * Clase que busca por email
 * @param email
 * @return
 */
public Organizador buscarOrganizadorPorEmail(String email) {
    String sql = "SELECT p.id, p.nombre, p.email, o.rol " +
                 "FROM PERSONAS p " +
                 "JOIN ORGANIZADORES o ON p.id = o.id_organizador " +
                 "WHERE p.email = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("email");
                String rol = rs.getString("rol");

                return new Organizador(id, nombre, correo, rol);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace(); // o usa logger
    }

    return null; // Si no se encuentra o hay error
}

}

