package soluzione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * La classe Event rappresenta un evento. Un'istanza di questa classe contiene
 * le informazioni di un determinato evento.
 * @author Ulises
 * @version 1.0
 */
public class Event implements Comparable<Event> {

	private boolean stateRegister;
	private boolean stateUser;
	private Date date;
	private String idUser;
	private CoordinateGeografiche coordinate;
	private String emotionalState;

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
	 * @throws EventException - Se l'id dell'utente è composto di meno di 5 caratteri,
	 *                        se la data, longitudine e latitudine non rispettano il
	 *                        formatto adeguato.
	 */
	public Event(String register, String stateUser, String date, String id, String latitude, String longitude,
			String emotion) throws EventException {
		
		setStateRegister(register);
		setStateUser(stateUser);
		setDate(date);
		setIdUser(id);
		coordinate = new CoordinateGeografiche(latitude, longitude);
		setEmotional(emotion);

	}

	/**
	 * Codifica lo stato di registrazione da parte d'un utente all'applicazione in booleano. 
	 * se l'utente è IN(REGISTRATO) lo stato = TRUE, In caso contrario ovvero 
	 * se l'utente non è regitrato "OUT" lo stato = FALSE.  
	 * @param state - stato di registrazione "IN o OUT"
	 */
	private void setStateRegister(String state) {
		if(state.equals("IN"))
			this.stateRegister = true;
		else
			this.stateRegister = false;
	}
	
	/**
	 * Codifica lo stato d'attivazione dell'utente che ha effettuato l'evento. TRUE se l'utente
	 * era attivo(Login), FALSE in caso contrario ovvero se si trovava disattivo(Logout). 
	 * non viene fatto nessun controllo sulla corretteza della stringha, 
	 * perché viene già effettuato tramite l'espressione regolare.
	 * @param statoUtente - Stato d'attivazione "Login o Logout" 
	 */
	private void setStateUser(String state) {
		if (state.equals("LOGIN"))
			this.stateUser = true;
		else
			this.stateUser = false;
	}
	
	/**
	 * Setta la data dell'evento, viene effettuato un controllo sulla data e la
	 * conversione da String a Date
	 * 
	 * @param data La data in tipo stringa
	 * 
	 * @throws EventException se la data passata come parametro non rappresenta una
	 *                        data corretta oppure se non rispetta il formatto
	 *                        derideto GGMMAAAA
	 */
	public void setDate(String data) throws EventException {
		if (validateDate(data) != null)
			this.date = validateDate(data);
		else
			throw new EventException(" Data non valida ");
	}
	
	/**
	 * Setta il codice identificativo dell'utente
	 * 
	 * @param idUtente - codice identificativo dell'utente
	 * 
	 * @throws EventException - se il codice identificativo è composto di meno o più
	 *                        di 5 caratteri
	 */
	public void setIdUser(String idUser) throws EventException {
		if (idUser.length() == 5)
			this.idUser = idUser;
		else
			throw new EventException("ID utente non corretto");
	}
	
	/**
	 * Setta lo stato emotivo dell'utente associato all'evento, lo stato può essere A = Arrabbiato, 
	 * F = Felice, T = Triste, N = Neutro, S = Sorpreso. Il controllo viene effettuato durante la lettura
	 * dell'evento dal file il cui è contenuto, per questo motivo non c'è biogno di fare ulteriori controlli.
	 * 
	 * @param emotionalState - Lo stato emotivo dell'utente
	 */
	public void setEmotional(String emotionalState) {
		this.emotionalState = emotionalState;
	}

	
	/**
	 * Restituisce lo stato di registrazione dell'utente assoviato all'evento.
	 *   
	 * @return - true se l'utente è registrato, FALSE in caso contrario.
	 */
	public boolean getstateRegister() {
		return stateRegister;
	}

	/**
	 * Restituisce lo stato d'attivazione dell'utente per quel evento
	 * 
	 * @return stateUser - stato attivazione utente
	 */
	public boolean getStateUser() {
		return stateUser;
	}

	/**
	 * Restitusice la data dell'evento che esegue il metodo
	 * 
	 * @return date - data dell'evento
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Restituisce il codice identificativo dell'utente
	 * 
	 * @return idUser - id dell'utente
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * Restutisce lo stato emotivo dell'utente associato all'evento che esegue il
	 * metodo
	 * 
	 * @return statomEmotivo - lo stato emotivo dell'utente
	 */
	public String getEmotion() {
		return emotionalState;
	}

	
   /**
    *  Restituisce la coordinata geografica dell'evento 
    * @return coordinate - Oggetto ti tipo coordinata che rappresenta la coordinata: Attitudine e Longitudine del luogo 
    * dove si è svolto l'evento
    * 
    */
	public CoordinateGeografiche getCoordinate() {
		return coordinate;
	}

	/**
	 * Converte la stringa che rappresenta la data dell'evento nel formatto
	 * Giorno/Mese/Anno se la stringa non rispetta il formatto adeguato o se la data
	 * non è corretta viene visualizzato un messaggio d'errore, altrimenti la
	 * stringa viene convertita in tipo Date e restituita.
	 * 
	 * @param date - La data in formatto Stringa 
	 * @return Date - La data dell'evento convertita in formatto Date
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
				+ idUser + coordinate.toString() + ", statoEmotivo=" + emotionalState + "]";
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
		if (other != null && (this.getDate().equals(other.getDate())) && this.coordinate.equals(other.coordinate)
				&& this.getIdUser().equals(other.getIdUser())
				&& (this.getEmotion().equals(other.getEmotion())))
			return true;
		else
			return false;
	}

	/**
	 * Confronta l'evento che esegue il metedo con un altro evento passato come parametro e 
	 * restituisce un numero entero che indica se l'istanza corrente precede, segue o se sono uguali
	 * (Se si trovano nella stessa posizione i ntermini d'ordinamento)
	 */
	@Override
	public int compareTo(Event other) {
		if (other != null)
			if (this.date.compareTo(other.date) != 0) {
				return this.date.compareTo(other.date);
			} else {
				if (this.idUser.compareTo(other.idUser) != 0) {
					return this.idUser.compareTo(other.idUser);
				} else
					return this.emotionalState.compareTo(other.emotionalState);
			}
		else
			return 0;
	}

}
