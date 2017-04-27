package controllers;

import models.Background;
import models.CharacterState;
import models.GameObject;
import views.SingleView;

import java.awt.*;

/**
 * Created by Nhan on 4/26/2017.
 */
public class BackgroundController extends SingleController {
    private boolean isMoveLeft;
    private boolean isMoveRight;

    public boolean isMoveLeft() {
        return isMoveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        isMoveLeft = moveLeft;
    }

    public boolean isMoveRight() {
        return isMoveRight;
    }

    public void setMoveRight(boolean moveright) {
        isMoveRight = moveright;
    }

    public BackgroundController(GameObject gameObject, SingleView view) {
        super(gameObject, view);
    }

    @Override
    public void run() {
        super.run();
//        if (isMoveLeft){
//            ((Background) gameObject).moveLeft();
//        } else if (isMoveRight){
//            ((Background) gameObject).moveRight();
//        }

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
