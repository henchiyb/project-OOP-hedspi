package scenes;

import controllers.MainCharacterController;
import game.GameConfig;
import managers.ControllerManager;
import models.CharacterState;
import models.MainCharacter;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Nhan on 3/7/2017.
 */
public class PlayScene extends GameScene{
    private MainCharacterController mainCharacterController;
    private Stack<Integer> stackControlAction;
    private ArrayList<Integer> arrayAction = new ArrayList<>();
    private MainCharacter mainCharacter;
    private Image backgroundImage;
    private ControllerManager controllerManager;
    public PlayScene(){
        mainCharacterController = new MainCharacterController(new MainCharacter(0, 300, 0, 80, 80));
        mainCharacter = mainCharacterController.getMainCharacter();
        stackControlAction = mainCharacter.getStackControlAction();
        backgroundImage = Utils.loadImage("res/background.png");
        controllerManager = new ControllerManager();
    }
    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        mainCharacterController.draw(g);
        controllerManager.draw(g);
    }



    private int popStackCount = 0;

    @Override
    public void run() {
        mainCharacterController.run();
        popStackCount++;
        if (popStackCount == GameConfig.POP_STACK_TIME){
            popStackCount = 0;
            if (!stackControlAction.empty())
                stackControlAction.pop();
        }

        if(mainCharacter.isLeft()){
            mainCharacter.setCharacterState(CharacterState.WALKING_LEFT);
        } else if (mainCharacter.isRight()){
            mainCharacter.setCharacterState(CharacterState.WALKING_RIGHT);
        } else if (mainCharacter.isUp()){
            mainCharacter.setCharacterState(CharacterState.WALKING_UP);
        } else if (mainCharacter.isDown()){
            mainCharacter.setCharacterState(CharacterState.WALKING_DOWN);
        } else if (mainCharacter.isAttack()){
            mainCharacter.setCharacterState(CharacterState.ATTACKING);
        } else if (mainCharacter.isDefend()){
            mainCharacter.setCharacterState(CharacterState.DEFENDING);
        } else if (mainCharacter.isJump()){
            mainCharacter.setCharacterState(CharacterState.JUMPING);
        } else {
            mainCharacter.setCharacterState(CharacterState.STANDING);
        }


        if(stackControlAction.size() > 2) {
            int a = stackControlAction.pop();
            int b = stackControlAction.pop();
            int c = stackControlAction.pop();
            System.out.println(a + " " + b + " " + c);
            System.out.println(KeyEvent.VK_K + "---" + KeyEvent.VK_RIGHT + "---" + KeyEvent.VK_J);
            if (a == KeyEvent.VK_J &&
                    b == KeyEvent.VK_RIGHT &&
                    c == KeyEvent.VK_K) {
                mainCharacter.setCharacterState(CharacterState.SKILL_SHOOTING);
                System.out.println(mainCharacter.getCharacterState()+"");
            }
        }
        controllerManager.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void checkKeyCodeInStack(Integer keyEvent){
        if (!stackControlAction.contains(keyEvent)){
            stackControlAction.add(keyEvent);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                checkKeyCodeInStack(KeyEvent.VK_UP);
                mainCharacter.setUp(true);
                break;
            case KeyEvent.VK_DOWN:
                checkKeyCodeInStack(KeyEvent.VK_DOWN);
                mainCharacter.setDown(true);
                break;
            case KeyEvent.VK_LEFT:
                checkKeyCodeInStack(KeyEvent.VK_LEFT);
                mainCharacter.setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                checkKeyCodeInStack(KeyEvent.VK_RIGHT);
                mainCharacter.setRight(true);
                break;
            case KeyEvent.VK_J:
                checkKeyCodeInStack(KeyEvent.VK_J);
                mainCharacter.setAttack(true);
                break;
            case KeyEvent.VK_K:
                checkKeyCodeInStack(KeyEvent.VK_K);
                mainCharacter.setDefend(true);
                break;
            case KeyEvent.VK_L:
                checkKeyCodeInStack(KeyEvent.VK_L);
                mainCharacter.setJump(true);
                break;
            default:
//                mainCharacter.setCharacterState(CharacterState.STANDING);
                break;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
//        if (mainCharacter.getCharacterState() != CharacterState.STANDING){
//            mainCharacter.setCharacterState(CharacterState.STANDING);
//        }
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                mainCharacter.setUp(false);
                break;
            case KeyEvent.VK_DOWN:
                mainCharacter.setDown(false);
                break;
            case KeyEvent.VK_LEFT:
                mainCharacter.setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                mainCharacter.setRight(false);
                break;
            case KeyEvent.VK_J:
                mainCharacter.setAttack(false);
                break;
            case KeyEvent.VK_K:
                mainCharacter.setDefend(false);
                break;
            case KeyEvent.VK_L:
                mainCharacter.setJump(false);
                break;

        }
    }
}
