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

		if (nomFichier == null) {
			System.out.println("Le nom du fichier ne peut pas être nul.");
			return;
		}
		calculerDimensionsStationnement(nomFichier);
		conceptionStationnement = new TypeVoiture[nombreRangees][nombrePlacesParRangee];
		occupation =  new Emplacement[nombreRangees][nombrePlacesParRangee];
		remplirConceptionDepuisFichier(nomFichier);
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

	if ((i<nombreRangees && j< nombrePlacesParRangee)&&peutStationnerA(i, j, v) && occupation[i][j]==null) {
			occupation[i][j].setVoiture(v);
			occupation[i][j].setHorodatage(horodatage);
		}else{
			System.out.println("La voiture "+Utilitaire.getLibelleParTypeVoiture(v.getType())+"("+v.getNumeroPlaque()+") ne peut pas être garée à "+"("+i+","+j+")"+"");
		}

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
		if ((i<0 || i>=nombreRangees) || (j<0 || j>=nombrePlacesParRangee)) {
			 return null;
		 }
		 if (occupation[i][j]==null) {
			 return null;
		
		 // SUPPRIMEZ CETTE INSTRUCTION APRÈS AVOIR IMPLÉMENTÉ CETTE MÉTHODE

		
		}
		 Emplacement e = occupation[i][j];
		 occupation[i][j]=null;
		 return e;// Supprimez cette instruction lorsque l’implémentation est complète.
	 // Supprimez cette instruction lorsque l’implémentation est complète.

	}

	/**
	 * Retourne l’instance d’emplacement à une position donnée (i, j)
	 * 
	 * @param i est l’indice de la rangée de stationnement
	 * @param j est l’indice de la place dans la rangée i
	 * @return l’instance d’emplacement à la position (i, j)
	 */
	public Emplacement getEmplacementA(int i, int j) {
	 return occupation[i][j];
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
			
		switch (Utilitaire.getLibelleParTypeVoiture(v.getType())) {
			case "P":
				if (conceptionStationnement[i][j]!=TypeVoiture.NA  ){
				return true;
			}
			case "R":
				if (conceptionStationnement[i][j]==TypeVoiture.REGULIERE || conceptionStationnement[i][j]==TypeVoiture.GRANDE ) {
					return true;
				}
				break;
			case "G":
				if (conceptionStationnement[i][j]==TypeVoiture.GRANDE) {
					return true;
				}
				break;
			case "E":
				
				if (conceptionStationnement[i][j]!=TypeVoiture.NA){
				return true;
			}
				
			default:
				return false;
		}
		return false;
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

		for (int i =0 ; i<nombreRangees;i++) {
			for (int j =0 ; j<nombrePlacesParRangee;j++) {
				if (peutStationnerA(i, j, v)== true){
					stationner(i, j, v, horodatage);
					return true;
				}
			}
		}
	// Supprimez cette instruction lorsque l’implémentation est complète.
		return false ;
	}
	

	/**
	 * @return la capacité totale du stationnement en excluant les emplacements qui
	 *         ne peuvent pas être utilisés pour stationner
	 *         (c.-à-d. les emplacements associés à TypeVoiture.NA)
	 */
	public int getCapaciteTotale() {
		int compteur = 0 ;
		for (int i =0 ; i<nombreRangees;i++) {
			for (int j =0 ; j<nombrePlacesParRangee;j++) {
				if (conceptionStationnement[i][j] == TypeVoiture.NA) {
					compteur++;
					
				}}}
		
		return (nombrePlacesParRangee*nombreRangees)-compteur; // Supprimez cette instruction lorsque l’implémentation est complète.
	
	}

	/**
	 * @return l’occupation totale du stationnement
	 */
	public int getOccupationTotale() {
		// ÉCRIVEZ VOTRE CODE ICI !
		int counteur = 0 ;
		for (int i=0 ;i<nombreRangees;i++) {
			for (int j=0 ; j<nombrePlacesParRangee;j++) {
				if (occupation[i][j]!=null) {
					counteur++;
				}
			}
		}
		
		return counteur;
		

		
	}// Supprimez cette instruction lorsque l’implémentation est complète.

	

	private void calculerDimensionsStationnement(String nomFichier) throws Exception {

		Scanner scanner = new Scanner(new File(nomFichier));
		int i=0;
		
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			// ÉCRIVEZ VOTRE CODE ICI !

			str=str.trim();
			
			
			if (i==0) {
				nombrePlacesParRangee = str.split(SEPARATEUR).length;
			}
			if (str.equals("")){
				break;
			}
			
			
			
			
			i++;
			}
		nombreRangees=i;
		

		scanner.close();
		
	}

	

	private void remplirConceptionDepuisFichier(String nomFichier) throws Exception {

		Scanner scanner = new Scanner(new File(nomFichier));

		// VOUS DEVEZ PEUT-ÊTRE DÉFINIR DES VARIABLES LOCALES ICI !
		int i =0 ;

		// boucle while pour lire la conception du stationnement
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			// ÉCRIVEZ VOTRE CODE ICI !
			str=str.trim();
			if (!(str.equals(""))) {
				for (int j =0 ; j<nombrePlacesParRangee;j++) {
				String[] parts = str.split(SEPARATEUR);
				conceptionStationnement[i][j] = Utilitaire.getTypeVoitureParLibelle(parts[j].trim());				
			}
			
			
			}
			i++;
			
			
		}

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
