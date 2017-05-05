package scenes;

import controllers.BackgroundController;
import controllers.MainCharacterController;
import managers.ControllerManager;
import managers.EnemyManager;
import managers.ItemManager;
import managers.SceneManager;
import models.Background;
import models.MainCharacter;
import utils.Utils;
import views.SingleView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Created by Nhan on 5/1/2017.
 */
public class PlayScene2 extends PlayScene {
    public PlayScene2(){
        super(0);
        this.actionType = ActionType.PLAY_STAGE_2;
//        MainCharacter.mainCharacter.setX(0);
//        MainCharacter.mainCharacter.setDrawX(0);
//        MainCharacter.mainCharacter.setZ(300);
//        MainCharacter.mainCharacter.setDrawY(300);
        Dimension dimension = new Dimension(2400, 600);
        BufferedImage backgroundImage = Utils.setSize(Utils.loadImage("res/background_2.png"), dimension);
        backgroundController = new BackgroundController(bgScene, new SingleView(backgroundImage));
        controllerManager.clear();
        controllerManager.addController(mainCharacterController);
        enemyManager = new EnemyManager(0, 3);
        itemManager = new ItemManager(1, 1, 1);
    }
}
