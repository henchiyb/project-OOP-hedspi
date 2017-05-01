package controllers;

import game.GameConfig;
import game.ResourceMap;
import managers.ControllerManager;
import models.*;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nhan on 4/27/2017.
 */
public class EnemyController extends CharacterController {
    private EnemyCharacter enemyCharacter = (EnemyCharacter) this.gameObject;
    private ArrayList<SkillCharacterController> skillCharacterControllerArrayList
            = ControllerManager.getSkillCharacterControllerArrayList();
    private int countTimeFall = 0;
    private EnemyType enemyType;

    public EnemyController(GameObject gameObject, EnemyType enemyType) {
        super(gameObject);
        this.enemyType = enemyType;
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
    private Animation animationDavisNormalAttack = new Animation(ResourceMap.DAVIS_NORMAL_ATTACK_2,
            GameConfig.ATTACKING_FRAME_RATE);
    private Animation animationDavisJumping = new Animation(ResourceMap.DAVIS_JUMPING,
            GameConfig.JUMPING_FRAME_RATE);
    private Animation animationDavisShooting = new Animation(ResourceMap.DAVIS_SHOOTING,
            GameConfig.ATTACKING_FRAME_RATE);
//    private Animation animationDavisFalling = new Animation(ResourceMap.DAVIS_FALLING_BEHIND,
//            GameConfig.FALLING_FRAME_RATE);
private Animation animationDavisFalling = new Animation("res/davis_falling_behind.png",
        GameConfig.FALLING_FRAME_RATE);
    private SingleView singleViewJumpingLeft = new SingleView(Utils.flipImage(
            Utils.loadImage("res/davis_jumping_01.png")));
    private SingleView singleViewJumpingRight = new SingleView(Utils.loadImage("res/davis_jumping_01.png"));
    private SingleView singleViewDefendindRight = new SingleView(Utils.loadImage("res/davis_defend_1.png"));
    private SingleView singleViewDefendindLeft = new SingleView(Utils.flipImage(
            Utils.loadImage("res/davis_defend_1.png")));
    private SingleView singleViewDefendWhenBeingAttackedRight = new SingleView(Utils.loadImage("res/davis_defend_0.png"));
    private SingleView singleViewDefendWhenBeingAttackedLeft = new SingleView(Utils.flipImage(
            Utils.loadImage("res/davis_defend_0.png")));
    private SingleView singleViewStunNormal1 = new SingleView(Utils.loadImage("res/davis_stun_normal_1_behind.png"));
    private SingleView singleViewStunNormal2 = new SingleView(Utils.loadImage("res/davis_stun_normal_2_behind.png"));
    private SingleView singleViewFalling = new SingleView(animationDavisFalling
            .getSubImage(animationDavisFalling.getNumberOfFrame()));

    @Override
    public void run() {
        super.run();
//        if (enemyCharacter.getCharacterState() == CharacterState.SKILL_SHOOTING){
//            if (animationDavisShooting.isAnimationEnd()) {
//                animationDavisShooting.setAnimationEnd(false);
//                skillCharacterControllerArrayList.register(new SkillCharacterController(
//                        new CharacterSkill(enemyCharacter.getX() + enemyCharacter.getHeight(),
//                                enemyCharacter.getY() + (enemyCharacter.getHeight() - CharacterSkill.SKILL_HEIGHT) / 2,
//                                enemyCharacter.getZ())));
//                enemyCharacter.setCharacterState(CharacterState.STANDING);
//            }
//        }
    }

    @Override
    public void draw(Graphics g) {
        switch (enemyCharacter.getCharacterState()){
            case STANDING:
                resetAnimation();
                if (enemyCharacter.isLeft())
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
                if (enemyCharacter.isLeft())
                    this.view = animationDavisWalkingLeft;
                else
                    this.view = animationDavisWalkingRight;
                break;
            case WALKING_UP:
                if (enemyCharacter.isLeft())
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
                    enemyCharacter.setCharacterState(CharacterState.STANDING);
                }
                break;
            case JUMPING:
                if (enemyCharacter.isLeft())
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
                if(enemyCharacter.isLeft())
                    this.view = singleViewDefendindLeft;
                else
                    this.view = singleViewDefendindRight;
                break;
            case SKILL_SHOOTING:
                this.view = animationDavisShooting;
                break;
            case STUN_NORMAL_1:
                this.view = singleViewStunNormal1;
                break;
            case STUN_NORMAL_2:
                this.view = singleViewStunNormal2;
                break;
            case FALL:
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
                    enemyCharacter.walkRight();
                }
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
    public EnemyCharacter getEnemyCharacter() {
        return enemyCharacter;
    }
}
