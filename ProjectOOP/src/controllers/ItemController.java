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
    private Animation standStillItem = new Animation("res/weapon0/4.png"); // stand still
    private Animation animationItem = new Animation("res/weapon0/0.png", "res/weapon0/1.png", "res/weapon0/2.png", "res/weapon0/3.png", "res/weapon0/4.png", "res/weapon0/5.png", "res/weapon0/6.png", "res/weapon0/7.png", "res/weapon0/8.png", "res/weapon0/9.png", "res/weapon0/10.png", "res/weapon0/11.png", "res/weapon0/12.png", "res/weapon0/13.png", "res/weapon0/14.png", "res/weapon0/15.png"); // falling down

    private MainCharacter mainCharacter;
    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }
    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    public void run() {
        switch (item.getItemSate()) {
            case IN_THE_SKY:
                item.fallDown();
                System.out.println(item.getY());
                break;
            case ON_GROUND:
                break;
            case ON_HAND:
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g) {
        switch (item.getItemSate()) {
            case IN_THE_SKY:
                this.view = animationItem;
                break;
            case ON_GROUND:
                this.view = standStillItem;
                break;
            default:
                break;
        }
        super.draw(g);
    }

    public Item getItem() {
        return item;
    }
}
