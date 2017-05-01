package models;


import controllers.CollisionController;

import java.util.Stack;

/**
 * Created by Nhan on 2/28/2017.
 */
public class MainCharacter extends Character{
    private Stack<Integer> stackControlAction;
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

    public static MainCharacter mainCharacter = new MainCharacter(0, 0, 300, 80, 80);

    @Override
    public GameObject getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {

    }
}
