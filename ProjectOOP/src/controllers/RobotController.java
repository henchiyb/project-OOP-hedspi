package controllers;

import game.ResourceMap;
import models.*;
import models.Robot;
import models.MainCharacter;
import views.Animation;
import views.SingleView;


import java.awt.*;

/**
 * Created by Admin on 3/25/2017.
 */
public class RobotController extends SingleController {

    private Robot robot = (Robot) this.gameObject;
    private MainCharacter mainCharacter;
    private SingleView robotImage = new SingleView("res/robot_left_4.png");
    private Animation animationRobot = new Animation("res/robot_left_0.png",
            "res/robot_left_1.png",
            "res/robot_left_2.png",
            "res/robot_left_3.png");

    //public static final int SPEED = 5;

    public RobotController(GameObject gameObject) {
        super(gameObject);
    }

    public RobotController(GameObject gameObject, Animation view) {
        super(gameObject, view);
        //this.gameVector.dx = SPEED;
    }

    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public MainCharacter getMainCharacter() {

        return mainCharacter;
    }

    @Override
    public void run() {
        //System.out.println("run");
        super.run();

        if((robot.getY() - mainCharacter.getY()) > 10)
            robot.walkUp();
        if((robot.getY() - mainCharacter.getY()) < -10)
            robot.walkDown();
        if((robot.getX() - mainCharacter.getX()) > 100)
            robot.walkLeft();
        if((robot.getX() - mainCharacter.getX()) < -100)
            robot.walkRight();

    }

    @Override
    public void draw(Graphics g) {
        if((robot.getY() - mainCharacter.getY()) > 10 ||
                (robot.getY() - mainCharacter.getY()) < -10 ||
                (robot.getX() - mainCharacter.getX()) > 100 ||
                (robot.getX() - mainCharacter.getX()) < -100){
            this.view = animationRobot;
        } else {
            this.view = robotImage;
        }
        super.draw(g);
    }

    public Robot getRobotController() {
        return robot;
    }
}
