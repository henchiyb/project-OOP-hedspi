package models;

import sun.applet.Main;

/**
 * Created by Nhan on 4/25/2017.
 */
public class RobotBullet extends GameObject {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    public RobotBullet(int x, int y, int z) {
        super(x, y, z);
    }

    @Override
    public GameObject getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if(otherCollision instanceof MainCharacter
                && ((MainCharacter) otherCollision).getCharacterState() == CharacterState.ATTACKING_NORMAL){

        }
    }
}
