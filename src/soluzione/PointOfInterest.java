package soluzione;

//import java.util.TreeSet;
//import java.util.Set;

/**
 * 
 * @author ulise
 *Questa classe rappresenta un punto di interesse
 */

public class PointOfInterest {
 
	private long latitudine;
	private long longitudione;
	private String nome;
	//private Set<Evento> events;
	
	public long getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(long latitudine) {
		this.latitudine = latitudine;
	}
	public long getLongitudione() {
		return longitudione;
	}
	public void setLongitudione(long longitudione) {
		this.longitudione = longitudione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}