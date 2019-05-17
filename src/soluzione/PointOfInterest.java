package soluzione;

//import java.util.TreeSet;
//import java.util.Set;

/**
 * Gli oggetti di questa classe rappresentano dei punti d'interesse
 * @author ulise
 */
 public class PointOfInterest {
 
	private CoordinateGeografiche coordinate;
	private String nome;
	//private Set<Evento> events;
	
	public PointOfInterest(String nome, double latitude, double longitude) {
		this.nome = nome;
		this.coordinate = new CoordinateGeografiche(latitude,longitude);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
    
	public CoordinateGeografiche getCordinate() {
		return coordinate;
	}
	
}
