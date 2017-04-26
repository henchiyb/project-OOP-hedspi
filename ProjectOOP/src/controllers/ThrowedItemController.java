package controllers;

import models.GameObject;
import models.Item;
import models.ItemState;
import models.ThrowedItem;
import views.Animation;

import java.awt.*;

/**
 * Created by Starlight on 4/25/2017.
 */
public class ThrowedItemController extends ItemController {
    private ThrowedItem throwedItem = (ThrowedItem) this.gameObject;

    public ThrowedItemController(GameObject gameObject) {
        super(gameObject);
    }

    private Animation standStillItem = new Animation("res/weapon3/5.png"); // stand still
    private Animation animationItem = new Animation("res/weapon3/0.png"); // falling down
    private Animation onHandItem = new Animation("res/weapon3/5.png");
    private Animation throwingItem = new Animation( "res/weapon3/2.png", "res/weapon3/3.png", "res/weapon3/4.png");
    private Animation brokenItem;
    private Animation disappearItem = new Animation("res/weapon3_broken/16.png");

    public void run() {
        switch (throwedItem.getItemSate()) {
            case IN_THE_SKY:
                throwedItem.fallDown();
                throwedItem.setOriginalXYCoord(throwedItem.getX(),throwedItem.getY());
                System.out.println(throwedItem.getY());
                break;
            case ON_HAND:
                throwedItem.pickedUp();
                break;
            case THROWING:
                throwedItem.throwed(throwedItem.getOriginalXCoord(), throwedItem.getOriginalYCoord());
                break;
            case ON_GROUND:
                // tam thoi, item roi xuong, doi 10s roi chuyen trang thai thanh 'bi nem di', to try method ThrowItem.throwed()
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throwedItem.setItemSate(ItemState.THROWING);
                break;
            case BROKEN:
                throwedItem.setItemSate(ItemState.DISAPPEAR);
                break;
            case DISAPPEAR:
                break;
            default:
                break;
        }

    }

    public void draw(Graphics g) {
        switch (throwedItem.getItemSate()) {
            case IN_THE_SKY:
                this.view = animationItem;
                break;
            case ON_GROUND:
                this.view = standStillItem;
                break;
            case ON_HAND:
                this.view = onHandItem;
                break;
            case THROWING:
                this.view = throwingItem;
                break;
            case BROKEN:
                this.view = standStillItem; //tam thoi the
                break;
            case DISAPPEAR:
                this.view = disappearItem;
                break;
            default:
                break;
        }
        super.draw(g);
    }

    public ThrowedItem getThrowedItem() {
        return throwedItem;
    }
}
