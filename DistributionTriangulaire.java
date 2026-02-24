/**
 * Cette classe fournit une implémentation d’une distribution de probabilité
 * triangulaire. Une explication mathématique simple de cette distribution
 * de probabilité est disponible sur Wikipédia à l’adresse :
 * https://en.wikipedia.org/wiki/Triangular_distribution
 * 
 * @auteur Mehrdad Sabetzadeh, Université d'Ottawa
 *
 */
public class DistributionTriangulaire {

	/**
	 * a, c, b sont les trois paramètres sur l’axe des x de
	 * https://en.wikipedia.org/wiki/File:Triangular_distribution_PMF.png
	 */
	int a, c, b;

	/**
	 * Constructeur de DistributionTriangulaire. Vous devez vérifier que la
	 * condition suivante est respectée : a < c ET c < b
	 * 
	 * @param a est la limite inférieure de la distribution
	 * @param c est le mode
	 * @param b est la limite supérieure de la distribution
	 */
	public DistributionTriangulaire(int a, int c, int b) {
	this.a =a;
	this.b = b;
	this.c = c;
	

	}

	/**
	 * @param x est un point sur l’axe des x
	 * @return la densité de probabilité au point x
	 */
	public Rationnel pdf(int x) {
		int proba ;
		if (this.c<x){
			proba = (2*(this.b-x))/((this.b-this.a)*(this.b-this.c));
		
		}else if ( this.c>x){
			proba = (2*(this.a-x))/((this.b-this.a)*(this.c-this.a));
		}
		else{
			proba =0;
		}
		// Rational prob_rational = p
		return null; // Supprimez cette instruction lorsque l’implémentation est complète.
	}

	/**
	 * 
	 * Cette méthode fournit deux exemples pour vous aider à tester votre
	 * implémentation de la distribution triangulaire. La sortie obtenue lors de
	 * l’exécution de cette méthode main dans la solution de référence vous est
	 * fournie avec le code de départ de A2.
	 * 
	 * @param args paramètres de ligne de commande (non utilisés dans le corps de la méthode)
	 */
	public static void main(String args[]) {

		System.out.println("=========== DistributionTriangulaire(0, 5, 10) =============");
		DistributionTriangulaire dist0 = new DistributionTriangulaire(0, 5, 10);
		Rationnel somme0 = new Rationnel(0, 1);

		for (int i = 0; i <= 10; i++) {
			Rationnel p = dist0.pdf(i);
			somme0 = somme0.plus(p);
			System.out.println("pdf(" + i + ") = " + dist0.pdf(i));
		}

		System.out.println();

		System.out.println("Intégrale (Somme) = " + somme0);

		System.out.println();

		
		System.out.println("=========== DistributionTriangulaire(0, 50, 100) =============");
		DistributionTriangulaire dist1 = new DistributionTriangulaire(0, 50, 100);
		Rationnel somme1 = new Rationnel(0, 1);

		for (int i = 0; i <= 100; i++) {
			Rationnel p = dist1.pdf(i);
			somme1 = somme1.plus(p);
			System.out.println("pdf(" + i + ") = " + dist1.pdf(i));
		}

		System.out.println();
		
		System.out.println("Intégrale (Somme) = " + somme1);

		System.out.println();

		System.out.println("=========== DistributionTriangulaire(0, 10, 50) =============");
		DistributionTriangulaire dist2 = new DistributionTriangulaire(0, 10, 50);
		Rationnel somme2 = new Rationnel(0, 1);

		for (int i = 0; i <= 50; i++) {
			Rationnel p = dist2.pdf(i);
			somme2 = somme2.plus(p);
			System.out.println("pdf(" + i + ") = " + dist2.pdf(i));
		}

		System.out.println();

		System.out.println("Intégrale (Somme) = " + somme2);

	}
}
