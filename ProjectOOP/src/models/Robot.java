package models;
import game.GameConfig;
/**
 * Created by Nguyen on 4/25/2017.
 */
public class Robot extends GameObject{
    public Robot(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height);
    }

    public void walkLeft(){
        this.x -= GameConfig.WALKING_SPEED;
    }
    public void walkRight(){
        this.x += GameConfig.WALKING_SPEED;
    }
    public void walkUp(){
        this.y -= GameConfig.WALKING_SPEED;
    }
    public void walkDown(){
        this.y += GameConfig.WALKING_SPEED;
    }
    public void runLeft(){
        this.x -= GameConfig.RUNNING_SPEED;
    }
    public void runRight(){
        this.x += GameConfig.RUNNING_SPEED;
    }

}
