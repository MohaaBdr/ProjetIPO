package gameCommons;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import infini.EnvInf;
import infini.FrogInf;
import util.Case;
import util.Direction;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int defaultHeight;         //hauteur par défaut
	public int height;         //hauteur actuelle
	public int maxHeight;         //hauteur maximale atteinte
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	private long timer;         //compte le temps passé en secondes

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
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
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.defaultHeight = height;
		this.maxHeight = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.timer = System.nanoTime();
	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
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
	public boolean testLose() {
		if(!environment.isSafe(frog.getPosition(), frog.getCompteur())){
			environment.clearLanes();    //pour avoir un affichage des secondes sans superpositions
			int score = maxHeight - defaultHeight;
			long f = System.nanoTime();
			timer =  (f - timer)/1000000000;
			graphic.endGameScreen("Défaite ! " + "Votre Score : " + score + " Temps : " + timer + " secondes");
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
		if(environment.isWinningPosition(frog.getPosition())){
			graphic.endGameScreen("Victoire");
			return true;
		}
		return false;
	}

	/**
	 * Ajoute une voie à l'arrayList roadLines
	 * Incrémente la valeur de la hauteur maximale atteinte
	 */
	public void infini(){
		this.environment.infini();
	}


	/**
	 * Incrémente ou décremente l'ordonnées des voies de roadLines en fonction de la direction
	 *
	 * @param d la Direction de la grenouille
	 */
	public void deplaceOrdCar(Direction d){
		environment.deplaceOrdCar(d);
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		testLose();
		testWin();
	}

}
