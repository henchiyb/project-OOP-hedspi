package models;

import controllers.CollisionController;
import controllers.SkillCharacterController;
import game.GameConfig;
import managers.ControllerManager;

/**
 * Created by Nhan on 4/27/2017.
 */
public class EnemyCharacter extends Character {
    public EnemyCharacter(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height, 50, 50, 5);
        this.setLeft(true);
        CollisionController.getInstance().register(this);
    }

    @Override
    public void walkLeft(){
        super.walkLeft();
        this.drawX -= GameConfig.WALKING_SPEED;
    }
    @Override
    public void walkRight(){
        super.walkRight();
        this.drawX += GameConfig.WALKING_SPEED;
    }
    @Override
    public void runLeft(){
        super.runLeft();
        this.drawX -= GameConfig.RUNNING_SPEED;
    }
    @Override
    public void runRight(){
        super.runRight();
        this.drawX += GameConfig.RUNNING_SPEED;
    }
    @Override
    public Character getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if (otherCollision instanceof MainCharacter) {
            CharacterState characterState = ((Character) otherCollision.getGameObject()).getCharacterState();
            if ( characterState == CharacterState.ATTACKING_NORMAL) {
                if (otherCollision.getGameObject().isAttack() && !this.isInvulnerable()) {
                    this.getHit(((MainCharacter)otherCollision).getDamage());
                    System.out.println(this.getHealth());
                    if (this.getCharacterState() == CharacterState.STUN_NORMAL_1) {
                        this.setCharacterState(CharacterState.STUN_NORMAL_2);
                    } else if (this.getCharacterState() == CharacterState.STUN_NORMAL_2) {
                        if ((this.getGameObject()).isLeft())
                            this.setCharacterState(CharacterState.FALL_RIGHT);
                        else
                            this.setCharacterState(CharacterState.FALL_LEFT);
                    } else {
                        this.setCharacterState(CharacterState.STUN_NORMAL_1);
                    }
                }
            }

        }
        if (otherCollision instanceof MainSkill && !this.isInvulnerable()){
            this.getHit(((CharacterSkill)otherCollision).getDamage());
            if ((this.getGameObject()).isLeft())
                this.setCharacterState(CharacterState.FALL_RIGHT);
            else
                this.setCharacterState(CharacterState.FALL_LEFT);
        }
    }
}
