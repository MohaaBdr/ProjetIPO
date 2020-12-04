package environment;

import java.util.ArrayList;
import environment.Lane;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Direction;

public class Environment implements IEnvironment {

    private ArrayList<Lane> roadLines;
    private Game game;

    /**
     *
     * @param game la partie de jeu
     */
    public Environment(Game game){
        this.game = game;
        this.roadLines = new ArrayList();
        this.roadLines.add(new Lane(game, 0, 0.0D));

        for(int i = 1; i < game.height - 1; ++i) {
            this.roadLines.add(new Lane(game, i,  (game.randomGen.nextInt(5)/100.0)+0.01));
        }

        this.roadLines.add(new Lane(game, game.defaultHeight, 0.0D));
    }



    /**
     * Teste si une case est sure, c'est � dire que la grenouille peut s'y poser
     * sans mourir
     *
     * @param c la case � tester
     * @return vrai s'il n'y a pas danger
     */
    @Override
    public boolean isSafe(Case c, int compt) {
        return (this.roadLines.get(c.ord)).isSafe(c);
    }

    /**
     * Teste si la case est une case d'arrivee
     *
     * @param c
     * @return vrai si la case est une case de victoire
     */
   @Override
    public boolean isWinningPosition(Case c) {
        if(c.ord == game.defaultHeight){
            return true;
        }else{
            return false;
        }
    }

    public void clearLanes(){
        this.roadLines.clear();
    }

    /**
     * Effectue une �tape d'actualisation de l'environnement
     */
    @Override
    public void update() {
        for(Lane l : this.roadLines){
            l.update();
        }
    }

    public void infini(){}  ////////////////TODO

    public void deplaceOrdCar(Direction d){}  ////////////////TODO


}
