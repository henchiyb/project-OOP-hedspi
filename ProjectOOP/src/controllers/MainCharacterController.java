package controllers;

import game.GameConfig;
import game.ResourceMap;
import managers.ControllerManager;
import managers.SceneManager;
import models.CharacterSkill;
import models.CharacterState;
import models.GameObject;
import models.MainCharacter;
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
    private ArrayList<SkillCharacterController> skillCharacterControllerArrayList
            = ControllerManager.getSkillCharacterControllerArrayList();
    private int countTimeStunNormal = 0;
    private int countTimeFall = 0;
    private int healthBarWidth = GameConfig.BAR_WIDTH;
    private int healManaBarWidth = GameConfig.BAR_WIDTH;

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
    private Animation animationDavisNormalAttack = new Animation(ResourceMap.DAVIS_NORMAL_ATTACK_2,
            GameConfig.ATTACKING_FRAME_RATE);
    private Animation animationDavisJumping = new Animation(ResourceMap.DAVIS_JUMPING,
            GameConfig.JUMPING_FRAME_RATE);
    private Animation animationDavisShooting = new Animation(ResourceMap.DAVIS_SHOOTING,
            GameConfig.ATTACKING_FRAME_RATE);
    private Animation animationDavisFalling = new Animation(ResourceMap.DAVIS_FALLING_BEHIND,
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

    @Override
    public void run() {
        super.run();
        healthBarWidth = mainCharacter.getHealth() / mainCharacter.getMaxHealth() * GameConfig.BAR_WIDTH;
        healManaBarWidth = mainCharacter.getMana() / mainCharacter.getMaxMana() * GameConfig.BAR_WIDTH;
        switch (mainCharacter.getCharacterState()) {
            case  SKILL_SHOOTING:
                SceneManager.getInstance().sceneAction(ActionType.DETACH);
                if (animationDavisShooting.isAnimationEnd()) {
                    animationDavisShooting.setAnimationEnd(false);
                    skillCharacterControllerArrayList.add(new SkillCharacterController(
                            new CharacterSkill(mainCharacter.getX() + mainCharacter.getHeight(), mainCharacter.getY(),
                                    mainCharacter.getZ() + (mainCharacter.getHeight() - CharacterSkill.SKILL_HEIGHT) / 2)));
                    mainCharacter.setCharacterState(CharacterState.STANDING);
                    SceneManager.getInstance().sceneAction(ActionType.ATTACH);
                }
                break;
            case STUN_NORMAL_1:
            case STUN_NORMAL_2:
                countTimeStunNormal++;
                SceneManager.getInstance().sceneAction(ActionType.DETACH);
                if (countTimeStunNormal > TIME_STUN) {
                    countTimeStunNormal = 0;
                    SceneManager.getInstance().sceneAction(ActionType.ATTACH);
                }
                break;
            case FALL:
                countTimeFall++;
                if (countTimeFall == 1) {
                    SceneManager.getInstance().sceneAction(ActionType.DETACH);
                }
                if (countTimeFall > TIME_FALL) {
                    countTimeFall = 0;
                    SceneManager.getInstance().sceneAction(ActionType.ATTACH);
                }
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        //Health bar
        g.setColor(Color.RED);
        g.fillRect(20, 80, healthBarWidth, GameConfig.BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(20, 80, healthBarWidth, GameConfig.BAR_HEIGHT);
        //Mana bar
        g.setColor(Color.BLUE);
        g.fillRect(20, 110, healthBarWidth, GameConfig.BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(20, 110, healthBarWidth, GameConfig.BAR_HEIGHT);
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
                this.view = animationDavisNormalAttack;
                if (animationDavisNormalAttack.isAnimationEnd()){
                    animationDavisNormalAttack.setAnimationEnd(false);
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
                this.view = animationDavisShooting;
                break;
            default:
                resetAnimation();
                break;
        }

        super.draw(g);
    }

    public void resetAnimation(){
        animationDavisNormalAttack.resetAnimation(0);
        animationDavisWalkingLeft.resetAnimation(0);
        animationDavisWalkingRight.resetAnimation(0);
        animationDavisJumping.resetAnimation(0);
    }
    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }
}
