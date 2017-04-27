package controllers;

import game.GameConfig;
import game.ResourceMap;
import models.CharacterSkill;
import models.GameObject;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;

/**
 * Created by Nhan on 3/11/2017.
 */
public class SkillCharacterController extends SingleController {

    private SingleView skillSingleView = new SingleView(Utils.loadImage("res/davis_ball_5.png"));
    private Animation skillAnimation = new Animation(ResourceMap.DAVIS_BALL_FLY, GameConfig.BALL_FLYING_FRAME_RATE);
    public SkillCharacterController(GameObject gameObject) {
        super(gameObject);
        this.view = skillAnimation;
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
        if (skillAnimation.isAnimationEnd()){
            skillAnimation.setAnimationEnd(false);
            this.view = skillSingleView;
        }
    }
}
