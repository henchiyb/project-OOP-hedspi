package game;

/**
 * Created by Nhan on 3/6/2017.
 */
public class GameConfig
{
    public static final int SKILL_DAVIS_SPEED = 5;
    public static int WALKING_SPEED = 2;
    public static int RUNNING_SPEED = 4;
    public static int JUMPING_SPEED_Y = -45;
    public static int JUMPING_SPEED_X = 15;
    public static int BALL_FLYING_SPEED = 10; // smaller => animation faster
    public static int FALLING_SPEED = 30;

    public static int WALKING_FRAME_RATE = 2; // smaller => animation faster
    public static int RUNNING_FRAME_RATE = 5; // smaller => animation faster
    public static int BALL_FLYING_FRAME_RATE = 7; // smaller => animation faster
    public static int STANDING_FRAME_RATE = 4; // smaller => animation faster
    public static int ATTACKING_FRAME_RATE = 5; // smaller => animation faster
    public static int FALLING_FRAME_RATE = 10; // smaller => animation faster
    public static int JUMPING_FRAME_RATE = 10; // smaller => animation faster
    public static int ITEM_FRAME_RATE = 2; // smaller => animation faster

    public static int GAME_OBJECT_DEPTH = 8;
    public static int GAME_OBJECT_WIDTH = 80;
    public static int GAME_OBJECT_HEIGHT = 80;

    public static int BAR_WIDTH = 150;
    public static int BAR_HEIGHT = 13;

    public static int POP_STACK_TIME = 100;
}
