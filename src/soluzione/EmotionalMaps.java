//Sanchez Perez Ulises Ezequiel 731583

package soluzione;

public class EmotionalMaps {

	public static void main(String[] args) {
		
		//Se l'array args � vuoto viene fornito un messaggio d'errore 
		if(args.length > 0) { 
		Operazioni.handleCommands(args[0]);
		}
		else 
			System.err.println("Non � stato inserito nessun percorso");

	}

}
