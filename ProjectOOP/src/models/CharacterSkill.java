package models;

import game.GameConfig;

/**
 * Created by Nhan on 3/11/2017.
 */
public class CharacterSkill extends GameObject {
    public CharacterSkill(int x, int y, int z) {
        super(x, y, z);
    }

    public CharacterSkill(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height);
    }

    public void moveLeft(){
        this.x -= GameConfig.SKILL_DAVIS_SPEED;
    }

    public void moveRight(){
        this.x += GameConfig.SKILL_DAVIS_SPEED;
    }


}
