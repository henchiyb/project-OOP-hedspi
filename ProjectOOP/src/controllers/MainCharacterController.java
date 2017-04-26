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

    private Animation animationDavisWalkingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_WALKING),
            GameConfig.WALKING_FRAME_RATE);
    private Animation animationDavisWalkingRight = new Animation(ResourceMap.DAVIS_WALKING,
            GameConfig.WALKING_FRAME_RATE);
    private Animation animationDavisStandingRight = new Animation(ResourceMap.DAVIS_STANDING,
            GameConfig.STANDING_FRAME_RATE);
    private Animation animationDavisStandingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_STANDING),
            GameConfig.STANDING_FRAME_RATE);
    private Animation animationDavisNormalAttack0 = new Animation(ResourceMap.DAVIS_NORMAL_ATTACK_0,
            GameConfig.ATTACKING_FRAME_RATE);
    private Animation animationDavisNormalAttack1 = new Animation(ResourceMap.DAVIS_NORMAL_ATTACK_1,
            GameConfig.ATTACKING_FRAME_RATE);
    private Animation animationDavisNormalAttack2 = new Animation(ResourceMap.DAVIS_NORMAL_ATTACK_2,
            GameConfig.ATTACKING_FRAME_RATE);


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
            case ATTACKING_NORMAL:
            case ATTACKING_HARD:
//                mainCharacter.runRight();
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
                if (mainCharacter.isLeft())
                    this.view = animationDavisStandingLeft;
                else
                    this.view = animationDavisStandingRight;
                break;
            case WALKING_LEFT:
                this.view = animationDavisWalkingLeft;
                break;
            case WALKING_RIGHT:
                this.view = animationDavisWalkingRight;
                break;
            case WALKING_DOWN:
                if (mainCharacter.isLeft())
                    this.view = animationDavisWalkingLeft;
                else
                    this.view = animationDavisWalkingRight;
                break;
            case WALKING_UP:
                if (mainCharacter.isLeft())
                    this.view = animationDavisWalkingLeft;
                else
                    this.view = animationDavisWalkingRight;
                break;
            case RUNNING_LEFT:
                this.view = animationDavisWalkingLeft;
                break;
            case RUNNING_RIGHT:
                this.view = animationDavisWalkingRight;
                break;
            case ATTACKING_NORMAL:
                this.view = animationDavisNormalAttack0;
                if (animationDavisNormalAttack0.isAnimationEnd()){
                    animationDavisNormalAttack0.setAnimationEnd(false);
                }
                break;
            case ATTACKING_HARD:
//                this.view = animationDavisNormalAttack0;
//                if (animationDavisNormalAttack0.isAnimationEnd()){
//                    this.view = animationDavisNormalAttack1;
//                    if (animationDavisNormalAttack1.isAnimationEnd()){
//                        this.view = animationDavisNormalAttack2;
//                        if (animationDavisNormalAttack2.isAnimationEnd()){
//                            mainCharacter.setAttack(false);
//                            animationDavisNormalAttack0.setAnimationEnd(false);
//                            animationDavisNormalAttack1.setAnimationEnd(false);
//                            animationDavisNormalAttack2.setAnimationEnd(false);
//                        }
//                    }
//                }
                break;
        }

        super.draw(g);
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }
}
