package multijoueur.env;

import java.util.ArrayList;

import multijoueur.GameMulti;
import util.Case;
import util.Direction;

public class LaneMulti {
    private final GameMulti game;
    private int ord;
    private final int speed;
    private ArrayList<CarMulti> cars ;
    private final boolean leftToRight;
    private final double density;
    private int horloge = 0;

    /**
     *
     * @param game la partie de jeu
     * @param ordonnee l'ordonnée de la voie
     * @param density la densité de la voie
     */
    public LaneMulti(GameMulti game, int ordonnee, double density){
        this.game = game;
        this.ord = ordonnee;
        this.density = density;
        this.cars = new ArrayList<CarMulti>();
        this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
        this.leftToRight = game.randomGen.nextBoolean();
        for(int i = 0; i < 4 * this.game.width; i++) {    //avoir les lignes pleines de voitures dès le début
            deplaceCar(true);
            mayAddCar();
        }
    }

    /**
     * déplace horizontalement les voitures et les retire si besoin
     * @param b
     */
    public void deplaceCar(boolean b){
        for(CarMulti car : this.cars){
            car.deplace(b);
        }
        enleveCar();
    }

    /**
     * déplace verticalement les voitures en fonction de la direction
     * @param d
     */
    public void deplaceOrdCar(Direction d, int var){
        for(CarMulti car : this.cars){
            car.deplaceOrd(d,var);
        }
        if(d == Direction.up) {
            this.ord-=var;

        }
        else {
            this.ord+=var;
        }
    }

    /**
     * retire les voitures en dehors de la fenêtre de jeu
     */
    public void enleveCar(){
        for(CarMulti c : cars){
            if(!c.estDedans()){
                this.cars.remove(c);
            }
        }
    }

    /**
     * Verifie si une des voitures de la liste est sur la case c
     * @param c
     * @return
     */
    public boolean isSafe(Case c) {
        for (CarMulti car : this.cars) {
            if (car.verifCase(c)) {
                return false;
            }
        }
        return true;
    }


    /*
     * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
     */

    /**
     * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
     * densit�, si la premi�re case de la voie est vide
     */
    private void mayAddCar() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                cars.add(new CarMulti(game, getBeforeFirstCase(), leftToRight));
            }
        }
    }

    private Case getFirstCase() {
        if (leftToRight) {
            return new Case(0, ord);
        } else
            return new Case(game.width - 1, ord);
    }

    private Case getBeforeFirstCase() {
        if (leftToRight) {
            return new Case(-1, ord);
        } else
            return new Case(game.width, ord);
    }

    /**
     * Toutes les voitures se déplacent d'une case au bout d'un nombre "tic d'horloge" égal à leur vitesse
     * Notez que cette méthode est appelée à chaque tic d'horloge
     * Les voitures sont ajoutes a l interface graphique meme quand elles ne bougent pas
     * A chaque tic d'horloge, une voiture peut être ajoutée
     */
    public void update() {
        horloge++;
        if(this.horloge <= this.speed){
            deplaceCar(false);
        }else{
            deplaceCar(true);
            mayAddCar();
            horloge=0;

        }
    }

}