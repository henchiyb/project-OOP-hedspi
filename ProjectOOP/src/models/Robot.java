package models;

import controllers.CollisionController;
import game.GameConfig;

/**
 * Created by Nhan on 4/25/2017.
 */
public class Robot extends GameObjectWithHp{
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    public Robot(int x, int y, int z) {
        super(x, y, z, WIDTH, HEIGHT);
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
    }
    public void walkDown(){
        this.y += GameConfig.WALKING_SPEED;
    }
    public void runLeft(){
        this.x -= GameConfig.RUNNING_SPEED;
    }
    public void runRight(){
        this.x += GameConfig.RUNNING_SPEED;
    }

    @Override
    public GameObjectWithHp getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if (otherCollision instanceof MainCharacter && ((GameObjectWithHp)otherCollision.getGameObject())
                .getCharacterState() == CharacterState.ATTACKING_NORMAL) {
            if (otherCollision.getGameObject().isAttack()){
                System.out.println("Attack Robot");
                if(this.getCharacterState() == CharacterState.STUN_NORMAL_1){
                    this.setCharacterState(CharacterState.STUN_NORMAL_2);
                } else if(this.getCharacterState() == CharacterState.STUN_NORMAL_2){
                    this.setCharacterState(CharacterState.FALL);
                } else {
                    this.setCharacterState(CharacterState.STUN_NORMAL_1);
                }
            }
        }
    }
}
