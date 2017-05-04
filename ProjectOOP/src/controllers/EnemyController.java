package controllers;

import game.GameConfig;
import game.ResourceMap;
import managers.ControllerManager;
import models.*;
import scenes.PlayScene;
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
        private EnemyType enemyType;

        private int countTimeFall = 0;
        private int countAnimationShoot = 0;
        private int countAnimationAttack = 0;

        private int countTimeDeadAnimation = 0;

        private Animation animationEnemyWalkingLeft;
        private Animation animationEnemyWalkingRight;
        private Animation animationEnemyRunningLeft;
        private Animation animationEnemyRunningRight;
        private Animation animationEnemyStandingRight;
        private Animation animationEnemyStandingLeft;
        private Animation animationEnemyNormalAttackRight;
        private Animation animationEnemyNormalAttackLeft;
        private Animation animationEnemyJumping;
        private Animation animationEnemyShootingRight;
        private Animation animationEnemyShootingLeft;
        private SingleView singleViewJumpingLeft;
        private SingleView singleViewJumpingRight;
        private SingleView singleViewDefendindRight;
        private SingleView singleViewDefendindLeft;
        private Animation animationEnemyFalling;
        private SingleView singleViewStunNormal1;
        private SingleView singleViewStunNormal2;
        private SingleView singleViewFalling;

        public EnemyController(GameObject gameObject, EnemyType enemyType) {
            super(gameObject);
            this.enemyType = enemyType;
            initView();
        }

        private void initView() {
            if (enemyType == EnemyType.SHOOT) {
                animationEnemyWalkingLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY1_WALKING),
                        GameConfig.WALKING_FRAME_RATE);
                animationEnemyWalkingRight = new Animation(Utils.flipImages(Utils.flipImages(ResourceMap.ENEMY1_WALKING)),
                        GameConfig.WALKING_FRAME_RATE);
                animationEnemyRunningLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY1_RUNNING),
                        GameConfig.RUNNING_FRAME_RATE);
                animationEnemyRunningRight = new Animation(ResourceMap.ENEMY1_RUNNING,
                        GameConfig.RUNNING_FRAME_RATE);
                animationEnemyStandingRight = new Animation(ResourceMap.ENEMY1_STANDING,
                        GameConfig.STANDING_FRAME_RATE);
                animationEnemyStandingLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY1_STANDING),
                        GameConfig.STANDING_FRAME_RATE);
                animationEnemyNormalAttackRight = new Animation(ResourceMap.ENEMY1_NORMAL_ATTACK,
                        GameConfig.ATTACKING_FRAME_RATE);
                animationEnemyNormalAttackLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY1_NORMAL_ATTACK),
                        GameConfig.ATTACKING_FRAME_RATE);
                animationEnemyJumping = new Animation(ResourceMap.ENEMY1_JUMPING,
                        GameConfig.JUMPING_FRAME_RATE);
                animationEnemyShootingRight = new Animation(ResourceMap.ENEMY1_SHOOTING,
                        GameConfig.ATTACKING_FRAME_RATE);
                animationEnemyShootingLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY1_SHOOTING),
                        GameConfig.ATTACKING_FRAME_RATE);
                animationEnemyFalling = new Animation("res/enemy/enemy1/enemy1_falling_behind.png", GameConfig.FALLING_FRAME_RATE);
                singleViewJumpingLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/enemy/enemy1/enemy1_jumping (2).png")));
                singleViewJumpingRight = new SingleView(Utils.loadImage("res/enemy/enemy1/enemy1_jumping (2).png"));
                singleViewDefendindRight = new SingleView(Utils.loadImage("res/enemy/enemy1/enemy1_defend.png"));
                singleViewDefendindLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/enemy/enemy1/enemy1_defend.png")));
                singleViewStunNormal1 = new SingleView(Utils.loadImage("res/enemy/enemy1/enemy1_stun_normal_1_behind (1).png"));
                singleViewStunNormal2 = new SingleView(Utils.loadImage("res/enemy/enemy1/enemy1_stun_normal_1_behind (2).png"));
                singleViewFalling = new SingleView(animationEnemyFalling.getSubImage(animationEnemyFalling.getNumberOfFrame()));
            } else if (enemyType == EnemyType.FIGHT) {
                animationEnemyWalkingLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY2_WALKING),
                        GameConfig.WALKING_FRAME_RATE);
                animationEnemyWalkingRight = new Animation(Utils.flipImages(Utils.flipImages(ResourceMap.ENEMY2_WALKING)),
                        GameConfig.WALKING_FRAME_RATE);
                animationEnemyRunningLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY2_RUNNING),
                        GameConfig.RUNNING_FRAME_RATE);
                animationEnemyRunningRight = new Animation(ResourceMap.ENEMY2_RUNNING,
                        GameConfig.RUNNING_FRAME_RATE);
                animationEnemyStandingRight = new Animation(ResourceMap.ENEMY2_STANDING,
                        GameConfig.STANDING_FRAME_RATE);
                animationEnemyStandingLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY2_STANDING),
                        GameConfig.STANDING_FRAME_RATE);
                animationEnemyNormalAttackRight = new Animation(ResourceMap.ENEMY2_NORMAL_ATTACK,
                        GameConfig.ATTACKING_FRAME_RATE);
                animationEnemyNormalAttackLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY2_NORMAL_ATTACK),
                        GameConfig.ATTACKING_FRAME_RATE);
                animationEnemyJumping = new Animation(ResourceMap.ENEMY2_JUMPING,
                        GameConfig.JUMPING_FRAME_RATE);
                animationEnemyShootingRight = new Animation(ResourceMap.ENEMY2_SHOOTING,
                        GameConfig.ATTACKING_FRAME_RATE);
                animationEnemyShootingLeft = new Animation(Utils.flipImages(ResourceMap.ENEMY2_SHOOTING),
                        GameConfig.ATTACKING_FRAME_RATE);
                animationEnemyFalling = new Animation("res/enemy/enemy2/enemy2_falling_behind.png", GameConfig.FALLING_FRAME_RATE);
                singleViewJumpingLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/enemy/enemy2/enemy2_jumping (3).png")));
                singleViewJumpingRight = new SingleView(Utils.loadImage("res/enemy/enemy2/enemy2_jumping (3).png"));
                singleViewDefendindRight = new SingleView(Utils.loadImage("res/enemy/enemy2/enemy2_defend.png"));
                singleViewDefendindLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/davis_defend_1.png")));
                singleViewStunNormal1 = new SingleView(Utils.loadImage("res/enemy/enemy2/enemy2_stun_normal_1_behind (1).png"));
                singleViewStunNormal2 = new SingleView(Utils.loadImage("res/enemy/enemy2/enemy2_stun_normal_1_behind (2).png"));
                singleViewFalling = new SingleView(animationEnemyFalling.getSubImage(animationEnemyFalling.getNumberOfFrame()));
            }
        }

        @Override
        public void run() {
            super.run();
            if (enemyCharacter.getCharacterState() == CharacterState.SKILL_SHOOTING) {
                if (animationEnemyShootingRight.isAnimationEnd() || animationEnemyShootingLeft.isAnimationEnd()) {
                    animationEnemyShootingRight.setAnimationEnd(false);
                    animationEnemyShootingLeft.setAnimationEnd(false);
                    SkillCharacterController skillController = new SkillCharacterController(new EnemySkill(
                            enemyCharacter.getX() + enemyCharacter.getHeight(),
                            enemyCharacter.getY(),
                            enemyCharacter.getZ(),
                            enemyCharacter.getDrawX()));
                    skillController.gameObject.setLeft(enemyCharacter.isLeft());
                    skillController.gameObject.setDrawY(mainCharacter.getZ() +
                            (mainCharacter.getHeight() - CharacterSkill.SKILL_HEIGHT) / 2);
                    ControllerManager.getSkillList().add(skillController);
                    enemyCharacter.setCharacterState(CharacterState.STANDING);
                }
            } else if (enemyCharacter.getCharacterState() == CharacterState.ATTACKING_NORMAL) {
                if (animationEnemyNormalAttackRight.isAnimationEnd() || animationEnemyNormalAttackLeft.isAnimationEnd()) {
                    animationEnemyNormalAttackRight.setAnimationEnd(false);
                    animationEnemyNormalAttackLeft.setAnimationEnd(false);
                    enemyCharacter.setCharacterState(CharacterState.STANDING);
                }
            } else if (enemyCharacter.getCharacterState() == CharacterState.DEAD) {
                countTimeDeadAnimation++;
                if (countTimeDeadAnimation == 1) {
                    mainCharacter.gainExp(5);
                    System.out.println("gain 5 EXP");
                }

            }

            if ((enemyCharacter.getX() - mainCharacter.getX()) > 0) {
                enemyCharacter.setLeft(true);
            } else {
                enemyCharacter.setLeft(false);
            }

            if (enemyCharacter.getCharacterState() != CharacterState.DEAD &&
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
                } else {
                    enemyCharacter.setCharacterState(CharacterState.STANDING);
                }
            }
        }

        @Override
        public void draw(Graphics g) {
            switch (enemyCharacter.getCharacterState()) {
                case STANDING:
                    resetAnimation();
                    if (enemyCharacter.isLeft())
                        this.view = animationEnemyStandingLeft;
                    else
                        this.view = animationEnemyStandingRight;
                    break;
                case WALKING_LEFT:
                    this.view = animationEnemyWalkingLeft;
                    break;
                case WALKING_RIGHT:
                    this.view = animationEnemyWalkingRight;
                    break;
                case WALKING_DOWN:
                    if (enemyCharacter.isLeft())
                        this.view = animationEnemyWalkingLeft;
                    else
                        this.view = animationEnemyWalkingRight;
                    break;
                case WALKING_UP:
                    if (enemyCharacter.isLeft())
                        this.view = animationEnemyWalkingLeft;
                    else
                        this.view = animationEnemyWalkingRight;
                    break;
                case RUNNING_LEFT:
                    this.view = animationEnemyRunningLeft;
                    break;
                case RUNNING_RIGHT:
                    this.view = animationEnemyRunningRight;
                    break;
                case ATTACKING_NORMAL:
                    if (enemyCharacter.isLeft())
                        this.view = animationEnemyNormalAttackLeft;
                    else
                        this.view = animationEnemyNormalAttackRight;
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
                    if (enemyCharacter.isLeft())
                        this.view = singleViewDefendindLeft;
                    else
                        this.view = singleViewDefendindRight;
                    break;
                case SKILL_SHOOTING:
                    if (enemyCharacter.isLeft())
                        this.view = animationEnemyShootingLeft;
                    else
                        this.view = animationEnemyShootingRight;
                    break;
                case STUN_NORMAL_1:
                    this.view = singleViewStunNormal1;
                    break;
                case STUN_NORMAL_2:
                    this.view = singleViewStunNormal2;
                    break;
                case FALL_LEFT:
                    countTimeFall++;
                    if (countTimeFall == 1) {
                        this.view = animationEnemyFalling;
                    }
                    if (countTimeFall > TIME_FALL) {
                        countTimeFall = 0;
                        animationEnemyFalling.setAnimationEnd(false);
                    }
                    if (animationEnemyFalling.isAnimationEnd()) {
                        this.view = singleViewFalling;
                    } else {
                        enemyCharacter.walkLeft();
                    }
                    break;
                case FALL_RIGHT:
                    countTimeFall++;
                    if (countTimeFall == 1) {
                        this.view = animationEnemyFalling;
                    }
                    if (countTimeFall > TIME_FALL) {
                        countTimeFall = 0;
                        animationEnemyFalling.setAnimationEnd(false);
                    }
                    if (animationEnemyFalling.isAnimationEnd()) {
                        this.view = singleViewFalling;
                    } else {
                        enemyCharacter.walkRight();
                    }
                    break;
                case DEAD:
                    this.view = singleViewFalling;
                    break;
            }
            super.draw(g);
        }

        public void resetAnimation() {
            animationEnemyNormalAttackRight.resetAnimation(0);
            animationEnemyWalkingLeft.resetAnimation(0);
            animationEnemyWalkingRight.resetAnimation(0);
            animationEnemyJumping.resetAnimation(0);
        }
    }