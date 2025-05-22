package TorneoVideojuegos;
/**
 * Clase que hace referencia a los videojuegos
 * @author Daniel
 * @since 19/05/2025
 * @version 1.0
 */
public class Juego {
	// Atributo de la clase
	private int id;
    private String nombre;
    private String genero;
    
    //Constructor
	public Juego(int id, String nombre, String genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
	}
	
	//Getters,Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
    
    
}
