package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	public Car(Game game, Case pos, boolean dir){
		this.game = game;
		this.length = game.randomGen.nextInt(3)+1;
		this.leftToRight= dir;
		int absc;
		if(dir) {
			this.leftPosition = new Case((pos.absc - this.length), pos.ord);
		}else{
			this.leftPosition = new Case(pos.absc, pos.ord);
		}
		this.leftPosition = pos;
	}

	public boolean verifCase (Case c){
		if(c.ord != this.leftPosition.ord){
			return false;
		}
		else if (c.absc >= this.leftPosition.absc || c.absc < (this.leftPosition.absc + this.length)){
			return true;
		}
		else { return false;}
	}

	public void deplace(boolean b) {
		if (b) {
			int sens; //1 si de gauche à droite, -1 is de droite à gauche
			if(this.leftToRight){
				sens=1;
			}
			else{ sens=-1; }
			this.leftPosition = new Case(this.leftPosition.absc + sens, this.leftPosition.ord);
		}
		this.addToGraphics();
	}

	public boolean estDehors (){
		if(this.leftPosition.absc + this.length <= 0 || this.leftPosition.absc >= this.game.width){
			return true;
		}
		return false;
	}

	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
