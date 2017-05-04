package models;

import controllers.GameObjectController;
import game.GameConfig;

/**
 * Created by Nhan on 4/26/2017.
 */
public class Background extends GameObject {
    private CharacterState backgroundState = CharacterState.STANDING;
    public Background(int x, int y, int z) {
        super(x, y, z);
        GameObjectController.getInstance().remove(this);
    }

    public Background(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height);
        GameObjectController.getInstance().remove(this);
    }

    public CharacterState getBackgroundState() {
        return backgroundState;
    }

    public void setBackgroundState(CharacterState backgroundState) {
        this.backgroundState = backgroundState;
    }

    @Override
    public GameObject getGameObject() {
        return null;
    }

    @Override
    public void onCollide(Collision otherCollision) {

    }

    public void moveLeft(int speed){
        if (this.drawX < GameConfig.MAP_START_X) {
            super.moveDrawXToRight(speed);
        }
    }

    public void moveRight(int speed){
        if (this.drawX > GameConfig.SCREEN_WIDTH - GameConfig.MAP_END_X) {
            super.moveDrawXToLeft(speed);
        }
    }
}
