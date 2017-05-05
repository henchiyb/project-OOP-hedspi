package controllers;

import game.GameConfig;
import game.ResourceMap;
import managers.ControllerManager;
import managers.SceneManager;
import models.*;
import scenes.ActionType;
import scenes.PlayScene;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nhan on 3/7/2017.
 */
public class MainCharacterController extends CharacterController {
    private static final int BAR_X = 135;
    private static final int STRING_X = 110;
    private MainCharacter mainCharacter = (MainCharacter) this.gameObject;
    private int countTimeFall = 0;

    private Animation animationDavisWalkingLeft;
    private Animation animationDavisWalkingRight;
    private Animation animationDavisRunningLeft;
    private Animation animationDavisRunningRight;
    private Animation animationDavisStandingRight;
    private Animation animationDavisStandingLeft;
    private Animation animationDavisNormalAttackRight;
    private Animation animationDavisNormalAttackLeft;
    private Animation animationDavisJumping;
    private Animation animationDavisShootingRight;
    private Animation animationDavisShootingLeft;
    private SingleView singleViewJumpingLeft;
    private SingleView singleViewJumpingRight;
    private SingleView singleViewDefendindRight;
    private SingleView singleViewDefendindLeft;
    private Animation animationDavisFalling;
    private SingleView singleViewStunNormal1;
    private SingleView singleViewStunNormal2;
    private SingleView singleViewFalling;
    private Animation animationDavisElevatingStandingRight;
    private Animation animationDavisElevatingStandingLeft;
    private Animation animationDavisThrowingRight;
    private Animation animationDavisThrowingLeft;
    private Animation animationDavisElevatingWalkingRight;
    private Animation animationDavisElevatingWalkingLeft;
    private Animation animationDavisElevatingRunningRight;
    private Animation animationDavisElevatingRunningLeft;

    private int countTimeRegenMana = 0;

    public MainCharacterController(GameObject gameObject) {
        super(gameObject);
        initView();
    }

