package controllers;

import models.GameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nhan on 5/4/2017.
 */
public class GameObjectController implements BaseController {
    private static GameObjectController instance;
    private ArrayList<GameObject> listObject;

    public static GameObjectController getInstance() {
        if(instance == null)
            instance = new GameObjectController();
        return instance;
    }

    public void add(GameObject gameObject){
        listObject.add(gameObject);
    }

    public void remove(GameObject gameObject){
        listObject.remove(gameObject);
    }

    public ArrayList<GameObject> getListObject() {
        return listObject;
    }

    private GameObjectController() {
        listObject = new ArrayList<>();
    }

    @Override
    public void run() {

    }

    @Override
    public void draw(Graphics g) {

    }
}
