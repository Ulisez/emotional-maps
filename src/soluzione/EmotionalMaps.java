/* Sanchez Perez Ulises Ezequiel 731583
   Selma 
   Alice */

package soluzione;

/**
 * La classe EmotionalMaps è la classe principale dell'applicazione
 * @author ulise
 *
 */

public class EmotionalMaps {

	/**
	 * Il main passa al metodo handleCommands della classe "Operations" 
	 * la prima stringa che li viene fornita come input tramite la linea di comandi. 
	 * Tale Stringa rappresenta il percorso del file dal quale leggere i comandi. Se non viene passato nessun percorso
	 * come parametro oppure se il percorso inserito è sbagliato viene visualizzto un messeggaio d'errore 
	 * con la corrispettiva causa.
	 * 
	 */
	public static void main(String[] args) {
		try{ 
		Operations.handleCommands(args[0]);
		}catch(Exception e) {
			System.err.println("Non è stato inserito nessun percorso");
			
			}
		
		}
	}
