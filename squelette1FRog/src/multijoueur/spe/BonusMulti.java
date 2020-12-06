package multijoueur.spe;

import multijoueur.GameMulti;
import util.Case;

import java.awt.*;

public class BonusMulti extends SpeMulti {
    public BonusMulti (GameMulti game, Case pos){
        super(game, pos, Color.orange);
    }

}