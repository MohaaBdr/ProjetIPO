package multijoueur;

import java.awt.Color;
import java.util.Random;

import gameCommons.IEnvironment;
import gameCommons.IFrog;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import infini.EnvInf;
import infini.FrogInf;
import util.Case;
import util.Direction;
import caseSpe.Trap;

public class GameMulti {

    public final Random randomGen = new Random();

    // Caracteristique de la partie
    public final int width;
    public final int defaultHeight; //hauteur par défault
    public int height1; //hauteur actuelle
    public int height2; //hauteur actuelle
    public int maxHeight1; //hauteur maximale atteinte
    public int maxHeight2; //hauteur maximale atteinte
    public final int minSpeedInTimerLoops;
    public final double defaultDensity;

    // Carateristique du score
    public int score1 = 0;
    public int score2 = 0;
    private long temps1;
    private long temps2;
    private long timer; //compte le temps passé en secondes
    private int bonus1 =0;
    private int bonus2 =0;


    // Lien aux objets utilis�s
    private IEnvironment environment;
    public IFrog frog1, frog2;
    private IFroggerGraphics graphic;

    /**
     *
     * @param graphic
     *            l'interface graphique
     * @param width
     *            largeur en cases
     * @param height
     *            hauteur en cases
     * @param minSpeedInTimerLoop
     *            Vitesse minimale, en nombre de tour de timer avant d�placement
     * @param defaultDensity
     *            densite de voiture utilisee par defaut pour les routes
     */
    public GameMulti(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
        super();
        this.graphic = graphic;
        this.width = width;
        this.height1 = height;
        this.height2 = height;
        this.defaultHeight = height;
        this.maxHeight1 = height;
        this.maxHeight2 = height;
        this.minSpeedInTimerLoops = minSpeedInTimerLoop;
        this.defaultDensity = defaultDensity;
        this.timer = System.nanoTime();
    }

    /**
     * Lie l'objet frog � la partie
     *
     * @param frog1
     * @param frog2
     */
    public void setFrog(IFrog frog1, IFrog frog2) {
        this.frog1 = frog1;
        this.frog2 = frog2;
    }

    /**
     * Lie l'objet environment a la partie
     *
     * @param environment
     */
    public void setEnvironment(IEnvironment environment) {
        this.environment = environment;
    }

    /**
     *
     * @return l'interface graphique
     */
    public IFroggerGraphics getGraphic() {
        return graphic;
    }

    /**
     * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
     * est le cas
     *
     * @return true si le partie est perdue
     */
    public boolean testLose1() {
        if((!environment.isSafe(frog1.getPosition(), frog1.getCompteur()))){
            frog1.resetPos(width /2 +5);
            score1 = 0;
            return true;
        }
        return false;
    }

    /**
     * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
     * est le cas
     *
     * @return true si le partie est perdue
     */
    public boolean testLose2() {
        if((!environment.isSafe(frog2.getPosition(), frog2.getCompteur()))){
            frog2.resetPos(width /2 -5);
            score2 = 0;
            return true;
        }
        return false;
    }

    /**
     * Incrélente le bonus si la grenouille du joueur 1 est sur une case bonus
     * @param c
     */
    public void isBonus1(Case c){
        if(environment.isBonus(c)){
            bonus1 ++;
        }
    }

    /**
     * Incrélente le bonus si la grenouille du joueur 2 est sur une case bonus
     * @param c
     */
    public void isBonus2(Case c){
        if(environment.isBonus(c)){
            bonus2 ++;
        }
    }

    /**
     * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
     * est le cas
     *
     * @return true si la partie est gagn�e
     */
    public boolean testWin1() {
        if(environment.isWinningPosition(frog1.getPosition())){
            long f1 = System.nanoTime();
            temps1 =  (f1 - timer)/1000000000;
            return true;
        }
        return false;
    }

    /**
     * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
     * est le cas
     *
     * @return true si la partie est gagn�e
     */
    public boolean testWin2() {
        if(environment.isWinningPosition(frog2.getPosition())){
            long f2 = System.nanoTime();
            temps2 =  (f2 - timer)/1000000000;
            return true;
        }
        return false;
    }

    /**
     * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
     * est le cas
     *
     * @return true si la partie est gagn�e
     */
    public boolean testWin() {
        if(testWin1() || testWin2()){
            environment.clearEnv();
            score1 += bonus1;
            score2 += bonus2;
            if(testWin1()) graphic.endGameScreen2("Victoire Joueur1 !", "Score Joueur1 : "  + score1, " Score Joueur2 : "+ score2, "temps : "+ temps1 + "s");
            if(testWin2()) graphic.endGameScreen2("Victoire Joueur2 !", "Score Joueur2 : "  + score2, " Score Joueur1 : "+ score1, "temps : "+ temps2 + "s");
            return true;
        }
        return false;
    }
    /**
     * Teste si une case est glissante
     *
     * @param c la case � tester
     * @return vrai si c'est une case glisse
     */
    public boolean isGlisse(Case c){
        return environment.isGlisse(c);
    }


    /**
     * Actualise l'environnement, affiche la grenouille et verifie la fin de
     * partie.
     */
    public void update() {
        graphic.clear();
        environment.update();
        this.graphic.add(new Element(frog1.getPosition(), Color.GREEN));
        this.graphic.add(new Element(frog2.getPosition(), Color.CYAN));
        isBonus1(frog1.getPosition());
        isBonus2(frog2.getPosition());
        frog1.faitGlisser();
        frog2.faitGlisser2();
        testLose1();
        testLose2();
        testWin();
    }

}