package multijoueur.spe;

import util.Case;
import util.Direction;

public interface CaseSpeMulti {

    /**
     * Vérifie si une case spéciale est sur une case c
     * @param c la case
     * @return
     */
    public boolean verifCase(Case c);

    /**
     * Déplace verticalement une case spéciale en fonction de la direction de la grenouille
     * @param d la direction choisie
     */
    public void deplaceOrd(Direction d, int var);

    /**
     * permet d'ajouter un element graphique correspondant a la case spéciale
     */
    public void addToGraphics();
}
