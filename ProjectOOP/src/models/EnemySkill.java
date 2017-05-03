package models;

/**
 * Created by Nhan on 5/1/2017.
 */
public class EnemySkill extends CharacterSkill {
    public EnemySkill(int x, int y, int z) {
        super(x, y, z, 10);
    }

    public EnemySkill(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height, 10);
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if (otherCollision instanceof MainCharacter){
            if (!((MainCharacter) otherCollision).isInvulnerable()){
                this.setAlive(false);
            }
        }
    }
}
