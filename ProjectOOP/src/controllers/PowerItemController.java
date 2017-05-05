package controllers;

import game.GameConfig;
import game.ResourceMap;
import models.GameObject;
import models.PowerItem;
import views.Animation;

import java.awt.*;

/**
 * Created by Nhan on 5/5/2017.
 */
public class PowerItemController extends ItemController {
    public PowerItemController(GameObject gameObject) {
        super(gameObject);
    }

    private PowerItem powerItem = (PowerItem) this.gameObject;
    public PowerItem getPowerItem() { return powerItem;}

    private Animation animationItem = new Animation(ResourceMap.MAGIC_BALLOON, GameConfig.BALLOON_FRAME_RATE);
//    private SingleView brokenItem = new SingleView(Utils.loadImage("res/weapon_broken/16.png"));

    public void run() {
        switch (powerItem.getItemSate()) {
            case BROKEN:
                powerItem.setAlive(false);
                powerItem.destroy();
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g) {
        switch (powerItem.getItemSate()) {
            case IN_THE_SKY:
                this.view = animationItem;
                break;
            case BROKEN:
//                this.view = brokenItem;
                break;
            default:
                break;
        }
        super.draw(g);
    }
}
