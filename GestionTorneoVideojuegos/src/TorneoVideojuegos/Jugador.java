package TorneoVideojuegos;
/**
 * Clase hija de persona
 * 
 * @author Daniel
 * @since 19/05/2025
 * @version 1.0
 */

public class Jugador extends Persona {
	//Atributos de la clase
    private String nombreUsuario;
    
    //Constructor
	public Jugador(int id, String nombre, String email, String nombreUsuario) {
		super(id, nombre, email);
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
	}

	// Getters,Setters
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
    
  
    
    
}
