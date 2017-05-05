package controllers;

import game.GameConfig;
import models.Background;
import models.CharacterState;
import models.GameObject;
import views.SingleView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nhan on 4/26/2017.
 */
public class BackgroundController extends SingleController {
    private ArrayList<GameObject> listObject = GameObjectController.getInstance().getListObject();
    private Background background = (Background) this.gameObject;

    public BackgroundController(GameObject gameObject, SingleView view) {
        super(gameObject, view);
    }

    @Override
    public void run() {
        super.run();
        switch (background.getBackgroundState()) {
            case WALKING_LEFT:
                if (background.getDrawX() < GameConfig.MAP_START_X) {
                    background.moveDrawXToRight(GameConfig.WALKING_SPEED);
                    for (int i = 0; i < listObject.size(); i++) {
                        listObject.get(i).moveDrawXToRight(GameConfig.WALKING_SPEED);
                    }
                }
                break;
            case WALKING_RIGHT:
                if (background.getDrawX() > GameConfig.SCREEN_WIDTH - GameConfig.MAP_END_X) {
                    background.moveDrawXToLeft(GameConfig.WALKING_SPEED);
                    for (int i = 0; i < listObject.size(); i++) {
                        listObject.get(i).moveDrawXToLeft(GameConfig.WALKING_SPEED);
                    }
                }
                break;
            case RUNNING_LEFT:
                if (background.getDrawX() < GameConfig.MAP_START_X) {
                    background.moveDrawXToRight(GameConfig.RUNNING_SPEED);
                    for (int i = 0; i < listObject.size(); i++) {
                        listObject.get(i).moveDrawXToRight(GameConfig.RUNNING_SPEED);
                    }
                }
                break;
            case RUNNING_RIGHT:
                if (background.getDrawX() > GameConfig.SCREEN_WIDTH - GameConfig.MAP_END_X) {
                    background.moveDrawXToLeft(GameConfig.RUNNING_SPEED);
                    for (int i = 0; i < listObject.size(); i++) {
                        listObject.get(i).moveDrawXToLeft(GameConfig.RUNNING_SPEED);
                    }
                }
                break;
            case STANDING:
                break;
        }
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
