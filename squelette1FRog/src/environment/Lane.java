package environment;

import java.util.ArrayList;
import java.util.Iterator;

import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int timer=0;

	public Lane(Game game, int ordonnee, double density){
		this.game=game;
		this.ord = ordonnee;
		this.density=density;
		this.cars = new ArrayList();
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = game.randomGen.nextBoolean();
	}



	public void update() {

		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e

		timer++;
		if(timer<this.speed){
			deplaceCars(false);
		}
		else{
			deplaceCars(true);
			mayAddCar();
			timer=0;
		}
	}

	//verifie si une des voitures de la liste est sur la case c
	public boolean isSafe(Case c){
		for(Car car : this.cars){
			return(!car.verifCase(c));
		}
		return true;
	}

	private void deplaceCars(boolean b) {
		for(Car car : this.cars){
			car.deplace(b);
		}
		enleveCars();
	}


	private void enleveCars() {
		for (Car c : cars) {
			if (c.estDehors()) {
				this.cars.remove(c);
			}
		}
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

}
