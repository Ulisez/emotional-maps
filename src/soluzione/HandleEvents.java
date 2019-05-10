package soluzione;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * @author ulise La classe HandelEvents è stata create per gestire la creazione
 *         degli eventi, questa classe si occupa di controllare il formatto
 *         della stringa che rappresneta un evento, se l'estringa rispetta il
 *         formatto desiderato, viene creato l'ogetto e restituito alla metodo
 *         chiamante.
 *
 */

public class HandleEvents {
	// Espressione regolare, rappresenta il formatto desiderato che la strina evento
	// dovrebbe rispettare
	private static final String regexEvent = "^(IN|OUT) (LOGIN|LOGOUT) (\\d{8}) (.*?) (.*?),(.*?) (A|F|S|T|N)$";

	protected static Event createEvent(String event) {
		Event eventobean;

		if (ValidationFormatEvent(event)) {
			eventobean = new Event();

			Pattern patternEvent = Pattern.compile(regexEvent);
			Matcher matcher = patternEvent.matcher(event);

			while (matcher.find()) {
				eventobean.setStatoRegistrazione(stateRegister(matcher.group(1)));
				eventobean.setStatoUtente(stateUser(matcher.group(2)));
				eventobean.setData(validationDate(matcher.group(3)));
				eventobean.setIdUtente(matcher.group(4));
				eventobean.setLatidudine(Double.parseDouble(matcher.group(5))); // Da modificare più avanti
				eventobean.setLongitudine(Double.parseDouble(matcher.group(6))); // Anche questo è da modificare
				// eventobean.setStatoEmotivo((setState(matcher.group(7))));
				// eventobean.setStatoEmotivo(StatoEmotivo.ARRABBIATO);
			}
			return eventobean;

		}
		return null;

	}

	/**
	 * <b>validationDate</b> <br>
	 * Questo metodo prende come parametro la sottostringa dell'evento che
	 * rappresenta la data
	 * @param date - data dell'evento
	 * @return Date
	 */
	private static Date validationDate(String date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
		formatDate.setLenient(false); // Fa un controllo rigosoro sulla data
		Date dateEvent;

		try {
			dateEvent = formatDate.parse(date);
			return dateEvent;

		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	/**
	 * In base alla Stringa presa come parametro setta lo stato dello utente Se il
	 * valore della stringa è IN, setto lo stato della registrazione a 1 altrimenti
	 * se il valore è OUT setto lo stato di registrazione dell'utente a 0
	 * 
	 * @param value
	 * @return booelan
	 */
	private static boolean stateRegister(String value) {
		if (value.equals("IN"))
			return true;
		else
			return false;
	}

	/**
	 * <b>stateUser</b> <br>
	 * In base al valore letto dalla stringa che rappresenta l'evento, setto lo
	 * stato dell'utente
	 * 
	 * @param value
	 * @return
	 */
	private static boolean stateUser(String value) {
		if (value.equals("LOGIN"))
			return true;
		else
			return false;
	}

	/**
	 * Metodo di soppurto, viene utilizzato per controllare il formatto della
	 * stringa(evento) Controlla il formatto della stringa(evento)se rispetta il
	 * formato adatto restituisce true
	 * @param eventConsidered
	 * @return boolean
	 */
	private static boolean ValidationFormatEvent(String eventConsidered) {

		if (Pattern.matches(regexEvent, eventConsidered))
			return true;
		else
			return false;
	}

}
