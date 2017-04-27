package models;

/**
 * Created by Nhan on 4/24/2017.
 */
public interface Collision {
    GameObject getGameObject();
    void onCollide(Collision otherCollision);
}
