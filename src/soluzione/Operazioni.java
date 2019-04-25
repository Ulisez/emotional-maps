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

public class Operazioni {

	protected static List<Evento> eventi = new ArrayList<Evento>();
	public static Queue<String> commandscoda = new LinkedList<String>();

	/*Metodo per la gestione dei comandi
	Verifico che il file esista se esite leggo i comandi all'interno del file e verifico che ogni 
	riga del file rappresenti effettivmaente un comando */
	
	public static void handleCommands(String fileName) {
		   String commandString;
		try(BufferedReader readerFile = new BufferedReader(new FileReader(fileName))) {
		     
			int index = 0;
		     
			while ((commandString = readerFile.readLine()) !=null) {
		    System.out.println(commandString);
		    index++;
		     //processCommand(commandString);
		    if(validationFormatCommand(commandString))  //invio il numero della riga che non rispetta il formatto
		        commandscoda.add(commandString);
		    else
		    	System.err.println(" La Stringa " +commandString + " (Riga " + index + " del file) non rispetta il formato desiderato");
		      }
		
		}catch (Exception e) {
			 System.err.println(e.getMessage());
			    }
		
		/*while(!commandscoda.isEmpty()) {
			System.out.println("");
			System.out.println(commandscoda.peek());
			} Questo metodo l'uso solo per stampare gli elementi all'interno della coda*/
		processCommand();
	}
		
   
	
	private static void processCommand() {
		
		Pattern pattern=null;
		String command;
		Matcher matcher;
		while(!commandscoda.isEmpty()) {
			command = commandscoda.poll();
			System.err.println("Sono entrato nel while di processCommand, sto svuotando la coda " + command);
            pattern = Pattern.compile("^import\\((.*?)\\)$");
            matcher = pattern.matcher(command);
            boolean found = false;
			     while(matcher.find()) {
			    	 found = true;
				 System.out.println("sono all'interno del while di matcher " + matcher.group(1));
			     String eventFileName = matcher.group(1);
			     Import(eventFileName);
					 }
				if(!found){ 
			      System.out.println("sono entrato qui");
				 pattern = Pattern.compile("^create_map\\((\\d{8}?)-(\\d{8}?)\\)$");
				 matcher = pattern.matcher(command);
				  while(matcher.find()) {
					 System.out.println("sono un bel create MAP");
					 //String fromDate = 
							 System.out.println("DA " +matcher.group(1)+ " A " +matcher.group(2));
					 //String toDate = 
						
				  }
					 //create_Map(fromDate,toDate);
				 }
			 }		
		}
	
	
	private static boolean validationFormatCommand(String commandString) {
	    
		String regexImport = "^import\\((.*?)\\)$";
		String regexCreateMap = "^create_map\\((\\d{8}?)-(\\d{8}?)\\)$";
		
		if(Pattern.matches(regexImport, commandString) || Pattern.matches(regexCreateMap, commandString)){
			return true;
			}
		else 
			return false;
		}
    
    
	private static void Import(String eventFileName) {
	   String event;
	   
		try(BufferedReader readerEvent = new BufferedReader(new FileReader(eventFileName))){
		   while((event = readerEvent.readLine()) != null) {
			   System.out.println(event);
			   
		   }
		   
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }
	}
    
    /*private static void createMap() {
    	
    }*/

}
