package controllers;

import game.GameConfig;
import game.ResourceMap;
import models.GameObject;
import models.MainCharacter;
import models.Robot;
import views.Animation;
import views.SingleView;

import java.awt.*;

/**
 * Created by Nhan on 4/25/2017.
 */
public class RobotController extends SingleController {
    private Robot robot = (Robot) this.gameObject;
    private MainCharacter mainCharacter;
    private SingleView robotImage = new SingleView("res/robot_left_0.png");
    private Animation animationRobot = new Animation(ResourceMap.ROBOT_WALKING, GameConfig.WALKING_FRAME_RATE);

    public RobotController(GameObject gameObject) {
        super(gameObject);
    }

    public RobotController(GameObject gameObject, Animation view) {
        super(gameObject, view);
    }

    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public MainCharacter getMainCharacter() {

        return mainCharacter;
    }

    @Override
    public void run() {
        super.run();
//        if((robot.getY() - mainCharacter.getY()) > 00)
//            robot.walkUp();
//        if((robot.getY() - mainCharacter.getY()) < 00)
//            robot.walkDown();
//
//        if((robot.getX() - mainCharacter.getX()) > 100)
//            robot.walkLeft();
//
//        if((robot.getX() - mainCharacter.getX()) < -100)
//            robot.walkRight();

        switch (robot.getCharacterState()){
            case STUN_NORMAL_1:
//                System.out.println("STUN_NORMAL_1");
                break;
            case STUN_NORMAL_2:
//                System.out.println("STUN_NORMAL_2");
                break;
            case FALL:
//                System.out.println("FALL");
                break;
        }
    }



    @Override
    public void draw(Graphics g) {
        this.view = animationRobot;
        super.draw(g);
    }

    public Robot getRobotController() {
        return robot;
    }
}
