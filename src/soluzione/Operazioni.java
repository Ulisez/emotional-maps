package soluzione;

import java.util.ArrayList;
//import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class Operazioni {

	//public static List<Evento> eventi = new ArrayList<Evento>();
	public static Queue<String> commandscoda = new LinkedList<String>();

	/*Metodo per la gestione dei comandi
	Verifico che il file esista se esite leggo i comandi all'interno del file e verifico che ogni 
	riga del file rappresenti effettivmaente un comando */
	
	public static void handleCommands(String fileName) {
		   String commandString;
		try(BufferedReader readerFile = new BufferedReader(new FileReader(fileName))) {
		while ((commandString = readerFile.readLine()) !=null) {
		    System.out.println(commandString);
		     //processCommand(commandString);
		    if(validationCommand(commandString)) { 
		    commandscoda.add(commandString);
		     }
			}
		while(!commandscoda.isEmpty()) {
			System.out.println("");
			System.out.println(commandscoda.poll());
		}
		} catch (Exception e) {
			 System.err.println(e.getMessage());
			    }
			}
		
    private static void processCommand(String commandString) {
		Pattern pattern = null;

		String regexImport = "^import\\((.*?)\\)$";

		pattern = Pattern.compile(regexImport);
		Matcher matcher = pattern.matcher(commandString);

		boolean found = false;
		while (matcher.find()) {
			found = true;
			//String eventFileName = matcher.group(1);
		}

		if (!found) {
			regexImport = "^create_map\\((\\d{8}?)-(\\d{8}?)\\)$";
			pattern = Pattern.compile(regexImport);
			matcher = pattern.matcher(commandString);
			while (matcher.find()) {
				String da = matcher.group(1);
				String a = matcher.group(2);
			}
		}

	}
	
	//vedere la funzionalita di split in modo di vedere se è possibile gestire due esprissioni regolari con un'unico oggetto
    private static boolean validationCommand(String commandString) {
		boolean validation=false;
		String regexImport = "^import\\((.*?)\\)$";
		String regexCreateMap = "^create_map\\((\\d{8}?)-(\\d{8}?)\\)$";
		Pattern patternImport = Pattern.compile(regexImport);
		Matcher matcherImport = patternImport.matcher(commandString);
		Pattern patternCreate = Pattern.compile(regexCreateMap);
		Matcher matcherCreate = patternCreate.matcher(commandString);
	
		if(matcherImport.matches() || matcherCreate.matches()) {
			validation = true;
			return validation;
			}
		else {
			System.err.println("La Stringa "+commandString + " non rispetta il formato desiderato");
			return false;
		}
	}
	
    /*  public static void import(String eventFileName) {
	 
	}*/

}
