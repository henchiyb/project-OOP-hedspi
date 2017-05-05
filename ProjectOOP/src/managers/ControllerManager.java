package managers;

import controllers.BaseController;
import controllers.SingleController;
import controllers.SkillCharacterController;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nhan on 3/11/2017.
 */
public class ControllerManager implements BaseController{

    private ArrayList<SingleController>  controllerArrayList;
    private static ArrayList<SkillCharacterController> skillList;
    public static ControllerManager instance = new ControllerManager();

    public static ArrayList<SkillCharacterController> getSkillList() {
        if(skillList == null)
            skillList = new ArrayList<>();
        return skillList;
    }

    private ControllerManager() {
        controllerArrayList = new ArrayList<>();
        skillList = new ArrayList<>();
    }

    public void addController(SingleController controller){
        controllerArrayList.add(controller);
    }

    public void removeController(SingleController controller){
        controllerArrayList.remove(controller);
    }

    public void clear(){
        controllerArrayList = new ArrayList<>();
    }

    public ArrayList<SingleController> getControllerArrayList() {
        return controllerArrayList;
    }

    @Override
    public void run() {
        for (int i = 0; i < skillList.size(); i++){
            SkillCharacterController controller = skillList.get(i);
            controller.run();
            if (!controller.isAlive()){
                controller.getGameObject().destroy();
                skillList.remove(controller);
            }
        }

        for (int i = 0; i < controllerArrayList.size(); i++){
            controllerArrayList.get(i).run();
            if(!controllerArrayList.get(i).isAlive()){
                removeController(controllerArrayList.get(i));
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < skillList.size(); i++){
            skillList.get(i).draw(g);
        }

        for (int i = 0; i < controllerArrayList.size(); i++){
            controllerArrayList.get(i).draw(g);
        }
    }
}
