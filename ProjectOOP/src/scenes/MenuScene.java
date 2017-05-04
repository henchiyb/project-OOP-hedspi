package scenes;

import game.GameWindow;
import managers.SceneManager;
import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Nhan on 4/30/2017.
 */
public class MenuScene extends GameScene implements MouseListener {
    private BufferedImage backgroundImage;
    private BufferedImage playImage;
    private GameWindow gameWindow;
    private Rectangle playRect;
    private final Point firstButtonLocation;
    private final Dimension buttonSize;

    public MenuScene(GameWindow gameWindow) throws IOException {
        this.actionType = ActionType.MENU_SCENE;
        this.gameWindow = gameWindow;
        buttonSize = new Dimension(200, this.gameWindow.getHeight() / 9);
        loadImage();
        firstButtonLocation = new Point(this.gameWindow.getWidth() / 2 - this.buttonSize.width / 2,
                this.gameWindow.getHeight() / 2 );
        makeRect();
    }

    private void loadImage() {
        try {
            backgroundImage = ImageIO.read(new FileInputStream("res/menu_scene.png"));
            playImage = ImageIO.read(new FileInputStream("res/play_button.png"));
            backgroundImage = Utils.setSize(backgroundImage, this.gameWindow.getSize());
            playImage = Utils.setSize(playImage,buttonSize.width, buttonSize.height);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){
        playRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y, playImage.getWidth(), playImage.getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (playRect.contains(e.getX(),e.getY())) {
            PlayScene.mainType = 1;
            SceneManager.getInstance().sceneAction(ActionType.CHOOSE_MAIN);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(playImage,playRect.x,playRect.y,null);
    }

    @Override
    public void run() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
