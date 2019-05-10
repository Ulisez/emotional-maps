
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
 * @author ulise 
 *         La classe Operations effettura tutte le operazioni necessarie
 *         per processare i comandi inoltre vengono effetuati tutti gli
 *         algoritmi neccesari per il giusto funzionamento dell'applicazione
 */
public class Operations {

	protected static List<Event> events = new ArrayList<Event>();
	public static Queue<String> commands = new LinkedList<String>(); // Coda per salvare i comandi letti dal file

	/**
	 * <b>handleCommands</b> <br>
	 * public static void handleCommands(<a>String</a> fileName) <br>
	 * <br>
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
				if (validationFormatCommand(commandString)) // stampo il numero della riga che non rispetta il formatto
					commands.add(commandString);
				else
					System.err.println(" La Stringa " + commandString + " (Riga " + index
							+ " del file) non rispetta il formato desiderato");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		/*
		 * while(!commandscoda.isEmpty()) { System.out.println("");
		 * System.out.println(commandscoda.peek()); } Questo metodo l'uso solo per
		 * stampare gli elementi all'interno della coda
		 */
		processCommand();
	}

	/**
	 * <b>processComand()</b> <br>
	 * public static void handleCommands() <br>
	 * <br>
	 * Il metodo processCommand è un metodo privato della classe Operations. Questo
	 * metodo itera la coda e processa un comando alla volta, se il comando letto è
	 * un import(event.txt) chiama la procedura Import(event) e gli passa il nome
	 * del file dal quale leggere gli eventi invece se il comando rappresenta un
	 * create_map(StartDate, finisDate) viene chiamata la procedura Create_Map().
	 */
	private static void processCommand() {

		Pattern pattern = null;
		String command; // Leggere i comandi dalla coda
		Matcher matcher;
		while (!commands.isEmpty()) {
			command = commands.poll();
			System.err.println("Sono entrato nel while di processCommand, sto svuotando la coda " + command);
			pattern = Pattern.compile("^import\\((.*?)\\)$");
			matcher = pattern.matcher(command);
			boolean found = false;
			while (matcher.find()) {
				found = true;
				System.out.println("sono all'interno del while di matcher " + matcher.group(1));
				String eventFileName = matcher.group(1);
				Import(eventFileName);
			}
			if (!found) {
				System.out.println("sono entrato qui");
				pattern = Pattern.compile("^create_map\\((\\d{8}?)-(\\d{8}?)\\)$");
				matcher = pattern.matcher(command);
				while (matcher.find()) {
					System.out.println("sono un bel create MAP");
					// String fromDate =
					System.out.println("DA " + matcher.group(1) + " A " + matcher.group(2));
					// String toDate =
				}
				// create_Map(fromDate,toDate);
			}
		}
	}

	/**
	 * <b>validationFormatCommand()</b> <br>
	 * boolean validationFormatCommand(String commandString) <br>
	 * <br>
	 * Il metodo validationFormatCommand controlla il formatto del comando
	 * utilizzando le espressioni regolari Se il comando passato come parametro
	 * rispetta il formato desiderato il metodo restituisce true in caso contrario
	 * restituisce false
	 * 
	 * @param commandString - Comando
	 * @return boolean
	 */
	private static boolean validationFormatCommand(String commandString) {
		// espressione regolare per il comando Import
		String regexImport = "^import\\((.*?)\\)$";
		// espressione regolare per il createMap
		String regexCreateMap = "^create_map\\((\\d{8}?)-(\\d{8}?)\\)$";
		// Se commandString rispetta una di queste espressioni regolari restituisce true
		if (Pattern.matches(regexImport, commandString) || Pattern.matches(regexCreateMap, commandString)) {
			return true;
		} else
			return false;
	}

	/**
	 * <b>Import</b> <br>
	 * Import(String evenFileName)<br>
	 * <br>
	 * Il metodo import legge e salva gli eventi in una struttara dati, il nome del
	 * file dal quale leggere gli eventi viene passato come parametro.
	 * 
	 * @param eventFileName - File dal quale importare gli eventi
	 */
	private static void Import(String eventFileName) {

		String event; //leggere l'evento

		/*
		 * Dichiarando lo stream all'interno della clausola try, una volta eseguita la
		 * clausola lo stream verrà chiuso automaticamente
		 */

		try (BufferedReader readerEvent = new BufferedReader(new FileReader(eventFileName))) {
			while ((event = readerEvent.readLine()) != null) {
				System.out.println(event);
				events.add(HandleEvents.createEvent(event)); // Salvo l'ogetto evento restituito dal metedo
			}

		} catch (Exception e) {
			System.out.println("SONO QUI DENTRO");
			// e.getCause();
			e.printStackTrace();
		}

	}

	/*
	 * private static void createMap() {
	 * 
	 * }
	 */

}
