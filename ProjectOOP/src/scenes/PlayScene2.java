package scenes;

import controllers.BackgroundController;
import controllers.MainCharacterController;
import managers.ControllerManager;
import managers.EnemyManager;
import managers.SceneManager;
import models.Background;
import models.MainCharacter;
import utils.Utils;
import views.SingleView;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Created by Nhan on 5/1/2017.
 */
public class PlayScene2 extends PlayScene {
    public PlayScene2(){
        this.actionType = ActionType.PLAY_STAGE_2;
        controllerManager = new ControllerManager();
        controllerManager.add(MainCharacterController.mainCharacterController);
        BufferedImage backgroundImage = Utils.loadImage("res/menu_scene.png");
        backgroundController = new BackgroundController(new Background(0, 0, 0), new SingleView(backgroundImage));
        enemyManager = new EnemyManager(0, 3);
    }
}
