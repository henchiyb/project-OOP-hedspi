package models;

import controllers.CollisionController;
import controllers.GameObjectController;
import game.GameConfig;
import utils.Rectangle3D;

/**
 * Created by Nhan on 2/28/2017.
 */
public abstract class GameObject implements Collision{
    protected boolean isAlive = true;
    protected boolean isLeft = true;
    protected boolean isAttack;
    protected boolean isJump;
    protected int x;
    protected int y;
    protected int z;
    protected int drawX;
    protected int drawY; // position y to draw
    private int width;
    private int height;

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public GameObject(int x, int y, int z){
        this.x = x;
        this.drawX = x;
        this. y = y ;
        this.z = z;
        this.drawY = z;
        GameObjectController.getInstance().add(this);
    }

    public GameObject(int x, int y, int z, int width, int height) {
        this.x = x;
        this.drawX = x;
        this.y = y;
        this.z = z;
        this.drawY = z;
        this.width = width;
        this.height = height;
        GameObjectController.getInstance().add(this);
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

    public int getDrawY() {
        return drawY;
    }

    public int getDrawX() {
        return drawX;
    }

    public void setDrawX(int drawX) {
        this.drawX = drawX;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public void setJump(boolean jump) {
        isJump = jump;
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

    public void destroy(){
        CollisionController.getInstance().unregister(this);
        GameObjectController.getInstance().remove(this);
    }

    public void moveDrawXToLeft(int speed){
        this.drawX -= speed;
    }

    public void moveDrawXToRight(int speed){
        this.drawX += speed;
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
