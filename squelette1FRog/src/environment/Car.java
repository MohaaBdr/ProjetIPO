package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import util.Direction;


public class Car {
    private final Game game;
    private Case leftPosition;
    private final boolean leftToRight;
    private final int length;
    private final Color colorLtR = Color.BLACK;
    private final Color colorRtL = Color.BLUE;

    /**
     *
     * @param game la partie de jeu
     * @param pos la position de la voiture
     * @param dir la direction de la voiture
     */
    public Car(Game game, Case pos, boolean dir){
        this.game = game;
        this.length = game.randomGen.nextInt(3) + 1;
        this.leftToRight = dir;
        if(dir) {
            this.leftPosition = new Case((pos.absc - this.length), pos.ord);
        }else{
            this.leftPosition = new Case(pos.absc, pos.ord);
        }
    }

    /**
     * Vérifie si une voiture est sur une case
     * @param c la case
     * @return
     */
    public boolean verifCase(Case c) {
        if (this.leftPosition.ord != c.ord) {
            return false;
        } else return c.absc >= this.leftPosition.absc && c.absc < (this.leftPosition.absc + this.length);
    }

    /**
     * Vérifie si une voiture est dans la fenêtre de jeu
     * @return
     */
    public boolean estDedans() {
        return this.leftPosition.absc + this.length > 0 || this.leftPosition.absc < this.game.width;
    }

    /**
     * Déplace horizontalement une voiture en fonction de sa direction si b est vrai
     * @param b
     */
    public void deplace(boolean b){
        if (b) {
            int sens;  //1 si de gauche à droite, -1 is de droite à gauche
            if(this.leftToRight){
                sens = 1;
            }else{
                sens = -1;
            }
            this.leftPosition = new Case(this.leftPosition.absc + (sens), this.leftPosition.ord);
        }

        this.addToGraphics();
    }

    /**
     * Déplace verticalement une voiture en fonction de la direction de la grenouille
     * @param d la direction choisie
     */
    public void deplaceOrd(Direction d, int var){
        int sens;  //positif si vers le bas, negatif si vers le haut
        if(d == Direction.up){
            sens = -var;
        }else{
            sens = var;
        }
        this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord + (sens));
        this.addToGraphics();
    }


    /* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
    private void addToGraphics() {
        for (int i = 0; i < length; i++) {
            Color color = colorRtL;
            if (this.leftToRight){
                color = colorLtR;
            }
            game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
        }
    }

}