package models;

import game.GameConfig;
import utils.Rectangle3D;

/**
 * Created by Nhan on 2/28/2017.
 */
public abstract class GameObject {
    protected boolean isAlive;
    protected boolean isLeft;
    protected boolean isRight;
    protected boolean isAttack;

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    protected boolean isJump;
    protected boolean isDefend;
    protected int x;
    protected int y;
    protected int z;
//    protected int drawX; // positon to draw
    protected int drawY; // position to draw
    private int width;
    private int height;

    public GameObject(int x, int y, int z){
        this.x = x;
        this. y = y ;
        this.z = z;
    }

    public GameObject(int x, int y, int z, int width, int height) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.drawY = y + z;
        this.width = width;
        this.height = height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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

    public boolean isAttack() {
        return isAttack;
    }

    public boolean isJump() {
        return isJump;
    }

    public boolean isDefend() {
        return isDefend;
    }

    public int getDrawY() {
        return drawY;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public void setJump(boolean jump) {
        isJump = jump;
    }

    public void setDefend(boolean defend) {
        isDefend = defend;
    }

    // BAN DAN ROBOT
    public int middleY() {
        return this.y + this.height / 2;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setDrawY(int drawY) {
        this.drawY = drawY;
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
