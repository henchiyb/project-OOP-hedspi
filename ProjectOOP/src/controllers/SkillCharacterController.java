package controllers;

import game.ResourceMap;
import models.CharacterSkill;
import models.GameObject;
import models.MainCharacter;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;

/**
 * Created by Nhan on 3/11/2017.
 */
public class SkillCharacterController extends SingleController {

    private SingleView skillView = new SingleView(Utils.loadImage(ResourceMap.DAVIS_STANDING));
    public SkillCharacterController(GameObject gameObject) {
        super(gameObject);
        this.view = skillView;
    }

    public SkillCharacterController(GameObject gameObject, SingleView view) {
        super(gameObject, view);
    }

    @Override
    public void run() {
        super.run();
        ((CharacterSkill) gameObject).moveRight();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
