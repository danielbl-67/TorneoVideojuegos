package TorneoVideojuegos;

public class EquipoInfo {
	 private String nombreEquipo;
	    private int cantidadJugadores;

	    public EquipoInfo(String nombreEquipo, int cantidadJugadores) {
	        this.nombreEquipo = nombreEquipo;
	        this.cantidadJugadores = cantidadJugadores;
	    }

	    public String getNombreEquipo() {
	        return nombreEquipo;
	    }

	    public int getCantidadJugadores() {
	        return cantidadJugadores;
	    }
}
