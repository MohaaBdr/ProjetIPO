package gameCommons;

import util.Case;
import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection();


	/**
	 * reset l'ordonnée de la grenouille à 0 et à l'abscisse x
	 * @param x
	 */
	public void resetPos(int x);
	
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);

	/**
	 * D�place la grenouille du joueur 2 dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move2(Direction key);

	/**
	 * Donne l'ordonnée de la grenouille par rapport à roadLines
	 * @return
	 */
	public int getCompteur();

	/**
	 * Fait glisser la grenouille
	 */
	public void faitGlisser();

	/**
	 * Fait glisser la grenouille 2
	 */
	public void faitGlisser2();


}
