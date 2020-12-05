package environment;

import java.util.ArrayList;

import caseSpe.Glisse;
import caseSpe.Trap;
import environment.Lane;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Direction;

public class Environment implements IEnvironment {

    private ArrayList<Lane> roadLines;
    private Game game;
    private ArrayList<Trap> traps;
    private ArrayList<Glisse> casesGlisse;
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

        ArrayList<Case> save = new ArrayList<Case>();
        this.traps = new ArrayList<>();
        for(int i=3; i < 7 + game.randomGen.nextInt(4); i++){
            save.add(new Case (game.randomGen.nextInt(game.width -1), game.randomGen.nextInt(game.defaultHeight -4) +3));
            traps.add(new Trap(game, save.get(i-3)));
        }

        this.casesGlisse = new ArrayList<>();
        for(int i=3; i < 7 + game.randomGen.nextInt(4); i++){
            Case test = new Case (game.randomGen.nextInt(game.width -1), game.randomGen.nextInt(game.defaultHeight -5) +3);
            for(Case c : save){
                while(test == c){
                    test = new Case (game.randomGen.nextInt(game.width -1), game.randomGen.nextInt(game.defaultHeight -5) +3);
                }
            }
            casesGlisse.add(new Glisse(game, test));
        }
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
        for(Trap t : traps){
            if (t.verifCase(c)){
                return false;
            }
        }
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
        if(c.ord == game.height-1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Effectue une �tape d'actualisation de l'environnement
     */
    @Override
    public void update() {
        for(Lane l : this.roadLines){
            l.update();
        }

        for(Trap t : traps){
            t.addToGraphics();
        }

        for(Glisse g : casesGlisse){
            g.addToGraphics();
        }
    }

    @Override
    public void clearEnv (){
        this.traps.clear();
        this.casesGlisse.clear();
        this.roadLines.clear();
    }

    public boolean isGlisse(Case c){
        for(Glisse g : casesGlisse){
            if(g.verifCase(c)){
                return true;
            }
        }
        return false;
    }

    public void infini(){}  ////////////////TODO

    public void deplaceOrdCar(Direction d, int var){}  ////////////////TODO

    public void infiniSpe(){} ////////////////TODO


}
