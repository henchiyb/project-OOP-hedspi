package models;

/**
 * Created by Nhan on 5/5/2017.
 */
public class PowerItem extends Item{
    private static int health = 5;

    public int getHealth() {
        return health;
    }

    public PowerItem(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height);
    }

    @Override
    public PowerItem getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if (otherCollision instanceof MainCharacter) {
            this.setItemSate(ItemState.BROKEN);
            if (((MainCharacter) otherCollision).getHealth() < ((MainCharacter) otherCollision).getMaxHealth() - this.getHealth())
                ((Character) otherCollision).setHealth(((Character) otherCollision).getHealth() + this.getHealth());
        }
    }
}
