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
	 * Vide l'arrayList roadLines
	 */
	public void clearLanes();

	/**
	 * Ajoute une voie à l'arrayList roadLines
	 * Incrémente la valeur de la hauteur maximale atteinte
	 */
	public void infini();  //////////////////////TODO

	/**
	 * Incrémente ou décremente l'ordonnées des voies de roadLines en fonction de la direction
	 *
	 * @param d la Direction de la grenouille
	 */
	public void deplaceOrdCar(Direction d);  /////////////////////TODO
}
