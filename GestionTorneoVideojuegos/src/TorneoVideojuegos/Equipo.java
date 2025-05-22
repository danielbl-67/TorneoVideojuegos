package TorneoVideojuegos;
/**
 * Clase de equipo
 * @author Daniel
 * @since 19/05/2025
 * @version 1.0
 */
public class Equipo {
	
	 //Atributos
	 private int id;
	 private String nombre;
	 private int idOrganizador;

	 //Constructor
	 public Equipo(int id, String nombre, int idOrganizador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idOrganizador = idOrganizador;
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
	public int getIdOrganizador() {
		return idOrganizador;
	}
	public void setIdOrganizador(int idOrganizador) {
		this.idOrganizador = idOrganizador;
	}
	 
	 
	
	
	
}
