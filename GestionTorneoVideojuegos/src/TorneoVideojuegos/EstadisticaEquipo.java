package TorneoVideojuegos;
/**
 * Clase para saber las estadisticas del equipo
 * @author Daniel
 * @since 20/05/2025
 * @version 1.0
 */
public class EstadisticaEquipo {
	//Atributos de la clase
	private String nombre;
    private int partidasJugadas;
    private int victorias;
    //Constructor
	public EstadisticaEquipo(String nombre, int partidasJugadas, int victorias) {
		super();
		this.nombre = nombre;
		this.partidasJugadas = partidasJugadas;
		this.victorias = victorias;
	}
	//Getter ,Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPartidasJugadas() {
		return partidasJugadas;
	}
	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}
	public int getVictorias() {
		return victorias;
	}
	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}
    
    
    
}
