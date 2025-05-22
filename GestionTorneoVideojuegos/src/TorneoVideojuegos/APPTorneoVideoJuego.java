package TorneoVideojuegos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.*;
/**
 * Aplicacion para la gestion del torneos de videojuegos
 * @author Daniel
 * @since 22/05/2025
 * @version 1.0
 */
public class APPTorneoVideoJuego {
 public static void main(String[] args) throws SQLException {
     var sc = new Scanner(System.in);
    
     JugadorDAO jugadorDAO = new JugadorDAO();
     OrganizadorDAO organizadorDAO = new OrganizadorDAO();
     TorneoDAO torneoDAO = new TorneoDAO();
     JuegoDAO juegoDAO =new JuegoDAO();
     EquipoDAO equipoDAO =new EquipoDAO();
     PartidaDAO partidaDAO =new PartidaDAO();

     while (true) {
         System.out.println("\n--- Menú Principal ---");
         System.out.println("1. Gestionar Jugadores");
         System.out.println("2. Gestionar Organizadores");
         System.out.println("3. Gestionar Juegos");
         System.out.println("4. Gestionar Torneos");
         System.out.println("5. Gestionar Equipos");
         System.out.println("6. Gestionar Partidas");
         System.out.println("7. Ver estadísticas por torneo");
         System.out.println("0. Salir");
         System.out.print("Opción: ");
         int opcion = sc.nextInt();
         sc.nextLine();

         try {
             switch (opcion) {
             case 1:// JUGADORES
                 System.out.println("1. Agregar 2. Modificar 3. Eliminar 4.FiltrarPorNombre 5.MostrarJugadores");
                 int opJ = sc.nextInt(); sc.nextLine();
                 if (opJ == 1) {
                     System.out.print("Nombre: "); 
                     String nombre = sc.nextLine();
                     System.out.print("Email: "); 
                     String email = sc.nextLine();
                     System.out.print("Usuario: "); 
                     String user = sc.nextLine();
                     jugadorDAO.agregarJugador(nombre, email, user);
                 } else if (opJ == 2) {
                     System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
                     System.out.print("Nuevo nombre: "); 
                     String nombre = sc.nextLine();
                     System.out.print("Nuevo email: "); 
                     String email = sc.nextLine();
                     System.out.print("Nuevo usuario: "); 
                     String user = sc.nextLine();
                     jugadorDAO.modificarJugador(id, nombre, email, user);
                 } else if (opJ == 3) {
                     System.out.print("ID: "); int id = sc.nextInt();
                     jugadorDAO.eliminarJugador(id);
                 }else if(opJ== 4) {
                	 System.out.print("Introduce texto a buscar en nombre de usuario: ");
                	 String texto = sc.nextLine();
                	 jugadorDAO.filtrarJugadoresPorNombre(texto);
                 }else if(opJ== 5) {
                	  jugadorDAO.obtenerTodosLosJugadores().stream()
                      .forEach(j -> {
                          System.out.println("ID: " + j.getId());
                          System.out.println("Nombre: " + j.getNombre());
                          System.out.println("Usuario: " + j.getNombreUsuario());
                          System.out.println("Email: " + j.getEmail());
                          System.out.println("------------------------");
                      });
                 }
                 break;

             case 2://ORGANIZADORES
                 System.out.println("1. Agregar 2. Modificar 3. Eliminar 4.Roles");
                 int opO = sc.nextInt(); sc.nextLine();
                 if (opO == 1) {
                	 System.out.println("Id:");
                	 int id = sc.nextInt();
                     System.out.print("Nombre: "); 
                     String nombre = sc.nextLine();
                     System.out.print("Email: "); 
                     String email = sc.nextLine();
                     System.out.print("Rol: "); 
                     String rol = sc.nextLine();
                     Organizador organizador = new Organizador(id, nombre, email, rol);
                     organizadorDAO.agregarOrganizador(organizador);
                 } else if (opO == 2) {
                     System.out.print("ID: "); 
                     int id = sc.nextInt(); sc.nextLine();
                     System.out.print("Nuevo nombre: "); 
                     String nombre = sc.nextLine();
                     System.out.print("Nuevo email: "); 
                     String email = sc.nextLine();
                     System.out.print("Nuevo rol: "); 
                     String rol = sc.nextLine();
                     Organizador organizador = new Organizador(id, nombre, email, rol);
                     organizadorDAO.modificarOrganizador(organizador);
                 } else if (opO == 3) {
                     System.out.print("ID: "); int id = sc.nextInt();
                     organizadorDAO.eliminarOrganizador(id);
                 }else if(opO == 4) {
                	 	System.out.print("Rol a filtrar (e.g., 'Principal'): ");
                	    String rol = sc.nextLine();
                	    organizadorDAO.obtenerTodosOrganizadores().stream()
                	        .filter(o -> o.getRol().equalsIgnoreCase(rol))
                	        .forEach(o -> {
                	            System.out.println("ID: " + o.getId());
                	            System.out.println("Nombre: " + o.getNombre());
                	            System.out.println("Email: " + o.getEmail());
                	            System.out.println("Rol: " + o.getRol());
                	            System.out.println("---------------------");
                	        });
                	    break;
                 }
                 break;

             case 3://JUEGO
                 System.out.println("1. Crear 2. Modificar 3. Eliminar 4.BusquedaPorGenero");
                 int opG = sc.nextInt(); sc.nextLine();
                 if (opG == 1) {
                	 System.out.print("id juego: "); 
                     int id = sc.nextInt();
                     System.out.print("Nombre juego: "); 
                     String nombre = sc.nextLine();
                     System.out.print("Género: "); 
                     String genero = sc.nextLine();
                     Juego juego = new Juego(id, nombre, genero);
                     JuegoDAO.crearJuego(juego);
                 } else if (opG == 2) {
                     System.out.print("ID: "); 
                     int id = sc.nextInt(); sc.nextLine();
                     System.out.print("Nuevo nombre: "); 
                     String nombre = sc.nextLine();
                     System.out.print("Nuevo género: "); 
                     String genero = sc.nextLine();
                     Juego juego = new Juego(id, nombre, genero);
                     JuegoDAO.modificarJuego(juego);
                 } else if (opG == 3) {
                     System.out.print("ID: "); int id = sc.nextInt();
                     juegoDAO.eliminarJuego(id);
                 }else if(opG == 4) {
                	 System.out.print("Género a buscar (e.g., 'Shooter'): ");
                	    String genero = sc.nextLine();
                	    juegoDAO.obtenerTodosLosJuegos().stream()
                	        .filter(j -> j.getGenero().equalsIgnoreCase(genero))
                	        .forEach(j -> {
                	            System.out.println("ID: " + j.getId());
                	            System.out.println("Nombre: " + j.getNombre());
                	            System.out.println("Género: " + j.getGenero());
                	            System.out.println("---------------------");
                	        });
                 }
                 break;
                 
             case 4://TORNEO
                 System.out.println("1. Crear 2. Modificar 3. Eliminar 4.TorneosOrdenados");
                 int opT = sc.nextInt(); sc.nextLine();
                 if (opT == 1) {
                	 System.out.print("ID torneo: "); 
                     int idTorneo= sc.nextInt();
                     System.out.print("Nombre torneo: "); 
                     String nombre = sc.nextLine();
                     System.out.print("Fecha Inicio torneo: "); 
                     Date fechaInicio = new Date();
                     System.out.print("Fecha Fin torneo: "); 
                     Date fechaFin = new Date();
                     System.out.print("ID juego: "); 
                     int idJuego= sc.nextInt();
                     Torneo torneo = new Torneo(idTorneo, nombre, fechaInicio,fechaFin,idJuego);
                     TorneoDAO.crearTorneo(torneo);
                 } else if (opT == 2) {
                     System.out.print("ID: "); 
                     int id = sc.nextInt(); sc.nextLine();
                     System.out.print("Nuevo nombre: "); 
                     String nombre = sc.nextLine();
                     System.out.print("ID juego: "); 
                     int idJuego = sc.nextInt();
                     Date hoy = new Date();
                     torneoDAO.modificarTorneo(id, nombre, hoy, hoy, idJuego);
                 } else if (opT == 3) {
                     System.out.print("ID: "); int id = sc.nextInt();
                     torneoDAO.eliminarTorneo(id);
                 }else if (opT == 4) {
                	 torneoDAO.obtenerTodosLosTorneos().stream()
                     .sorted(Comparator.comparing(Torneo::getFechaInicio))
                     .forEach(t -> {
                         System.out.println("ID: " + t.getId());
                         System.out.println("Nombre: " + t.getNombre());
                         System.out.println("Fecha inicio: " + t.getFechaInicio());
                         System.out.println("Fecha fin: " + t.getFechaFin());
                         System.out.println("---------------------");
                     });
                 }
                 break;

             case 5://EQUIPOS
                 System.out.println("1. Crear 2. Modificar 3. Eliminar 4.MostraJugadoresEquipo");
                 int opE = sc.nextInt(); sc.nextLine();
                 if (opE == 1) {
                	 System.out.print("ID equipo: "); 
                     int idE = sc.nextInt();
                     System.out.print("Nombre equipo: "); 
                     String nombre = sc.nextLine();
                     System.out.print("ID organizador: "); 
                     int idOrg = sc.nextInt();
                     Equipo equipo = new Equipo(idE, nombre,idOrg);
                     EquipoDAO.crearEquipo(equipo);
                 } else if (opE == 2) {
                     System.out.print("ID: "); 
                     int idE = sc.nextInt(); sc.nextLine();
                     System.out.print("Nuevo nombre: "); 
                     String nombre = sc.nextLine();
                     System.out.print("ID organizador: "); 
                     int idOrg = sc.nextInt();
                     Equipo equipo = new Equipo(idE, nombre,idOrg);
                     equipoDAO.modificarEquipo(equipo);
                 } else if (opE == 3) {
                     System.out.print("ID: "); int id = sc.nextInt();
                     equipoDAO.eliminarEquipo(id);
                 } else if(opE == 4) {
                	 List<EquipoInfo> lista = equipoDAO.obtenerEquiposConConteoDeJugadores();

                	 lista.stream()
                	      .sorted(Comparator.comparingInt(EquipoInfo::getCantidadJugadores).reversed())
                	      .forEach(e -> {
                	          System.out.println("Equipo: " + e.getNombreEquipo());
                	          System.out.println("Jugadores: " + e.getCantidadJugadores());
                	          System.out.println("------------------------");
                	      });


                 }
                 break;

             case 6://PARTIDAS
                 System.out.println("1. Programar partida 2. Registrar resultado");
                 int opP = sc.nextInt();
                 if (opP == 1) {
                	 System.out.print("ID partida: "); 
                     int idP = sc.nextInt();
                     System.out.print("ID torneo: "); 
                     int idT = sc.nextInt();
                     System.out.print("ID equipo 1: "); 
                     int e1 = sc.nextInt();
                     System.out.print("ID equipo 2: "); 
                     int e2 = sc.nextInt();
                     System.out.print("Fecha partida ");
                     Date fecha = new Date();
                     System.out.print("Resultado: "); 
                     int result = sc.nextInt();
                     sc.nextLine();
                     Partida partida = new Partida(idP,idT,e1,e2,(java.sql.Date) fecha,result);
                     partidaDAO.programarPartida(partida);
                 } else if (opP == 2) {
                     System.out.print("ID partida: "); 
                     int id = sc.nextInt(); sc.nextLine();
                     System.out.print("Resultado (nombre ganador): "); 
                     String res = sc.nextLine();
                     partidaDAO.registrarResultado(id, res);
                 }
                 break;

             case 7://ESTADISTICAS
                 System.out.print("ID torneo: ");
                 int torneoId = sc.nextInt();
                 torneoDAO.obtenerEstadisticasPorTorneo(torneoId);
                 break;

             case 0://SALIDA
                 System.out.println("Saliendo...");
                 return;
         }
     } catch (SQLException e) {
         System.out.println("Error: " + e.getMessage());
     }
 }
 }
}
