/* Sanchez Perez Ulises Ezequiel 731583
   Selma 
   Alice 
   Ismael */

package soluzione;

/**
 * La classe EmotionalMaps � la classe principale dell'applicazione
 * @author ulise
 *
 */

public class EmotionalMaps {

	/**
	 * Il metodo main l'unica cosa che fa � controllare che venga passato un parametro
	 * (il nome del file dal quale leggere i comandi), se non viene passato nessun pamatro
	 * visualizza un messeggario d'errore. 
	 */
	public static void main(String[] args) {
		
   //Verifica dell'esistenza di una stringa passata come parametro
		try{ 
		Operations.handleCommands(args[0]);
		}catch(Exception e) {
			System.err.println("Non � stato inserito nessun percorso");
			//e.printStackTrace();
			}
		
		}
	}
