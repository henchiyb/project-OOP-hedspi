package scenes;

import controllers.BackgroundController;
import controllers.CollisionController;
import controllers.MainCharacterController;
import controllers.RobotController;
import game.GameConfig;
import managers.ControllerManager;
import models.*;
import utils.Utils;
import views.SingleView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Nhan on 3/7/2017.
 */
public class PlayScene extends GameScene{
    private MainCharacterController mainCharacterController;
    private Stack<Integer> stackControlAction;
    private Stack<Integer> stackCheckPressed;
    private MainCharacter mainCharacter;
    private BufferedImage backgroundImage;
    private BackgroundController backgroundController;
    private ControllerManager controllerManager;
    private RobotController robotController;
    public PlayScene(){
        mainCharacterController = new MainCharacterController(new MainCharacter(0, 300, 0, 80, 80));
        mainCharacter = mainCharacterController.getMainCharacter();
        stackControlAction = mainCharacter.getStackControlAction();
        stackCheckPressed = new Stack<>();
        backgroundImage = Utils.loadImage("res/background.png");
        backgroundController = new BackgroundController(new Background(0, 0, 0), new SingleView(backgroundImage));
        controllerManager = new ControllerManager();

        robotController = new RobotController(new models.Robot(600,300,0));
        robotController.setMainCharacter(mainCharacter);
    }
    @Override
    public void update(Graphics g) {
//        g.drawImage(backgroundImage, 0, 0, null);
        backgroundController.draw(g);
        mainCharacterController.draw(g);
        robotController.draw(g);
        controllerManager.draw(g);
    }

    private int popStackCount = 0;

    @Override
    public void run() {
        mainCharacterController.run();
        robotController.run();
        backgroundController.run();
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
            if (a == KeyEvent.VK_J &&
                    b == KeyEvent.VK_D &&
                    c == KeyEvent.VK_K) {
                mainCharacter.setCharacterState(CharacterState.SKILL_SHOOTING);
                System.out.println(mainCharacter.getCharacterState() + "");
            } else if (a == KeyEvent.VK_J &&
                    b == KeyEvent.VK_J &&
                    c == KeyEvent.VK_J) {
                mainCharacter.setCharacterState(CharacterState.ATTACKING_HARD);
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
    private int count = 0;
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
                default:
//                mainCharacter.setCharacterState(CharacterState.STANDING);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (mainCharacter.getCharacterState() != CharacterState.RUNNING_LEFT &&
                mainCharacter.getCharacterState() != CharacterState.RUNNING_RIGHT
                && mainCharacter.getCharacterState() != CharacterState.ATTACKING_NORMAL
                && mainCharacter.getCharacterState() != CharacterState.ATTACKING_HARD
                && mainCharacter.getCharacterState() != CharacterState.SKILL_SHOOTING
                && mainCharacter.getCharacterState() != CharacterState.JUMPING
                && mainCharacter.getCharacterState() != CharacterState.DEFENDING){
            mainCharacter.setCharacterState(CharacterState.STANDING);
        }
        stackCheckPressed = new Stack<>();
    }
}
