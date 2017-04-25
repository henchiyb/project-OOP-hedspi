package controllers;

import models.GameObject;
import models.Robot;
import models.RobotBullet;
import views.Animation;
import views.SingleView;

import java.awt.*;

/**
 * Created by Admin on 4/24/2017.
 */
public class BulletRobotController extends SingleController {

    public static final int SPEED = 3;
    private Robot robot;
    private RobotBullet robotBullet = (RobotBullet) this.gameObject;
    private SingleView bulletrobotImage = new SingleView("res/robot_left_3.png");

    public BulletRobotController(GameObject gameObject) {
        super(gameObject);


    }

    @Override
    public void run() {
        System.out.println("Bullet");
        super.run();
        if (robotBullet.getX() > 0) {

            for (int i = 0 ; i < 5; i++) {
                System.out.println(" Ban ra");
                this.gameObject.setX(gameObject.getX() - SPEED);
            }

        }
    }

    @Override
    public void draw(Graphics g) {
        if (this.gameObject.getX() > 0) {
            this.view = bulletrobotImage;
            System.out.println("Image Bullet");
        }
        super.draw(g);
    }

    public BulletRobotController(GameObject gameObject, Animation view) {
        super(gameObject, view);
    }

    public BulletRobotController(GameObject gameObject, SingleView view) {
        super(gameObject, view);
    }

    public RobotBullet getRobotBullet() {
        return robotBullet;
    }
}
