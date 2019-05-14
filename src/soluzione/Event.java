package soluzione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La classe Event rappresenta un evento. Un'istanza di questa classe contiene
 * le informazioni di un determinato evento.
 * @author ulise
 * @version 1.0
 */
public class Event implements Comparable<Event> {

	// private boolean statoRegistrazione;
	// private boolean statoUtente;
	private String stateRegister;
	private String stateUser;
	private Date date;
	private String idUser;
	private double longitudine;
	private double latitudine;
	private char statoEmotivo;

	/**
	 * Unico costruttore della classe, crea un'istanza della classe event
	 * 
	 * @param register    Lo stato di registrazione dell'utente
	 * @param stateUser   Lo stato di attivazione dell'utente
	 * @param date        La data in cui � stato effettuato l'evento
	 * @param id          Il codice identificativo dell'utente
	 * @param longitudine longitudine della posizione dell'utente
	 * @param latitudine  latitudine della posizione dell'utente
	 * @param emozione    Lo stato emotivo dell'utente
	 * @throws EventException Se l'id dell'utente � composto di meno di 5 caratteri,
	 *                        se la data, longitudine e latitudine non rispettano il
	 *                        formatto adeguato.
	 */
	public Event(String register, String stateUser, String date, String id, String longitudine, String latitudine,
			String emozione) throws EventException {
		setStateRegister(register);
		setStatoUtente(stateUser);
		setData(date);
		setIdUtente(id);
		setLatitudine(latitudine);
		setLongitudine(longitudine);
		setStatoEmotivo(emozione);

	}

	/**
	 * Restuisce lo stato della registrazione dell'utente
	 * @return Stato registraione all'applicazione
	 */
	public String getStateRegister() {
		return stateRegister;
	}

	/**
	 * Modifica lo stato di registrazione dell'utente IN utente registrato - OUT
	 * utente non registrato
	 * @param statoRegistrazione stato di registrazione(IN o OUT )
	 */
	public void setStateRegister(String statoRegistrazione) {
		this.stateRegister = statoRegistrazione;
	}

	/**
	 * Restituisce lo stato d'attivazione dell'utente per quel evento
	 * @return stato attivazione utente
	 */
	public String getStateUser() {
		return stateUser;
	}

	/**
	 * Modifica lo stato di attivazione dell'utente per quel evento, non viene fatto
	 * nessun controllo perch� viene gi� effettuato tramite l'espressione regolare
	 * @param statoUtente
	 */
	public void setStatoUtente(String statoUtente) {
		this.stateUser = statoUtente;
	}

	/**
	 * Restitusice la data dell'evento che esegue il metodo
	 * @return data dell'evento
	 */
	public Date getData() {
		return date;
	}

	/**
	 * Modifica la data dell'evento, viene effettuato un controllo sulla data e la
	 * conversione da String a Date
	 * @param data La data in tipo stringa
	 * @throws EventException se la data passata come parametro non rappresenta una
	 *data corretta oppure se non rispetta il formatto derideto GGMMAAAA
	 */
	public void setData(String data) throws EventException {
		if (validateDate(data) != null)
			this.date = validateDate(data);
		else
			throw new EventException(" Data non valida ");
	}

	/**
	 * Restituisce il codice identificativo dell'utente
	 * 
	 * @return id dell'utente
	 */
	public String getIdUtente() {
		return idUser;
	}

	/**
	 * Setta(modifica) il codice identificativo dell'utente
	 * 
	 * @param idUtente codice dell'utente
	 * @throws EventException se il codice identificativo � composto di meno o pi�
	 *                        di 5 caratteri
	 */
	public void setIdUtente(String idUtente) throws EventException {
		if (idUtente.length() == 5)
			this.idUser = idUtente;
		else
			throw new EventException("ID utente non corretto");
	}

	/**
	 * Restutuisce la longitudine dell'evento che esegue il medoto
	 * 
	 * @return longitudine dell'evento
	 */
	public double getLongitudine() {
		return longitudine;
	}

	/**
	 * 
	 * @param longitudine
	 * @throws EventException
	 */
	public void setLongitudine(String longitudine) throws EventException {
		try {
			this.longitudine = Double.parseDouble(longitudine);
		} catch (Exception e) {
			throw new EventException("Longitudine non corretta");
		}
	}

	/**
	 * Restituisce la latitudine dell'evento che esegue il metodo
	 * 
	 * @return latitudine
	 */
	public double getLatidudine() {
		return latitudine;
	}

	/**
	 * 
	 * @param latitudine
	 * @throws EventException
	 */
	public void setLatitudine(String latitudine) throws EventException {
		try {
			this.latitudine = Double.parseDouble(latitudine);
		} catch (Exception e) {
			throw new EventException("Latitudine non corretta");
		}
	}

	/**
	 * Restutisce lo stato emotivo dell'utente associato all'evento che esegue il
	 * metodo
	 * 
	 * @return lo stato emotivo dell'utente
	 */
	public char getStatoEmotivo() {
		return statoEmotivo;
	}

	/**
	 * 
	 * @param statoEmotivo
	 */
	public void setStatoEmotivo(String statoEmotivo) {
		this.statoEmotivo = statoEmotivo.charAt(0);
	}

	/**
	 * Converte la stringa che rappresenta la data dell'evento nel formatto
	 * Giorno/Mese/Anno se la stringa non rispetta il formatto adeguato o se la data
	 * non � corretta viene visualizzato un messaggio d'errore, altrimenti la
	 * stringa viene convertita in tipo Date
	 * 
	 * @param date
	 * @return Date
	 */
	private Date validateDate(String date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
		formatDate.setLenient(false); // controllo rigosoro sulla data
		Date dateEvent;

		try {
			dateEvent = formatDate.parse(date);
			return dateEvent;

		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * Restituisce una stringa con i dati rispettivi all'evento che esegue il metodo
	 * 
	 * @Override
	 */
	public String toString() {
		return "Event [stateRegister=" + stateRegister + ", stateUser=" + stateUser + ", date=" + date + ", idUser="
				+ idUser + ", longitudine=" + longitudine + ", latitudine=" + latitudine + ", statoEmotivo="
				+ statoEmotivo + "]";
	}

	@Override
	public int compareTo(Event o) {

		return 0;
	}

}
