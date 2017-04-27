package controllers;

import models.Collision;
import models.GameObject;

import java.util.ArrayList;

/**
 * Created by Nhan on 4/24/2017.
 */
public class CollisionController {
    private static CollisionController instance;

    public static CollisionController getInstance() {
        if (instance == null)
            instance  = new CollisionController();
        return instance;
    }

    public ArrayList<Collision> collisionsList;
    private CollisionController(){
        collisionsList = new ArrayList<>();
    }

    public void add(Collision collision){
        collisionsList.add(collision);
    }

    public void remove(Collision collision){
        collisionsList.remove(collision);
    }

    public void checkCollide(){
        for (int i = 0; i < collisionsList.size(); i++){
            Collision collisionI = collisionsList.get(i);
            GameObject gameObjectI = collisionI.getGameObject();
            for (int j = i + 1; j < collisionsList.size(); j++){
                Collision collisionJ = collisionsList.get(j);
                GameObject gameObjectJ = collisionJ.getGameObject();
                if (gameObjectI.intersects(gameObjectJ)){
                    collisionI.onCollide(collisionJ);
                    collisionJ.onCollide(collisionI);
                }
            }
        }
    }

}
