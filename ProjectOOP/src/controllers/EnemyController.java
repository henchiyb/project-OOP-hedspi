package controllers;

import game.GameConfig;
import game.ResourceMap;
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
    private MainCharacter mainCharacter = MainCharacter.mainCharacter;
    private int countTimeFall = 0;
    private EnemyType enemyType;
    private int countAnimationShoot = 0;
    private int countAnimationAttack = 0;
    private ArrayList<SkillCharacterController> listEnemyBullet;

    public EnemyController(GameObject gameObject, EnemyType enemyType) {
        super(gameObject);
        this.enemyType = enemyType;
        listEnemyBullet = new ArrayList<>();
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
        if (enemyCharacter.getCharacterState() == CharacterState.SKILL_SHOOTING){
            if (animationDavisShootingRight.isAnimationEnd() || animationDavisShootingLeft.isAnimationEnd()) {
                animationDavisShootingRight.setAnimationEnd(false);
                animationDavisShootingLeft.setAnimationEnd(false);
                SkillCharacterController skillController = new SkillCharacterController(new EnemySkill(
                        enemyCharacter.getX() + enemyCharacter.getHeight(),
                        enemyCharacter.getY(),
                        enemyCharacter.getZ()));
                skillController.gameObject.setLeft(enemyCharacter.isLeft());
                skillController.gameObject.setDrawY(mainCharacter.getZ() +
                        (mainCharacter.getHeight() - CharacterSkill.SKILL_HEIGHT) / 2);
                listEnemyBullet.add(skillController);
                enemyCharacter.setCharacterState(CharacterState.STANDING);
            }
        } else if (enemyCharacter.getCharacterState() == CharacterState.ATTACKING_NORMAL) {
            if (animationDavisNormalAttackRight.isAnimationEnd() || animationDavisNormalAttackLeft.isAnimationEnd()) {
                animationDavisNormalAttackRight.setAnimationEnd(false);
                animationDavisNormalAttackLeft.setAnimationEnd(false);
                enemyCharacter.setCharacterState(CharacterState.STANDING);
            }
        }

        if ((enemyCharacter.getX() - mainCharacter.getX()) > 0) {
            enemyCharacter.setLeft(true);
        } else {
            enemyCharacter.setLeft(false);
        }

        if(enemyCharacter.getCharacterState() != CharacterState.DEAD &&
                enemyCharacter.getCharacterState() != CharacterState.STUN_NORMAL_1 &&
                enemyCharacter.getCharacterState() != CharacterState.STUN_NORMAL_2 &&
                enemyCharacter.getCharacterState() != CharacterState.FALL_LEFT &&
                enemyCharacter.getCharacterState() != CharacterState.FALL_RIGHT) {
            if (Math.abs(enemyCharacter.getZ() - mainCharacter.getZ()) < 300
                    && Math.abs(enemyCharacter.getX() - mainCharacter.getX()) < 300
                    && !mainCharacter.isInvulnerable()
                    ) {
                if (enemyType == EnemyType.SHOOT) {
                    if ((enemyCharacter.getZ() - mainCharacter.getZ()) > 5) {
                        enemyCharacter.setCharacterState(CharacterState.WALKING_UP);
                    } else if ((enemyCharacter.getZ() - mainCharacter.getZ()) < -5) {
                        enemyCharacter.setCharacterState(CharacterState.WALKING_DOWN);
                    } else if ((enemyCharacter.getX() - mainCharacter.getX()) > 200) {
                        enemyCharacter.setCharacterState(CharacterState.WALKING_LEFT);
                    } else if ((enemyCharacter.getX() - mainCharacter.getX()) < -200) {
                        enemyCharacter.setCharacterState(CharacterState.WALKING_RIGHT);
                    } else {
                        countAnimationShoot++;
                        if (countAnimationShoot == 1 &&
                                enemyCharacter.getCharacterState() != CharacterState.STUN_NORMAL_1 &&
                                enemyCharacter.getCharacterState() != CharacterState.STUN_NORMAL_2 &&
                                enemyCharacter.getCharacterState() != CharacterState.FALL_LEFT &&
                                enemyCharacter.getCharacterState() != CharacterState.FALL_RIGHT) {
                            enemyCharacter.setCharacterState(CharacterState.SKILL_SHOOTING);
                        }
                        if (countAnimationShoot == GameConfig.ENEMY_SHOOTING_DURATION) {
                            countAnimationShoot = 0;
                        }
                    }
                } else if (enemyType == EnemyType.FIGHT) {
                    if ((enemyCharacter.getZ() - mainCharacter.getZ()) > 5) {
                        enemyCharacter.setCharacterState(CharacterState.WALKING_UP);
                    } else if ((enemyCharacter.getZ() - mainCharacter.getZ()) < -5) {
                        enemyCharacter.setCharacterState(CharacterState.WALKING_DOWN);
                    } else if ((enemyCharacter.getX() - mainCharacter.getX()) > 20) {
                        enemyCharacter.setCharacterState(CharacterState.WALKING_LEFT);
                    } else if ((enemyCharacter.getX() - mainCharacter.getX()) < -20) {
                        enemyCharacter.setCharacterState(CharacterState.WALKING_RIGHT);
                    } else {
                        countAnimationAttack++;
                        if (countAnimationAttack == 1 &&
                                enemyCharacter.getCharacterState() != CharacterState.STUN_NORMAL_1 &&
                                enemyCharacter.getCharacterState() != CharacterState.STUN_NORMAL_2 &&
                                enemyCharacter.getCharacterState() != CharacterState.FALL_LEFT &&
                                enemyCharacter.getCharacterState() != CharacterState.FALL_RIGHT) {
                            enemyCharacter.setCharacterState(CharacterState.ATTACKING_NORMAL);
                        }
                        if (countAnimationAttack == 20) {
                            countAnimationAttack = 0;
                        }
                    }
                }
            } else if (enemyCharacter.getCharacterState() == CharacterState.DEAD){

            } else if (enemyCharacter.getCharacterState() != CharacterState.STUN_NORMAL_1 ||
                    enemyCharacter.getCharacterState() != CharacterState.STUN_NORMAL_2 ||
                    enemyCharacter.getCharacterState() != CharacterState.FALL_LEFT ||
                    enemyCharacter.getCharacterState() != CharacterState.FALL_RIGHT) {
                enemyCharacter.setCharacterState(CharacterState.STANDING);
            }
        }
        for (int i = 0; i < listEnemyBullet.size(); i++) {
            listEnemyBullet.get(i).run();
            if(listEnemyBullet.get(i).gameObject.getX() < 0 || listEnemyBullet.get(i).gameObject.getX() > 600){
                listEnemyBullet.get(i).gameObject.setAlive(false);
            }
        }
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
                if (enemyCharacter.isLeft())
                    this.view = animationDavisNormalAttackLeft;
                else
                    this.view = animationDavisNormalAttackRight;
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
                if(enemyCharacter.isLeft())
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
                    enemyCharacter.walkLeft();
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
                    enemyCharacter.walkRight();
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
        for (int i = 0; i < listEnemyBullet.size(); i++) {
            listEnemyBullet.get(i).draw(g);
        }
    }

    public void resetAnimation(){
        animationDavisNormalAttackRight.resetAnimation(0);
        animationDavisWalkingLeft.resetAnimation(0);
        animationDavisWalkingRight.resetAnimation(0);
        animationDavisJumping.resetAnimation(0);
    }
}
