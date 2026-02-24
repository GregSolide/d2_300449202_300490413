/**
 * @auteur Mehrdad Sabetzadeh, Université d'Ottawa
 *
 */
public class Emplacement {
	private Voiture voiture;
	private int horodatage;

	public Voiture getVoiture() {
		return this.voiture; // Supprimez cette instruction lorsque l’implémentation est complète.

	}

	public void setVoiture(Voiture voiture) {
	
		this.voiture = voiture;
	
	}

	public int getHorodatage() {
		return horodatage;

	}

	public void setHorodatage(int horodatage) {

		this.horodatage = horodatage;
		
	}

	public Emplacement(Voiture voiture, int horodatage) {
	this.voiture = voiture;
	this.horodatage= horodatage;
	}

	/**
	 * Retourne une représentation sous forme de chaîne de caractères de l’emplacement
	 * Cette méthode est complète; vous n’avez pas besoin de la modifier.
	 */
	public String toString() {
		return voiture + ", horodatage : " + horodatage;
	}
}
