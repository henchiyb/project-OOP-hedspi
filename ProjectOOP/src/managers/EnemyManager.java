package managers;

import controllers.BaseController;
import controllers.EnemyController;
import controllers.EnemyType;
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
            int randomX = ThreadLocalRandom.current().nextInt(300, 800 + 1);
            int randomY = ThreadLocalRandom.current().nextInt(0, 600 + 1);
            EnemyController enemyController = new EnemyController(new EnemyCharacter(randomX, 0, randomY, 80, 80),
                    EnemyType.FIGHT);
            enemyControllerList.add(enemyController);
        }
        for (int i = 0; i < numOfEnemyShoot; i++){
            int randomX = ThreadLocalRandom.current().nextInt(300, 800 + 1);
            int randomY = ThreadLocalRandom.current().nextInt(0, 600 + 1);
            EnemyController enemyController = new EnemyController(new EnemyCharacter(randomX, 0, randomY, 80, 80),
                    EnemyType.SHOOT);
            enemyControllerList.add(enemyController);
        }
    }
    @Override
    public void run() {
        for (int i = 0; i < enemyControllerList.size(); i++){
            EnemyController enemy = enemyControllerList.get(i);
            enemy.run();
            if (!enemy.isAlive()){
                enemy.getEnemyCharacter().destroy();
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
