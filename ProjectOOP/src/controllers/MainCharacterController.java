package controllers;

import game.GameConfig;
import game.ResourceMap;
import models.GameObject;
import models.MainCharacter;
import views.Animation;

import java.awt.*;

/**
 * Created by Nhan on 3/7/2017.
 */
public class MainCharacterController extends SingleController {
    MainCharacter mainCharacter = (MainCharacter) this.gameObject;
    public MainCharacterController(GameObject gameObject) {
        super(gameObject);
    }
    Animation animationDavisWalkingRight = new Animation(ResourceMap.DAVIS_WALKING, GameConfig.WALKING_FRAME_RATE);
    Animation animationDavisStanding = new Animation(ResourceMap.DAVIS_STANDING, GameConfig.STANDING_FRAME_RATE);

    @Override
    public void run() {
        switch (mainCharacter.getCharacterState()){
            case STANDING:
                break;
            case WALKING_LEFT:
                mainCharacter.walkLeft();
                break;
            case WALKING_RIGHT:
                mainCharacter.walkRight();
                break;
            case WALKING_DOWN:
                mainCharacter.walkDown();
                break;
            case WALKING_UP:
                mainCharacter.walkUp();
                break;
            case RUNNING_LEFT:
                mainCharacter.runLeft();
                break;
            case RUNNING_RIGHT:
                mainCharacter.runRight();
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        switch (mainCharacter.getCharacterState()){
            case STANDING:
                this.view = animationDavisStanding;
                break;
            case WALKING_LEFT:
                this.view = animationDavisWalkingRight;
                break;
            case WALKING_RIGHT:
                this.view = animationDavisWalkingRight;
                break;
            case WALKING_DOWN:
                this.view = animationDavisWalkingRight;
                break;
            case WALKING_UP:
                this.view = animationDavisWalkingRight;
                break;
            case RUNNING_LEFT:
                mainCharacter.runLeft();
                break;
            case RUNNING_RIGHT:
                mainCharacter.runRight();
                break;
        }
        super.draw(g);
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }
}
