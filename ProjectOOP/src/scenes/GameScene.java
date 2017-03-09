package scenes;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by Nhan on 3/7/2017.
 */
public abstract class GameScene implements KeyListener{
    protected SceneListener sceneListener;

    public void setSceneListener(SceneListener sceneListener) {
        this.sceneListener = sceneListener;
    }

    public abstract void update(Graphics g);
    public abstract void run();

}
