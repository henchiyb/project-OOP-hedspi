package models;

/**
 * Created by Admin on 4/7/2017.
 */
public class RobotBullet extends GameObject {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    public RobotBullet(int x, int y, int z) {
        super(x, y, z,WIDTH,HEIGHT);
    }
}
