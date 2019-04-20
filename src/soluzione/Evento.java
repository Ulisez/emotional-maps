package soluzione;

import java.util.Date;

public class Evento {
 
	private boolean statoRegistrazione;
	private boolean statoUtente;
	private Date data;
	private String idUtente;
	private double longitudine;
	private double latidudine;
	private StatoEmotivo statoEmotivo;
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
	public StatoEmotivo getStatoEmotivo() {
		return statoEmotivo;
	}
	public void setStatoEmotivo(StatoEmotivo statoEmotivo) {
		this.statoEmotivo = statoEmotivo;
	}
	
	
}
