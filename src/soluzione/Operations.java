
package soluzione;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
//import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * La classe Operations effettura tutte le operazioni necessarie per processare
 * i comandi inoltre vengono effetuati tutti gli algoritmi neccesari per il
 * giusto funzionamento dell'applicazione.
 * 
 * @author Ulises Sanchez
 */
public class Operations {

	private static final Pattern regexImport = Pattern.compile("^import\\((.*?)\\)$");

	private static final Pattern regexCreateMap = Pattern.compile("^create_map\\((\\d{8}?)-(\\d{8}?)\\)$");

	private static final Pattern Event_Pattern = Pattern
			.compile("^(IN|OUT) (LOGIN|LOGOUT) (\\d{8}) (.*?) (\\d.*),(\\d.*) (A|F|S|T|N)$");

	protected static List<Event> events = new ArrayList<Event>();

	public static Queue<String> commands = new LinkedList<String>();

	/**
	 * Legge i comandi dal file di testo passato come parametro, controlla se il
	 * file esiste, in caso positivo legge i comandi e li salva all'interno di una
	 * coda solo se rispettano il formatto adeguato, invece se il file non esiste
	 * viene stampato un messaggio d'errore
	 * @param fileName - Il nome del file dal quale leggere i comandi
	 */
	public static void handleCommands(String fileName) {
		String commandString;
		try (BufferedReader readerFile = new BufferedReader(new FileReader(fileName))) {
			int index = 0;
			while ((commandString = readerFile.readLine()) != null) {
				System.out.println(commandString);
				index++;
				if (validateFormatCommand(commandString)) // stampo il numero della riga che non rispetta il formatto
					commands.add(commandString);
				else
					System.err.println(" La Stringa " + commandString + " (Riga " + index
							+ " del file) non rispetta il formato desiderato");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		processCommand();
		
		for(Event e: events) {
			System.out.println(e.toString());
		}

	}

	/**
	 * Questo metodo itera la coda e processa un comando alla volta, se il comando
	 * letto è un import(event.txt) chiama la procedura Import(event) e gli passa il
	 * nome del file dal quale leggere gli eventi invece se il comando rappresenta
	 * un create_map viene chiamata la procedura Create_Map().
	 */
	private static void processCommand() {
		String command;
		Matcher matcher;
		while (!(commands.isEmpty())) {
			command = commands.poll();
			if (command.contains("import")) {
				System.out.println("Mi trovo qui import");
				matcher = regexImport.matcher(command);
				if (matcher.matches()) {
					String fileName = matcher.group(1);
					Import(fileName);
				}
			} else {
				matcher = regexCreateMap.matcher(command);
				if (matcher.matches()) {
					System.out.println("mi trovo qui dentro createMap");
					String fromdate = matcher.group(1);
					String todate = matcher.group(2);
					createMap(fromdate, todate);

				}
			}
		}

	}

	/**
	 * Importa gli eventi dal file di testo passato come parametro.Per ogni stringa
	 * letta dal file viene effettuato un piccolo controllo sul formatto. Se il
	 * controllo su una stringa ha esito positivo, vengono prese tutte le sue
	 * sottostringhe e usate come parametri per creare un oggeto di tipo evento
	 * corrispodente, l'evento creato viene salvato in una struttura dati. In caso
	 * contratio ovvero se la stringa non rispetta il formatto, viene ignorata e si
	 * passa alla lettura della stringa successiva.
	 *  @param eventFileName - File dal quale importare gli eventi
	 */
	private static void Import(String eventFileName) {
		String evento;
		try (BufferedReader lettore = new BufferedReader(new FileReader(eventFileName))) {
			while ((evento = lettore.readLine()) != null) {
				System.out.println(evento);
				if (Pattern.matches(Event_Pattern.pattern(), evento)) {
					Matcher matEvent = Event_Pattern.matcher(evento);
					while (matEvent.find()) {
						try {
						events.add(new Event(matEvent.group(1), matEvent.group(2), matEvent.group(3), matEvent.group(4),
								matEvent.group(5), matEvent.group(6), matEvent.group(7)));
						}catch(EventException e) {
							System.out.println(e.getMessage());
						}
					}

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Crea la mappa emozionale
	 * @param fromdate
	 * @param todate
	 */
	private static void createMap(String fromdate, String todate) {
		System.out.println(fromdate + " ," + todate);
	}

	/**
	 * controlla il formatto del comando letto dal file utilizzando le espressioni
	 * regolari Se il comando passato come parametro rispetta uno dei due formati
	 * desiderati il metodo restituisce true in caso contrario restituisce false
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

}
