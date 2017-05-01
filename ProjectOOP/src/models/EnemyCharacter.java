package models;

import controllers.CollisionController;

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
                    System.out.println(this.isAlive());
                    if (this.getCharacterState() == CharacterState.STUN_NORMAL_1) {
                        this.setCharacterState(CharacterState.STUN_NORMAL_2);
                    } else if (this.getCharacterState() == CharacterState.STUN_NORMAL_2) {
                        this.setCharacterState(CharacterState.FALL);
                    } else {
                        this.setCharacterState(CharacterState.STUN_NORMAL_1);
                    }
                }
            }
        }
    }
}
