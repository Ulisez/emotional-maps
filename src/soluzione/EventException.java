package soluzione;

@SuppressWarnings("serial")

/**
 * EventException è una sottoClasse di Exception e viene utilizzata per indicare 
 * li errori che potrebbero essere sollevati dalla classe Event. 
 * @author ulise
 *
 */
public class EventException extends RuntimeException{
	
	/**
	 * Costruttore di default della classe EventException, crea un'istanza di questa classe,
	 * l'istanza creata indica un'eccezione non controllata 
	 */
	public EventException() {
		super();
	}
	
	/**
	 * Costruttore della classe EventException, crea un'istanza di quesa clase
	 * @param messageError è il messaggio di errore personalizzato
	 */
	public EventException(String messageError) {
		super(messageError);
	}

}
