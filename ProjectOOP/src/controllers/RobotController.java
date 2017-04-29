package controllers;

import game.ResourceMap;
import models.*;
import models.Robot;
import models.MainCharacter;
import views.Animation;
import views.SingleView;


import java.awt.*;
import java.util.ArrayList;

import utils.Utils;

/**
 * Created by Admin on 3/25/2017.
 */
public class RobotController extends SingleController {

    private Robot robot = (Robot) this.gameObject;
    private MainCharacter mainCharacter;
    private SingleView robotImage = new SingleView("res/Walk (1).png");
    private int countTimeShoot = 0;
    private ArrayList<SingleController> listEnemyBullet = new ArrayList<>();

    private Animation animationRobot = new Animation("res/Walk (1).png",
            "res/Walk (2).png",
            "res/Walk (3).png",
            "res/Walk (4).png",
            "res/Walk (5).png");
   
    private Animation animationRobot2 = new Animation(Utils.flipImages(ResourceMap.ROBOT),2);
    private Animation animationRobot3 = new Animation(Utils.flipImages(ResourceMap.ROBOT_STANDING),2);
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

        countTimeShoot++;
        if(countTimeShoot ==  100){
            countTimeShoot = 0;
            BulletRobotController bulletRobotController = new BulletRobotController(new RobotBullet(robot.getX(),robot.middleY() - 10, 0 ));
            listEnemyBullet.add(bulletRobotController);
        }
        for (int i =0; i < listEnemyBullet.size(); i++){
            listEnemyBullet.get(i).run();
        }

    }

    @Override
    public void draw(Graphics g) {

       if((robot.getY() - mainCharacter.getY()) > 10)
            {
            this.view = animationRobot;

        } 
            
       else if((robot.getY() - mainCharacter.getY()) < -10)
           {
            this.view = animationRobot;

        }  
       else if((robot.getX() - mainCharacter.getX()) > 100)
          
             {
            this.view = animationRobot2;

        } 
       else if((robot.getX() - mainCharacter.getX()) < -100)
          
        {
            this.view = animationRobot;

        } else {
           if((robot.getX() - mainCharacter.getX()) < 0)
            
           this.view = robotImage;
           else 
            this.view = animationRobot3;   
         
        }
        for (int i =0; i < listEnemyBullet.size(); i++){
            listEnemyBullet.get(i).draw(g);
        }
        super.draw(g);
    }

    public Robot getRobotController() {
        return robot;
    }
}
