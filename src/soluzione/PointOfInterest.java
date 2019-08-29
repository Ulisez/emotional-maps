package soluzione;

import java.util.ArrayList;
import java.util.Date;

//import java.util.Date;
import java.util.TreeSet;

/**
 * Gli oggetti di questa classe rappresentano dei punti d'interesse. 
 * 
 * @author ulise
 */
public class PointOfInterest {

	/**
	 *ContenEvents è una struttura dati TreeSet che contiene tutti gli eventi associati a un 
	 *determinato punto d'interesse. In questo modo ogni punto d'interesse contiene li eventi
	 *effettuati preso le sue vicinanze.
	 */
	private CoordinateGeografiche coordinate;
	private String name;
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
 * Costruttutore della classe che permette di creare oggetti che rappresentano dei punti D'interessi
 * @param nome - nome del punto d'interesse 
 * @param latitude - la latitudine del punto d'interesse
 * @param longitude - la longitudine del punto d'interesse
 */
	public PointOfInterest(String name, double latitude, double longitude) {
		this.name = name;
		this.coordinate = new CoordinateGeografiche(latitude, longitude);
		this.contenEvents = new TreeSet<>();
		this.numberPoint = ++countPoint;
	}
/**
 * Restituisce il nome del punto d'interesse 
 * @return String - nome del punto d'intesse
 */
	public String getNome() {
		return name;
	}

	/**
	 * Setta il nome del punto d'interesse che esegue il metodo
	 * @param nome 
	 */
	public void setNome(String name) {
		this.name = name;
	}
/**
 * 
 * @return CoordinateGeografic
 */
	public CoordinateGeografiche getCordinate() {
		return coordinate;
	}

	/**
	 * Restituisce il numero assocciato al punto d'interesse che esegue il metodo
	 * @return int - Il numero del punto d'interesse che esegue il metodo
	 */
	public int getnumberPoint() {
		return numberPoint;
	}

	
	/**
	 * 
	 * @param e - Parametri di tipo Event 
	 */
	public void addEvent(Event e) {
		this.contenEvents.add(e);
	}
    
	/**
	 * getPoints è un metodo statico che restituisce i 3 punti d'interesse 
	 * @return ArrayList<PointOfInterest> - La lista contenenti tutti i punti di interessi.
	 */
	public static ArrayList<PointOfInterest> getPoints() {
		ArrayList<PointOfInterest> allpoints = new ArrayList<>();
		allpoints.add(POI1);
		allpoints.add(POI2);
		allpoints.add(POI3);
		return allpoints;

	}
 /**
  * Restituisce tutti gli eventi associati al punto d'interesse che esegue il metodo.
  * @return ArraList<Event> - Lista di eventi
  */
	public ArrayList<Event> getListEvents() {
		ArrayList<Event> eventlist = new ArrayList<Event>(contenEvents);
		return eventlist;
	}
 /**
  * calculateMap è un metodo statico che calcola e visualizza le Mappe emozionali. Questo metodo
  * chiama la sotto-funzione "countStates" per il calcolo delle mappe emozionali.
  * @param datestart - Data di inizio 
  * @param dateEnd - Data di fine 
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
  * countState è un metodo privato che conta la quantità di occorrenze per ciascun stato 
  * emozionale sia dei soli utenti attivi sia per tutti gli utenti (Attivi e non attivi - registrati e non più registrati)
  * degli eventi effettuati tra la data Inizio e la data Fine.
  * @param datestart - La data d'inizio dell'intervallo 
  * @param dateEnd - Data di fine dell'intervallo.
  * @return String - Risultato del calcolo della mappa emozionale Riguardante tutti gli eventi all'interno
  * dell'intervallo. 
  * 
  */
	private String countStates(Date datestart, Date dateEnd) {
	
		int countstate[] = new int[5];
		int countstateAll[] = new int[5];
		int activEvents = 0;
		int allevents = 0;

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
						allevents++;
						countstateAll[0]++;
						if (evento.getStateUser()) {
							activEvents++;
							countstate[0]++;
						}
						break;
					case "F":
						allevents++;
						countstateAll[1]++;
						if (evento.getStateUser()) {
							activEvents++;
							countstate[1]++;
						}
						break;
					case "S":
						allevents++;
						countstateAll[2]++;
						if (evento.getStateUser()) {
							activEvents++;
							countstate[2]++;
						}
						break;
					case "T":
						allevents++;
						countstateAll[3]++;
						if (evento.getStateUser()) {
							activEvents++;
							countstate[3]++;
						}
						break;
					case "N":
						allevents++;
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
	
     	return calculateAllMap(countstateAll,allevents);

		

	}
   
	/**
	 * calculateActiveMap è una funzione che calcola e stampa la Mappa Emozionale 
	 * degli utenti attivi.
	 * @param states - Array di interi. ogni posizione contiene la quantità di occorrenze per 
	 * ciascun stato emozionale. La posizione 0 rappresenta la quantità di utenti Arrabbiati,
	 * La posizione 1 la quantità di utenti Felici e cosi via....
	 * @param events - Rappresenta il numero totale di utenti Attivi
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
	 * calculateAllMap è una funzione privata che calcola la mappa emozionale riguardanti tutti
	 * gli eventi di un punto d'interesse. Una volta effettuato il calcolo restutisce in risultato in 
	 * formatto di Stringa. 
	 * @param states - Array di interi, ogni posizione contiene la quantità di occorrenze per 
	 * ciascun stato emozionale. La posizione 0 rappresenta la quantità di utenti Arrabbiati,
	 * La posizione 1 la quantità di utenti Felici e cosi via....
	 * @param events - Quantità di eventi totali.
	 * @return String - Risultato della mappa emozionale
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
		return "POI" + this.numberPoint + " [ nome = " + name + " " + " coordinate: " + coordinate.toString() + " ] ";
	}

}
