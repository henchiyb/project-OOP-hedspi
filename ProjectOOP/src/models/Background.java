package models;

import game.GameConfig;

/**
 * Created by Nhan on 4/26/2017.
 */
public class Background extends GameObject {
    public Background(int x, int y, int z) {
        super(x, y, z);
    }

    public Background(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height);
    }

    @Override
    public GameObject getGameObject() {
        return null;
    }

    @Override
    public void onCollide(Collision otherCollision) {

    }

    public void moveLeft(){
        this.x += GameConfig.RUNNING_SPEED;
    }

    public void moveRight(){
        this.x -= GameConfig.RUNNING_SPEED;
    }
}
