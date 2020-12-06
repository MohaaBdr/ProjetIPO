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
     * @param frog
     */
    public void setFrog(IFrog frog);

    /**
     * Lie les grenouilles � l'environneemnt graphique
     * @param frog1
     * @param frog2
     */
    public void setFrog2(IFrog frog1, IFrog frog2);

    /**
     * Lance un �cran de fin de partie
     * @param message le texte � afficher
     */
    public void endGameScreen(String message);

    /**
     * Lance un �cran de fin de partie
     * @param message1
     * @param message2
     * @param message3
     * @param message4
     */
    public void endGameScreen2(String message1, String message2, String message3, String message4);

}
