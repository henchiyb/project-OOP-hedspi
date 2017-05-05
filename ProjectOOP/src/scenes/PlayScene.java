package scenes;

import controllers.*;
import game.GameConfig;
import managers.ControllerManager;
import managers.EnemyManager;
import managers.ItemManager;
import managers.SceneManager;
import models.*;
import utils.Utils;
import views.SingleView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Created by Nhan on 3/7/2017.
 */
public class PlayScene extends GameScene{
    private ItemManager itemManager;
    private Stack<Integer> stackControlAction;
    private Stack<Integer> stackCheckPressed;
    private MainCharacter mainCharacter;

    protected BackgroundController backgroundController;
    protected ControllerManager controllerManager;
    protected EnemyManager enemyManager;
    public static int mainType = 1;
    public static MainCharacterController mainCharacterController = null;
    public static Background bgScene1 = new Background(0, 0, 0);
    private int count = 0;

    public PlayScene(){
        this.actionType = ActionType.PLAY_STAGE_1;
        controllerManager = ControllerManager.instance;
        if (mainCharacterController == null)
            mainCharacterController = new MainCharacterController(MainCharacter.mainCharacter);
        controllerManager.addController(mainCharacterController);
        mainCharacter = MainCharacter.mainCharacter;
        stackControlAction = mainCharacter.getStackControlAction();
        stackCheckPressed = new Stack<>();
        BufferedImage backgroundImage = Utils.loadImage("res/background_play_1.png");
        backgroundController = new BackgroundController(bgScene1, new SingleView(backgroundImage));
        enemyManager = new EnemyManager(5, 0);
        itemManager = new ItemManager(0, 0, 0);
    }
    @Override
    public void update(Graphics g) {
        backgroundController.draw(g);
        controllerManager.draw(g);
        enemyManager.draw(g);
        itemManager.draw(g);
//        count++;
//        if (count % 1610 == 0) itemManager.addBalloon();
//        else if (count % 5000 == 0) itemManager.addStone();
//        else if (count % 4000 == 0) itemManager.addBox();
    }

    private int popStackCount = 0;

    @Override
    public void run() {
        backgroundController.run();
        enemyManager.run();
        itemManager.run();
        popStackCount++;
        if (popStackCount == GameConfig.POP_STACK_DURATION){
            popStackCount = 0;
            if (!stackControlAction.empty())
                stackControlAction.pop();
        }
        if (stackControlAction.size() > 1) {
            int a = stackControlAction.pop();
            int b = stackControlAction.pop();
            if (a == KeyEvent.VK_A && b == KeyEvent.VK_A) {
                if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                        ){
                    mainCharacter.setCharacterState(CharacterState.ELEVATING_RUNNING_LEFT);
                } else {
                    mainCharacter.setCharacterState(CharacterState.RUNNING_LEFT);
                }
            } else if (a == KeyEvent.VK_D && b == KeyEvent.VK_D) {
                if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                        ) {
                    mainCharacter.setCharacterState(CharacterState.ELEVATING_RUNNING_RIGHT);
                } else {
                    mainCharacter.setCharacterState(CharacterState.RUNNING_RIGHT);
                }
            }
        }
        controllerManager.run();
        CollisionController.getInstance().checkCollide();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void addKeyCodeIntoStack(Integer keyEvent){
            stackControlAction.add(keyEvent);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (!stackCheckPressed.contains(e.getKeyCode())) {
            stackCheckPressed.add(e.getKeyCode());
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    addKeyCodeIntoStack(KeyEvent.VK_W);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ){
                        mainCharacter.setCharacterState(CharacterState.ELEVATING_WALKING_UP);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.WALKING_UP);
                    }
                    break;
                case KeyEvent.VK_S:
                    addKeyCodeIntoStack(KeyEvent.VK_S);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ){
                        mainCharacter.setCharacterState(CharacterState.ELEVATING_WALKING_DOWN);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.WALKING_DOWN);
                    }
                    break;
                case KeyEvent.VK_A:
                    addKeyCodeIntoStack(KeyEvent.VK_A);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ){
                        mainCharacter.setCharacterState(CharacterState.ELEVATING_WALKING_LEFT);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.WALKING_LEFT);
                    }
                    mainCharacter.setLeft(true);
                    break;
                case KeyEvent.VK_D:
                    addKeyCodeIntoStack(KeyEvent.VK_D);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ){
                        mainCharacter.setCharacterState(CharacterState.ELEVATING_WALKING_RIGHT);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.WALKING_RIGHT);
                    }
                    mainCharacter.setLeft(false);
                    break;
                case KeyEvent.VK_J:
                    addKeyCodeIntoStack(KeyEvent.VK_J);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ){
                        mainCharacter.setCharacterState(CharacterState.THROWING);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.ATTACKING_NORMAL);
                    }
                    break;
                case KeyEvent.VK_K:
                    addKeyCodeIntoStack(KeyEvent.VK_K);
                    if (mainCharacter.getMana() > 0)
                        mainCharacter.setCharacterState(CharacterState.SKILL_SHOOTING);
                    break;
                case KeyEvent.VK_L:
                    addKeyCodeIntoStack(KeyEvent.VK_L);
                    mainCharacter.setCharacterState(CharacterState.JUMPING);
                    break;
                case KeyEvent.VK_SPACE:
                    SceneManager.getInstance().sceneAction(ActionType.PLAY_STAGE_2);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         if (mainCharacter.getCharacterState() != CharacterState.RUNNING_LEFT
                && mainCharacter.getCharacterState() != CharacterState.RUNNING_RIGHT
                && mainCharacter.getCharacterState() != CharacterState.ATTACKING_NORMAL
                && mainCharacter.getCharacterState() != CharacterState.SKILL_SHOOTING
                && mainCharacter.getCharacterState() != CharacterState.JUMPING
                && mainCharacter.getCharacterState() != CharacterState.STUN_NORMAL_1
                && mainCharacter.getCharacterState() != CharacterState.STUN_NORMAL_2
                && mainCharacter.getCharacterState() != CharacterState.FALL_LEFT
                && mainCharacter.getCharacterState() != CharacterState.FALL_RIGHT
                && mainCharacter.getCharacterState() != CharacterState.DEAD
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_WALKING_UP
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_WALKING_DOWN
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_WALKING_LEFT
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_WALKING_RIGHT
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_RUNNING_LEFT
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_RUNNING_RIGHT){
            mainCharacter.setCharacterState(CharacterState.STANDING);
        } else if (mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                 || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                 || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                 || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                 || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                 || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                 ) {
             mainCharacter.setCharacterState(CharacterState.ELEVATING);
         }
        stackCheckPressed = new Stack<>();
    }
}
