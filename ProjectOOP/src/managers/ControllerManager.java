package managers;

import controllers.BaseController;
import controllers.CollisionController;
import controllers.SingleController;
import controllers.SkillCharacterController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Nhan on 3/11/2017.
 */
public class ControllerManager implements BaseController{

    private ArrayList<SingleController>  controllerArrayList;
    private static ArrayList<SkillCharacterController>  skillCharacterControllerArrayList;

    public static ArrayList<SkillCharacterController> getSkillCharacterControllerArrayList() {
        if(skillCharacterControllerArrayList == null)
            skillCharacterControllerArrayList = new ArrayList<>();
        return skillCharacterControllerArrayList;
    }

    public ControllerManager() {
        controllerArrayList = new ArrayList<>();
        skillCharacterControllerArrayList = new ArrayList<>();
    }

    public void add(SingleController controller){
        controllerArrayList.add(controller);
    }

    public void remove(SingleController controller){
        controllerArrayList.remove(controller);
    }

    public ArrayList<SingleController> getControllerArrayList() {
        return controllerArrayList;
    }

    @Override
    public void run() {
        for (int i = 0; i < skillCharacterControllerArrayList.size(); i++){
            skillCharacterControllerArrayList.get(i).run();
        }
        for (int i = 0; i < controllerArrayList.size(); i++){
            controllerArrayList.get(i).run();
            if(!controllerArrayList.get(i).isAlive()){
                remove(controllerArrayList.get(i));
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < skillCharacterControllerArrayList.size(); i++){
            skillCharacterControllerArrayList.get(i).draw(g);
        }

        for (int i = 0; i < controllerArrayList.size(); i++){
            controllerArrayList.get(i).draw(g);
        }
    }
}
