package controllers;

import game.GameConfig;
import game.ResourceMap;
import models.CharacterState;
import models.GameObject;
import models.ItemState;
import models.ThrowedItem;
import views.Animation;

import java.awt.*;

/**
 * Created by Nhan on 5/1/2017.
 */
public class ThrowedItemController extends ItemController {
    public ThrowedItemController(GameObject gameObject) {
        super(gameObject);
    }
    private int countTimeBroken = 0;

    private ThrowedItem throwedItem = (ThrowedItem) this.gameObject;
    private Animation standStillItem = throwedItem.isBox() ? new Animation(ResourceMap.BOX_STANDSTILL, GameConfig.ITEM_FRAME_RATE) :
            new Animation(ResourceMap.STONE_STANDSTILL, GameConfig.ITEM_FRAME_RATE);   // ON_HAND & ON_GROUND
    private Animation fallingDownItem = throwedItem.isBox() ? new Animation(ResourceMap.BOX_FALDOWN, GameConfig.ITEM_FRAME_RATE) :
            new Animation(GameConfig.ITEM_FRAME_RATE, ResourceMap.STONE_FALLDOWN);    // FALL_DOWN
    private Animation throwingItem = throwedItem.isBox() ? new Animation(GameConfig.ITEM_FRAME_RATE, ResourceMap.BOX_THROWING) :
            new Animation(GameConfig.ITEM_FRAME_RATE, ResourceMap.STONE_THROWING);     // THROWING
    private Animation brokenItem = throwedItem.isBox()? new Animation(GameConfig.ITEM_BROKEN_FRAME_RATE, ResourceMap.BOX_BROKEN) :
            new Animation(GameConfig.ITEM_BROKEN_FRAME_RATE, ResourceMap.STONE_BROKEN);  // BROKEN

    public void run() {
        switch (throwedItem.getItemSate()) {
            case IN_THE_SKY:
                throwedItem.fallDown();
                break;
            case ON_HAND:
                throwedItem.pickedUp();
                if (throwedItem.getMainCharacter().getCharacterState() == CharacterState.THROWING) {
                    throwedItem.setItemSate(ItemState.THROWING);
                } else if (throwedItem.getMainCharacter().getCharacterState() == CharacterState.FALL_LEFT
                        || throwedItem.getMainCharacter().getCharacterState() == CharacterState.FALL_RIGHT
                        || throwedItem.getMainCharacter().getCharacterState() == CharacterState.STUN_NORMAL_1
                        || throwedItem.getMainCharacter().getCharacterState() == CharacterState.STUN_NORMAL_2
                        || throwedItem.getMainCharacter().getCharacterState() == CharacterState.JUMPING
                        || throwedItem.getMainCharacter().getCharacterState() == CharacterState.SKILL_SHOOTING
                        ) {
                    throwedItem.fallDownFromCharacter();
                    throwedItem.setItemSate(ItemState.ON_GROUND);
                }
                break;
            case THROWING:
                throwedItem.throwed(throwedItem.getOriginalXCoordDraw(),
                        throwedItem.getOriginalYCoordDraw(),
                        throwedItem.getOriginalXCoord(),
                        throwedItem.getOriginalZCoord());
                break;
            case ON_GROUND:
                break;
            case BROKEN:
                countTimeBroken++;
                if (countTimeBroken > 60) {
                    throwedItem.destroy();
                    throwedItem.setAlive(false);
                }
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g) {
        switch (throwedItem.getItemSate()) {
            case IN_THE_SKY:
                this.view = fallingDownItem;
                break;
            case ON_GROUND:
                this.view = standStillItem;
                break;
            case ON_HAND:
                this.view = standStillItem;
                break;
            case THROWING:
                this.view = throwingItem;
                break;
            case BROKEN:
                this.view = brokenItem;
                break;
            default:
                break;
        }
        super.draw(g);
    }
}
