package controllers;

import models.GameObject;
import models.Item;

/**
 * Created by Nhan on 5/1/2017.
 */
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
