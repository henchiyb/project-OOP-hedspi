package models;

import game.GameConfig;
import utils.Rectangle3D;

/**
 * Created by Nhan on 2/28/2017.
 */
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int z;
    private int width;
    private int height;

    public GameObject(int x, int y, int z){
        this.x = x;
        this. y = y ;
    }

    public GameObject(int x, int y, int z, int width, int height) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean intersects(GameObject gameObject){
        Rectangle3D rectThis = this.getRect();
        Rectangle3D rectObject = gameObject.getRect();
        return rectThis.intersect(rectObject);
    }

    private Rectangle3D getRect(){
        return new Rectangle3D(x, y, z, width, height, GameConfig.GAME_OBJECT_DEPTH);
    }
}
