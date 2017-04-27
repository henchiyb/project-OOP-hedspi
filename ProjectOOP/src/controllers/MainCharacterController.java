package controllers;

import game.GameConfig;
import game.ResourceMap;
import managers.ControllerManager;
import models.CharacterSkill;
import models.CharacterState;
import models.GameObject;
import models.MainCharacter;
import utils.Utils;
import views.Animation;
import views.SingleView;

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
    private Animation animationDavisWalkingRight = new Animation(Utils.flipImages(Utils.flipImages(ResourceMap.DAVIS_WALKING)),
            GameConfig.WALKING_FRAME_RATE);
    private Animation animationDavisRunningLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_RUNNING),
            GameConfig.RUNNING_FRAME_RATE);
    private Animation animationDavisRunningRight = new Animation(ResourceMap.DAVIS_RUNNING,
            GameConfig.RUNNING_FRAME_RATE);
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
    private Animation animationDavisJumping = new Animation(ResourceMap.DAVIS_JUMPING,
            GameConfig.JUMPING_FRAME_RATE);
    private Animation animationDavisShooting = new Animation(ResourceMap.DAVIS_SHOOTING,
            GameConfig.ATTACKING_FRAME_RATE);
    private Animation animationDavisFalling = new Animation(ResourceMap.DAVIS_SHOOTING,
            GameConfig.ATTACKING_FRAME_RATE);
    private SingleView singleViewJumpingLeft = new SingleView(Utils.flipImage(
            Utils.loadImage("res/davis_jumping_01.png")));
    private SingleView singleViewJumpingRight = new SingleView(Utils.loadImage("res/davis_jumping_01.png"));
    private SingleView singleViewDefendindRight = new SingleView(Utils.loadImage("res/davis_defend_1.png"));
    private SingleView singleViewDefendindLeft = new SingleView(Utils.flipImage(
            Utils.loadImage("res/davis_defend_1.png")));
    private SingleView singleViewDefendWhenBeingAttackedRight = new SingleView(Utils.loadImage("res/davis_defend_0.png"));
    private SingleView singleViewDefendWhenBeingAttackedLeft = new SingleView(Utils.flipImage(
            Utils.loadImage("res/davis_defend_0.png")));
    private SingleView singleViewStunNormal1 = new SingleView(Utils.loadImage("res/davis_jumping_01.png"));
    private SingleView singleViewStunNormal2 = new SingleView(Utils.loadImage("res/davis_jumping_01.png"));


    private int countTimeJumping = 0;
    private int countTimeAttackAnimation = 0;
    private int countTimeDefendAnimation = 0;
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
                mainCharacter.setAttack(true);
                countTimeAttackAnimation++;
                if(countTimeAttackAnimation < 15){
                    mainCharacter.setAttack(false);
                } else {
                    mainCharacter.setAttack(true);
                    countTimeAttackAnimation = 0;
                }
                break;
            case ATTACKING_HARD:
//                mainCharacter.runRight();
                break;
            case SKILL_SHOOTING:
                if (animationDavisShooting.isAnimationEnd()) {
                    animationDavisShooting.setAnimationEnd(false);
                    skillCharacterControllerArrayList.add(new SkillCharacterController(
                            new CharacterSkill(mainCharacter.getX() + mainCharacter.getHeight(),
                                    mainCharacter.getY() + (mainCharacter.getHeight() - CharacterSkill.SKILL_HEIGHT) / 2,
                                    mainCharacter.getZ())));
                    mainCharacter.setCharacterState(CharacterState.STANDING);
                }
                break;
            case JUMPING:
                handleJumping(CharacterState.JUMPING);
                break;
            case JUMPING_AT_LEFT:
                handleJumping(CharacterState.JUMPING_AT_LEFT);
                break;
            case JUMPING_AT_RIGHT:
                handleJumping(CharacterState.JUMPING_AT_RIGHT);
                break;
            case DEFENDING:
                countTimeDefendAnimation++;
                if (countTimeDefendAnimation > 20){
                    mainCharacter.setCharacterState(CharacterState.STANDING);
                    countTimeDefendAnimation = 0;
                }
                break;
        }
    }

    private void handleJumping(CharacterState characterState){
        countTimeJumping++;
        if(countTimeJumping == 1) {
            mainCharacter.setY0(mainCharacter.getY());
            mainCharacter.setVelocityY(GameConfig.JUMPING_SPEED_Y);
            mainCharacter.setVelocityX(GameConfig.JUMPING_SPEED_X);
        }
        if (countTimeJumping < 43){
            if (characterState == CharacterState.JUMPING) {
                mainCharacter.jump(countTimeJumping * 0.017);
            } else if (characterState == CharacterState.JUMPING_AT_LEFT){
                mainCharacter.jumpAtLeft(countTimeJumping * 0.017);
            } else if (characterState == CharacterState.JUMPING_AT_RIGHT){
                mainCharacter.jumpAtRight(countTimeJumping * 0.017);
            }
        } else {
            countTimeJumping = 0;
            mainCharacter.setCharacterState(CharacterState.STANDING);
        }
    }
    @Override
    public void draw(Graphics g) {
        switch (mainCharacter.getCharacterState()){
            case STANDING:
                resetAnimation();
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
                this.view = animationDavisRunningLeft;
                break;
            case RUNNING_RIGHT:
                this.view = animationDavisRunningRight;
                break;
            case ATTACKING_NORMAL:
                this.view = animationDavisNormalAttack2;
                if (animationDavisNormalAttack2.isAnimationEnd()){
                    animationDavisNormalAttack2.setAnimationEnd(false);
                    mainCharacter.setCharacterState(CharacterState.STANDING);
                }
                break;
            case ATTACKING_HARD:
                this.view = animationDavisNormalAttack0;
                if (animationDavisNormalAttack0.isAnimationEnd()){
                    this.view = animationDavisNormalAttack1;
                    if (animationDavisNormalAttack1.isAnimationEnd()){
                        this.view = animationDavisNormalAttack2;
                        if (animationDavisNormalAttack2.isAnimationEnd()){
                            mainCharacter.setAttack(false);
                            animationDavisNormalAttack0.setAnimationEnd(false);
                            animationDavisNormalAttack1.setAnimationEnd(false);
                            animationDavisNormalAttack2.setAnimationEnd(false);
                            mainCharacter.setCharacterState(CharacterState.STANDING);
                        }
                    }
                }
                break;
            case JUMPING:
                if (mainCharacter.isLeft())
                    this.view = singleViewJumpingLeft;
                else
                    this.view = singleViewJumpingRight;
                break;
            case JUMPING_AT_LEFT:
                this.view = singleViewJumpingLeft;
                break;
            case JUMPING_AT_RIGHT:
                this.view = singleViewJumpingRight;
                break;
            case DEFENDING:
                if(mainCharacter.isLeft())
                    this.view = singleViewDefendindLeft;
                else
                    this.view = singleViewDefendindRight;
                break;
            case SKILL_SHOOTING:
                this.view = animationDavisShooting;
                break;
            default:
                resetAnimation();
                break;
        }

        super.draw(g);
    }

    public void resetAnimation(){
        animationDavisNormalAttack0.setImageCount(0);
        animationDavisNormalAttack1.setImageCount(0);
        animationDavisNormalAttack2.setImageCount(0);
        animationDavisWalkingLeft.setImageCount(0);
        animationDavisWalkingRight.setImageCount(0);
        animationDavisJumping.setImageCount(0);
    }
    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }
}
