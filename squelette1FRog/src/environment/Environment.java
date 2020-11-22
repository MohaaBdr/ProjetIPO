package environment;

import java.util.ArrayList;
import java.util.Iterator;

import givenEnvironment.Lane;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
    private ArrayList<Lane> roadLines;
    private Game game;

    public Environment (Game game){
        this.roadLines = new ArrayList();
        this.game = game;
        this.roadLines.add(new Lane(game,0,0));
        for(int i = 1; i < game.height - 1; ++i) {
            this.roadLines.add(new Lane(game, i,game.randomGen.nextDouble()%0.5));
        }
        this.roadLines.add(new Lane(game, game.height - 1, 0));

    }

    @Override
    public boolean isSafe(Case c) {
        return this.roadLines.get(c.ord).isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        if (c.ord == game.height-1) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void update() {
        for(Lane l : this.roadLines){
            l.update();
        }
    }
}
