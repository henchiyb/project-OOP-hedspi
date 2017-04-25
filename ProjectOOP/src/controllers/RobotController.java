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
    private SingleView robotImage = new SingleView("res/Walk (1).png");
    private Animation animationRobot = new Animation("res/Walk (1).png",
            "res/Walk (2).png",
            "res/Walk (3).png",
            "res/Walk (4).png",
            "res/Walk (5).png");

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
