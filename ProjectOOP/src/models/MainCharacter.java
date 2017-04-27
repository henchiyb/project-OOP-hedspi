package models;


import controllers.CollisionController;
import game.GameConfig;

import java.util.Stack;

/**
 * Created by Nhan on 2/28/2017.
 */
public class MainCharacter extends GameObjectWithHp{
    private int velocityY;
    private int velocityX;
    private Stack<Integer> stackControlAction;

    public Stack<Integer> getStackControlAction() {
        return stackControlAction;
    }

    public MainCharacter(int x, int y, int z, int width, int height){
        super(x, y, z, width, height);
        stackControlAction  = new Stack<>();
        this.setLeft(true);
        CollisionController.getInstance().add(this);
    }

    public void walkLeft(){
        this.x -= GameConfig.WALKING_SPEED;
    }
    public void walkRight(){
        this.x += GameConfig.WALKING_SPEED;
    }
    public void walkUp(){
        this.y -= GameConfig.WALKING_SPEED;
        this.drawY -= GameConfig.WALKING_SPEED;
    }
    public void walkDown(){
        this.y += GameConfig.WALKING_SPEED;
        this.drawY += GameConfig.WALKING_SPEED;
    }
    public void runLeft(){
        this.x -= GameConfig.RUNNING_SPEED;
    }
    public void runRight(){
        this.x += GameConfig.RUNNING_SPEED;
    }

    private int y0;

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void jump(double time){
        int gravity = 5;
        System.out.println("drawY = " + drawY + " y = " + y + " y0 = " + y0);
        velocityY += gravity * time;
        //z is space jump
        this.z += velocityY * time;
        System.out.println("z = " + z);
        this.drawY += velocityY * time;
        if (this.drawY >= y0){
            this.drawY = y0;
            velocityY = 0;
            this.z = 0;
        }
    }

    public void jumpAtLeft(double t){
        this.x += velocityX * t;
        jump(t);
    }

    public void jumpAtRight(double t){
        this.x -= velocityX * t;
        jump(t);
    }

    @Override
    public GameObjectWithHp getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if (otherCollision instanceof Robot && this.getCharacterState() == CharacterState.ATTACKING_NORMAL) {

        }
    }
}
