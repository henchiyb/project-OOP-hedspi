package models;

/**
 * Created by Nhan on 5/2/2017.
 */
public class MainSkill extends CharacterSkill {
    public MainSkill(int x, int y, int z) {
        super(x, y, z, 20);
    }

    public MainSkill(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height, 20);
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if (otherCollision instanceof EnemyCharacter) {
            if(!((EnemyCharacter) otherCollision).isInvulnerable())
                this.setAlive(false);
        }
    }
}
