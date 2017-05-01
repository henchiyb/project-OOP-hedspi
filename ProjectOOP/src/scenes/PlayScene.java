package scenes;

import controllers.*;
import game.GameConfig;
import managers.ControllerManager;
import managers.EnemyManager;
import managers.SceneManager;
import models.*;
import utils.Utils;
import views.SingleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Created by Nhan on 3/7/2017.
 */
public class PlayScene extends GameScene{
    private Stack<Integer> stackControlAction;
    private Stack<Integer> stackCheckPressed;
    private MainCharacter mainCharacter;
    protected BackgroundController backgroundController;
    protected ControllerManager controllerManager;
    protected EnemyManager enemyManager;

    public PlayScene(){
        this.actionType = ActionType.PLAY_STAGE_1;
        controllerManager = new ControllerManager();
        controllerManager.add(MainCharacterController.mainCharacterController);
        mainCharacter = MainCharacter.mainCharacter;
        stackControlAction = mainCharacter.getStackControlAction();
        stackCheckPressed = new Stack<>();
        BufferedImage backgroundImage = Utils.loadImage("res/background.png");
        backgroundController = new BackgroundController(new Background(0, 0, 0), new SingleView(backgroundImage));
        enemyManager = new EnemyManager(5, 3);

    }
    @Override
    public void update(Graphics g) {
        backgroundController.draw(g);
        controllerManager.draw(g);
        enemyManager.draw(g);
    }

    private int popStackCount = 0;

    @Override
    public void run() {
        backgroundController.run();
        enemyManager.run();
        popStackCount++;
        if (popStackCount == GameConfig.POP_STACK_TIME){
            popStackCount = 0;
            if (!stackControlAction.empty())
                stackControlAction.pop();
        }
        if (stackControlAction.size() > 2) {
            int a = stackControlAction.pop();
            int b = stackControlAction.pop();
            int c = stackControlAction.pop();
            System.out.println(a + " " + b + " " + c);
            System.out.println(KeyEvent.VK_K + "---" + KeyEvent.VK_D + "---" + KeyEvent.VK_J);
            if (a == KeyEvent.VK_K &&
                    b == KeyEvent.VK_D &&
                    c == KeyEvent.VK_J) {
                mainCharacter.setCharacterState(CharacterState.SKILL_SHOOTING);
                System.out.println(mainCharacter.getCharacterState() + "");
            } else if (a == KeyEvent.VK_A &&
                    b == KeyEvent.VK_A) {
                mainCharacter.setCharacterState(CharacterState.RUNNING_LEFT);
                System.out.println(mainCharacter.getCharacterState() + "");
            } else if (a == KeyEvent.VK_D &&
                    b == KeyEvent.VK_D) {
                mainCharacter.setCharacterState(CharacterState.RUNNING_RIGHT);
                System.out.println(mainCharacter.getCharacterState() + "");
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
                    mainCharacter.setCharacterState(CharacterState.WALKING_UP);
                    break;
                case KeyEvent.VK_S:
                    addKeyCodeIntoStack(KeyEvent.VK_S);
                    mainCharacter.setCharacterState(CharacterState.WALKING_DOWN);
                    break;
                case KeyEvent.VK_A:
                    addKeyCodeIntoStack(KeyEvent.VK_A);
                    mainCharacter.setCharacterState(CharacterState.WALKING_LEFT);
                    backgroundController.setMoveLeft(true);
                    backgroundController.setMoveRight(false);
                    mainCharacter.setLeft(true);
                    break;
                case KeyEvent.VK_D:
                    addKeyCodeIntoStack(KeyEvent.VK_D);
                    mainCharacter.setCharacterState(CharacterState.WALKING_RIGHT);
                    backgroundController.setMoveLeft(false);
                    backgroundController.setMoveRight(true);
                    mainCharacter.setLeft(false);
                    break;
                case KeyEvent.VK_J:
                    addKeyCodeIntoStack(KeyEvent.VK_J);
                    mainCharacter.setCharacterState(CharacterState.ATTACKING_NORMAL);
                    break;
                case KeyEvent.VK_K:
                    addKeyCodeIntoStack(KeyEvent.VK_K);
                    mainCharacter.setCharacterState(CharacterState.DEFENDING);
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
        if (mainCharacter.getCharacterState() != CharacterState.RUNNING_LEFT &&
                mainCharacter.getCharacterState() != CharacterState.RUNNING_RIGHT
                && mainCharacter.getCharacterState() != CharacterState.ATTACKING_NORMAL
                && mainCharacter.getCharacterState() != CharacterState.SKILL_SHOOTING
                && mainCharacter.getCharacterState() != CharacterState.JUMPING
                && mainCharacter.getCharacterState() != CharacterState.DEFENDING){
            mainCharacter.setCharacterState(CharacterState.STANDING);
        }
        stackCheckPressed = new Stack<>();
    }
}
