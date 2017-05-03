package controllers;

import game.GameConfig;
import game.ResourceMap;
import managers.ControllerManager;
import managers.SceneManager;
import models.*;
import scenes.ActionType;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nhan on 3/7/2017.
 */
public class MainCharacterController extends CharacterController {
    private MainCharacter mainCharacter = (MainCharacter) this.gameObject;
    private ArrayList<SkillCharacterController> skillMainList
            = ControllerManager.getSkillMainList();
    private int countTimeStunNormal = 0;
    private int countTimeFall = 0;
    private int countTimeFallRun = 0;

    private MainCharacterController(GameObject gameObject) {
        super(gameObject);
    }

    public static MainCharacterController mainCharacterController = new MainCharacterController(MainCharacter.mainCharacter);

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
    private Animation animationDavisNormalAttackRight = new Animation(ResourceMap.DAVIS_NORMAL_ATTACK_2,
            GameConfig.ATTACKING_FRAME_RATE);
    private Animation animationDavisNormalAttackLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_NORMAL_ATTACK_2),
            GameConfig.ATTACKING_FRAME_RATE);
    private Animation animationDavisJumping = new Animation(ResourceMap.DAVIS_JUMPING,
            GameConfig.JUMPING_FRAME_RATE);
    private Animation animationDavisShootingRight = new Animation(ResourceMap.DAVIS_SHOOTING,
            GameConfig.ATTACKING_FRAME_RATE);
    private Animation animationDavisShootingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_SHOOTING),
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
    private Animation animationDavisFalling = new Animation("res/davis_falling_behind.png",
            GameConfig.FALLING_FRAME_RATE);
    private SingleView singleViewStunNormal1 = new SingleView(Utils.loadImage("res/davis_stun_normal_1_behind.png"));
    private SingleView singleViewStunNormal2 = new SingleView(Utils.loadImage("res/davis_stun_normal_2_behind.png"));
    private SingleView singleViewFalling = new SingleView(animationDavisFalling
            .getSubImage(animationDavisFalling.getNumberOfFrame()));

    @Override
    public void run() {
        super.run();
        switch (mainCharacter.getCharacterState()) {
            case  SKILL_SHOOTING:
                SceneManager.getInstance().sceneAction(ActionType.DETACH);
                if (animationDavisShootingRight.isAnimationEnd() || animationDavisShootingLeft.isAnimationEnd()) {
                    animationDavisShootingRight.setAnimationEnd(false);
                    animationDavisShootingLeft.setAnimationEnd(false);
                    SkillCharacterController skillController = new SkillCharacterController(
                            new MainSkill(mainCharacter.getX() + mainCharacter.getHeight(),
                                    mainCharacter.getY(),
                                    mainCharacter.getZ(),
                                    GameConfig.GAME_OBJECT_WIDTH,
                                    GameConfig.GAME_OBJECT_HEIGHT));
                    skillController.gameObject.setDrawY(mainCharacter.getZ() +
                            (mainCharacter.getHeight() - CharacterSkill.SKILL_HEIGHT) / 2);
                    skillController.gameObject.setLeft(mainCharacter.isLeft());
                    skillMainList.add(skillController);
                    mainCharacter.setCharacterState(CharacterState.STANDING);
                }
                break;
            case STUN_NORMAL_1:
            case STUN_NORMAL_2:
            case FALL_RIGHT:
            case FALL_LEFT:
                SceneManager.getInstance().sceneAction(ActionType.DETACH);
                break;
            case STANDING:
                SceneManager.getInstance().sceneAction(ActionType.ATTACH);
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        //Health bar
        g.setColor(Color.RED);
        g.fillRect(20, 80, mainCharacter.getHealthBarWidth(), GameConfig.BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(20, 80, GameConfig.BAR_WIDTH, GameConfig.BAR_HEIGHT);

        //Mana bar
        g.setColor(Color.BLUE);
        g.fillRect(20, 110, mainCharacter.getManaBarWidth(), GameConfig.BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(20, 110, GameConfig.BAR_WIDTH, GameConfig.BAR_HEIGHT);
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
                if (mainCharacter.isLeft())
                    this.view = animationDavisNormalAttackLeft;
                else
                    this.view = animationDavisNormalAttackRight;
                if (animationDavisNormalAttackRight.isAnimationEnd() || animationDavisNormalAttackRight.isAnimationEnd()){
                    animationDavisNormalAttackRight.setAnimationEnd(false);
                    animationDavisNormalAttackLeft.setAnimationEnd(false);
                    mainCharacter.setCharacterState(CharacterState.STANDING);
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
                if(mainCharacter.isLeft())
                    this.view = animationDavisShootingLeft;
                else
                    this.view = animationDavisShootingRight;
                break;
            case STUN_NORMAL_1:
                this.view = singleViewStunNormal1;
                break;
            case STUN_NORMAL_2:
                this.view = singleViewStunNormal2;
                break;
            case FALL_LEFT:
                countTimeFall++;
                if (countTimeFall == 1){
                    this.view = animationDavisFalling;
                }
                if (countTimeFall > TIME_FALL){
                    countTimeFall = 0;
                    animationDavisFalling.setAnimationEnd(false);
                }
                if (animationDavisFalling.isAnimationEnd()){
                    this.view = singleViewFalling;
                } else {
                        mainCharacter.walkLeft();
                }
                break;
            case FALL_RIGHT:
                countTimeFall++;
                if (countTimeFall == 1){
                    this.view = animationDavisFalling;
                }
                if (countTimeFall > TIME_FALL){
                    countTimeFall = 0;
                    animationDavisFalling.setAnimationEnd(false);
                }
                if (animationDavisFalling.isAnimationEnd()){
                    this.view = singleViewFalling;
                } else {
                    mainCharacter.walkRight();
                }
                break;
            case DEAD:
                this.view = singleViewFalling;
                break;
            default:
                resetAnimation();
                break;
        }

        super.draw(g);
    }

    public void resetAnimation(){
        animationDavisNormalAttackRight.resetAnimation(0);
        animationDavisNormalAttackLeft.resetAnimation(0);
        animationDavisWalkingLeft.resetAnimation(0);
        animationDavisWalkingRight.resetAnimation(0);
        animationDavisJumping.resetAnimation(0);

    }
    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }
}
