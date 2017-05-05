package models;

import game.GameConfig;

/**
 * Created by Nhan on 5/1/2017.
 */
public class ThrowedItem extends Item{
    private int damage;

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    private int hit;

    public int getHit() {
        return this.hit;
    }

    public void setHit(int h) {
        hit = h;
    }

    private int originalXCoordDraw, originalYCoordDraw, originalXCoord, originalZCoord;

    public void setOriginalXZCoord(int drawX, int drawY, int x, int z) {
        this.originalXCoordDraw = drawX;
        this.originalYCoordDraw = drawY;
        this.originalXCoord = x;
        this.originalZCoord = z;
    }

    public int getOriginalXCoord() {
        return originalXCoord;
    }

    public int getOriginalZCoord() {
        return originalZCoord;
    }

    public int getOriginalXCoordDraw() {
        return originalXCoordDraw;
    }

    public int getOriginalYCoordDraw() {
        return originalYCoordDraw;
    }


    private boolean isBox;

    public boolean isBox() {
        return isBox;
    }

    public void setBox(boolean isBox) {
        this.isBox = isBox;
    }


    public ThrowedItem(int x, int y, int z, int width, int height, int hit, int damage, boolean isBox) {
        super(x, y, z);
        this.setHit(hit);
        this.setDamage(damage);
        this.isBox = isBox;
        this.drawY = 0;
    }

    @Override
    public ThrowedItem getGameObject() {
        return this;
    }

    public void fallDown() {
        if (this.getDrawY() < this.getZ()) {
            this.setDrawY(this.getDrawY() + GameConfig.FALLING_SPEED);
        } else {
            this.setItemSate(ItemState.ON_GROUND);
            this.setDrawY(this.getZ());
        }
    }

    public void pickedUp() {
        this.setDrawX(this.getMainCharacter().getDrawX());
        this.setX(this.getMainCharacter().getX());
        this.setY(this.getMainCharacter().getY() - 35);
        this.setZ(this.getMainCharacter().getZ());
        this.setDrawY(this.getZ() - this.getHeight());
        this.setOriginalXZCoord(this.getDrawX(), this.getDrawY(), this.getX(), this.getZ());
    }

    public void fallDownFromCharacter() {
        this.setDrawX(this.getMainCharacter().getDrawX() + this.getWidth() + 1);
        this.setX(this.getMainCharacter().getX() + this.getWidth() + 1);
        this.setY(this.getMainCharacter().getY());
        this.setZ(this.getMainCharacter().getZ());
        this.setDrawY(this.getZ());
        this.setItemSate(ItemState.ON_GROUND);
        this.setOriginalXZCoord(this.getDrawX(), this.getDrawY(), this.getX(), this.getZ());
    }

    public void throwed(int xCoordDraw, int yCoordDraw, int xCoord, int zCoord) {
        if (this.getDrawY() < this.getMainCharacter().getDrawY() + 25) {
            this.setDrawY(this.getDrawY() + 3);
            int elipceSize = (int) (150 * Math.sqrt(10000 - (this.getDrawY() - yCoordDraw - 100) *
                    (this.getDrawY() - yCoordDraw - 100)) / 100); // ellipse x^2/a^2 + y^2/b^2 = 1 with a = 150, b= 100
            if (getMainCharacter().isLeft) {
                this.setDrawX(xCoordDraw - elipceSize);
                this.setX(xCoord- elipceSize);
            } else {
                this.setDrawX(xCoordDraw + elipceSize);
                this.setX(xCoord + elipceSize);
            }
        } else {
            this.setItemSate(ItemState.ON_GROUND);
            this.setDrawY(this.getMainCharacter().getZ());
            this.setHit(this.getHit() - 1);
            if (this.getHit() < 0) {
                this.setItemSate(ItemState.BROKEN);
            }
        }
    }

    @Override
    public void onCollide(Collision otherCollision) {
        // encounter the enemy
        if (otherCollision instanceof EnemyCharacter ) {
            EnemyCharacter enemyCharacter = (EnemyCharacter) otherCollision;
            if (!enemyCharacter.isInvulnerable() && this.getItemSate() != ItemState.ON_GROUND)
                if (enemyCharacter.isLeft())
                    enemyCharacter.setCharacterState(CharacterState.FALL_RIGHT);
                else
                    enemyCharacter.setCharacterState(CharacterState.FALL_LEFT);
            enemyCharacter.setHealth(((EnemyCharacter) otherCollision).getHealth() - this.getDamage());
        }
        // 2 items collide
        if (otherCollision instanceof ThrowedItem) {
            if (((ThrowedItem) otherCollision).getX() < this.getX()) {
                this.setX(this.getX() + 5);
                this.setDrawX(this.getDrawX() + 5);
            }
            else {
                this.setX(this.getX() - 5);
                this.setDrawX(this.getDrawX() - 5);
            }
        }
    }
}
