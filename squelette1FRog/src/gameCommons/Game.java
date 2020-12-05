package gameCommons;

import java.awt.Color;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import infini.EnvInf;
import infini.FrogInf;
import util.Case;
import util.Direction;
import caseSpe.Trap;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int defaultHeight; //hauteur par défault
	public int height; //hauteur actuelle
	public int maxHeight; //hauteur maximale atteinte
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	private long timer; //compte le temps passé en secondes


	// Lien aux objets utilis�s
	private IEnvironment environment;
	public IFrog frog;
	//private FrogInf frogInf;
	private IFroggerGraphics graphic;
	public Trap trap;

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
		//this.trap = new Trap(this, new Case (3,2));
	}

	/**
	 * Lie l'objet trap � la partie
	 *
	 * @param trap
	 */
	/*public void setTrap(Trap trap){
		this.trap = trap;
	}*/

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
			environment.clearEnv();
			long f = System.nanoTime();
			long temps =  (f - timer)/1000000000;
			int score = maxHeight - defaultHeight;
			graphic.endGameScreen("Défaite ! " + "Votre Score : " + score + " temps : " + temps + " secs") ;
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
	 * Ajoute une voie à l'arraylist RoadLines
	 * Ajoute une case piège
	 * Incrémente la valeur de la hauteur maximale atteinte
	 */
	public void infini(){

		this.environment.infini();
		this.environment.infiniSpe();
	}

	public boolean isGlisse(Case c){
		return environment.isGlisse(c);
	}

	/**
	 * Incrémente ou décrémente l'ordonnée des voies de roadLines en fonction de d
	 *
	 * @param d la direction de la grenouille
	 */
	public void deplaceOrdCar(Direction d, int var){
		environment.deplaceOrdCar(d, 1);
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
		frog.faitGlisser();
		//environment.isGlisse(frog.getPosition());
	}

}
