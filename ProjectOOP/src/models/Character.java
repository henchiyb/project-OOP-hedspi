package models;

import controllers.CollisionController;
import controllers.SkillCharacterController;
import game.GameConfig;

import java.util.Stack;

/**
 * Created by Nhan on 4/27/2017.
 */
public class Character extends GameObject {
    private int health;
    private int mana;
    private int damage;
    private CharacterState characterState = CharacterState.STANDING;
    private int velocityY;
    private int velocityX;
    private int y0;
    private boolean invulnerable = false;
    private Stack<Integer> stackControlAction;

    public CharacterState getCharacterState() {
        return characterState;
    }

    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setManaChange(int mana) {
        this.mana += mana;
    }


    public void setY0(int y0) {
        this.y0 = y0;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public boolean isInvulnerable() {
        return invulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void damageUp(int damage){
        this.damage += damage;
    }

    public Stack<Integer> getStackControlAction() {
        return stackControlAction;
    }

    public Character(int x, int y, int z, int width, int height, int health, int mana, int damage) {
        super(x, y, z, width, height);
        this.health = health;
        this.mana = mana;
        this.damage = damage;
        stackControlAction = new Stack<>();
    }
    public void getHit(int damage){
        this.health -= damage;
        if (this.health <= 0){
            characterState = CharacterState.DEAD;
            setInvulnerable(true);
            System.out.println("is DEAD");
        }
    }

    public void walkLeft() {
        if (this.x > 0)
            this.x -= GameConfig.WALKING_SPEED;
        else
            this.x = 0;
    }

    public void walkRight() {
        if (this.x < GameConfig.SCREEN_WIDTH - this.getWidth())
            this.x += GameConfig.WALKING_SPEED;
        else
            this.x = GameConfig.SCREEN_WIDTH - this.getWidth();

    }

    public void walkUp() {
        if (this.z > 0) {
            this.drawY -= GameConfig.WALKING_SPEED;
            this.z -= GameConfig.WALKING_SPEED;
        } else {
            this.drawY = 0;
            this.z = 0;
        }
    }

    public void walkDown() {
        if (this.z < GameConfig.SCREEN_HEIGHT - this.getHeight()) {
            this.drawY += GameConfig.WALKING_SPEED;
            this.z += GameConfig.WALKING_SPEED;
        } else {
            this.drawY = GameConfig.SCREEN_HEIGHT - this.getHeight();
            this.z = GameConfig.SCREEN_HEIGHT - this.getHeight();
        }
    }

    public void runLeft() {
        this.x -= GameConfig.RUNNING_SPEED;
    }

    public void runRight() {
        this.x += GameConfig.RUNNING_SPEED;
    }

    public void jump(double time) {
        int gravity = 5;
        velocityY += gravity * time;
        //y is space jump
        this.y += velocityY * time;
        this.drawY += velocityY * time;
        if (this.drawY >= y0) {
            this.drawY = y0;
            velocityY = 0;
            this.z = y0;
            this.y = 0;
        }
    }

    public void jumpAtLeft(double t) {
        this.x += velocityX * t;
        jump(t);
    }

    public void jumpAtRight(double t) {
        this.x -= velocityX * t;
        jump(t);
    }

    @Override
    public GameObject getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {

    }
}
