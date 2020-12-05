package caseSpe;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;
import util.Direction;

import java.awt.*;

public class Glisse implements CaseSpe{

    private Game game;
    private Case pos;
    private final Color c = Color.PINK;

    public Glisse (Game game, Case pos){
        this.game=game;
        this.pos=pos;
    }

    @Override
    public boolean verifCase(Case c) {
        if (this.pos.ord != c.ord) {
            return false;
        } else if (c.absc == this.pos.absc) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deplaceOrd(Direction d){
        int sens;  //1 si de gauche vers le bas, -1 si vers le haut
        if(d == Direction.up){
            sens = -1;
        }else{
            sens = 1;
        }
        this.pos = new Case(this.pos.absc, this.pos.ord + (sens));
        this.addToGraphics();
    }

    @Override
    public void addToGraphics() {
        Color color = c;
        game.getGraphic().add(new Element(pos.absc, pos.ord, color));
    }
}
