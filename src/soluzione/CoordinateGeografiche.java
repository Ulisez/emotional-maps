/**
 * 
 */
package soluzione;

/**
 * La classe coordinateGeografiche rappresenta un determinato punto sulla terra,
 * ogni punto è caratterizzato da due parametri Latitudine e Longitudine
 * 
 * @author Ulises Sanchez
 *
 */
public class CoordinateGeografiche{

	private double latitude;
	private double longitude;

	
	/**
	 * Crea un'istanza della classe CoordinateGeografiche
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public CoordinateGeografiche(double latitude, double longitude) {

		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Riceve la latitudine e longitudine in formatto stringhe ed effettua una conversione
	 * in formatto double 
	 * @param latitude
	 * @param longitude
	 */
	public CoordinateGeografiche(String latitude, String longitude) {
		try {
			this.latitude = Double.parseDouble(latitude);
			this.longitude = Double.parseDouble(longitude);
		} catch (Exception e) {
			System.out.println("Coordinate sbagliate");
		}

	}
    
	/**
	 * Restituisce la Latitudine del punto geografico
	 * @return double - Latitudine della coordinata.
	 */
	public double getLatitude() {
		return latitude;
	}
/**
 * Modifica la latitudine della coordinata 
 * @param latitude
 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
 
	/**
	 * Restituisce la longitudine della coordinata geografica che esegue il metodo.
	 * @return double - Restituisce la Longitudine del punto geografico.
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Modifica la Longitudine della coordinata Geografica che esegue il medoto.
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Permette di determinare se due coordinate sono uguali o meno. La coordinata geoagrafica
	 * che esegue il metodo viene confrontata con un'altra coordinata passata come parametro.
	 * Due coordinate sono uguali se hanno lo stesso valore di altitudine e longitudine, altrimenti 
	 * sono diverse.
	 * @return boolean - True se le coordinate sono uguali, False altrimenti.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordinateGeografiche other = (CoordinateGeografiche) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	/**
	 * Calcola e restituisce la distanza in kilometri tra due punti geografici.
	 * L'algoritmo utilizzato si basa sulla formula di Haversine che permette
	 * appunto di calcolare la distanza tra due punti sulla terra.
	 * 
	 * @param other rappresenta il punto geografico con nel quale calcolare la
	 *              distanza
	 * @return double - Distanza in chilometri tra due punti geografici.
	 */
	public double distanceTo(CoordinateGeografiche other) {
		double AVERAGE_RADIUS_OF_EARTH_KM = 6372.795477598;
		double distance; // Il calcolo viene effetuato in radianti.

		double latDistance = Math.toRadians(this.latitude - other.latitude);// calcola la differenza tra le latitudine
																			// delle due coordinate

		double lngDistance = Math.toRadians(this.longitude - other.longitude);// Calcola la differenza tra longitudine
																				// delle due coordinate

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(this.latitude))
				* Math.cos(Math.toRadians(other.latitude)) * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

		double calculate = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		distance = (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * calculate));

		return distance;
	}

	@Override
	/**
	 * Visualizza i valori dei campi dell'ogetto che esegue il metodo. 
	 */
	public String toString() {
		return " latitude = " + latitude + ", longitude = " + longitude;
	}
	
	


}