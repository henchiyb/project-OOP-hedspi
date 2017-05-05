package managers;

import controllers.BaseController;
import controllers.ItemController;
import controllers.PowerItemController;
import controllers.ThrowedItemController;
import game.GameConfig;
import models.PowerItem;
import models.ThrowedItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Nhan on 5/5/2017.
 */
public class ItemManager implements BaseController{
    private ArrayList<ItemController> itemControllerList;

    public ItemManager(int numOfBox, int numOfStone, int numOfMagicBalloon) {
        itemControllerList = new ArrayList<>();
        for (int i = 0; i < numOfMagicBalloon; i++) addBalloon();
        for (int i = 0; i < numOfBox; i++) addBox();
        for (int i = 0; i < numOfStone; i++) addStone();
    }

    public void addBox() {
        int randomX = ThreadLocalRandom.current().nextInt(GameConfig.MAP_START_X + 40,
                GameConfig.SCREEN_WIDTH - GameConfig.GAME_OBJECT_WIDTH);
        int randomZ = ThreadLocalRandom.current().nextInt(GameConfig.MAP_START_Y,
                GameConfig.MAP_END_Y - GameConfig.GAME_OBJECT_HEIGHT);
        ThrowedItemController throwedItemController = new ThrowedItemController(new ThrowedItem(randomX, 0, randomZ,
                GameConfig.GAME_OBJECT_WIDTH, GameConfig.GAME_OBJECT_HEIGHT / 2, 1, 5, true));
        itemControllerList.add(throwedItemController);
    }

    public void addStone() {
        int randomX = ThreadLocalRandom.current().nextInt(GameConfig.MAP_START_X + 40,
                GameConfig.SCREEN_WIDTH - GameConfig.GAME_OBJECT_WIDTH);
        int randomZ = ThreadLocalRandom.current().nextInt(GameConfig.MAP_START_Y,
                GameConfig.MAP_END_Y - GameConfig.GAME_OBJECT_HEIGHT);
        ThrowedItemController throwedItemController = new ThrowedItemController(new ThrowedItem(randomX, 0, randomZ,
                GameConfig.GAME_OBJECT_WIDTH, GameConfig.GAME_OBJECT_HEIGHT / 2, 2, 10, false));
        itemControllerList.add(throwedItemController);
    }

    public void addBalloon() {
        int randomX = ThreadLocalRandom.current().nextInt(GameConfig.MAP_START_X + 40,
                GameConfig.MAP_END_X - GameConfig.GAME_OBJECT_WIDTH);
        int randomZ = ThreadLocalRandom.current().nextInt(GameConfig.MAP_START_Y,
                GameConfig.MAP_END_Y - GameConfig.GAME_OBJECT_HEIGHT);
        PowerItemController powerItemController = new PowerItemController(new PowerItem(randomX, 0, randomZ,
                GameConfig.GAME_OBJECT_WIDTH, GameConfig.GAME_OBJECT_HEIGHT / 2));
        itemControllerList.add(powerItemController);
    }

    @Override
    public void run() {
        for (int i = 0; i < itemControllerList.size(); i++) {
            ItemController item = itemControllerList.get(i);
            item.run();
            if (!item.isAlive())
                itemControllerList.remove(i);
        }
    }

    @Override
    public void draw(Graphics g) {
        for (ItemController it : itemControllerList) {
            it.draw(g);
        }
    }
}