    private void initView(){
        if (PlayScene.mainType == 1) {
            animationDavisWalkingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_WALKING),
                    GameConfig.WALKING_FRAME_RATE);
            animationDavisWalkingRight = new Animation(Utils.flipImages(Utils.flipImages(ResourceMap.DAVIS_WALKING)),
                    GameConfig.WALKING_FRAME_RATE);
            animationDavisRunningLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_RUNNING),
                    GameConfig.RUNNING_FRAME_RATE);
            animationDavisRunningRight = new Animation(ResourceMap.DAVIS_RUNNING,
                    GameConfig.RUNNING_FRAME_RATE);
            animationDavisStandingRight = new Animation(ResourceMap.DAVIS_STANDING,
                    GameConfig.STANDING_FRAME_RATE);
            animationDavisStandingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_STANDING),
                    GameConfig.STANDING_FRAME_RATE);
            animationDavisNormalAttackRight = new Animation(ResourceMap.DAVIS_NORMAL_ATTACK_2,
                    GameConfig.ATTACKING_FRAME_RATE);
            animationDavisNormalAttackLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_NORMAL_ATTACK_2),
                    GameConfig.ATTACKING_FRAME_RATE);
            animationDavisJumping = new Animation(ResourceMap.DAVIS_JUMPING,
                    GameConfig.JUMPING_FRAME_RATE);
            animationDavisShootingRight = new Animation(ResourceMap.DAVIS_SHOOTING,
                    GameConfig.ATTACKING_FRAME_RATE);
            animationDavisShootingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_SHOOTING),
                    GameConfig.ATTACKING_FRAME_RATE);
            animationDavisFalling = new Animation("res/davis_falling_behind.png", GameConfig.FALLING_FRAME_RATE);
            singleViewJumpingLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/davis_jumping_01.png")));
            singleViewJumpingRight = new SingleView(Utils.loadImage("res/davis_jumping_01.png"));
            singleViewDefendindRight = new SingleView(Utils.loadImage("res/davis_defend_1.png"));
            singleViewDefendindLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/davis_defend_1.png")));
            singleViewStunNormal1 = new SingleView(Utils.loadImage("res/davis_stun_normal_1_behind.png"));
            singleViewStunNormal2 = new SingleView(Utils.loadImage("res/davis_stun_normal_2_behind.png"));
            singleViewFalling = new SingleView(animationDavisFalling.getSubImage(animationDavisFalling.getNumberOfFrame()));
            animationDavisElevatingStandingRight = new Animation(ResourceMap.DAVIS_ELEVATING,
                    GameConfig.STANDING_FRAME_RATE);
            animationDavisElevatingStandingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_ELEVATING),
                    GameConfig.STANDING_FRAME_RATE);
            animationDavisThrowingRight = new Animation(ResourceMap.DAVIS_THROWING, GameConfig.STANDING_FRAME_RATE);
            animationDavisThrowingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_THROWING),
                    GameConfig.STANDING_FRAME_RATE);
            animationDavisElevatingWalkingRight = new Animation(ResourceMap.DAVIS_ELEVATING_WALKING,
                    GameConfig.RUNNING_FRAME_RATE);
            animationDavisElevatingWalkingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_ELEVATING_WALKING),
                    GameConfig.RUNNING_FRAME_RATE);
            animationDavisElevatingRunningRight = new Animation(ResourceMap.DAVIS_ELEVATING_WALKING,
                    GameConfig.WALKING_FRAME_RATE);
            animationDavisElevatingRunningLeft = new Animation(ResourceMap.DAVIS_ELEVATING_WALKING,
                    GameConfig.WALKING_FRAME_RATE);
            mainCharacter.setDamage(10);
            mainCharacter.setHealth(100);
            mainCharacter.setMaxHealth(100);
            mainCharacter.setMana(50);
            mainCharacter.setMaxMana(50);
            System.out.println("player 1");
        } else {
            animationDavisWalkingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_WALKING),
                    GameConfig.WALKING_FRAME_RATE);
            animationDavisWalkingRight = new Animation(Utils.flipImages(Utils.flipImages(ResourceMap.DAVIS_WALKING)),
                    GameConfig.WALKING_FRAME_RATE);
            animationDavisRunningLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_RUNNING),
                    GameConfig.RUNNING_FRAME_RATE);
            animationDavisRunningRight = new Animation(ResourceMap.DAVIS_RUNNING,
                    GameConfig.RUNNING_FRAME_RATE);
            animationDavisStandingRight = new Animation(ResourceMap.DAVIS_STANDING,
                    GameConfig.STANDING_FRAME_RATE);
            animationDavisStandingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_STANDING),
                    GameConfig.STANDING_FRAME_RATE);
            animationDavisNormalAttackRight = new Animation(ResourceMap.DAVIS_NORMAL_ATTACK_2,
                    GameConfig.ATTACKING_FRAME_RATE);
            animationDavisNormalAttackLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_NORMAL_ATTACK_2),
                    GameConfig.ATTACKING_FRAME_RATE);
            animationDavisJumping = new Animation(ResourceMap.DAVIS_JUMPING,
                    GameConfig.JUMPING_FRAME_RATE);
            animationDavisShootingRight = new Animation(ResourceMap.DAVIS_SHOOTING,
                    GameConfig.ATTACKING_FRAME_RATE);
            animationDavisShootingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_SHOOTING),
                    GameConfig.ATTACKING_FRAME_RATE);
            animationDavisFalling = new Animation("res/davis_falling_behind.png", GameConfig.FALLING_FRAME_RATE);
            singleViewJumpingLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/davis_jumping_01.png")));
            singleViewJumpingRight = new SingleView(Utils.loadImage("res/davis_jumping_01.png"));
            singleViewDefendindRight = new SingleView(Utils.loadImage("res/davis_defend_1.png"));
            singleViewDefendindLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/davis_defend_1.png")));
            singleViewStunNormal1 = new SingleView(Utils.loadImage("res/davis_stun_normal_1_behind.png"));
            singleViewStunNormal2 = new SingleView(Utils.loadImage("res/davis_stun_normal_2_behind.png"));
            singleViewFalling = new SingleView(animationDavisFalling.getSubImage(animationDavisFalling.getNumberOfFrame()));
            mainCharacter.setDamage(15);
            mainCharacter.setHealth(80);
            mainCharacter.setMaxHealth(80);
            mainCharacter.setMana(60);
            mainCharacter.setMaxMana(60);
            System.out.println("player 2");
        }
    }
    @Override
    public void run() {
        super.run();
        System.out.println("X : " + mainCharacter.getX() + " Z: " + mainCharacter.getZ());
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
                                    mainCharacter.getDrawX(),
                                    GameConfig.GAME_OBJECT_WIDTH,
                                    GameConfig.GAME_OBJECT_HEIGHT));
                    skillController.gameObject.setDrawY(mainCharacter.getZ() +
                            (mainCharacter.getHeight() - CharacterSkill.SKILL_HEIGHT) / 2);
                    skillController.gameObject.setLeft(mainCharacter.isLeft());
                    ControllerManager.getSkillList().add(skillController);
                    mainCharacter.setManaChange(-10);
                    mainCharacter.updateManaBarWidth();
                    mainCharacter.setCharacterState(CharacterState.STANDING);
                }
                break;
            case WALKING_LEFT:
                if (mainCharacter.getDrawX() <= 0) {
                    PlayScene.bgScene1.setBackgroundState(CharacterState.WALKING_LEFT);
                }
                break;
            case WALKING_RIGHT:
                if (mainCharacter.getDrawX() >= GameConfig.SCREEN_WIDTH - GameConfig.GAME_OBJECT_WIDTH) {
                    PlayScene.bgScene1.setBackgroundState(CharacterState.WALKING_RIGHT);
                }
                break;
            case RUNNING_LEFT:
                if (mainCharacter.getDrawX() <= 0) {
                    PlayScene.bgScene1.setBackgroundState(CharacterState.RUNNING_LEFT);
                }
                break;
            case RUNNING_RIGHT:
                if (mainCharacter.getDrawX() >= GameConfig.SCREEN_WIDTH - GameConfig.GAME_OBJECT_WIDTH) {
                    PlayScene.bgScene1.setBackgroundState(CharacterState.RUNNING_RIGHT);
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
            case ELEVATING_WALKING_LEFT:
                mainCharacter.walkLeft();
                break;
            case ELEVATING_WALKING_RIGHT:
                mainCharacter.walkRight();
                break;
            case ELEVATING_WALKING_UP:
                mainCharacter.walkUp();
                break;
            case ELEVATING_WALKING_DOWN:
                mainCharacter.walkDown();
                break;
            case ELEVATING_RUNNING_LEFT:
                mainCharacter.runLeft();
                break;
            case ELEVATING_RUNNING_RIGHT:
                mainCharacter.runRight();
                break;
            case ELEVATING:
                break;
        }
        if (mainCharacter.getCharacterState() != CharacterState.WALKING_LEFT &&
                mainCharacter.getCharacterState() != CharacterState.WALKING_RIGHT &&
                mainCharacter.getCharacterState()  != CharacterState.RUNNING_LEFT &&
                mainCharacter.getCharacterState()  != CharacterState.RUNNING_RIGHT){
            PlayScene.bgScene1.setBackgroundState(CharacterState.STANDING);
        }
        if(mainCharacter.getMainExp() == GameConfig.MAX_EXP){
            mainCharacter.setMainExp(0);
            mainCharacter.levelUp();
        }

        countTimeRegenMana++;
        if(countTimeRegenMana > GameConfig.REGEN_MANA_DURATION) {
            countTimeRegenMana = 0;
            if (mainCharacter.getMana() < mainCharacter.getMaxMana()) {
                mainCharacter.setManaChange(1);
                mainCharacter.updateManaBarWidth();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        //Health bar
        g.setColor(Color.BLACK);
        g.drawString("HP", STRING_X, 50);
        g.setColor(Color.RED);
        g.fillRect(BAR_X, 40, mainCharacter.getHealthBarWidth(), GameConfig.BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(BAR_X, 40, GameConfig.BAR_WIDTH, GameConfig.BAR_HEIGHT);

        //Mana bar
        g.drawString("MP", STRING_X, 75);
        g.setColor(Color.BLUE);
        g.fillRect(BAR_X, 65, mainCharacter.getManaBarWidth(), GameConfig.BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(BAR_X, 65, GameConfig.BAR_WIDTH, GameConfig.BAR_HEIGHT);

        //EXP bar
        g.drawString("EXP", STRING_X, 95);
        g.setColor(Color.CYAN);
        g.fillRect(BAR_X, 90, mainCharacter.getExpBarWidth(), GameConfig.EXP_BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(BAR_X, 90, GameConfig.BAR_WIDTH, GameConfig.EXP_BAR_HEIGHT);
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
            case ELEVATING:
                if (mainCharacter.isLeft())
                    this.view = animationDavisElevatingStandingLeft;
                else
                    this.view = animationDavisElevatingStandingRight;
                break;
            case THROWING:
                if (mainCharacter.isLeft())
                    this.view = animationDavisThrowingLeft;
                else
                    this.view = animationDavisThrowingRight;
                break;
            case ELEVATING_WALKING_LEFT:
                this.view = animationDavisElevatingWalkingLeft;
                break;
            case ELEVATING_WALKING_RIGHT:
                this.view = animationDavisElevatingWalkingRight;
                break;
            case ELEVATING_WALKING_DOWN:
                if (mainCharacter.isLeft())
                    this.view = animationDavisElevatingWalkingLeft;
                else
                    this.view = animationDavisElevatingWalkingRight;
                break;
            case ELEVATING_WALKING_UP:
                if (mainCharacter.isLeft())
                    this.view = animationDavisElevatingWalkingLeft;
                else
                    this.view = animationDavisElevatingWalkingRight;
                break;
            case ELEVATING_RUNNING_LEFT:
                this.view = animationDavisElevatingRunningLeft;
                break;
            case ELEVATING_RUNNING_RIGHT:
                this.view = animationDavisElevatingRunningRight;
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
}
