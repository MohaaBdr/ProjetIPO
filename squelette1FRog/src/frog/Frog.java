package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Case pos;
	private Direction dir;

	public Frog(Game game){
		this.game = game;
		this.dir = Direction.up;
		this.pos =new Case(game.width/2, 0);
	}


	@Override
	public Case getPosition() {
		return this.pos;
	}

	@Override
	public Direction getDirection() {
		return this.dir;
	}

	@Override
	public void move(Direction key) {
		if(key==Direction.up && pos.ord < game.height-1){
			pos = new Case(pos.absc, pos.ord+1);
		}
		else if(key==Direction.down && pos.ord > 0){
			pos = new Case(pos.absc, pos.ord-1);
		}
		else if(key==Direction.left && pos.absc > 0){
			pos = new Case(pos.absc-1, pos.ord);
		}
		else if(key==Direction.right && pos.absc < game.width-1){
			pos = new Case(pos.absc+1, pos.ord);
		}
	}
}
