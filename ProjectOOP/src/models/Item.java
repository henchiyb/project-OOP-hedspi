package models;

import controllers.MainCharacterController;
import game.GameConfig;

import java.util.Stack;

/**
 * Created by Starlight on 4/15/2017.
 */
public class Item extends GameObject {
    private int weaponHP;
    private int dropHurt;
    private int mana;
    public void setWeaponHP(int hp) {this.weaponHP = hp;}
    public int getWeaponHP() {return this.weaponHP;}

    private ItemState itemSate = ItemState.IN_THE_SKY;
    //private Stack<Integer> stackControlAction;

    public Item(int x, int y, int z, int width, int height, int weaponHP, int dropHurt, int mana) {
        super(x, y, z, width, height);
        this.weaponHP = weaponHP;
        this.dropHurt = dropHurt;
        this.mana = mana;
        //stackControlAction = new Stack<>();
    }
    /*
        public Stack<Integer> getStackControlAction() {
        return stackControlAction;
    }
     */
    private MainCharacter mainCharacter;
    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }
    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    public ItemState getItemSate() {
        return itemSate;
    }
    public void setItemSate(ItemState itemSate) {
        this.itemSate = itemSate;
    }
    public void fallDown() {
        if (this.y < 400)
            this.setY(this.y + GameConfig.FALLING_SPEED);
        else {
            this.setItemSate(ItemState.ON_GROUND);
        }
    }

    public void pickedUp() {
        while (this.getX() == this.getMainCharacter().getX() && this.getY() == this.getMainCharacter().getY()) {
            this.setY(getY() + 80);
            System.out.println("PICK UP");
        }

    }
}
