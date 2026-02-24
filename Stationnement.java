import java.io.File;
import java.util.Scanner;

/**
 * @auteur Mehrdad Sabetzadeh, Université d'Ottawa
 */
public class Stationnement {
	/**
	 * Le délimiteur qui sépare les valeurs
	 */
	private static final String SEPARATEUR = ",";

	/**
	 * Variable d’instance pour stocker le nombre de rangées dans un stationnement
	 */
	private int nombreRangees;

	/**
	 * Variable d’instance pour stocker le nombre de places par rangée dans un stationnement
	 */
	private int nombrePlacesParRangee;

	/**
	 * Variable d’instance (tableau à deux dimensions) pour stocker la conception du stationnement
	 */
	private TypeVoiture[][] conceptionStationnement;

	/**
	 * Variable d’instance (tableau à deux dimensions) pour stocker l’information
	 * d’occupation des places dans le stationnement
	 */
	private Emplacement[][] occupation;

	/**
	 * Construit un stationnement en chargeant un fichier
	 * 
	 * @param nomFichier est le nom du fichier
	 */
	public Stationnement(String nomFichier) throws Exception {

		// ÉCRIVEZ VOTRE CODE ICI !
		
	}

	public int getNombreRangees() {
		return nombreRangees;
	}

	public int getNombrePlacesParRangee() {
		return nombrePlacesParRangee;
	}

	/**
	 * Stationne une voiture (v) à un emplacement donné (i, j) dans le stationnement.
	 * 
	 * @param i           est l’indice de la rangée de stationnement
	 * @param j           est l’indice de la place dans la rangée i
	 * @param v           est la voiture à stationner
	 * @param horodatage  est le temps (simulé) auquel la voiture est stationnée
	 */
	public void stationner(int i, int j, Voiture v, int horodatage) {

		// ÉCRIVEZ VOTRE CODE ICI !		

	}

	/**
	 * Retire la voiture stationnée à un emplacement donné (i, j) dans le stationnement
	 * 
	 * @param i est l’indice de la rangée de stationnement
	 * @param j est l’indice de la place dans la rangée i
	 * @return l’emplacement retiré; la méthode retourne null lorsque i ou j sont hors
	 *         limites, ou lorsqu’il n’y a aucune voiture stationnée à (i, j)
	 */
	public Emplacement retirer(int i, int j) {

		// ÉCRIVEZ VOTRE CODE ICI !
		
		return null; // Supprimez cette instruction lorsque l’implémentation est complète.

	}

	/**
	 * Retourne l’instance d’emplacement à une position donnée (i, j)
	 * 
	 * @param i est l’indice de la rangée de stationnement
	 * @param j est l’indice de la place dans la rangée i
	 * @return l’instance d’emplacement à la position (i, j)
	 */
	public Emplacement getEmplacementA(int i, int j) {

		// ÉCRIVEZ VOTRE CODE ICI !
		
		return null; // Supprimez cette instruction lorsque l’implémentation est complète.
	}

	/**
	 * Vérifie si une voiture (ayant un certain type) est autorisée à stationner à
	 * l’emplacement (i, j)
	 *
	 * NOTE : Cette méthode est complète; vous n’avez pas besoin de la modifier.
	 * 
	 * @param i est l’indice de la rangée de stationnement
	 * @param j est l’indice de la place dans la rangée i
	 * @return true si la voiture v peut stationner à (i, j), false sinon
	 */
	public boolean peutStationnerA(int i, int j, Voiture v) {

		// ÉCRIVEZ VOTRE CODE ICI !
		
		return false; // Supprimez cette instruction lorsque l’implémentation est complète.
	}

	/**
	 * Tente de stationner une voiture dans le stationnement. Le stationnement est
	 * réussi si un emplacement approprié est disponible. Si un tel emplacement est
	 * trouvé (n’importe où dans le stationnement), la voiture est stationnée à cet
	 * emplacement avec l’horodatage indiqué et la méthode retourne « true ».
	 * Si aucun emplacement approprié n’est trouvé, aucune action n’est effectuée
	 * et la méthode retourne simplement « false ».
	 * 
	 * @param v           est la voiture à stationner
	 * @param horodatage  est le temps de simulation auquel le stationnement est tenté
	 * @return true si la voiture est stationnée avec succès quelque part dans le
	 *         stationnement, false sinon
	 */
	public boolean tenterStationnement(Voiture v, int horodatage) {

		// ÉCRIVEZ VOTRE CODE ICI !
		
		return false; // Supprimez cette instruction lorsque l’implémentation est complète.

	}

	/**
	 * @return la capacité totale du stationnement en excluant les emplacements qui
	 *         ne peuvent pas être utilisés pour stationner
	 *         (c.-à-d. les emplacements associés à TypeVoiture.NA)
	 */
	public int getCapaciteTotale() {

		// ÉCRIVEZ VOTRE CODE ICI !
		
		return -1; // Supprimez cette instruction lorsque l’implémentation est complète.
	
	}

	/**
	 * @return l’occupation totale du stationnement
	 */
	public int getOccupationTotale() {

		// ÉCRIVEZ VOTRE CODE ICI !
		
		return -1; // Supprimez cette instruction lorsque l’implémentation est complète.

	}

	private void calculerDimensionsStationnement(String nomFichier) throws Exception {

		// ÉCRIVEZ VOTRE CODE ICI !

	}

	private void remplirConceptionDepuisFichier(String nomFichier) throws Exception {

		// ÉCRIVEZ VOTRE CODE ICI !

	}

	/**
	 * NOTE : Cette méthode est complète; vous n’avez pas besoin de la modifier.
	 * @return Chaîne de caractères contenant les informations du stationnement
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("==== Conception du stationnement ====").append(System.lineSeparator());

		for (int i = 0; i < conceptionStationnement.length; i++) {
			for (int j = 0; j < conceptionStationnement[0].length; j++) {
				buffer.append((conceptionStationnement[i][j] != null)
						? Utilitaire.getLibelleParTypeVoiture(conceptionStationnement[i][j])
						: Utilitaire.getLibelleParTypeVoiture(TypeVoiture.NA));
				if (j < nombrePlacesParRangee - 1) {
					buffer.append(", ");
				}
			}
			buffer.append(System.lineSeparator());
		}

		buffer.append(System.lineSeparator())
		      .append("==== Occupation du stationnement ====")
		      .append(System.lineSeparator());

		for (int i = 0; i < occupation.length; i++) {
			for (int j = 0; j < occupation[0].length; j++) {
				buffer.append("(" + i + ", " + j + "): "
						+ ((occupation[i][j] != null) ? occupation[i][j] : "Inoccupé"));
				buffer.append(System.lineSeparator());
			}

		}
		return buffer.toString();
	}
}
