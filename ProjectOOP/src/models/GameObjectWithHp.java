package models;

/**
 * Created by Nhan on 4/26/2017.
 */
public abstract class GameObjectWithHp extends GameObject {
    private CharacterState characterState = CharacterState.STANDING;

    public CharacterState getCharacterState() {
        return characterState;
    }

    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }

    public GameObjectWithHp(int x, int y, int z) {
        super(x, y, z);
    }

    public GameObjectWithHp(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height);
    }

//    public void
}
