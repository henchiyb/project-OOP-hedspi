package models;

import controllers.CollisionController;
import game.GameConfig;

/**
 * Created by Nhan on 5/1/2017.
 */
public class Item extends GameObject {
    private ItemState itemSate = ItemState.IN_THE_SKY;
    public ItemState getItemSate() {
        return itemSate;
    }
    public void setItemSate(ItemState itemSate) {
        this.itemSate = itemSate;
    }

    public Item(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height);
        CollisionController.getInstance().register(this);
    }

    public Item(int x, int y, int z) {
        super(x, y, z);
        CollisionController.getInstance().register(this);
    }

    private MainCharacter mainCharacter = MainCharacter.mainCharacter;
    public MainCharacter getMainCharacter() { return mainCharacter;}

    @Override
    public Item getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {

    }
}
