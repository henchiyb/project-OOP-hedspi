package scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by Nhan on 3/7/2017.
 */
public abstract class GameScene extends JPanel implements KeyListener{
    protected ActionType actionType;

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public abstract void update(Graphics g);
    public abstract void run();

}
