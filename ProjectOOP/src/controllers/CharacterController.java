package controllers;

import game.GameConfig;
import managers.SceneManager;
import models.Character;
import models.CharacterState;
import models.GameObject;
import scenes.ActionType;

/**
 * Created by Nhan on 4/27/2017.
 */
public abstract class CharacterController extends SingleController {
    public static final int TIME_FALL = 120;
    public static final int TIME_STUN = 90;
    public static final int TIME_DEFEND = 20;
    public static final int TIME_ATTACK = 15;
    public static final int TIME_JUMP = 43;

    private Character character = (Character) this.gameObject;
    private int countTimeJumping = 0;
    private int countTimeAttackAnimation = 0;
    private int countTimeDefendAnimation = 0;
    private int countTimeStunNormal = 0;
    private int countTimeFall = 0;

    public CharacterController(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void run() {
        switch (this.character.getCharacterState()) {
            case STANDING:
                break;
            case WALKING_LEFT:
                character.walkLeft();
                break;
            case WALKING_RIGHT:
                character.walkRight();
                break;
            case WALKING_DOWN:
                character.walkDown();
                break;
            case WALKING_UP:
                character.walkUp();
                break;
            case RUNNING_LEFT:
                character.runLeft();
                break;
            case RUNNING_RIGHT:
                character.runRight();
                break;
            case ATTACKING_NORMAL:
                character.setAttack(true);
                countTimeAttackAnimation++;
                if(countTimeAttackAnimation < TIME_ATTACK){
                    character.setAttack(false);
                } else {
                    character.setAttack(true);
                    countTimeAttackAnimation = 0;
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
//                SceneManager.getInstance().sceneAction(ActionType.DETACH, false);
                if (countTimeDefendAnimation > TIME_DEFEND){
                    character.setCharacterState(CharacterState.STANDING);
                    countTimeDefendAnimation = 0;
//                    SceneManager.getInstance().sceneAction(ActionType.ATTACH, false);
                }
                break;
            case STUN_NORMAL_1:
            case STUN_NORMAL_2:
                countTimeStunNormal++;
                if (countTimeStunNormal > TIME_STUN){
                    countTimeStunNormal = 0;
                    character.setCharacterState(CharacterState.STANDING);
                }
                break;
            case FALL:
                countTimeFall++;
                if(countTimeFall == 1){
                    character.setInvulnerable(true);
                }
                if (countTimeFall > TIME_FALL){
                    countTimeFall = 0;
                    character.setInvulnerable(false);
                    character.setCharacterState(CharacterState.STANDING);
                }
                break;
        }
    }
    private void handleJumping(CharacterState characterState){
        countTimeJumping++;
        if(countTimeJumping == 1) {
            character.setY0(character.getZ());
            character.setVelocityY(GameConfig.JUMPING_SPEED_Y);
            character.setVelocityX(GameConfig.JUMPING_SPEED_X);
            SceneManager.getInstance().sceneAction(ActionType.DETACH);
        }
        if (countTimeJumping < TIME_JUMP){
            if (characterState == CharacterState.JUMPING) {
                character.jump(countTimeJumping * 0.017);
            } else if (characterState == CharacterState.JUMPING_AT_LEFT){
                character.jumpAtLeft(countTimeJumping * 0.017);
            } else if (characterState == CharacterState.JUMPING_AT_RIGHT){
                character.jumpAtRight(countTimeJumping * 0.017);
            }
        } else {
            countTimeJumping = 0;
            SceneManager.getInstance().sceneAction(ActionType.ATTACH);
            character.setCharacterState(CharacterState.STANDING);
        }
    }
}
