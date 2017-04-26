package controllers;

import game.GameConfig;
import models.GameObject;
import models.Item;
import models.MainCharacter;
import sun.applet.Main;
import views.Animation;

import java.awt.*;

public class ItemController extends SingleController {
    private Item item = (Item)this.gameObject;
    public ItemController(GameObject gameObject) {
        super(gameObject);
    }

    public void run() {
        switch (item.getItemSate()) {
            case IN_THE_SKY:
                item.fallDown();
                System.out.println(item.getY());
                break;
            case ON_GROUND:
                break;
            default:
                break;
        }
    }
}
