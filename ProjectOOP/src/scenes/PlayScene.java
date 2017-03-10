package scenes;

import controllers.MainCharacterController;
import models.CharacterState;
import models.MainCharacter;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Nhan on 3/7/2017.
 */
public class PlayScene extends GameScene{
    private MainCharacterController mainCharacterController;
    private MainCharacter mainCharacter;
    private Image backgroundImage;
    public PlayScene(){
        mainCharacterController = new MainCharacterController(new MainCharacter(0, 300, 0, 80, 80));
        mainCharacter = mainCharacterController.getMainCharacter();
        backgroundImage = Utils.loadImage("res/background.png");
    }
    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        mainCharacterController.draw(g);
    }

    @Override
    public void run() {
        mainCharacterController.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                mainCharacter.setCharacterState(CharacterState.WALKING_UP);
                break;
            case KeyEvent.VK_DOWN:
                mainCharacter.setCharacterState(CharacterState.WALKING_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                mainCharacter.setCharacterState(CharacterState.WALKING_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                mainCharacter.setCharacterState(CharacterState.WALKING_RIGHT);
                break;
            default:
                mainCharacter.setCharacterState(CharacterState.STANDING);
                break;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (mainCharacter.getCharacterState() != CharacterState.STANDING){
            mainCharacter.setCharacterState(CharacterState.STANDING);
        }
//        switch (e.getKeyCode()){
//            case KeyEvent.VK_UP:
//                mainCharacter.setCharacterState(CharacterState.STANDING);
//                break;
//            case KeyEvent.VK_DOWN:
//                mainCharacter.setCharacterState(CharacterState.STANDING);
//                break;
//            case KeyEvent.VK_LEFT:
//                mainCharacter.setCharacterState(CharacterState.STANDING);
//                break;
//            case KeyEvent.VK_RIGHT:
//                mainCharacter.setCharacterState(CharacterState.STANDING);
//                break;
//
//        }
    }
}
