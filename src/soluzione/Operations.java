
package soluzione;


import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Queue;
import java.util.LinkedList;

/**
 * La classe Operations contiene tutte le operazioni necessarie per processare effetuati tutti gli algoritmi neccesari per il
 * giusto funzionamento dell'applicazione.
 * 
 * @author Ulises Sanchez
 */
public class Operations {

	/**
	 *Le costanti regexImport, regexCreateMap e Event_Pattern di tipo Pattern 
	 *rapprensentano il modello di formatto che le stringhe Espressioni regolare, 
	 *una per ogni comando e un'altra per l'evento. 
	 */
	private static final Pattern regexImport = Pattern.compile("^import\\((.*?)\\)$");

	private static final Pattern regexCreateMap = Pattern.compile("^create_map\\((\\d{8}?)-(\\d{8}?)\\)$");

	private static final Pattern Event_Pattern = Pattern
			.compile("^(IN|OUT) (LOGIN|LOGOUT) (\\d{8}) (.*?) (\\d.*),(\\d.*) (A|F|S|T|N)$");

	/**
	 * Struttura dati in cui vengono salvati i comandi letti dal file
	 * che contiene i vari comandi che l'applicazione deve eseguire
	 */
	public static Queue<String> commands = new LinkedList<String>();



	/**
	 * Legge i comandi dal file di testo passato come parametro, controlla se il
	 * file esiste, in caso positivo legge i comandi e li salva all'interno di una
	 * coda solo se rispettano il formatto adeguato, invece se il file non esiste
	 * viene stampato un messaggio d'errore
	 * 
	 * @param fileName - Il nome del file dal quale leggere i comandi
	 */
	public static void handleCommands(String fileName) {
		String commandString;
		try (BufferedReader readerFile = new BufferedReader(new FileReader(fileName))) {
			//int index = 0;
			while ((commandString = readerFile.readLine()) != null) {
				//System.out.println(commandString);
				//index++;
				if (validateFormatCommand(commandString)) // stampo il numero della riga che non rispetta il formatto
					commands.add(commandString);
				/*else
					System.err.println(" La Stringa " + commandString + " (Riga " + index
							+ " del file) non rispetta il formato desiderato");*/
			} 

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	
		processCommand();

		// Stampa lista eventi, solo per verifica
		//for (PointOfInterest i : points) {
		 /* for(PointOfInterest i: PointOfInterest.getPoints()) {
			System.out.println(i.toString());
			if (!(i.getListEvents().isEmpty())) {
				System.out.println(i.getListEvents().size());
				

			}
		}*/
	}

	/**
	 * Questo metodo itera la coda e processa un comando alla volta, se il comando
	 * letto è un import(event.txt) chiama la procedura Import(event) e gli passa il
	 * nome del file dal quale leggere gli eventi invece, se il comando rappresenta
	 * un create_map viene chiamata la procedura Create_Map(dataStart, Dataend).
	 */
	private static void processCommand() {
		String command;
		Matcher matcher;
		while (!(commands.isEmpty())) {
			command = commands.poll();
			if (command.contains("import")) {
				//System.out.println("Mi trovo qui import");
				matcher = regexImport.matcher(command);
				if (matcher.matches()) {
					String fileName = matcher.group(1);
					Import(fileName);
				}
			} else {
				matcher = regexCreateMap.matcher(command);
				if (matcher.matches()) {
					//System.out.println("mi trovo qui dentro createMap");
					String fromdate = matcher.group(1);
					String todate = matcher.group(2);
					createMap(fromdate, todate);

				}
			}
		}

	}

	/**
	 * Importa gli eventi dal file di testo passato come parametro.Per ogni evento
	 * letto dal file viene effettuato un piccolo controllo sulle sottostringhe che 
	 * compongono l'evento. Se il controllo su tutte le sottostringhe ha esito positivo,
	 *  vengono prese e usate come parametri per creare un oggeto di tipo evento
	 * corrispodente. L'evento creato viene salvato in un'opportuna struttura dati. In caso
	 * contratio ovvero se la stringa non rispetta il formatto, viene ignorata e si
	 * passa alla lettura della stringa(Evento) successiva.
	 * 
	 * @param eventFileName - File dal quale importare gli eventi
	 */
	private static void Import(String eventFileName) {
		String evento;
		try (BufferedReader lettore = new BufferedReader(new FileReader(eventFileName))) {
			while ((evento = lettore.readLine()) != null) {
				//System.out.println(evento);
				if (Pattern.matches(Event_Pattern.pattern(), evento)) {
					Matcher matEvent = Event_Pattern.matcher(evento);
					while (matEvent.find()) {
						try {
							//System.out.println("Sono arrivato qui");
							Event object = new Event(matEvent.group(1), matEvent.group(2), matEvent.group(3),
									matEvent.group(4), matEvent.group(5), matEvent.group(6), matEvent.group(7));
							if(object.getstateRegister() || !(object.getStateUser()))
								putEvent(object);

						} catch (EventException e) {
							//System.out.println("HAHAHAHA");
							System.out.println(e.fillInStackTrace());
						}
					}

				}
			}
		} catch (Exception e) {
			//System.out.println("HAHAHAHAmimimimi");
			e.printStackTrace();
		}
	}

	/**
	 * Il metodo createMap riceve in ingresso le due date in formatto stringhe
	 * e le converte in formatto Date. Se una delle due date non rispetta il formatto desiderato
	 * il comando createMap non sarà eseguito. Inoltre prima di eseguire il calcolo 
	 * della mappa emozionale viene controllato che la data d'inizio sia minore della data finale
	 * se l'esito è negativo il comando createMap non sarà eseguito. 
	 * @param fromdate - Data inizio dell'intervallo
	 * @param todate - Data fine dell'intervallo
	 */
	private static void createMap(String fromdate, String todate) {

		SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
		Date start, end;
		formatDate.setLenient(false);

		try {

			start = formatDate.parse(fromdate);
			end = formatDate.parse(todate);

			if (start.before(end)) {
				PointOfInterest.calculateMap(start, end);

			} else
				System.out.println(fromdate + "avviene dopo di " + todate);

		} catch (ParseException e) {
			System.out.println("La rappresentazione della data è sbagliata");
		}

	}
	
	

	/**
	 * controlla il formatto del comando letto dal file utilizzando le espressioni
	 * regolari Se il comando passato come parametro rispetta uno dei due formati
	 * desiderati il metodo restituisce true in caso contrario restituisce false
	 * 
	 * @param commandString - Stringa da validare
	 * @return boolean - esito della validazione
	 */
	private static boolean validateFormatCommand(String commandString) {
		if (Pattern.matches(regexImport.pattern(), commandString)
				|| Pattern.matches(regexCreateMap.pattern(), commandString)) {
			return true;
		} else
			return false;
	}
/**
 * Calcola la distanza dell'evento da ogni punto d'interesse e le salva all'interno di un array di 
 * lunghezza 3, ogni posizione rappresenta la distanza dell'evento da un punto d'interesse, 
 * la posizione 0 contiene la distanza dell'evento dal punto d'interesse 1 "Duomo" la posizione 1 la
 * distanza dal punto d'interesse 2 "L'arco della pace" e l'ultima posizione la distanza dell'evento
 * dal punto d'interesse 3 "Navigli"
 * @param e - l'evento 
 * @return distances - array che contiene la distanza dell'evento dai 3 punti d'interesse
 */
	private static double[] distanceEventsToPoints(Event e) {

		double[] distances = new double[3];

		for (int i = 0; i < 3; i++) {
			double distance = e.getCoordinate().distanceTo(PointOfInterest.getPoints().get(i).getCordinate());
			distances[i] = distance;
		}
		return distances;
	}
 /**
  * PutEvent è un metodo privato che assoccia un evento al punto d'interesse più vicino. 
  * Effettua una serie di confronti fino a trovare il punto d'interesse più vicino, una volta
  * trovato chiama il metodo addEvent del punto d'interesse che si occuppera di inserire l'evento
  * all'interno della strutta dati che contiene gli eventi. Se
  * l'evento dista più di 2.5 km da tutti i punti d'interesse non verrà associato a nessuno di essi.
  * @param e - Evento da associare al punto d'interesse più vicino.
  */
	private static void putEvent(Event e) {
		double distancemax = 2.5;
		double[] ranges = distanceEventsToPoints(e);
		if (ranges[0] <= ranges[1] && ranges[0] <= ranges[2] && ranges[0] <= distancemax) {
			PointOfInterest.getPoints().get(0).addEvent(e);
		} else if (ranges[1] <= ranges[0] && ranges[1] <= ranges[0] && ranges[1] <= distancemax) {
			PointOfInterest.getPoints().get(1).addEvent(e);
		} else if (ranges[2] <= ranges[0] && ranges[2] <= ranges[1] && ranges[2] <= distancemax) {
			PointOfInterest.getPoints().get(2).addEvent(e);
		}
	}

}
