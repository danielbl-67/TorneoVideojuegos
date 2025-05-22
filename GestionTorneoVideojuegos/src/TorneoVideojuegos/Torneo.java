package TorneoVideojuegos;

import java.sql.Date;
import java.time.LocalDate;
/**
 * Clase principal ya que todo se vasa en esta
 * @author Daniel
 * @since 19/05/2025
 * @version 1.0
 */
public class Torneo {
	
	//Atributos
	private int id;
    private String nombre;
    private java.util.Date fechaInicio;
    private java.util.Date fechaFin;
    private int idJuego;
    
    //Constructor
	public Torneo(int id, String nombre, java.util.Date fechaInicio, java.util.Date fechaFin, int idJuego) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idJuego = idJuego;
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
	public java.util.Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public java.util.Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}
	
}
