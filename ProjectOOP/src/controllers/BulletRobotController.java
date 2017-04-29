package controllers;

import game.ResourceMap;
import models.GameObject;
import models.Robot;
import models.RobotBullet;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;
import models.MainCharacter;

/**
 * Created by Admin on 4/24/2017.
 */
public class BulletRobotController extends SingleController {

    public static final int SPEED = 1;
    private MainCharacter mainCharacter;
    
    private Robot robot;
    private RobotBullet robotBullet = (RobotBullet) this.gameObject;
//    private SingleView bulletrobotImage = new SingleView("res/wp2.png");
    private Animation animationBullet0 = new Animation(ResourceMap.BULLET,5);
    private Animation animationBullet = new Animation(Utils.flipImages(ResourceMap.BULLET),5);
    public BulletRobotController(GameObject gameObject) {
        super(gameObject);


    }

    public void setMainCharater(MainCharacter mainCharater){
        this.mainCharacter = mainCharacter;
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
         if(this.gameObject.getX() > 100){
            this.view = animationBullet;
            System.out.println("Image Bullet");
            super.draw(g);
        }
        
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
