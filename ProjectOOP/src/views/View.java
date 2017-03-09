package views;

import models.GameObject;

import java.awt.*;

/**
 * Created by Nhan on 3/6/2017.
 */
public interface View {
    void draw(Graphics g, GameObject gameObject);

}
