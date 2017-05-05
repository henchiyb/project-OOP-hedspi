package game;

/**
 * Created by Nhan on 3/6/2017.
 */
public class GameConfig
{
    public static final int SCREEN_WIDTH = 768;
    public static final int SCREEN_HEIGHT = 600;
    public static final int MAP_WIDTH = 2400;
    public static final int MAP_HEIGHT = 600;
    public static final int MAP_START_X = 0;
    public static final int MAP_END_X = 2400;
    public static final int MAP_START_Y = 220;
    public static final int MAP_END_Y = 580;


    public static final int SKILL_DAVIS_SPEED = 5;
    public static int WALKING_SPEED = 2;
    public static int RUNNING_SPEED = 4;
    public static int JUMPING_SPEED_Y = -45;
    public static int JUMPING_SPEED_X = 15;
    public static int BALL_FLYING_SPEED = 10;
    public static int FALLING_SPEED = 30;

    // smaller => animation faster
    public static int WALKING_FRAME_RATE = 2;
    public static int RUNNING_FRAME_RATE = 5;
    public static int BALL_FLYING_FRAME_RATE = 7;
    public static int STANDING_FRAME_RATE = 4;
    public static int ATTACKING_FRAME_RATE = 5;
    public static int FALLING_FRAME_RATE = 10;
    public static int JUMPING_FRAME_RATE = 10;
    public static int ITEM_FRAME_RATE = 3;
    public static int ITEM_BROKEN_FRAME_RATE = 20;
    public static int BALLOON_FRAME_RATE = 30;

    public static int GAME_OBJECT_DEPTH = 8;
    public static int GAME_OBJECT_WIDTH = 40;
    public static int GAME_OBJECT_HEIGHT = 80;

    public static int BAR_WIDTH = 150;
    public static int BAR_HEIGHT = 13;
    public static int EXP_BAR_HEIGHT = 5;
    public static final int MAX_EXP = 25;

    public static int POP_STACK_DURATION = 100;
    public static final int ENEMY_SHOOTING_DURATION = 200;
    public static final int ENEMY_ATTACK_DURATION = 100;
    public static final int REGEN_MANA_DURATION = 60;
}
