package graphicalElements;

import gameCommons.IFrog;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'�l�ment aux �l�ments � afficher
	 * @param e
	 */
    public void add(Element e);
    
    /**
     * Enl�ve tous les �l�ments actuellement affich�s
     */
    public void clear();
    
    /***
     * Actualise l'affichage
     */
    public void repaint();
    
    /**
     * Lie la grenouille � l'environneemnt graphique
     * @param frog1
     * @param frog2
     */
    public void setFrog(IFrog frog);

    public void setFrog2(IFrog frog1, IFrog frog2);

    /**
     * Lance un �cran de fin de partie
     * @param message le texte � afficher
     */
    public void endGameScreen(String message);
}
