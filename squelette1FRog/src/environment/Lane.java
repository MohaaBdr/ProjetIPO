package environment;

import java.util.ArrayList;
import java.util.Iterator;

import util.Case;
import gameCommons.Game;
import util.Direction;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int horloge = 0;


	/**
	 *
	 * @param game la partie de jeu
	 *
	 * @param ordonnee l'ordonnée de la voie
	 *
	 * @param density la densité de la voie
	 */
	public Lane(Game game, int ordonnee, double density){
		this.game = game;
		this.ord = ordonnee;
		this.density = density;
		this.cars = new ArrayList<Car>();
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = game.randomGen.nextBoolean();
		for(int i = 0; i < 4 * this.game.width; i++) {    //avoir les lignes pleines de voitures dès le début
			deplaceCar(true);
			mayAddCar();
		}
	}

	/**
	 * Deplace horizontalement les voitures et les retirent si besoin
	 * @param b
	 */
	public void deplaceCar(boolean b){
		for(Car car : this.cars){
			car.deplace(b);
		}
		enleveCar();
	}

	/**
	 * Deplace verticalement les voitures en fonction de la direction
	 * @param d la direction
	 */
	public void deplaceOrdCar(Direction d){
		for(Car car : this.cars){
			car.deplaceOrd(d);
		}
		if(d == Direction.up) {
			this.ord-=1;

		}
		else {
			this.ord+=1;
		}
	}

	/**
	 * Retire les voitures qui sont en dehors de la fenêtre de jeu
	 */
	public void enleveCar(){
		for(Car c : cars){
			if(!c.estDedans()){
				this.cars.remove(c);
			}
		}
	}

	/**
	 * Verifie une voiture est sur la case c
	 * @param c la case
	 * @return
	 */
	public boolean isSafe(Case c) {   //verifie si une des voitures de la liste est sur la case c
		for (Car car : this.cars) {
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
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
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
	 * cette méthode est appelée à chaque tic d'horloge
	 * Les voitures sont ajoutées à l'interface graphique même quand elles ne bougent pas
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