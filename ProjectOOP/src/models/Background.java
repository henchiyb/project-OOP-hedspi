package models;

import controllers.GameObjectController;
import game.GameConfig;

/**
 * Created by Nhan on 4/26/2017.
 */
public class Background extends GameObject {
    private CharacterState backgroundState = CharacterState.STANDING;
    public Background(int drawX) {
        super(0, 0, 0);
        GameObjectController.getInstance().remove(this);
        this.drawX = drawX;
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
}
