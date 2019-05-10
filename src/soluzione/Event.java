package soluzione;

import java.util.Date;

/**
 * 
 * @author ulise
 * Rappresenta l'evento 
 *  
 *
 */

public class Event {
 
	private boolean statoRegistrazione; // 0 significa che l'utente non è registrato, 1 altrimenti
	private boolean statoUtente;        // 0 significa che l'utente non era loggato, 1 altrimenti
	private Date data;                 //La data di quando è stato effettuato l'evento
	private String idUtente;           //Codice identificativo dell'utente
	private double longitudine;       /* Cordinate, rappresentazio la posizione dell'utente*/
	private double latidudine;          
	private char statoEmotivo;       //Rappresenta lo stato emotivo dell'utente 
	
	public boolean isStatoRegistrazione() {
		return statoRegistrazione;
	}
	public void setStatoRegistrazione(boolean statoRegistrazione) {
		this.statoRegistrazione = statoRegistrazione;
	}
	public boolean isStatoUtente() {
		return statoUtente;
	}
	public void setStatoUtente(boolean statoUtente) {
		this.statoUtente = statoUtente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	public double getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}
	public double getLatidudine() {
		return latidudine;
	}
	public void setLatidudine(double latidudine) {
		this.latidudine = latidudine;
	}
	public char getStatoEmotivo() {
		return statoEmotivo;
	}
	public void setStatoEmotivo(char statoEmotivo) {
		this.statoEmotivo = statoEmotivo;
	}
	
	
}
