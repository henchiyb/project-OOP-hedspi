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

    private SingleView skillSingleViewRight = new SingleView(Utils.loadImage("res/davis_ball_5.png"));
    private SingleView skillSingleViewLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/davis_ball_5.png")));
    private Animation skillAnimationRight = new Animation(ResourceMap.DAVIS_BALL_FLY, GameConfig.BALL_FLYING_FRAME_RATE);
    private Animation skillAnimationLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_BALL_FLY), GameConfig.BALL_FLYING_FRAME_RATE);
    public SkillCharacterController(GameObject gameObject) {
        super(gameObject);
        this.view = skillAnimationRight;
    }

    public SkillCharacterController(GameObject gameObject, SingleView view) {
        super(gameObject, view);
    }

    @Override
    public void run() {
        super.run();
        if (!gameObject.isLeft())
            ((CharacterSkill) gameObject).moveRight();
        else
            ((CharacterSkill) gameObject).moveLeft();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if(gameObject.isLeft()){
            this.view = skillAnimationLeft;
        } else {
            this.view = skillSingleViewRight;
        }
        if (skillAnimationRight.isAnimationEnd()){
            skillAnimationRight.setAnimationEnd(false);
            if(gameObject.isLeft()){
                this.view = skillSingleViewLeft;
            } else {
                this.view = skillSingleViewRight;
            }
        }
    }
}
