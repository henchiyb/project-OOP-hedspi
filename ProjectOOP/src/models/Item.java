package models;

import controllers.MainCharacterController;
import game.GameConfig;

import java.util.Stack;

/**
 * Created by Starlight on 4/15/2017.
 */
public class Item extends GameObject {
    private ItemState itemSate = ItemState.IN_THE_SKY;
    //private Stack<Integer> stackControlAction;

    public Item(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height);
        //stackControlAction = new Stack<>();
    }
    /*
        public Stack<Integer> getStackControlAction() {
        return stackControlAction;
    }
     */
    public ItemState getItemSate() {
        return itemSate;
    }
    public void setItemSate(ItemState itemSate) {
        this.itemSate = itemSate;
    }
    public void fallDown() {
        if (this.y < 500)
            this.setY(this.y + GameConfig.FALLING_SPEED);
        else
            this.setItemSate(ItemState.ON_GROUND);
    }
}
