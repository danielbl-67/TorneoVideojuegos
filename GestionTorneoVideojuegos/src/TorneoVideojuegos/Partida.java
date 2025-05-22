package TorneoVideojuegos;

import java.sql.Date;

/**
 * Clase de partida
 * @author Daniel
 * @since 19/05/2025
 * @version 1.0
 */
public class Partida {

	//Atributos
	private int id;
    private int idTorneo;
    private int idEquipo1;
    private int idEquipo2;
    private Date fecha;
    private int resultado;
	
    //Constructor
    public Partida(int id, int idTorneo, int idEquipo1, int idEquipo2, Date fecha, int resultado) {
		super();
		this.id = id;
		this.idTorneo = idTorneo;
		this.idEquipo1 = idEquipo1;
		this.idEquipo2 = idEquipo2;
		this.fecha = fecha;
		this.resultado = resultado;
	}
    
    //Getter,Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdTorneo() {
		return idTorneo;
	}
	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}
	public int getIdEquipo1() {
		return idEquipo1;
	}
	public void setIdEquipo1(int idEquipo1) {
		this.idEquipo1 = idEquipo1;
	}
	public int getIdEquipo2() {
		return idEquipo2;
	}
	public void setIdEquipo2(int idEquipo2) {
		this.idEquipo2 = idEquipo2;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getResultado() {
		return resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
    
  
    
}
