package models;


import game.GameConfig;

import java.util.Stack;

/**
 * Created by Nhan on 2/28/2017.
 */
public class MainCharacter extends GameObject{
    private  CharacterState characterState = CharacterState.STANDING;
    private  Stack<Integer> stackControlAction;

    public Stack<Integer> getStackControlAction() {
        return stackControlAction;
    }

    public MainCharacter(int x, int y, int z, int width, int height){
        super(x, y, z, width, height);
        stackControlAction  = new Stack<>();
        this.setLeft(true);
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
//    public void jump(){
//        this.drawY = y + z;
//    }

    public CharacterState getCharacterState() {
        return characterState;
    }

    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }
}
