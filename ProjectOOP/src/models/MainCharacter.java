package models;


import game.GameConfig;

/**
 * Created by Nhan on 2/28/2017.
 */
public class MainCharacter extends GameObject{

    public MainCharacter(int x, int y, int z, int width, int height){
        super(x, y, z, width, height);
    }
    private  CharacterState characterState = CharacterState.STANDING;

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
        this.x -= GameConfig.WALKING_SPEED * 2;
    }
    public void runRight(){
        this.x += GameConfig.WALKING_SPEED * 2;
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
