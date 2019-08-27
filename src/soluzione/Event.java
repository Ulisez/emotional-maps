package soluzione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.TreeSet;

/**
 * La classe Event rappresenta un evento. Un'istanza di questa classe contiene
 * le informazioni di un determinato evento.
 * 
 * @author ulise
 * @version 1.0
 */
public class Event implements Comparable<Event> {

	// private boolean statoRegistrazione;
	// private boolean statoUtente;
	private String stateRegister;
	private boolean stateUser;
	private Date date;
	private String idUser;
	// private double longitudine;
	// private double latitudine;
	private CoordinateGeografiche coordinate;
	private String statoEmotivo;

	/**
	 * Unico costruttore della classe, crea un'istanza della classe event
	 * 
	 * @param register    Lo stato di registrazione dell'utente
	 * @param stateUser   Lo stato di attivazione dell'utente
	 * @param date        La data in cui è stato effettuato l'evento
	 * @param id          Il codice identificativo dell'utente
	 * @param longitudine longitudine della posizione dell'utente
	 * @param latitudine  latitudine della posizione dell'utente
	 * @param emozione    Lo stato emotivo dell'utente
	 * @throws EventException Se l'id dell'utente è composto di meno di 5 caratteri,
	 *                        se la data, longitudine e latitudine non rispettano il
	 *                        formatto adeguato.
	 */
	public Event(String register, String stateUser, String date, String id, String latitude, String longitude,
			String emozione) throws EventException {
		setStateRegister(register);
		setStatoUtente(stateUser);
		setData(date);
		setIdUtente(id);
		coordinate = new CoordinateGeografiche(latitude, longitude);
		setStatoEmotivo(emozione);

	}

	/**
	 * Restuisce lo stato della registrazione dell'utente
	 * 
	 * @return Stato registraione all'applicazione
	 */
	public String getStateRegister() {
		return stateRegister;
	}

	/**
	 * Modifica lo stato di registrazione dell'utente IN utente registrato - OUT
	 * utente non registrato
	 * 
	 * @param statoRegistrazione stato di registrazione(IN o OUT )
	 */
	public void setStateRegister(String statoRegistrazione) {
		this.stateRegister = statoRegistrazione;
	}

	/**
	 * Restituisce lo stato d'attivazione dell'utente per quel evento
	 * 
	 * @return stato attivazione utente
	 */
	public boolean getStateUser() {
		return stateUser;
	}

	/**
	 * Modifica lo stato di attivazione dell'utente per quel evento, non viene fatto
	 * nessun controllo perché viene già effettuato tramite l'espressione regolare
	 * 
	 * @param statoUtente
	 */
	public void setStatoUtente(String statoUtente) {
		if (statoUtente.equals("LOGIN"))
			this.stateUser = true;
		else
			this.stateUser = false;
	}

	/**
	 * Restitusice la data dell'evento che esegue il metodo
	 * 
	 * @return data dell'evento
	 */
	public Date getData() {
		return date;
	}

	/**
	 * Modifica la data dell'evento, viene effettuato un controllo sulla data e la
	 * conversione da String a Date
	 * 
	 * @param data La data in tipo stringa
	 * @throws EventException se la data passata come parametro non rappresenta una
	 *                        data corretta oppure se non rispetta il formatto
	 *                        derideto GGMMAAAA
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
	 * @throws EventException se il codice identificativo è composto di meno o più
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
	/*
	 * public double getLongitudine() { return longitudine; }
	 */

	/**
	 * 
	 * @param longitudine
	 * @throws EventException
	 */
	/*
	 * public void setLongitudine(String longitudine) throws EventException { try {
	 * this.longitudine = Double.parseDouble(longitudine); } catch (Exception e) {
	 * throw new EventException("Longitudine non corretta"); } }
	 */

	/**
	 * Restituisce la latitudine dell'evento che esegue il metodo
	 * 
	 * @return latitudine
	 */
	/*
	 * public double getLatidudine() { return latitudine; }
	 */

	/**
	 * Setta(Modifica) la latitudine dell'evento che esegue il metodo
	 * 
	 * @param latitudine
	 * @throws EventException
	 */
	/*
	 * public void setLatitudine(String latitudine) throws EventException { try {
	 * this.latitudine = Double.parseDouble(latitudine); } catch (Exception e) {
	 * throw new EventException("Latitudine non corretta"); } }
	 */

	/**
	 * Restutisce lo stato emotivo dell'utente associato all'evento che esegue il
	 * metodo
	 * 
	 * @return lo stato emotivo dell'utente
	 */
	public String getStatoEmotivo() {
		return statoEmotivo;
	}

	/**
	 * Setta lo stato emotivo dell'utente
	 * 
	 * @param statoEmotivo
	 */
	public void setStatoEmotivo(String statoEmotivo) {
		this.statoEmotivo = statoEmotivo;
	}

	public CoordinateGeografiche getCoordinate() {
		return coordinate;
	}

	/**
	 * Converte la stringa che rappresenta la data dell'evento nel formatto
	 * Giorno/Mese/Anno se la stringa non rispetta il formatto adeguato o se la data
	 * non è corretta viene visualizzato un messaggio d'errore, altrimenti la
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
				+ idUser + coordinate.toString() + ", statoEmotivo=" + statoEmotivo + "]";
	}

	/**
	 * Confronta due eventi e indica se sono uguali o meno
	 * 
	 * @param other - L'evento con cui viene confrontato l'evento che esegue il
	 *              metodo
	 * @return restituisce true se due eventi sono uguali, due eventi sono uguali se
	 *         hanno le stesse coordinate, lo stesso ID, la stessa data e lo stesso
	 *         stato emotivo. Altrimenti restituisce false
	 */
	public boolean equals(Event other) {
		if (other != null && (this.getData().equals(other.getData())) && this.coordinate.equals(other.coordinate)
				&& this.getIdUtente().equals(other.getIdUtente())
				&& (this.getStatoEmotivo().equals(other.getStatoEmotivo())))
			return true;
		else
			return false;
	}

	@Override
	public int compareTo(Event other) {
		if (other != null)
			if (this.date.compareTo(other.date) != 0) {
				return this.date.compareTo(other.date);
			} else {
				if (this.idUser.compareTo(other.idUser) != 0) {
					return this.idUser.compareTo(other.idUser);
				} else
					return this.statoEmotivo.compareTo(other.statoEmotivo);
			}
		else
			return 0;
	}

}
