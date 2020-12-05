package caseSpe;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;
import util.Direction;

import java.awt.*;

public class Spe implements CaseSpe {

    private final Game game;
    private Case pos;
    private final Color c ;

    public Spe (Game game, Case pos, Color c){
        this.game=game;
        this.pos=pos;
        this.c= c;
    }

    @Override
    public boolean verifCase(Case c) {
        if (this.pos.ord != c.ord) {
            return false;
        } else return c.absc == this.pos.absc;
    }

    @Override
    public void deplaceOrd(Direction d, int var){
        int sens;  //1 si de gauche vers le bas, -1 si vers le haut
        if(d == Direction.up){
            sens = -var;
        }else{
            sens = var;
        }
        this.pos = new Case(this.pos.absc, this.pos.ord + (sens));
        this.addToGraphics();
    }

    @Override
    public void addToGraphics() {
        game.getGraphic().add(new Element(pos.absc, pos.ord, c));
    }
}
