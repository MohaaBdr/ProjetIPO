package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Case position;
	private Direction direction;

	/**
	 *
	 * @param game la partie de jeu
	 */
	public Frog(Game game){
		this.game = game;
		this.direction = Direction.up;
		this.position = new Case(game.width/2, 0);
	}


	@Override
	public Case getPosition() {
		return this.position;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public int getCompteur() {
		return 0;
	}

	public void faitGlisser() {
		if (game.isGlisse(position)) {
			Case newPos = new Case(position.absc, position.ord + 2);
			position = newPos;
		}
	}


	@Override
	public void move(Direction key) {
		if((key == Direction.up) && (position.ord < game.height-1)){
			this.game.height++;
			if (this.game.height>=this.game.maxHeight) {
				this.game.maxHeight++;
			}
			Case newPos = new Case(position.absc, position.ord+1);
			position = newPos;
		}else if((key == Direction.down) && (position.ord > 0)){
			this.game.height--;
			Case newPos = new Case(position.absc, position.ord-1);
			position = newPos;
		}else if((key == Direction.left) && (position.absc > 0)){
			Case newPos = new Case(position.absc-1, position.ord);
			position = newPos;
		}else if((key == Direction.right) && (position.absc < game.width-1)){
			Case newPos = new Case(position.absc+1, position.ord);
			position = newPos;
		}
	}
}
