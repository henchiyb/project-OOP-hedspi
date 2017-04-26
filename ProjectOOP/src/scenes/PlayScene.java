package scenes;

import controllers.ItemController;
import controllers.MainCharacterController;
import controllers.ThrowedItemController;
import game.GameConfig;
import managers.ControllerManager;
import models.*;
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
    private Stack<Integer> stackCheckPressed;
    private ArrayList<Integer> arrayAction = new ArrayList<>();
    private MainCharacter mainCharacter;
    private Image backgroundImage;
    private ControllerManager controllerManager;

    private ThrowedItem throwedItem;
    private ThrowedItemController boxController, stoneController;

    public PlayScene(){
        mainCharacterController = new MainCharacterController(new MainCharacter(0, 300, 0, 80, 80));
        mainCharacter = mainCharacterController.getMainCharacter();
        stackControlAction = mainCharacter.getStackControlAction();
        stackCheckPressed = new Stack<>();

        // todo: chon ngau nhien vat pham va cho thoi diem roi xuong ngau nhien
        boxController = new ThrowedItemController(new ThrowedItem(300,40,0,80,80, 400, 150, 0));
        throwedItem = boxController.getThrowedItem();
        throwedItem.setMainCharacter(mainCharacter);

        backgroundImage = Utils.loadImage("res/background.png");
        controllerManager = new ControllerManager();
    }
    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        mainCharacterController.draw(g);
        boxController.draw(g);
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

        boxController.run();

        if (stackControlAction.size() > 2) {
            int a = stackControlAction.pop();
            int b = stackControlAction.pop();
            int c = stackControlAction.pop();
            System.out.println(a + " " + b + " " + c);
            System.out.println(KeyEvent.VK_K + "---" + KeyEvent.VK_RIGHT + "---" + KeyEvent.VK_J);
            if (a == KeyEvent.VK_J &&
                    b == KeyEvent.VK_RIGHT &&
                    c == KeyEvent.VK_K) {
                mainCharacter.setCharacterState(CharacterState.SKILL_SHOOTING);
                System.out.println(mainCharacter.getCharacterState() + "");
            } else if (a == KeyEvent.VK_J &&
                    b == KeyEvent.VK_J &&
                    c == KeyEvent.VK_J) {
                mainCharacter.setCharacterState(CharacterState.ATTACKING_HARD);
                System.out.println(mainCharacter.getCharacterState() + "");
            } else if (a == KeyEvent.VK_LEFT &&
                    b == KeyEvent.VK_LEFT) {
                mainCharacter.setCharacterState(CharacterState.RUNNING_LEFT);
                System.out.println(mainCharacter.getCharacterState() + "");
            } else if (a == KeyEvent.VK_RIGHT &&
                    b == KeyEvent.VK_RIGHT) {
                mainCharacter.setCharacterState(CharacterState.RUNNING_RIGHT);
                System.out.println(mainCharacter.getCharacterState() + "");
            }
        }
        controllerManager.run();
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
                case KeyEvent.VK_UP:
                    addKeyCodeIntoStack(KeyEvent.VK_UP);
                    mainCharacter.setCharacterState(CharacterState.WALKING_UP);
                    break;
                case KeyEvent.VK_DOWN:
                    addKeyCodeIntoStack(KeyEvent.VK_DOWN);
                    mainCharacter.setCharacterState(CharacterState.WALKING_DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    addKeyCodeIntoStack(KeyEvent.VK_LEFT);
                    mainCharacter.setCharacterState(CharacterState.WALKING_LEFT);
                    mainCharacter.setLeft(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    addKeyCodeIntoStack(KeyEvent.VK_RIGHT);
                    mainCharacter.setCharacterState(CharacterState.WALKING_RIGHT);
                    mainCharacter.setLeft(false);
                    break;
                case KeyEvent.VK_J:
                    addKeyCodeIntoStack(KeyEvent.VK_J);
                    mainCharacter.setCharacterState(CharacterState.ATTACKING_NORMAL);
                    if (throwedItem.getItemSate() == ItemState.ON_GROUND)
                        throwedItem.setItemSate(ItemState.ON_HAND);
                    if (throwedItem.getItemSate() == ItemState.ON_HAND)
                        throwedItem.setItemSate(ItemState.THROWING);
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
                mainCharacter.getCharacterState() != CharacterState.RUNNING_RIGHT){
            mainCharacter.setCharacterState(CharacterState.STANDING);
        }
        stackCheckPressed = new Stack<>();
    }
}
