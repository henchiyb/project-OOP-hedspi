package models;


import controllers.CollisionController;
import game.GameConfig;

import java.util.Stack;

/**
 * Created by Nhan on 2/28/2017.
 */
public class MainCharacter extends Character{
    private Stack<Integer> stackControlAction;
    private int healthBarWidth = GameConfig.BAR_WIDTH;
    private int manaBarWidth = GameConfig.BAR_WIDTH;

    public Stack<Integer> getStackControlAction() {
        return stackControlAction;
    }
    private int maxHealth = 100;
    private int maxMana = 100;
    private MainCharacter(int x, int y, int z, int width, int height){
        super(x, y, z, width, height, 100, 100, 10);
        stackControlAction  = new Stack<>();
        this.setLeft(true);
        CollisionController.getInstance().register(this);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void updateBarWidth(){
        healthBarWidth = mainCharacter.getHealth() * GameConfig.BAR_WIDTH / maxHealth;
        manaBarWidth = mainCharacter.getMana() * GameConfig.BAR_WIDTH / maxMana;
    }

    public int getHealthBarWidth() {
        return healthBarWidth;
    }

    public int getManaBarWidth() {
        return manaBarWidth;
    }

    public static MainCharacter mainCharacter = new MainCharacter(0, 0, 300, GameConfig.GAME_OBJECT_WIDTH,
            GameConfig.GAME_OBJECT_HEIGHT);

    @Override
    public void getHit(int damage){
        super.getHit(damage);
        updateBarWidth();
    }

    @Override
    public GameObject getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if (otherCollision instanceof EnemyCharacter) {
            CharacterState characterState = ((Character) otherCollision.getGameObject()).getCharacterState();
            if ( characterState == CharacterState.ATTACKING_NORMAL) {
                if (otherCollision.getGameObject().isAttack() && !this.isInvulnerable()) {
                    this.getHit(((Character)otherCollision).getDamage());
                    if (this.getCharacterState() == CharacterState.STUN_NORMAL_1) {
                        this.setCharacterState(CharacterState.STUN_NORMAL_2);
                    } else if (this.getCharacterState() == CharacterState.STUN_NORMAL_2) {
                        if ((otherCollision.getGameObject()).isLeft())
                            this.setCharacterState(CharacterState.FALL_LEFT);
                        else
                            this.setCharacterState(CharacterState.FALL_RIGHT);
                    } else {
                        this.setCharacterState(CharacterState.STUN_NORMAL_1);
                    }
                } else if (this.isInvulnerable()){
                    ((Character) otherCollision.getGameObject()).setCharacterState(CharacterState.STANDING);
                }
            }
        }
        else if (otherCollision instanceof EnemySkill && !this.isInvulnerable()){
            this.getHit(((CharacterSkill)otherCollision).getDamage());
            if (!otherCollision.getGameObject().isLeft())
                this.setCharacterState(CharacterState.FALL_RIGHT);
            else
                this.setCharacterState(CharacterState.FALL_LEFT);
        }
    }
}
