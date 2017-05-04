package models;

import controllers.CollisionController;
import game.GameConfig;

/**
 * Created by Nhan on 3/11/2017.
 */
public class CharacterSkill extends GameObject {
    public static final int SKILL_HEIGHT = 40;
    public static final int SKILL_WIDTH = 40;
    private int damage;

    public CharacterSkill(int x, int y, int z, int drawX, int damage) {
        super(x, y, z, SKILL_WIDTH, SKILL_HEIGHT);
        CollisionController.getInstance().register(this);
        this.damage = damage;
        this.drawX = drawX;
    }

    public CharacterSkill(int x, int y, int z, int drawX, int width, int height, int damage) {
        super(x, y, z, width, height);
        CollisionController.getInstance().register(this);
        this.damage = damage;
        this.drawX = drawX;
    }

    public int getDamage() {
        return damage;
    }

    public void moveLeft(){
        this.x -= GameConfig.BALL_FLYING_SPEED;
        this.drawX -= GameConfig.BALL_FLYING_SPEED;
        if (this.x < 0)
            isAlive = false;
    }

    public void moveRight(){
        this.x += GameConfig.BALL_FLYING_SPEED;
        this.drawX += GameConfig.BALL_FLYING_SPEED;
        if (this.x > GameConfig.MAP_WIDTH)
            isAlive = false;
    }


    @Override
    public GameObject getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {

    }
}
