package soluzione;

import java.util.ArrayList;
import java.util.Date;

//import java.util.Date;
import java.util.TreeSet;

/**
 * Gli oggetti di questa classe rappresentano dei punti d'interesse
 * 
 * @author ulise
 */
public class PointOfInterest {

	private CoordinateGeografiche coordinate;
	private String nome;
	private TreeSet<Event> contenEvents;
	private int numberPoint;
	private static int countPoint;
	/**
	 * Definisco i tre punti d'interesse come costanti
	 */
	private static final PointOfInterest POI1 = new PointOfInterest("Duomo", 45.464, 9.190);
	private static final PointOfInterest POI2 = new PointOfInterest("Arco Della Pace", 45.473, 9.173);
	private static final PointOfInterest POI3 = new PointOfInterest("Navigli", 45.458, 9.181);
/**
 * 
 * @param nome
 * @param latitude
 * @param longitude
 */
	public PointOfInterest(String nome, double latitude, double longitude) {
		this.nome = nome;
		this.coordinate = new CoordinateGeografiche(latitude, longitude);
		this.contenEvents = new TreeSet<>();
		this.numberPoint = ++countPoint;
	}
/**
 * 
 * @return
 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
/**
 * 
 * @return
 */
	public CoordinateGeografiche getCordinate() {
		return coordinate;
	}

	/**
	 * 
	 * @return
	 */
	public int getnumberPoint() {
		return numberPoint;
	}
/**
 * 
 * @param e
 */
	public void addEvent(Event e) {
		this.contenEvents.add(e);
	}
    
	/**
	 * 
	 * @return
	 */
	public static ArrayList<PointOfInterest> getPoints() {
		ArrayList<PointOfInterest> allpoints = new ArrayList<>();
		allpoints.add(POI1);
		allpoints.add(POI2);
		allpoints.add(POI3);
		return allpoints;

	}
 /**
  * 
  * @return
  */
	public ArrayList<Event> getListEvents() {
		ArrayList<Event> eventlist = new ArrayList<Event>(contenEvents);
		return eventlist;
	}
 /**
  * 
  * @param datestart
  * @param dateEnd
  */
	public static void calculateMap(Date datestart, Date dateEnd) {
        ArrayList<String> allResult = new ArrayList<>();
        
		System.out.println("\n"+"    ****** MAPPA EMOZIONALE UTENTI ATTIVI ****** " + "");
		for (PointOfInterest point : PointOfInterest.getPoints()) {
			allResult.add(point.countStates(datestart, dateEnd));
			}
		
		System.out.println("\n" +"    ****** MAPPA EMOZIONALE DI TUTTI GLI UTENTI ****** " );
		 for(String result: allResult) {
			 System.out.println(result);
		 }
	}
 /**
  * 
  * @param datestart
  * @param dateEnd
  * @return
  */
	private String countStates(Date datestart, Date dateEnd) {
	
		int countstate[] = new int[5];
		int countstateAll[] = new int[5];
		int activEvents = 0;

		for (int i = 0; i < countstate.length; i++) {
			countstate[i] = 0;
			countstateAll[i] = 0;
		}
		
		for (Event evento : this.contenEvents) {
			  
			int confronto = evento.getDate().compareTo(dateEnd);
			if (confronto == 0 || confronto == -1) {
				if (evento.getDate().after(datestart) || evento.getDate().equals(datestart)) {
					switch (evento.getEmotion()) {
					case "A":
						countstateAll[0]++;
						if (evento.getStateUser()) {
							activEvents++;
							countstate[0]++;
						}
						break;
					case "F":
						countstateAll[1]++;
						if (evento.getStateUser()) {
							activEvents++;
							countstate[1]++;
						}
						break;
					case "S":
						countstateAll[2]++;
						if (evento.getStateUser()) {
							activEvents++;
							countstate[2]++;
						}
						break;
					case "T":
						countstateAll[3]++;
						if (evento.getStateUser()) {
							activEvents++;
							countstate[3]++;
						}
						break;
					case "N":
						countstateAll[4]++;
						if (evento.getStateUser()) {
							activEvents++;
							countstate[4]++;
						}
					}
				}
			} else
				break;
		}
	
		calculateActiveMap(countstate, activEvents);
	
     	return calculateAllMap(countstateAll,this.contenEvents.size());

		

	}
   
	/**
	 * 
	 * @param states
	 * @param events
	 */
	private void calculateActiveMap(int states[], int events) {

		char Emotionalstate[] = { 'A', 'F', 'S', 'T', 'N' };

		if (events != 0) {
			System.out.print("  " + "POI" + this.numberPoint + "" + " - ");
			for (int i = 0; i < states.length; i++) {
				double calcolo = ((double) (states[i] * 100) / events);
				System.out.print("%" + Math.round(calcolo) + "" + Emotionalstate[i] + " ");
				
			}
			System.out.println();
		} else
			System.out.println("  " + "POI" + this.numberPoint + "" + " - " + "%0A, %0F, %0S, %0T, %0N");

	}
	
	/**
	 * 
	 * @param states
	 * @param events
	 * @return
	 */
	private String calculateAllMap(int states[], int events) {
		String result="";
		char Emotionalstate[] = { 'A', 'F', 'S', 'T', 'N' };
		if (events == 0) {
			result = "  " + "POI" + this.numberPoint + "" + "- " + "%0A, %0F, %0S, %0T, %0N";
		} else {
			String calculate="";
			result = "  "+"POI" + this.numberPoint + ""+ "- ";
			for (int i = 0; i < states.length; i++) {
				double calcolo = ((double) (states[i]*100) / events);
			calculate = calculate + "%" + Math.round(calcolo) + "" + Emotionalstate[i] + " ";
			}
			
			result = result +" "+calculate;
		}
	       return result;
	}



	@Override
	public String toString() {
		return "POI" + this.numberPoint + " [ nomee = " + nome + " " + " coordinate: " + coordinate.toString() + " ] ";
	}

}
