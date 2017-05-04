package views;

import models.GameObject;
import utils.Utils;

import java.awt.*;

/**
 * Created by Nhan on 3/7/2017.
 */
public class SingleView implements View {
    private Image image;

    public SingleView(Image image) {
        this.image = image;
    }

    public SingleView(String url) {
        this.image = Utils.loadImage(url);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void draw(Graphics g, GameObject gameObject) {
        g.drawImage(
                image,
                gameObject.getDrawX(),
                gameObject.getDrawY(),
//                gameObject.getWidth(),
//                gameObject.getHeight(),
                null);
    }
}
