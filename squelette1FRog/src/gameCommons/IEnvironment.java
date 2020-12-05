package gameCommons;

import util.Case;
import util.Direction;

public interface IEnvironment {

	/**
	 * Teste si une case est sure, c'est � dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c
	 *            la case � tester
	 * @return vrai s'il n'y a pas danger
	 */
	public boolean isSafe(Case c, int compt); ////////////////////TODO

	//public boolean isSafe(Case c);

	/**
	 * Teste si la case est une case d'arrivee
	 * 
	 * @param c
	 * @return vrai si la case est une case de victoire
	 */
	public boolean isWinningPosition(Case c);

	/**
	 * Effectue une �tape d'actualisation de l'environnement
	 */
	public void update();

	/**
	 * Ajoute une voie à l'arraylist RoadLines
	 * Incrémente la valeur de la hauteur maximale atteinte
	 */
	public void infini();  /////////////////////TODO

	/**
	 * Incrémente ou décrémente l'ordonnée des voies de roadLines en fonction de d
	 *
	 * @param d la direction de la grenouille
	 */
	public void deplaceOrdCar(Direction d, int var);  /////////////////////TODO

	/**
	 * Vide l'arraylist roadLines et des case spéciales
	 */
	public void clearEnv();

	/**
	 * Ajoute une case spéciale aux arraylist des cases spéciales
	 */
	public void infiniSpe();

	public boolean isGlisse(Case c);

	}
