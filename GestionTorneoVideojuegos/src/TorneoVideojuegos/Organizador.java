package TorneoVideojuegos;
/**
 * Clase hija de persona
 * 
 * @author Daniel
 * @since 19/05/2025
 * @version 1.0
 */
public class Organizador extends Persona {
 
	//Atributos de la clase
	protected String rol;
	
	//Constructor
	public Organizador(int id, String nombre, String email,String rol) {
		super(id, nombre, email);
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.rol = rol;
	}
	
	//Getters,Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	
}
