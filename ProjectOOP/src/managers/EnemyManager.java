package managers;

import controllers.BaseController;
import controllers.EnemyController;
import controllers.EnemyType;
import game.GameConfig;
import models.EnemyCharacter;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Nhan on 4/30/2017.
 */
public class EnemyManager implements BaseController{
    private ArrayList<EnemyController> enemyControllerList;

    public EnemyManager(int numOfEnemyFight, int numOfEnemyShoot){
        enemyControllerList = new ArrayList<>();
        for (int i = 0; i < numOfEnemyFight; i++){
            int randomX = ThreadLocalRandom.current().nextInt(300,
                    GameConfig.MAP_END_X - GameConfig.GAME_OBJECT_WIDTH + 1);
            int randomY = ThreadLocalRandom.current().nextInt(GameConfig.MAP_START_Y,
                    GameConfig.MAP_END_Y + 1 - GameConfig.GAME_OBJECT_HEIGHT);
            EnemyController enemyController = new EnemyController(new EnemyCharacter(randomX,
                    0,
                    randomY,
                    GameConfig.GAME_OBJECT_WIDTH,
                    GameConfig.GAME_OBJECT_HEIGHT),
                    EnemyType.FIGHT);
            enemyControllerList.add(enemyController);
        }
        for (int i = 0; i < numOfEnemyShoot; i++){
            int randomX = ThreadLocalRandom.current().nextInt(300,
                    GameConfig.MAP_END_X - GameConfig.GAME_OBJECT_WIDTH + 1);
            int randomY = ThreadLocalRandom.current().nextInt(GameConfig.MAP_START_Y,
                    GameConfig.MAP_END_Y + 1 - GameConfig.GAME_OBJECT_HEIGHT);
            EnemyController enemyController = new EnemyController(new EnemyCharacter(randomX,
                    0,
                    randomY,
                    GameConfig.GAME_OBJECT_WIDTH,
                    GameConfig.GAME_OBJECT_HEIGHT),
                    EnemyType.SHOOT);
            enemyControllerList.add(enemyController);
        }
    }
    synchronized
    @Override
    public void run() {
        for (int i = 0; i < enemyControllerList.size(); i++){
            EnemyController enemy = enemyControllerList.get(i);
            enemy.run();
            if (!enemy.isAlive()){
                enemyControllerList.remove(i);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < enemyControllerList.size(); i++){
            enemyControllerList.get(i).draw(g);
        }
    }
}
