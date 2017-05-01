package models;

import game.GameConfig;

/**
 * Created by Nhan on 5/1/2017.
 */
public class ThrowedItem extends Item{
    public ThrowedItem(int x, int y, int z, int width, int height, int weaponHP, int dropHurt, int mana) {
        super(x, y, z, width, height, weaponHP, dropHurt, mana);
    }

    private int originalXCoord, originalYCoord;

    public void setOriginalXYCoord (int x, int y) {
        this.originalXCoord = x; this.originalYCoord = y;
    }
    public int getOriginalXCoord() {return originalXCoord;}
    public int getOriginalYCoord() {return originalYCoord;}

    public void throwed(int xCoord, int yCoord) {
        if (this.getY() < yCoord + 100) {
            this.setY(this.getY() + 3);
            if (getMainCharacter().isLeft) {
                this.setX(xCoord - (int) (150 * Math.sqrt(10000 - (this.getY() - yCoord - 100) * (this.getY() - yCoord - 100)) / 100)); // ellipse x^2/a^2 + y^2/b^2 = 1 with a = 150, b= 100
            } else {
                this.setX(xCoord + (int) (150 * Math.sqrt(10000 - (this.getY() - yCoord - 100) * (this.getY() - yCoord - 100)) / 100)); // ellipse
            }
            //TODO: if encounter the enemy, decrease its mana & broken
        } else {
            if (this.getWeaponHP() > 0)
                this.setItemSate(ItemState.ON_GROUND);
            else
                this.setItemSate(ItemState.BROKEN);
        }
        System.out.println("WeaponHP: " + this.getWeaponHP());
    }
}
