package multijoueur;

import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class FrogMulti implements IFrog {

    private GameMulti gameMulti;
    public Case position;
    private Direction direction;

    /**
     *
     * @param gameMulti la partie de jeu
     */
    public FrogMulti(GameMulti gameMulti, int abs) {
        this.gameMulti = gameMulti;
        this.direction = Direction.up;
        this.position = new Case(abs, 0);
    }

    public void resetPos(int x){
        this.position = new Case(x, 0);
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
        if (gameMulti.isGlisse(position)) {
            Case newPos = new Case(position.absc, position.ord + 2);
            position = newPos;

            if(this.gameMulti.height1>=this.gameMulti.maxHeight1) {
                gameMulti.score1+=2;
                gameMulti.maxHeight1+=2;
            }
            else if (this.gameMulti.height1>=this.gameMulti.maxHeight1-1) {
                gameMulti.score1+=1;
                gameMulti.maxHeight1+=1;
            }
            gameMulti.height1+=2;
        }
    }

    public void faitGlisser2() {
        if (gameMulti.isGlisse(position)) {
            Case newPos = new Case(position.absc, position.ord + 2);
            position = newPos;

            if(this.gameMulti.height2>=this.gameMulti.maxHeight2) {
                gameMulti.score2+=2;
                gameMulti.maxHeight2+=2;
            }
            else if (this.gameMulti.height2>=this.gameMulti.maxHeight2-1) {
                gameMulti.score2+=1;
                gameMulti.maxHeight1+=1;
            }
            gameMulti.height2+=2;
        }
    }



    @Override
    public void move(Direction key) {
        if((key == Direction.up) && (position.ord < gameMulti.defaultHeight-1)){
            this.gameMulti.height1++;
            if (this.gameMulti.height1>=this.gameMulti.maxHeight1) {
                this.gameMulti.maxHeight1++;
                gameMulti.score1++;
            }
            Case newPos = new Case(position.absc, position.ord+1);
            position = newPos;
        }else if((key == Direction.down) && (position.ord > 0)){
            this.gameMulti.height1--;
            Case newPos = new Case(position.absc, position.ord-1);
            position = newPos;
        }else if((key == Direction.left) && (position.absc > 0)){
            Case newPos = new Case(position.absc-1, position.ord);
            position = newPos;
        }else if((key == Direction.right) && (position.absc < gameMulti.width-1)){
            Case newPos = new Case(position.absc+1, position.ord);
            position = newPos;
        }
    }

    public void move2(Direction key) {
        if((key == Direction.up) && (position.ord < gameMulti.defaultHeight-1)){
            this.gameMulti.height2++;
            if (this.gameMulti.height2>=this.gameMulti.maxHeight2) {
                this.gameMulti.maxHeight2++;
                gameMulti.score2++;
            }
            Case newPos = new Case(position.absc, position.ord+1);
            position = newPos;
        }else if((key == Direction.down) && (position.ord > 0)){
            this.gameMulti.height2--;
            Case newPos = new Case(position.absc, position.ord-1);
            position = newPos;
        }else if((key == Direction.left) && (position.absc > 0)){
            Case newPos = new Case(position.absc-1, position.ord);
            position = newPos;
        }else if((key == Direction.right) && (position.absc < gameMulti.width-1)){
            Case newPos = new Case(position.absc+1, position.ord);
            position = newPos;
        }
    }
}
