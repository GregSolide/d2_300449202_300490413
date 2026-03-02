/**
 * @auteur Mehrdad Sabetzadeh, Université d'Ottawa
 *
 */
public class Simulateur {

	/**
	 * Durée maximale pendant laquelle une voiture peut être stationnée dans le stationnement
	 */
	public static final int DUREE_MAX_STATIONNEMENT = 8 * 3600;

	/**
	 * Durée totale de la simulation en secondes (simulées)
	 */
	public static final int DUREE_SIMULATION = 24 * 3600;

	/**
	 * La distribution de probabilité pour le départ d'une voiture du stationnement en fonction
	 * de la durée pendant laquelle la voiture a été stationnée dans le stationnement
	 */
	public static final DistributionTriangulaire pdfDepart = new DistributionTriangulaire(
			0,
			DUREE_MAX_STATIONNEMENT / 2,
			DUREE_MAX_STATIONNEMENT
	);

	/**
	 * La probabilité qu'une voiture arrive à une seconde (simulée) donnée.
	 */
	private Rationnel probabiliteArriveeParSeconde;

	/**
	 * L'horloge de simulation.
	 */
	private int horloge;

	/**
	 * Nombre total d'étapes (secondes simulées) pendant lesquelles la simulation doit s'exécuter.
	 */
	private int etapes;

	/**
	 * Instance du stationnement simulé.
	 */
	private Stationnement stationnement;

	/**
	 * File pour les voitures voulant entrer dans le stationnement
	 */
	private File<Emplacement> fileEntrante;

	/**
	 * File pour les voitures voulant sortir du stationnement
	 */
	private File<Emplacement> fileSortante;

	/**
	 * @param stationnement        est le stationnement à simuler
	 * @param tauxArriveeParHeure  est le taux HORAIRE auquel les voitures se présentent devant le stationnement
	 * @param etapes               est le nombre total d'étapes de la simulation
	 */
	public Simulateur(Stationnement stationnement, int tauxArriveeParHeure, int etapes) {

		this.stationnement = stationnement;
		this.etapes = etapes;
		this.horloge = 0;
		this.probabiliteArriveeParSeconde = new Rationnel(tauxArriveeParHeure, 3600);

		fileEntrante = new FileChainee<Emplacement>();
		fileSortante = new FileChainee<Emplacement>();
	}

	/**
	 * Simule le stationnement pendant le nombre d'étapes spécifié.
	 */
	public void simuler() {

		this.horloge = 0;


		Emplacement enAttenteDeStationnement = null;

		while (horloge < etapes) {


			if (GenerateurAleatoire.evenementSurvenu(this.probabiliteArriveeParSeconde)) {
				Emplacement nouvelle = new Emplacement(GenerateurAleatoire.genererVoitureAleatoire(), horloge);
				fileEntrante.enfiler(nouvelle);
			}


			if (enAttenteDeStationnement == null && !fileEntrante.estVide()) {
				enAttenteDeStationnement = fileEntrante.defiler();
			}


			if (enAttenteDeStationnement != null) {
				if (stationnement.tenterStationnement(enAttenteDeStationnement.getVoiture(),enAttenteDeStationnement.getHorodatage())) {
					System.out.println(enAttenteDeStationnement.getVoiture()
						+ " est ENTRÉE à l'instant " + horloge
						+ "; l'occupation est maintenant de "
						+ stationnement.getOccupationTotale());
					enAttenteDeStationnement = null;
				}
		
			}

			
			for (int i = 0; i < stationnement.getNombreRangees(); i++) {
				for (int j = 0; j < stationnement.getNombrePlacesParRangee(); j++) {
					Emplacement c = stationnement.getEmplacementA(i, j);
					if (c != null && c.getVoiture() != null) {
						int dureeStationnement = horloge - c.getHorodatage();

						
						if (dureeStationnement >= DUREE_MAX_STATIONNEMENT) {
						
							stationnement.retirer(i, j);
							fileSortante.enfiler(c);
						} else if (GenerateurAleatoire.evenementSurvenu(pdfDepart.pdf(dureeStationnement))) {
							
							stationnement.retirer(i, j);
							fileSortante.enfiler(c);
						}
					}
				}
			}


			if (!fileSortante.estVide()) {
				Emplacement emp = fileSortante.defiler();
				System.out.println(emp.getVoiture()
					+ " est SORTIE à l'instant " + horloge
					+ "; l'occupation est maintenant de "
					+ stationnement.getOccupationTotale());
			}

			horloge++;
		}
	}

	/**
	 * main de l'application.
	 */
	public static void main(String args[]) throws Exception {

		InfoEtudiant.afficher();

		if (args.length < 2) {
			System.out.println("Utilisation : java Simulateur <nom_fichier_conception_stationnement> <taux_horaire_d_arrivee>");
			System.out.println("Exemple : java Simulateur parking.inf 11");
			return;
		}

		if (!args[1].matches("\\d+")) {
			System.out.println("Le taux horaire d'arrivée doit être un entier positif !");
			return;
		}

		Stationnement stationnement = new Stationnement(args[0]);

		System.out.println("Nombre total de places de stationnement utilisables (capacité) : "
			+ stationnement.getCapaciteTotale());

		Simulateur sim = new Simulateur(stationnement, Integer.parseInt(args[1]), DUREE_SIMULATION);

		long debut, fin;

		System.out.println("=== DÉBUT DE LA SIMULATION ===");
		debut = System.currentTimeMillis();
		sim.simuler();
		fin = System.currentTimeMillis();
		System.out.println("=== FIN DE LA SIMULATION ===");

		System.out.println();

		System.out.println("La simulation a pris " + (fin - debut) + "ms.");

		System.out.println();

		int compteur = 0;

		while (!sim.fileEntrante.estVide()) {
			sim.fileEntrante.defiler();
			compteur++;
		}

		System.out.println("Longueur de la file de voitures à l'entrée à la fin de la simulation : " + compteur);
	}
}
