package soluzione;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;

public class Operazioni {

	//public static List<Evento> eventi = new ArrayList<Evento>();

	/*Metodo per la gestione dei comandi
	Verifico che il file esista se esite leggo i comandi all'interno del file e verifico che ogni 
	riga del file rappresenti effettivmaente un comando */
	
	public static void handleCommands(String fileName) {
		
		File file = new File(fileName);
		if(file.isFile()) {
			
		}
		Scanner scanner = null;
		if (file.exists()) {
			try {
				scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					String commandString = scanner.nextLine();
					//processCommand(commandString);
				}
			} catch (FileNotFoundException e) {
				/*e.printStackTrace()*/
				System.out.println("File non esiste");
			}

		}
		else
			System.out.println("Mi trovo qui");
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
	
    /*  public static void import(String eventFileName) {
	 
	}*/

}
