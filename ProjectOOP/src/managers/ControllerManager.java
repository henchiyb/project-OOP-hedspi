package managers;

import controllers.BaseController;
import controllers.SingleController;
import controllers.SkillCharacterController;
import models.MainSkill;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nhan on 3/11/2017.
 */
public class ControllerManager implements BaseController{

    private ArrayList<SingleController>  controllerArrayList;
    private static ArrayList<SkillCharacterController> skillMainList;
    private static ArrayList<SkillCharacterController>  skillEnemyList;

    public static ArrayList<SkillCharacterController> getSkillMainList() {
        if(skillMainList == null)
            skillMainList = new ArrayList<>();
        return skillMainList;
    }

    public static ArrayList<SkillCharacterController> getSkillEnemyList() {
        if(skillEnemyList == null)
            skillEnemyList = new ArrayList<>();
        return skillEnemyList;
    }

    public ControllerManager() {
        controllerArrayList = new ArrayList<>();
        skillMainList = new ArrayList<>();
        skillEnemyList = new ArrayList<>();
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
        for (int i = 0; i < skillMainList.size(); i++){
            SkillCharacterController controller = skillMainList.get(i);
            controller.run();
            if (!controller.isAlive()){
                controller.getGameObject().destroy();
                skillMainList.remove(controller);
            }
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
        for (int i = 0; i < skillMainList.size(); i++){
            skillMainList.get(i).draw(g);
        }

        for (int i = 0; i < controllerArrayList.size(); i++){
            controllerArrayList.get(i).draw(g);
        }
    }
}
