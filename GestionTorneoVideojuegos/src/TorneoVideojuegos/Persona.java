package TorneoVideojuegos;
/**
 * Clase padre en la que se basa los jugadores y los organizadores
 * @author Daniel
 * @since 19/05/2025
 * @version 1.0
 */
public class Persona {
	//Atributos de la clase
	protected int id;
    protected String nombre;
    protected String email;
    
    //Constructor
    public Persona(int id, String nombre, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}
	//Getter,Setters
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

    
}
