package controllers;

import game.GameConfig;
import game.ResourceMap;
import managers.ControllerManager;
import models.CharacterSkill;
import models.GameObject;
import models.MainCharacter;
import utils.Utils;
import views.Animation;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nhan on 3/7/2017.
 */
public class MainCharacterController extends SingleController {
    private MainCharacter mainCharacter = (MainCharacter) this.gameObject;
    private ArrayList<SkillCharacterController> skillCharacterControllerArrayList
            = ControllerManager.getSkillCharacterControllerArrayList();

    public MainCharacterController(GameObject gameObject) {
        super(gameObject);
    }

    private Animation animationDavisWalkingRight = new Animation(ResourceMap.DAVIS_WALKING, GameConfig.WALKING_FRAME_RATE);
    private Animation animationDavisStanding = new Animation(ResourceMap.DAVIS_STANDING, GameConfig.STANDING_FRAME_RATE);


    @Override
    public void run() {
        switch (mainCharacter.getCharacterState()) {
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
            case SKILL_SHOOTING:
                skillCharacterControllerArrayList.add(new SkillCharacterController(
                        new CharacterSkill(mainCharacter.getX() + mainCharacter.getHeight(),
                        mainCharacter.getY() + (mainCharacter.getHeight() - CharacterSkill.SKILL_HEIGHT) / 2,
                        mainCharacter.getZ())));
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
//                mainCharacter.runLeft();
                break;
            case RUNNING_RIGHT:
//                mainCharacter.runRight();
                break;
        }

        super.draw(g);
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }
}
