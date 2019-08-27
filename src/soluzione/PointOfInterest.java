package soluzione;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.Date;
import java.util.TreeSet;

/**
 * Gli oggetti di questa classe rappresentano dei punti d'interesse
 * 
 * @author ulise
 */
public class PointOfInterest {

	private CoordinateGeografiche coordinate;
	private String nome;
	private TreeSet<Event> contenEvents;

	public PointOfInterest(String nome, double latitude, double longitude) {
		this.nome = nome;
		this.coordinate = new CoordinateGeografiche(latitude, longitude);
		this.contenEvents = new TreeSet<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CoordinateGeografiche getCordinate() {
		return coordinate;
	}

	public void addEvent(Event e) {
		this.contenEvents.add(e);
	}

	public ArrayList<Event> getListEvents() {
		ArrayList<Event> eventlist = new ArrayList<Event>(contenEvents);
		return eventlist;
	}

	public String createMapAll(Date dateone, Date datetwo) {
		int countstate[] = new int[10];
		String result[] = new String[6];
		int sumevent = 0;
		int sumactive = 0;

		for (int i = 0; i < countstate.length; i++) {
			countstate[i] = 0;
		}

		for (Event evento : contenEvents) {
			int confronto = evento.getData().compareTo(datetwo);
			if (confronto == 0 || confronto == -1) {
				if (evento.getData().after(dateone) || evento.getData().equals(dateone)) {
					switch (evento.getStatoEmotivo()) {
					case "A":
						countstate[0]++;
						sumevent++;
						if (evento.getStateUser()) {
							sumactive++;
							countstate[5]++;
						}
						break;
					case "F":
						countstate[1]++;
						sumevent++;
						if (evento.getStateUser()) {
							sumactive++;
							countstate[6]++;
						}
						break;
					case "S":
						countstate[2]++;
						sumevent++;
						if (evento.getStateUser()) {
							sumactive++;
							countstate[7]++;
						}
						break;
					case "T":
						countstate[3]++;
						sumevent++;
						if (evento.getStateUser()) {
							sumactive++;
							countstate[8]++;
						}
						break;
					case "N":
						countstate[4]++;
						sumevent++;
						if (evento.getStateUser()) {
							sumactive++;
							countstate[9]++;
						}
					}
				}
			} else
				break;
		}

		 System.out.println(this.getClass().getName() + " - " + Math.round(((double)(countstate[0]*100)/sumevent))+ "%" + "A, "
				+ (double)((countstate[1] * 100) / sumevent) + "%" + "F, " + ((countstate[2] * 100) / sumevent) + "%" + "S, "
				+ ((countstate[3] * 100) / sumevent) + "%" + "T," + ((countstate[4] * 100) / sumevent) + "%" + "N");
	
		if(sumactive!=0) {
		 return ""+this.getClass().getName()+ " - " +((countstate[5]*100)/sumactive)+"%A, "+
				 ((countstate[6]*100)/sumactive)+"%F, "+((countstate[7]*100)/sumactive)+"%S, "+
				 ((countstate[8]*100)/sumactive)+"%T, "+((countstate[9]*100)/sumactive)+"%N, ";
		}
		else
			return ""+this.getClass().getName()+ " - 0%A, 0%F, 0%S, 0%T, 0%N ";
	
	}


	@Override
	public String toString() {
		return this.getClass().getName() + "[ nome = " + nome + " " + " coordinate: " + coordinate.toString() + " ] ";
	}

}
