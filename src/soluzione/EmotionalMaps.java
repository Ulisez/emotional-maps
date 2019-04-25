//Sanchez Perez Ulises Ezequiel 731583

package soluzione;

public class EmotionalMaps {

	public static void main(String[] args) {
		
		//Verificazione dell'esistenza di una stringa passata come parametro al metodo main
		try{ 
		Operazioni.handleCommands(args[0]);
		}catch(Exception e) {
			System.err.println("Non è stato inserito nessun percorso");
			}
		}
	}
