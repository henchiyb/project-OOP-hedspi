package controllers;

import game.GameConfig;
import models.GameObject;
import models.Item;
import utils.Utils;

import java.awt.*;

/**
 * Created by Nhan on 5/1/2017.
 */
public class ItemController extends SingleController {
    private Item item = (Item)this.gameObject;
    public ItemController(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void draw(Graphics g){
        Utils.drawShadow(g, item);
        super.draw(g);
    }
}
