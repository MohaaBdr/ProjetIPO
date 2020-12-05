package environment;

import java.util.ArrayList;

import caseSpe.Bonus;
import caseSpe.Glisse;
import caseSpe.Spe;
import caseSpe.Trap;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Direction;

public class Environment implements IEnvironment {

    private ArrayList<Lane> roadLines;
    private final Game game;
    private ArrayList<Spe> casesSpe;
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

        //ajout des cases spéciales
        /* les coordonnées des pièges sont enregistrés dans save, elles seront ensuite comparés avec celle des cases
        glissantes pour ne pas avoir les memes coordonnées */
        ArrayList<Case> save = new ArrayList<>();

        this.casesSpe = new ArrayList<>();

        int compt=0;
        for(int i=3; i < 7 + game.randomGen.nextInt(4); i++){
            save.add(new Case (game.randomGen.nextInt(game.width -1), game.randomGen.nextInt(game.defaultHeight -4) +3));
            casesSpe.add(new Trap(game, save.get(compt)));
            compt++;
        }

        for(int i=3; i < 7 + game.randomGen.nextInt(4); i++){
            Case test = new Case (game.randomGen.nextInt(game.width -1), game.randomGen.nextInt(game.defaultHeight -5) +3);
            for(Case c : save){
                while(test == c){
                    test = new Case (game.randomGen.nextInt(game.width -1), game.randomGen.nextInt(game.defaultHeight -5) +3);
                }
            }
            save.add(test);
            casesSpe.add(new Glisse(game, test));
        }

        for(int i=3; i < 7 + game.randomGen.nextInt(4); i++){
            Case test = new Case (game.randomGen.nextInt(game.width -1), game.randomGen.nextInt(game.defaultHeight -5) +3);
            for(Case c : save){
                while(test == c){
                    test = new Case (game.randomGen.nextInt(game.width -1), game.randomGen.nextInt(game.defaultHeight -5) +3);
                }
            }
            casesSpe.add(new Bonus(game, test));
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
        for(Spe s : casesSpe){
            if (s.verifCase(c) && s instanceof Trap){
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
        if(c.ord == game.defaultHeight-1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void clearEnv (){
        this.casesSpe.clear();
        this.roadLines.clear();
    }

    @Override
    public boolean isGlisse(Case c){
        for(Spe s : casesSpe ){
            if(s.verifCase(c) && s instanceof Glisse){
                return true;
            }
        }
        return false;
    }

    public boolean isBonus(Case c){
       for(int i= 0; i<casesSpe.size(); i++){
           if(casesSpe.get(i).verifCase(c) && casesSpe.get(i) instanceof Bonus){
               casesSpe.remove(i);
               return true;
           }
       }
       return false;
    }

    /**
     * Effectue une �tape d'actualisation de l'environnement
     */
    @Override
    public void update() {
        for(Spe s : casesSpe){
        s.addToGraphics();
        }
        for(Lane l : this.roadLines){
            l.update();
        }
    }

    @Override
    public void infini(){}

    @Override
    public void deplaceOrdCar(Direction d, int var){}

    @Override
    public void infiniSpe(){}


}
