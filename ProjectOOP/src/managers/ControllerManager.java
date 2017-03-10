package managers;

import controllers.BaseController;
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

    public ControllerManager(ArrayList<SingleController> controllerArrayList) {
        this.controllerArrayList = controllerArrayList;
    }

    public ControllerManager() {
    }

    public ArrayList<SingleController> getControllerArrayList() {
        return controllerArrayList;
    }

    @Override
    public void run() {
//        for (int i = 0; i < controllerArrayList.size(); i++){
//            controllerArrayList.get(i).run();
//        }
        for (int i = 0; i < skillCharacterControllerArrayList.size(); i++){
            skillCharacterControllerArrayList.get(i).run();
        }
    }

    @Override
    public void draw(Graphics g) {
//        for (int i = 0; i < controllerArrayList.size(); i++){
//            controllerArrayList.get(i).draw(g);
//        }
        for (int i = 0; i < skillCharacterControllerArrayList.size(); i++){
            skillCharacterControllerArrayList.get(i).draw(g);
        }
    }
}
